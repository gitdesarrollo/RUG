import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteMinimoExistenciasComponent } from './reporte-minimo-existencias.component';

describe('ReporteMinimoExistenciasComponent', () => {
  let component: ReporteMinimoExistenciasComponent;
  let fixture: ComponentFixture<ReporteMinimoExistenciasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteMinimoExistenciasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteMinimoExistenciasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
