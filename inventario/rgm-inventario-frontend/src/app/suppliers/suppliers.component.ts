import { Component, OnInit, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MaterializeAction } from 'angular2-materialize';
import { LoadingService } from '../shared/loading.service';
import { Supplier } from '../shared/supplier.model';
import { Subscription } from 'rxjs/Subscription';
import { SuppliersService } from '../shared/suppliers.service';
import { environment } from '../../environments/environment';
import { UsersService } from '../shared/users.service';

declare var Materialize:any;

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.css']
})
export class SuppliersComponent implements OnInit {
  public loading = false;
  supplierForm: FormGroup;
  suppliers: Supplier[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalSupplier: Supplier;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: number;
  urlReporteProveedores: string;

  constructor(private suppliersService: SuppliersService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.urlReporteProveedores = environment.base_url + '/reporte-proveedor?usuario=' + this.usersService.authenticatedUser.usuarioId;
    this.suppliersService.suppliers = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.httpSubscription = this.suppliersService.fetchData().subscribe(
      data => {
        this.suppliers = data.value;
        this.suppliersService.addSuppliers(this.suppliers);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.localSubscription = this.suppliersService.suppliersChanged.subscribe(
      (suppliers: Supplier[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'El proveedor fue eliminado exitosamente.';
        } else if (this.editMode) {
          message = 'El proveedor fue modificado exitosamente.';
        } else {
          message = 'El proveedor fue creado exitosamente.';
        }
        this.suppliers = suppliers;
        Materialize.toast(message, 4000);
        this.onCancelClicked();
      }
    );
    this.initForm();
  }

  private initForm() {
    this.supplierForm = new FormGroup({
      'nombre': new FormControl('', Validators.required),
      'direccion': new FormControl('', Validators.required),
      'nit': new FormControl('', Validators.required),
      'estado': new FormControl('', Validators.required)
    });
  }

  onAddSupplierClicked() {
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditSupplierClicked(supplier: Supplier, index: number) {
    this.modalSupplier = supplier;
    this.editMode = true;
    this.editIndex = index;
    this.editId = supplier.id;
    this.supplierForm.patchValue({
      nombre: supplier.nombre,
      direccion: supplier.direccion,
      nit: supplier.nit,
      estado: supplier.estado
    });
    Materialize.updateTextFields();
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onRemoveSupplierClicked(index: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.suppliersService.deleteSupplier(index, this.suppliers[index].id);
  }

  onSubmit() {
    let supplier: Supplier = new Supplier;
    supplier.nombre = this.supplierForm.value.nombre;
    supplier.direccion = this.supplierForm.value.direccion;
    supplier.nit = this.supplierForm.value.nit;
    supplier.estado = this.supplierForm.value.estado;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      supplier.id = this.editId;
      this.suppliersService.updateSupplier(this.editIndex, supplier);
    } else {
      this.suppliersService.addSupplier(supplier);
    }
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal",params:['close']});
    this.supplierForm.reset();
    this.editMode = false;
    this.deleteMode = false;
    this.editIndex = -1;
  }
}
