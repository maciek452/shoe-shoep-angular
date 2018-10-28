import {Component, OnInit} from '@angular/core';
import {Shoe} from '../../model/shoe';
import {ShoesService} from '../../services/shoes.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-view-shoe',
  templateUrl: './view-shoe.component.html',
  styleUrls: ['./view-shoe.component.css']
})
export class ViewShoeComponent implements OnInit {

  shoe: Shoe;

  constructor(private shoesService: ShoesService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.shoesService.findShoe(Number(id)).subscribe(shoe => this.shoe = shoe);
  }

}
