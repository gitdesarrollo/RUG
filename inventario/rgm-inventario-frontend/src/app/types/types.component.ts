import { Component, OnInit, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Type } from '../shared/type.model';
import { MaterializeAction } from 'angular2-materialize';
import { Subscription } from 'rxjs/Subscription';
import { TypesService } from '../shared/types.service';
import { LoadingService } from '../shared/loading.service';

declare var Materialize:any;

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.css']
})
export class TypesComponent implements OnInit {
  public loading = false;
  typeForm: FormGroup;
  types: Type[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalType: Type;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: number;

  constructor(private typesService: TypesService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.typesService.types = [];
    this.types = this.typesService.getTypes();
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.httpSubscription = this.typesService.fetchData().subscribe(
      data => {
        this.types = data.value;
        this.typesService.addTypes(this.types);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.localSubscription = this.typesService.typesChanged.subscribe(
      (types: Type[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'El tipo de articulo fue eliminado exitosamente.';
        } else if (this.editMode) {
          message = 'El tipo de articulo fue modificado exitosamente.';
        } else {
          message = 'El tipo de articulo fue creado exitosamente.';
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
    this.modalType = new Type;
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditTypeClicked(type: Type, index: number) {
    this.modalType = type;
    this.editMode = true;
    this.editIndex = index;
    this.editId = type.id;
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
    this.typesService.deleteType(index, this.types[index].id);
  }

  onSubmit() {
    let type: Type = new Type;
    type.nombre = this.typeForm.value.nombre;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      type.id = this.editId;
      this.typesService.updateType(this.editIndex, type);
    } else {
      this.typesService.addType(type);
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
