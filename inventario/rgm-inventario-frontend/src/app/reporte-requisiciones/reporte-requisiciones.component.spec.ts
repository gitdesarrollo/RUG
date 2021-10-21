import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteRequisicionesComponent } from './reporte-requisiciones.component';

describe('ReporteRequisicionesComponent', () => {
  let component: ReporteRequisicionesComponent;
  let fixture: ComponentFixture<ReporteRequisicionesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteRequisicionesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteRequisicionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
