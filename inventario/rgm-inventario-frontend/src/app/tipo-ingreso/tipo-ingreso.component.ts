import { Component, OnInit, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
//import { Type } from '../shared/type.model';
import { TipoIngreso } from '../shared/tipo-ingreso.model';
import { MaterializeAction } from 'angular2-materialize';
import { Subscription } from 'rxjs/Subscription';
import { TiposIngresoService } from '../shared/tipos-ingreso.service';
import { LoadingService } from '../shared/loading.service';

declare var Materialize:any;

@Component({
  selector: 'app-types',
  templateUrl: './tipo-ingreso.component.html',
  styleUrls: ['./tipo-ingreso.component.css']
})
export class TipoIngresoComponent implements OnInit {
  public loading = false;
  typeForm: FormGroup;
  types: TipoIngreso[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalType: TipoIngreso;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: number;
  type: TipoIngreso;

  constructor(private typesService: TiposIngresoService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.typesService.tipos = [];
    this.types = this.typesService.getTipoIngresos();
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.httpSubscription = this.typesService.fetchData().subscribe(
      data => {
        this.types = data.value;
        this.typesService.addTipos(this.types);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.localSubscription = this.typesService.tiposChanged.subscribe(
      (types: TipoIngreso[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'El tipo de ingreso fue eliminado exitosamente.';
        } else if (this.editMode) {
          message = 'El tipo de ingreso fue modificado exitosamente.';
        } else {
          message = 'El tipo de ingreso fue creado exitosamente.';
        }
        this.types = types;
        Materialize.toast(message, 4000);
        this.onCancelClicked();
      }
    );
    this.initForm();
  }

  private initForm() {
    this.typeForm = new FormGroup({
      'nombre': new FormControl('', Validators.required)
    });
  }

  onAddTypeClicked() {
    this.modalType = new TipoIngreso;
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditTypeClicked(type: TipoIngreso, index: number) {
    this.modalType = type;
    this.editMode = true;
    this.editIndex = index;
    this.editId = type.tipoIngresoId;
    this.typeForm.patchValue({
      nombre: type.nombre
    });
    Materialize.updateTextFields();
    this.modalActions.emit({action:"modal", params:["open"]});
  }

   onRemoveTypeClicked(index: number) {
     this.deleteMode = true;
     this.loading = true;
     this.loadingService.changeLoading(this.loading);
     this.typesService.deleteType(index, this.types[index].tipoIngresoId);
   }

  onSubmit() {
    let type: TipoIngreso = new TipoIngreso;
    type.nombre = this.typeForm.value.nombre;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      type.tipoIngresoId = this.editId;
      this.typesService.updateTipoIngreso(this.editIndex, type);
    } else {
      this.typesService.addTipoIngreso(type);
    }
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal",params:['close']});
    this.typeForm.reset();
    this.editMode = false;
    this.deleteMode = false;
    this.editIndex = -1;
  }
}
