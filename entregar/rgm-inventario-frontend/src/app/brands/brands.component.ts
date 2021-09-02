import { Component, OnInit, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MaterializeAction } from 'angular2-materialize';
import { LoadingService } from '../shared/loading.service';
import { Brand } from '../shared/brand.model';
import { Subscription } from 'rxjs/Subscription';
import { BrandsService } from '../shared/brands.service';

declare var Materialize:any;

@Component({
  selector: 'app-brands',
  templateUrl: './brands.component.html',
  styleUrls: ['./brands.component.css']
})
export class BrandsComponent implements OnInit {
  public loading = false;
  brandForm: FormGroup;
  brands: Brand[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalBrand: Brand;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: number;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;

  constructor(private brandsService: BrandsService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.brandsService.brands = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.httpSubscription = this.brandsService.fetchData(this.currentPage, this.pageSize).subscribe(
      data => {
        this.brands = data.value;
        this.total = data.total;
        this.brandsService.addBrands(this.brands);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.localSubscription = this.brandsService.brandsChanged.subscribe(
      (brands: Brand[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'La marca fue eliminada exitosamente.';
        } else if (this.editMode) {
          message = 'La marca fue modificada exitosamente.';
        } else {
          message = 'La marca fue creada exitosamente.';
        }
        Materialize.toast(message, 4000);
        this.onPageChange(1);
        this.onCancelClicked();
      }
    );
    this.initForm();
  }

  private initForm() {
    this.brandForm = new FormGroup({
      'nombre': new FormControl('', Validators.required)
    });
  }

  onAddBrandClicked() {
    this.modalBrand = new Brand;
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditBrandClicked(brand: Brand, index: number) {
    this.modalBrand = brand;
    this.editMode = true;
    this.editIndex = index;
    this.editId = brand.id;
    this.brandForm.patchValue({
      nombre: brand.nombre
    });
    Materialize.updateTextFields();
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onRemoveBrandClicked(index: number) {
    this.deleteMode = true;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.brandsService.deleteBrand(index, this.brands[index].id);
  }

  onSubmit() {
    let brand: Brand = new Brand;
    brand.nombre = this.brandForm.value.nombre;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      brand.id = this.editId;
      this.brandsService.updateBrand(this.editIndex, brand);
    } else {
      this.brandsService.addBrand(brand);
    }
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal",params:['close']});
    this.brandForm.reset();
    this.editMode = false;
    this.deleteMode = false;
    this.editIndex = -1;
  }

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.brands = [];
    this.httpSubscription = this.brandsService.fetchData(page, this.pageSize).subscribe(
      data => {
        this.brands = data.value;
        this.currentPage = page;
        this.brandsService.addBrands(this.brands);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }
}
