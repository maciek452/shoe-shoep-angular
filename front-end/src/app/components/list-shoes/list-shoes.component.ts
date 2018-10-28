import {Component, OnInit} from '@angular/core';
import {ShoesService} from '../../services/shoes.service';
import {Observable} from 'rxjs/Observable';
import {Shoe} from '../../model/shoe';

@Component({
  selector: 'app-list-shoes',
  templateUrl: './list-shoes.component.html',
  styleUrls: ['./list-shoes.component.css']
})
export class ListShoesComponent implements OnInit {

  shoes: Observable<Shoe[]>;

  constructor(private shoesService: ShoesService) {
  }

  ngOnInit() {
    this.shoes = this.shoesService.findAllShoes();
  }

  remove(shoe: Shoe) {
    this.shoesService.removeShoe(shoe)
      .subscribe(() => this.ngOnInit());
  }
}
