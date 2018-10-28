import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewShoeComponent } from './view-shoe.component';

describe('ViewShoeComponent', () => {
  let component: ViewShoeComponent;
  let fixture: ComponentFixture<ViewShoeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewShoeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewShoeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
