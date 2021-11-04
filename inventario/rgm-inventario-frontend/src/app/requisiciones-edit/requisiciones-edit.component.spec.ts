import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequisicionesEditComponent } from './requisiciones-edit.component';

describe('RequisicionesEditComponent', () => {
  let component: RequisicionesEditComponent;
  let fixture: ComponentFixture<RequisicionesEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequisicionesEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequisicionesEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
