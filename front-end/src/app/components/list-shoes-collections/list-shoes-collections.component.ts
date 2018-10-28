import { Component, OnInit } from '@angular/core';
import {ShoesService} from '../../services/shoes.service';
import {Observable} from 'rxjs/Observable';
import { ShoesCollection } from '../../model/shoesCollection';

@Component({
  selector: 'app-list-shoes-collections',
  templateUrl: './list-shoes-collections.component.html',
  styleUrls: ['./list-shoes-collections.component.css']
})
export class ListShoesCollectionsComponent implements OnInit {

  shoesCollections: Observable<ShoesCollection[]>;

  constructor(private shoesService: ShoesService) { }

  ngOnInit() {
    this.shoesCollections = this.shoesService.findAllShoesCollections();
  }

  remove(shoesCollection: ShoesCollection) {
    this.shoesService.removeShoesCollection(shoesCollection)
      .subscribe(() => this.ngOnInit());
  }
}
