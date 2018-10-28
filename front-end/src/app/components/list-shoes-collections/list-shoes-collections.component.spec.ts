import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListShoesCollectionsComponent } from './list-shoes-collections.component';

describe('ListShoesCollectionsComponent', () => {
  let component: ListShoesCollectionsComponent;
  let fixture: ComponentFixture<ListShoesCollectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListShoesCollectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListShoesCollectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
