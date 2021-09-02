import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteDespachosComponent } from './reporte-despachos.component';

describe('ReporteDespachosComponent', () => {
  let component: ReporteDespachosComponent;
  let fixture: ComponentFixture<ReporteDespachosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteDespachosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteDespachosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
