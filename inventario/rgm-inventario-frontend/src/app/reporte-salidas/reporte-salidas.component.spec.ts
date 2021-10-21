import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteSalidasComponent } from './reporte-salidas.component';

describe('ReporteSalidasComponent', () => {
  let component: ReporteSalidasComponent;
  let fixture: ComponentFixture<ReporteSalidasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteSalidasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteSalidasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
