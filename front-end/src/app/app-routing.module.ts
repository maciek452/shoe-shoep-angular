
import {ListShoesComponent} from './components/list-shoes/list-shoes.component';
import {ViewShoeComponent} from './components/view-shoe/view-shoe.component';
import {ListShoesCollectionsComponent} from './components/list-shoes-collections/list-shoes-collections.component';
import {EditShoeComponent} from './components/edit-shoe/edit-shoe.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { EditShoesCollectionComponent } from './components/edit-shoes-collection/edit-shoes-collection.component';

const routes: Routes = [
  {path: '', component: ListShoesComponent},
  {path: 'shoes/new', component: EditShoeComponent},
  {path: 'shoes/:id', component: ViewShoeComponent},
  {path: 'shoes/:id/edit', component: EditShoeComponent},
  {path: 'shoes-collections', component: ListShoesCollectionsComponent},
  {path: 'shoes-collections/new', component: EditShoesCollectionComponent},
  {path: 'shoes-collections/:id/edit', component: EditShoesCollectionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
