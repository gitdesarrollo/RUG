import { Component, OnInit, ViewChild, EventEmitter } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import * as moment from 'moment';
import { Article } from '../shared/article.model';
import { TipoSalida } from '../shared/tipo-salida.model';
import { DetalleSalida } from '../shared/detalle-salida.model';
import { ArticlesService } from '../shared/articles.service';
import { UsersService } from '../shared/users.service';
import { TiposSalidaService } from '../shared/tipos-salida.service';
import { Salida } from '../shared/salida.model';
import { SalidasService } from '../shared/salidas.service';
import { SwalComponent } from '@toverux/ngx-sweetalert2';
import { LoadingService } from '../shared/loading.service';
import { environment } from '../../environments/environment';

declare var Materialize:any;

@Component({
  selector: 'app-salidas',
  templateUrl: './salidas.component.html',
  styleUrls: ['./salidas.component.css']
})
export class SalidasComponent implements OnInit {
  public loading = false;
  @ViewChild('f') detalleForm: NgForm;
  salidaForm: FormGroup;
  modalActions = new EventEmitter<string|MaterializeAction>();
  articles: Article[];
  tiposSalida: TipoSalida[];
  articlesSubscription: Subscription;
  tiposSalidaSubscription: Subscription;
  salidasSubscription: Subscription;
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
   detalleSalida: DetalleSalida[];
   modalDetalle: DetalleSalida;
   @ViewChild('swalArticuloAgregado') private swalArticuloAgregado: SwalComponent;
   @ViewChild('swalDetalleObligatorios') private swalDetalleObligatorios: SwalComponent;
   @ViewChild('swalSalidaObligatorios') private swalSalidaObligatorios: SwalComponent;
   @ViewChild('swalNoDetalle') private swalNoDetalle: SwalComponent;
   @ViewChild('swalMaximoSalida') private swalMaximoSalida: SwalComponent;
   @ViewChild('swalCero') private swalCero: SwalComponent;
   codigoArticulo: string;
   descripcionArticulo: string;

   constructor(private articlesService: ArticlesService,
    private salidasService: SalidasService,
    private tiposSalidaService: TiposSalidaService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

   ngOnInit() {
     this.loading = true;
     this.loadingService.changeLoading(this.loading);
     this.detalleSalida = [];
     this.articles = [];
     this.articlesSubscription = this.articlesService.fetchData(null, null, null).subscribe(
       data => {
         this.articles = data.value;
         // this.articles.forEach(o => this.autocompleteInit.data[o.descripcion] = null);
         // this.articles.forEach(o => this.nameToObject[o.descripcion] = o);
         this.loading = false;
         this.loadingService.changeLoading(this.loading);
       }
     );
     this.tiposSalida = [];
     this.tiposSalidaSubscription = this.tiposSalidaService.fetchData().subscribe(
       data => {
         this.tiposSalida = data.value;
       }
     );
     this.salidasSubscription = this.salidasService.salidaCreada.subscribe(
       data => {
         Materialize.toast('La salida fue registrada exitosamente. Codigo: ' + data.correlativo, 4000);
      }
    );
     this.initForm();
   }

   private initForm() {
     this.salidaForm = new FormGroup({
       'correlativo': new FormControl(''),
       'fecha': new FormControl('', Validators.required),
       'tipoSalida': new FormControl('', Validators.required),
       'observaciones': new FormControl('')
     });
   }

   onAddArticleClicked() {
     this.codigoArticulo = '';
     this.descripcionArticulo = '';
     this.modalDetalle = new DetalleSalida;
     this.modalActions.emit({action:"modal", params:["open"]});
   }

   onAddDetalleClicked() {
     if (!this.modalDetalle.codigoArticulo || !this.detalleForm.value.cantidad) {
       this.swalDetalleObligatorios.show();
       return;
     }
     if (this.detalleForm.value.cantidad > this.modalDetalle.articulo.existencia) {
       this.swalMaximoSalida.show();
       return;
     }
     if (this.detalleForm.value.cantidad == 0) {
       this.swalCero.show();
       return;
     }
     const codigoArticulo = this.modalDetalle.codigoArticulo;
     const encontrado = this.detalleSalida.find(function (obj) {
       return obj.codigoArticulo == codigoArticulo;
     });
     if (!encontrado) {
       this.modalDetalle.cantidad = this.detalleForm.value.cantidad;
       this.detalleSalida.push(this.modalDetalle);
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
     this.salidaForm.reset();
     this.detalleSalida = [];
   }

   onArticuloSelected(articulo: Article) {
     this.modalDetalle.codigoArticulo = articulo.codigo;
     this.modalDetalle.articulo = articulo;
     this.codigoArticulo = articulo.codigo;
     this.descripcionArticulo = articulo.descripcion;
     Materialize.updateTextFields();
   }

   onRemoveArticleClicked(index: number) {
     this.detalleSalida.splice(index, 1);
   }

   onSubmit() {
     // validar campos obligatorios y detalle
     if (!this.salidaForm.value.fecha || !this.salidaForm.value.tipoSalida || !this.salidaForm.value.observaciones) {
       this.swalSalidaObligatorios.show();
       return;
     }
     if (!this.detalleSalida || this.detalleSalida.length == 0) {
       this.swalNoDetalle.show();
       return;
     }
     let salida: Salida = new Salida;
     salida.fecha = moment(this.salidaForm.value.fecha, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss');
     salida.tipoSalidaId = this.salidaForm.value.tipoSalida;
     salida.observaciones = this.salidaForm.value.observaciones;
     salida.correlativo = this.salidaForm.value.correlativo;
     salida.usuarioId = this.usersService.authenticatedUser.usuarioId;
     salida.detalle = this.detalleSalida;
     salida.detalle.forEach(o => o.articulo = null);
     this.salidasService.addSalida(salida);
     this.onClearClicked();
   }

   stopPropagation(event) {
     event.stopPropagation();
   }
 }
