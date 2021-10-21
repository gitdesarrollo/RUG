import { Component, OnInit, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MaterializeAction } from 'angular2-materialize';
import { LoadingService } from '../shared/loading.service';
import { Article } from '../shared/article.model';
import { Subscription } from 'rxjs/Subscription';
import * as moment from 'moment';
import { ArticlesService } from '../shared/articles.service';
import { Type } from '../shared/type.model';
import { TypesService } from '../shared/types.service';
import { Brand } from '../shared/brand.model';
import { BrandsService } from '../shared/brands.service';
import { Unit } from '../shared/unit.model';
import { UnitsService } from '../shared/units.service';
import { Supplier } from '../shared/supplier.model';
import { SuppliersService } from '../shared/suppliers.service';
import { Inventory } from '../shared/inventory.model';
import { Filtro } from '../shared/filtro.model';

declare var Materialize:any;

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {
  public loading = false;
  articleForm: FormGroup;
  articles: Inventory[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalArticle: Article;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  typesSubscription: Subscription;
  brandsSubscription: Subscription;
  unitsSubscription: Subscription;
  suppliersSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: string;
  types: Type[];
  brands: Brand[];
  units: Unit[];
  suppliers: Supplier[];
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  servicesCalled: number;
  filtro: Filtro;
  filTexto: string;

  @ViewChild('filTextoInput') filTextoInput: ElementRef;

  constructor(private articlesService: ArticlesService,
    private typesService: TypesService,
    private brandsService: BrandsService,
    private unitsService: UnitsService,
    private suppliersService: SuppliersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.filtro = new Filtro;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.typesService.getTypes();

    this.servicesCalled = 0;
    this.articlesService.articles = [];
    this.servicesCalled++;
    this.httpSubscription = this.articlesService.fetchData(null, this.currentPage, this.pageSize).subscribe(
      data => {
        this.articles = data.value;
        this.total = data.total;
        // this.articlesService.addArticles(this.articles);
      },
      err => console.error(err),
      () => {
        this.servicesCalled--;
        if (this.servicesCalled === 0) {
          this.loading = false;
          this.loadingService.changeLoading(this.loading);
        }
      }
    );
    this.localSubscription = this.articlesService.articlesChanged.subscribe(
      (articles: Article[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'El articulo fue eliminado exitosamente.';
        } else if (this.editMode) {
          message = 'El articulo fue modificado exitosamente.';
        } else {
          message = 'El articulo fue creado exitosamente.';
        }
        Materialize.toast(message, 4000);
        this.onPageChange(1);
        this.onCancelClicked();
      }
    );
    this.servicesCalled++;
    this.typesSubscription = this.typesService.fetchData().subscribe(
      data => {
        this.types = data.value;
      },
      err => console.error(err),
      () => {
        this.servicesCalled--;
        if (this.servicesCalled === 0) {
          this.loading = false;
          this.loadingService.changeLoading(this.loading);
        }
      }
    );
    this.servicesCalled++;
    this.brandsSubscription = this.brandsService.fetchData(null, null).subscribe(
      data => {
        this.brands = data.value;
      },
      err => console.error(err),
      () => {
        this.servicesCalled--;
        if (this.servicesCalled === 0) {
          this.loading = false;
          this.loadingService.changeLoading(this.loading);
        }
      }
    );
    this.servicesCalled++;
    this.unitsSubscription = this.unitsService.fetchData(null, null).subscribe(
      data => {
        this.units = data.value;
      },
      err => console.error(err),
      () => {
        this.servicesCalled--;
        if (this.servicesCalled === 0) {
          this.loading = false;
          this.loadingService.changeLoading(this.loading);
        }
      }
    );
    this.servicesCalled++;
    this.suppliersSubscription = this.suppliersService.fetchData().subscribe(
      data => {
        this.suppliers = data.value;
      },
      err => console.error(err),
      () => {
        this.servicesCalled--;
        if (this.servicesCalled === 0) {
          this.loading = false;
          this.loadingService.changeLoading(this.loading);
        }
      }
    );
    this.initForm();
  }

  private initForm() {
    this.articleForm = new FormGroup({
      'codigo': new FormControl('', Validators.required),
      'codigoBarras': new FormControl(''),
      'descripcion': new FormControl('', Validators.required),
      'tipo': new FormControl('', Validators.required),
      'marca': new FormControl('', Validators.required),
      'unidad': new FormControl('', Validators.required),
      'proveedor': new FormControl('', Validators.required),
      'valor': new FormControl('', Validators.required),
      'minimoExistencia': new FormControl('', Validators.required),
      'perecedero': new FormControl(false)
    });
  }

  onAddArticleClicked() {
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditArticleClicked(article: Article, index: number) {
    this.modalArticle = article;
    this.editMode = true;
    this.editIndex = index;
    this.editId = article.codigo;
    this.articleForm.patchValue({
      codigo: article.codigo,
      codigoBarras: article.codigoBarras,
    	descripcion: article.descripcion,
    	minimoExistencia: article.minimoExistencia,
    	perecedero: article.perecedero,
    	valor: article.valor,
    	marca: article.marcaId,
    	proveedor: article.proveedorId,
    	tipo: article.tipoArticuloId,
    	unidad: article.unidadMedidaId
    });
    Materialize.updateTextFields();
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onRemoveArticleClicked(index: number) {
    this.deleteMode = true;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.articlesService.deleteArticle(index, this.articles[index].codigo);
  }

  onSubmit() {
    let article: Article = new Article;
    article.codigo = this.articleForm.value.codigo;
  	article.codigoBarras = this.articleForm.value.codigoBarras;
  	article.descripcion = this.articleForm.value.descripcion;
    article.perecedero = this.articleForm.value.perecedero ? this.articleForm.value.perecedero : false;
  	article.minimoExistencia = this.articleForm.value.minimoExistencia;
  	article.valor = this.articleForm.value.valor;
    article.marcaId = this.articleForm.value.marca;
  	article.proveedorId = this.articleForm.value.proveedor;
  	article.tipoArticuloId = this.articleForm.value.tipo;
  	article.unidadMedidaId = this.articleForm.value.unidad;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      article.codigo = this.editId;
      this.articlesService.updateArticle(this.editIndex, article);
    } else {
      this.articlesService.addArticle(article);
    }
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal",params:['close']});
    this.articleForm.reset();
    this.editMode = false;
    this.deleteMode = false;
    this.editIndex = -1;
  }

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.articles = [];
    this.httpSubscription = this.articlesService.fetchData(null, page, this.pageSize).subscribe(
      data => {
        this.articles = data.value;
        this.currentPage = page;
        // this.articlesService.addArticles(this.articles);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  filTextoChanged(filTexto: string) {
    this.filtro.texto = filTexto;
    this.refreshData();
  }

  onFilTexto() {
    let event = new Event('blur', {});
    this.filTextoInput.nativeElement.dispatchEvent(event);
  }

  refreshData() {
    this.articlesService.articles = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.httpSubscription = this.articlesService.fetchData(this.filtro, 1, this.pageSize).subscribe(
        data => {
            this.articles = data.value;     
            this.total = data.total;       
            this.currentPage = 1;
            //this.articlesService.addArticles(this.articles);
        },
        err => console.error(err),
        () => {
            this.loading = false;
            this.loadingService.changeLoading(this.loading);
        }
    );
}

}
