import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from './app-routing.module';
import {ListShoesComponent} from './components/list-shoes/list-shoes.component';
import {ViewShoeComponent} from './components/view-shoe/view-shoe.component';
import {ShoesService} from './services/shoes.service';
import {ListShoesCollectionsComponent} from './components/list-shoes-collections/list-shoes-collections.component';
import {EditShoeComponent} from './components/edit-shoe/edit-shoe.component';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EditShoesCollectionComponent } from './components/edit-shoes-collection/edit-shoes-collection.component';


@NgModule({
  declarations: [
    AppComponent,
    ListShoesComponent,
    ViewShoeComponent,
    EditShoeComponent,
    ListShoesCollectionsComponent,
    EditShoesCollectionComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,

    AppRoutingModule
  ],
  providers: [ShoesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
