import { Component, OnInit } from '@angular/core';
import { Shoe } from '../../model/shoe';
import { ShoesCollection } from '../../model/shoesCollection';
import { ShoesService } from '../../services/shoes.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-shoe',
  templateUrl: './edit-shoe.component.html',
  styleUrls: ['./edit-shoe.component.css']
})
export class EditShoeComponent implements OnInit {

  shoe: Shoe;
  avaibleShoesCollections: ShoesCollection[];

  constructor(private shoesService: ShoesService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id == null) {
      this.shoe = { id: null, model: '', size: null, shoesCollections: [] };
    } else {
      this.shoesService.findShoe(Number(id)).subscribe(shoe => this.shoe = shoe);
    }

    this.shoesService.findAllShoesCollections()
      .subscribe(shoesCollections => this.avaibleShoesCollections = shoesCollections);
  }

  save() {
    this.shoesService.saveShoe(this.shoe).subscribe(() => this.router.navigateByUrl(''));
  }

  compareShoesCollections(shoesCollection1: ShoesCollection, shoesCollection2: ShoesCollection) {
    return shoesCollection1 && shoesCollection2 ?
      shoesCollection1.id === shoesCollection2.id : shoesCollection1 === shoesCollection2;
  }
}
