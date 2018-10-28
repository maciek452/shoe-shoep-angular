import { Component, OnInit } from '@angular/core';
import { ShoesCollection } from '../../model/shoesCollection';
import { ShoesService } from '../../services/shoes.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-shoes-collection',
  templateUrl: './edit-shoes-collection.component.html',
  styleUrls: ['./edit-shoes-collection.component.css']
})
export class EditShoesCollectionComponent implements OnInit {

  shoesCollection: ShoesCollection;

  constructor(private shoesService: ShoesService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id == null) {
      this.shoesCollection = { id: null, name: '', season: '' };
    } else {
      this.shoesService.findShoesCollection(Number(id)).subscribe(shoesCollection => this.shoesCollection = shoesCollection);
    }
  }

  save() {
    this.shoesService.saveShoesCollection(this.shoesCollection).subscribe(() => this.router.navigateByUrl('shoes-collections'));
  }
}
