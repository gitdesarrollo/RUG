import { Component, OnInit, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TipoSalida } from '../shared/tipo-salida.model';
import { MaterializeAction } from 'angular2-materialize';
import { Subscription } from 'rxjs/Subscription';
import { TiposSalidaService } from '../shared/tipos-salida.service';
import { LoadingService } from '../shared/loading.service';

declare var Materialize:any;

@Component({
  selector: 'app-types',
  templateUrl: './tipo-salida.component.html',
  styleUrls: ['./tipo-salida.component.css']
})
export class TipoSalidaComponent implements OnInit {
  public loading = false;
  typeForm: FormGroup;
  types: TipoSalida[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalType: TipoSalida;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: number;
  type: TipoSalida;

  constructor(private typesService: TiposSalidaService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.typesService.tipos = [];
    this.types = this.typesService.getTipoSalidas();
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
      (types: TipoSalida[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'El tipo de salida fue eliminado exitosamente.';
        } else if (this.editMode) {
          message = 'El tipo de salida fue modificado exitosamente.';
        } else {
          message = 'El tipo de salida fue creado exitosamente.';
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
    this.modalType = new TipoSalida;
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditTypeClicked(type: TipoSalida, index: number) {
    this.modalType = type;
    this.editMode = true;
    this.editIndex = index;
    this.editId = type.tipoSalidaId;
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
     this.typesService.deleteType(index, this.types[index].tipoSalidaId);
   }

  onSubmit() {
    let type: TipoSalida = new TipoSalida;
    type.nombre = this.typeForm.value.nombre;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      type.tipoSalidaId = this.editId;
      this.typesService.updateTipoSalida(this.editIndex, type);
    } else {
      this.typesService.addTipoSalida(type);
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
