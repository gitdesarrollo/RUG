import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnulacionesComponent } from './anulaciones.component';

describe('AnulacionesComponent', () => {
  let component: AnulacionesComponent;
  let fixture: ComponentFixture<AnulacionesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnulacionesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnulacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
