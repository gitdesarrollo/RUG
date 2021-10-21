import { Component, OnInit, ViewChild, EventEmitter } from '@angular/core';
import { FormGroup, NgForm, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import * as moment from 'moment';
import { Article } from '../shared/article.model';
import { ArticlesService } from '../shared/articles.service';
import { UsersService } from '../shared/users.service';
import { DetalleRequisicion } from '../shared/detalle-requisicion.model';
import { Requisicion } from '../shared/requisicion.model';
import { RequisicionesService } from '../shared/requisiciones.service';
import { SwalComponent } from '@toverux/ngx-sweetalert2';
import { LoadingService } from '../shared/loading.service';
import { environment } from '../../environments/environment';
import { Inventory } from '../shared/inventory.model';

declare var Materialize:any;
//declare var jQuery: any;

@Component({
  selector: 'app-requisiciones',
  templateUrl: './requisiciones.component.html',
  styleUrls: ['./requisiciones.component.css']
})
export class RequisicionesComponent implements OnInit {
  public loading = false;
  @ViewChild('f') detalleForm: NgForm;
  requisicionForm: FormGroup;
  modalActions = new EventEmitter<string|MaterializeAction>();
  articles: Article[];
  articlesSubscription: Subscription;
  requisicionesSubscription: Subscription;
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
   nameToObject = {};
   detalleRequisicion: DetalleRequisicion[];
   modalDetalle: DetalleRequisicion;
   @ViewChild('swalArticuloAgregado') private swalArticuloAgregado: SwalComponent;
   @ViewChild('swalDetalleObligatorios') private swalDetalleObligatorios: SwalComponent;
   @ViewChild('swalRequisicionObligatorios') private swalRequisicionObligatorios: SwalComponent;
   @ViewChild('swalNoDetalle') private swalNoDetalle: SwalComponent;
   @ViewChild('swalMaximoRequisicion') private swalMaximoRequisicion: SwalComponent;
   @ViewChild('swalCero') private swalCero: SwalComponent;
   codigoArticulo: string;
   descripcionArticulo: string;
   urlBase: string;
   urlRequisicionPdf: string;

   constructor(private articlesService: ArticlesService,
    private requisicionesService: RequisicionesService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

   ngOnInit() {
     this.urlRequisicionPdf = null;
     this.loading = true;
     this.loadingService.changeLoading(this.loading);
     this.detalleRequisicion = [];
     this.articles = [];
     this.urlBase = environment.base_url;
     this.articlesSubscription = this.articlesService.fetchData(null, null, null).subscribe(
       data => {
         this.articles = data.value;
         this.loading = false;
         this.loadingService.changeLoading(this.loading);
       }
     );
     /*jQuery('.datepicker').on('mousedown',(ev: Event, picker: any)  => {
        ev.preventDefault();
     });*/
     this.requisicionesSubscription = this.requisicionesService.requisicionCreada.subscribe(
       data => {
         Materialize.toast('La requisicion fue registrada exitosamente. Codigo: ' + data.correlativo, 4000);
         this.urlRequisicionPdf = environment.base_url + '/reporte-requisiciones?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&idRequisicion=' + data.requisicionId;
         // llamar a servlet para imprimir formato de requisicion
         this.requisicionesService.downloadPdf(data.requisicionId).subscribe(
          res => {
            var blob = new Blob([res], { type: 'application/pdf' });
            var url = window.URL.createObjectURL(blob);
            var pwa = window.open(url);
            if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
              alert('Por favor desactive el bloqueo de ventanas emergentes e intente nuevamente.');
            }
          }
        );
      }
    );
     this.initForm();
   }

   private initForm() {
     this.requisicionForm = new FormGroup({
       'correlativo': new FormControl(''),
       'fecha': new FormControl('', Validators.required),
       'observaciones': new FormControl('')
     });
   }

   onAddArticleClicked() {
     this.codigoArticulo = '';
     this.descripcionArticulo = '';
     this.modalDetalle = new DetalleRequisicion;
     this.modalActions.emit({action:"modal", params:["open"]});
   }

   onAddDetalleClicked() {
     if (!this.modalDetalle.codigoArticulo || !this.detalleForm.value.cantidad) {
       this.swalDetalleObligatorios.show();
       return;
     }
     if (this.detalleForm.value.cantidad > this.modalDetalle.articulo.existencia) {
       this.swalMaximoRequisicion.show();
       return;
     }
     if (this.detalleForm.value.cantidad == 0) {
       this.swalCero.show();
       return;
     }
     const codigoArticulo = this.modalDetalle.codigoArticulo;
     const encontrado = this.detalleRequisicion.find(function (obj) {
       return obj.codigoArticulo == codigoArticulo;
     })
     if (!encontrado) {
       this.modalDetalle.cantidad = this.detalleForm.value.cantidad;
       this.detalleRequisicion.push(this.modalDetalle);
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
     this.detalleForm.reset();
     this.requisicionForm.reset();
     this.detalleRequisicion = [];
     this.urlRequisicionPdf = null;
   }

   onArticuloSelected(articulo: Article) {
     this.modalDetalle.codigoArticulo = articulo.codigo;
     this.modalDetalle.articulo = articulo;
     this.codigoArticulo = articulo.codigo;
     this.descripcionArticulo = articulo.descripcion;
     Materialize.updateTextFields();
   }

   onRemoveArticleClicked(index: number) {
     this.detalleRequisicion.splice(index, 1);
   }

   onSubmit() {
     // validar campos obligatorios y detalle
     if (!this.requisicionForm.value.fecha || !this.requisicionForm.value.observaciones) {
       this.swalRequisicionObligatorios.show();
       return;
     }
     if (!this.detalleRequisicion || this.detalleRequisicion.length == 0) {
       this.swalNoDetalle.show();
       return;
     }
     let requisicion: Requisicion = new Requisicion;
     requisicion.fecha = moment(this.requisicionForm.value.fecha, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss');
     requisicion.observaciones = this.requisicionForm.value.observaciones;
     requisicion.usuarioId = this.usersService.authenticatedUser.usuarioId;
     requisicion.detalle = this.detalleRequisicion;
     requisicion.detalle.forEach(o => o.articulo = null);
     this.requisicionesService.addRequisicion(requisicion);
     this.onClearClicked();
   }

   stopPropagation(event) {
     event.stopPropagation();
   }
}
