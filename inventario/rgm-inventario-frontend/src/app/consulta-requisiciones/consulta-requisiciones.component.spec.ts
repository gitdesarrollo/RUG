import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaRequisicionesComponent } from './consulta-requisiciones.component';

describe('ConsultaRequisicionesComponent', () => {
  let component: ConsultaRequisicionesComponent;
  let fixture: ComponentFixture<ConsultaRequisicionesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultaRequisicionesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaRequisicionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
