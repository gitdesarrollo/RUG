import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaDespachosComponent } from './consulta-despachos.component';

describe('ConsultaDespachosComponent', () => {
  let component: ConsultaDespachosComponent;
  let fixture: ComponentFixture<ConsultaDespachosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultaDespachosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaDespachosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
