import { Component, OnInit, EventEmitter, ViewChild } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import * as moment from 'moment';
import { Article } from '../shared/article.model';
import { ArticlesService } from '../shared/articles.service';
import { DetalleIngreso } from '../shared/detalle-ingreso.model';
import { Ingreso } from '../shared/ingreso.model';
import { IngresosService } from '../shared/ingresos.service';
import { UsersService } from '../shared/users.service';
import { TipoIngreso } from '../shared/tipo-ingreso.model';
import { TiposIngresoService } from '../shared/tipos-ingreso.service';
import { LoadingService } from '../shared/loading.service';
import { SwalComponent } from '@toverux/ngx-sweetalert2';
import { environment } from '../../environments/environment';

declare var Materialize:any;

@Component({
  selector: 'app-ingresos',
  templateUrl: './ingresos.component.html',
  styleUrls: ['./ingresos.component.css']
})
export class IngresosComponent implements OnInit {
  public loading = false;
  @ViewChild('f') detalleForm: NgForm;
  ingresoForm: FormGroup;
  modalActions = new EventEmitter<string|MaterializeAction>();
  articles: Article[];
  tiposIngreso: TipoIngreso[];
  articlesSubscription: Subscription;
  tiposIngresoSubscription: Subscription;
  ingresosSubscription: Subscription;
  autocompleteInit = {
    onAutocomplete: (val) => {
      this.onArticuloSelected(val);
    },
    'property': 'descripcion'
  };
  autocompleteCodigoInit = {
    onAutocomplete: (val) => {
      this.onArticuloSelected(val);
    },
    'property': 'codigo'
  };
  //autocompleteInit: any;
  data = {};
   nameToObject = {};
   detalleIngreso: DetalleIngreso[];
   modalDetalle: DetalleIngreso;
   @ViewChild('swalArticuloAgregado') private swalArticuloAgregado: SwalComponent;
   @ViewChild('swalDetalleObligatorios') private swalDetalleObligatorios: SwalComponent;
   @ViewChild('swalIngresoObligatorios') private swalIngresoObligatorios: SwalComponent;
   @ViewChild('swalNoDetalle') private swalNoDetalle: SwalComponent;
   @ViewChild('swalCero') private swalCero: SwalComponent;
   @ViewChild('autocomplete') private autocomplete;
   codigoArticulo: string;
   descripcionArticulo: string;

   constructor(private articlesService: ArticlesService,
    private ingresosService: IngresosService,
    private tiposIngresoService: TiposIngresoService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

   ngOnInit() {
     this.loading = true;
     this.loadingService.changeLoading(this.loading);
     this.detalleIngreso = [];
     this.articles = [];
     this.articlesSubscription = this.articlesService.fetchData(null, null, null).subscribe(
       data => {
         this.articles = data.value;
         // this.articles.forEach(o => this.data[o.descripcion] = null);
         // this.autocompleteInit.data = this.articles;
         /*this.autocompleteInit = {
           'data': this.articles,
           onAutocomplete: (val) => {
             this.onArticuloSelected(val);
           },
         };*/
         this.articles.forEach(o => this.nameToObject[o.descripcion] = o);
         // this.autocompleteInit.updateData();
         this.loading = false;
         this.loadingService.changeLoading(this.loading);
         // console.log(this.data);
       }
     );
     this.tiposIngreso = [];
     this.tiposIngresoSubscription = this.tiposIngresoService.fetchData().subscribe(
       data => {
         this.tiposIngreso = data.value;
       }
     );
     this.ingresosSubscription = this.ingresosService.ingresoCreado.subscribe(
       data => {
         Materialize.toast('El ingreso fue registrado exitosamente. Codigo: ' + data.correlativo, 4000);
      }
    );
     this.initForm();
   }

   private initForm() {
     this.ingresoForm = new FormGroup({
       'correlativo': new FormControl(''),
       'fecha': new FormControl('', Validators.required),
       'tipoIngreso': new FormControl('', Validators.required),
       'referencia': new FormControl(''),
       'observaciones': new FormControl('')
     });
   }

   onAddArticleClicked() {
     this.codigoArticulo = '';
     this.descripcionArticulo = '';
     this.modalDetalle = new DetalleIngreso;
     this.modalActions.emit({action:"modal", params:["open"]});
   }

   onAddDetalleClicked() {
     // validar los campos obligatorios
     if (!this.modalDetalle.codigoArticulo || !this.detalleForm.value.precio || !this.detalleForm.value.cantidad || (this.modalDetalle.articulo.perecedero && !this.detalleForm.value.fechaVencimiento)) {
       this.swalDetalleObligatorios.show();
       return;
     }
     if (this.detalleForm.value.cantidad == 0) {
       this.swalCero.show();
       return;
     }
     const codigoArticulo = this.modalDetalle.codigoArticulo;
     const fechaVencimiento = this.detalleForm.value.fechaVencimiento;
     const encontrado = this.detalleIngreso.find(function (obj) {
       return (obj.codigoArticulo == codigoArticulo && moment(obj.fechaVencimiento, 'YYYY-MM-DDTHH:mm:ss').format('DD/MM/YYYY') == fechaVencimiento);
     })
     if (!encontrado) {
       this.modalDetalle.precio = this.detalleForm.value.precio;
       this.modalDetalle.cantidad = this.detalleForm.value.cantidad;
       if (this.detalleForm.value.fechaVencimiento) {
         this.modalDetalle.fechaVencimiento = moment(this.detalleForm.value.fechaVencimiento, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss')
       }
       this.detalleIngreso.push(this.modalDetalle);
       this.onCancelClicked();
     } else {
       this.swalArticuloAgregado.show();
     }
   }

   onCancelClicked() {
     this.modalActions.emit({action:"modal",params:['close']});
     this.detalleForm.reset();
   }

   onClearClicked() {
     this.ingresoForm.reset();
     this.detalleIngreso = [];
   }

   onArticuloSelected(articulo: Article) {
     this.modalDetalle.codigoArticulo = articulo.codigo;
     this.modalDetalle.articulo = articulo;
     this.codigoArticulo = articulo.codigo;
     this.descripcionArticulo = articulo.descripcion;
     Materialize.updateTextFields();
   }

   onRemoveArticleClicked(index: number) {
     this.detalleIngreso.splice(index, 1);
   }

   onSubmit() {
     // validar campos obligatorios y detalle
     if (!this.ingresoForm.value.fecha || !this.ingresoForm.value.tipoIngreso || !this.ingresoForm.value.referencia || !this.ingresoForm.value.observaciones) {
       this.swalIngresoObligatorios.show();
       return;
     }
     if (!this.detalleIngreso || this.detalleIngreso.length == 0) {
       this.swalNoDetalle.show();
       return;
     }
     let ingreso: Ingreso = new Ingreso;
     ingreso.fecha = moment(this.ingresoForm.value.fecha, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss');
     ingreso.tipoIngresoId = this.ingresoForm.value.tipoIngreso;
     ingreso.referencia = this.ingresoForm.value.referencia;
     ingreso.observaciones = this.ingresoForm.value.observaciones;
     ingreso.correlativo = this.ingresoForm.value.correlativo;
     ingreso.usuarioId = this.usersService.authenticatedUser.usuarioId;
     ingreso.detalle = this.detalleIngreso;
     ingreso.detalle.forEach(o => o.articulo = null);
     this.ingresosService.addIngreso(ingreso);
     this.onClearClicked();
   }

   stopPropagation(event) {
     event.stopPropagation();
   }
}
