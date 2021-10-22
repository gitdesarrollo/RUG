import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteInventarioGeneralComponent } from './reporte-inventario-general.component';

describe('ReporteInventarioGeneralComponent', () => {
  let component: ReporteInventarioGeneralComponent;
  let fixture: ComponentFixture<ReporteInventarioGeneralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteInventarioGeneralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteInventarioGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
