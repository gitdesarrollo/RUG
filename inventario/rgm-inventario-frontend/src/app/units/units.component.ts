import { Component, OnInit, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MaterializeAction } from 'angular2-materialize';
import { LoadingService } from '../shared/loading.service';
import { Unit } from '../shared/unit.model';
import { Subscription } from 'rxjs/Subscription';
import { UnitsService } from '../shared/units.service';

declare var Materialize:any;

@Component({
  selector: 'app-units',
  templateUrl: './units.component.html',
  styleUrls: ['./units.component.css']
})
export class UnitsComponent implements OnInit {
  public loading = false;
  unitForm: FormGroup;
  units: Unit[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalUnit: Unit;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: number;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;

  constructor(private unitsService: UnitsService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.unitsService.units = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.httpSubscription = this.unitsService.fetchData(this.currentPage, this.pageSize).subscribe(
      data => {
        this.units = data.value;
        this.total = data.total;
        this.unitsService.addUnits(this.units);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.localSubscription = this.unitsService.unitsChanged.subscribe(
      (units: Unit[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'La unidad de medida fue eliminada exitosamente.';
        } else if (this.editMode) {
          message = 'La unidad de medida fue modificada exitosamente.';
          // this.units = units;
        } else {
          message = 'La unidad de medida fue creada exitosamente.';
          // this.onPageChange(1);
        }
        Materialize.toast(message, 4000);
        this.onPageChange(1);
        this.onCancelClicked();
      }
    );
    this.initForm();
  }

  private initForm() {
    this.unitForm = new FormGroup({
      'nombre': new FormControl('', Validators.required)
    });
  }

  onAddUnitClicked() {
    this.modalUnit = new Unit;
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditUnitClicked(unit: Unit, index: number) {
    this.modalUnit = unit;
    this.editMode = true;
    this.editIndex = index;
    this.editId = unit.id;
    this.unitForm.patchValue({
      nombre: unit.nombre
    });
    Materialize.updateTextFields();
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onRemoveUnitClicked(index: number) {
    this.deleteMode = true;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.unitsService.deleteUnit(index, this.units[index].id);
  }

  onSubmit() {
    let unit: Unit = new Unit;
    unit.nombre = this.unitForm.value.nombre;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      unit.id = this.editId;
      this.unitsService.updateUnit(this.editIndex, unit);
    } else {
      this.unitsService.addUnit(unit);
    }
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal",params:['close']});
    this.unitForm.reset();
    this.editMode = false;
    this.deleteMode = false;
    this.editIndex = -1;
  }

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.units = [];
    this.httpSubscription = this.unitsService.fetchData(page, this.pageSize).subscribe(
      data => {
        this.units = data.value;
        this.currentPage = page;
        this.unitsService.addUnits(this.units);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }
}
