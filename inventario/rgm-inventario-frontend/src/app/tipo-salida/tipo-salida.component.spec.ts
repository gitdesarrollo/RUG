import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoSalidaComponent } from './tipo-salida.component';

describe('TipoSalidaComponent', () => {
  let component: TipoSalidaComponent;
  let fixture: ComponentFixture<TipoSalidaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipoSalidaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoSalidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
