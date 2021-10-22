import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AprobacionesIngresoComponent } from './aprobaciones-ingreso.component';

describe('AprobacionesIngresoComponent', () => {
  let component: AprobacionesIngresoComponent;
  let fixture: ComponentFixture<AprobacionesIngresoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AprobacionesIngresoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AprobacionesIngresoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
