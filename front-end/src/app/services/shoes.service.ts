import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Shoe} from '../model/shoe';
import {ShoesCollection} from '../model/shoesCollection';

@Injectable()
export class ShoesService {

  constructor(private http: HttpClient) {
  }

  findAllShoes(): Observable<Shoe[]> {
    return this.http.get<Shoe[]>('api/shoes');
  }

  findShoe(id: number): Observable<Shoe> {
    return this.http.get<Shoe>(`api/shoes/${id}`);
  }

  removeShoe(shoe: Shoe): Observable<any> {
    return this.http.delete(`api/shoes/${shoe.id}`);
  }

  saveShoe(shoe: Shoe): Observable<any> {
    if (shoe.id) {
      return this.http.put(`api/shoes/${shoe.id}`, shoe);
    } else {
      return this.http.post('api/shoes/', shoe);
    }
  }

  findAllShoesCollections(): Observable<ShoesCollection[]> {
    return this.http.get<ShoesCollection[]>('api/shoesCollections');
  }

  findShoesCollection(id: number): Observable<ShoesCollection> {
    return this.http.get<ShoesCollection>(`api/shoesCollections/${id}`);
  }

  removeShoesCollection(shoesCollection: ShoesCollection): Observable<any> {
    return this.http.delete(`api/shoesCollections/${shoesCollection.id}`);
  }

  saveShoesCollection(shoesCollection: ShoesCollection): Observable<any> {
    if (shoesCollection.id) {
      return this.http.put(`api/shoesCollections/${shoesCollection.id}`, shoesCollection);
    } else {
      return this.http.post('api/shoesCollections/', shoesCollection);
    }
  }

}
