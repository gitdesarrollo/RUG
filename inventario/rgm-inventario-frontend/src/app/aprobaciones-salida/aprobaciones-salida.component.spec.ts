import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AprobacionesSalidaComponent } from './aprobaciones-salida.component';

describe('AprobacionesSalidaComponent', () => {
  let component: AprobacionesSalidaComponent;
  let fixture: ComponentFixture<AprobacionesSalidaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AprobacionesSalidaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AprobacionesSalidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
