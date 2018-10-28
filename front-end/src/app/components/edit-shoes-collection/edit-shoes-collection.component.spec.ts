import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditShoesCollectionsComponent } from './edit-shoes-collection.component';

describe('EditShoeComponent', () => {
  let component: EditShoesCollectionsComponent;
  let fixture: ComponentFixture<EditShoesCollectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditShoesCollectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditShoesCollectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
