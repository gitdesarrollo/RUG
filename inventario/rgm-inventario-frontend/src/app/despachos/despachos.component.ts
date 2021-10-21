import { Component, OnInit, EventEmitter, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import * as moment from 'moment';
import { Requisicion } from '../shared/requisicion.model';
import { RequisicionesService } from '../shared/requisiciones.service';
import { Despacho } from '../shared/despacho.model';
import { DespachosService } from '../shared/despachos.service';
import { DetalleDespacho } from '../shared/detalle-despacho.model';
import { LoadingService } from '../shared/loading.service';
import { Filtro } from '../shared/filtro.model';
import { DetalleIngreso } from '../shared/detalle-ingreso.model';
import { DetalleIngresosService } from '../shared/detalle-ingresos.service';
import { SwalComponent } from '@toverux/ngx-sweetalert2';
import { environment } from '../../environments/environment';
import { DespachoPerecedero } from '../shared/despacho-perecedero.model';
import { UsersService } from '../shared/users.service';

declare var Materialize:any;

@Component({
  selector: 'app-despachos',
  templateUrl: './despachos.component.html',
  styleUrls: ['./despachos.component.css']
})
export class DespachosComponent implements OnInit {
  public loading = false;
  requisiciones: Requisicion[];
  httpSubscription: Subscription;
  requisicionesSubscription: Subscription;
  despachosSubscription: Subscription;
  detalleSubscription: Subscription;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalRequisicion: Requisicion;
  cantidadAprobada: number[];
  rechazo: boolean;
  editIndex: number;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  estado: string = 'A';
  filtro: Filtro;
  stock: DetalleIngreso[];
  seleccionarIndex: number;
  cantidadDespachar: number[];
  @ViewChild('swalCantidadSeleccionada') private swalCantidadSeleccionada: SwalComponent;
  @ViewChild('swalCantidadPerecedero') private swalCantidadPerecedero: SwalComponent;
  urlDespachoPdf: string;
  despachoPerecedero: DespachoPerecedero[];

  constructor(private requisicionesService: RequisicionesService,
    private despachosService: DespachosService,
    private detalleIngresosService: DetalleIngresosService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.urlDespachoPdf = null;
    this.filtro = new Filtro;
    this.filtro.estado = 'A';
    this.rechazo = false;
    this.requisiciones = [];
    this.httpSubscription = this.requisicionesService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        this.requisiciones = data.value;
        this.total = data.total;
      }
    );
    this.requisicionesSubscription = this.requisicionesService.requisicionesChanged.subscribe(
      (requisiciones: Requisicion[]) => {
        // this.requisiciones = requisiciones;
        this.modalActions.emit({action:"modal",params:['close']});
        this.editIndex = -1;
        this.onPageChange(1);
      }
    );
    this.despachosSubscription = this.despachosService.despachoCreado.subscribe(
       data => {
         Materialize.toast('El despacho fue registrado exitosamente. Codigo: ' + data.correlativo, 4000);
         this.urlDespachoPdf = environment.base_url + '/reporte-despacho?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&id=' + data.despachoId;
         // actualizar los detalles de ingreso
         /*this.modalRequisicion.detalle.forEach(o => {
           o.despachoPerecedero.forEach(dp => {
             let detalleIngreso: DetalleIngreso = new DetalleIngreso;
             detalleIngreso.detalleIngresoId = dp.detalleIngresoId;
             detalleIngreso.stock = dp.cantidad;
             this.detalleIngresosService.updateDetalleIngreso(dp.detalleIngresoId, detalleIngreso);
           });
         });*/
         // llamar a servlet para imprimir formato de despacho
         this.despachosService.downloadPdf(data.despachoId).subscribe(
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
  }

  onViewRequisicionClicked(index: number) {
    this.editIndex = index;
    this.modalRequisicion = this.requisiciones[index];
    this.rechazo = false;
    this.cantidadAprobada = new Array<number>(this.modalRequisicion.detalle.length);
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal", params:["close"]});
    this.urlDespachoPdf = null;
  }

  onSubmit() {
    // validar si tiene algun producto perecedero que se haya seleccionado el detalle
    /*let perecederoIngresado = true;
    this.modalRequisicion.detalle.forEach(o => {
      if (o.articulo.perecedero && (!o.despachoPerecedero || o.despachoPerecedero.length === 0)) {
        perecederoIngresado = false;
      }
    });
    if (!perecederoIngresado) {
      this.swalCantidadPerecedero.show();
      return;
    }*/
    let despacho: Despacho = new Despacho;
    despacho.fecha = moment().format('YYYY-MM-DDTHH:mm:ss');
    despacho.requisicionId = this.modalRequisicion.requisicionId;
    despacho.observaciones = this.modalRequisicion.observaciones;
    despacho.detalle = [];
    this.modalRequisicion.detalle.forEach(o => {
      let detalle: DetalleDespacho = new DetalleDespacho;
      detalle.codigoArticulo = o.codigoArticulo;
      detalle.cantidad = o.cantidadAprobada;
      despacho.detalle.push(detalle);
    });
    this.despachosService.addDespacho(despacho);
    this.onCancelClicked();
  }

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.requisiciones = [];
    this.httpSubscription = this.requisicionesService.fetchData(this.filtro, page, this.pageSize).subscribe(
      data => {
        this.requisiciones = data.value;
        this.currentPage = page;
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  onSeleccionarClicked(index: number) {
    this.seleccionarIndex = index;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.detalleSubscription = this.detalleIngresosService.fetchData(this.modalRequisicion.detalle[index].codigoArticulo).subscribe(
      data => {
        this.stock = data.value;
        this.cantidadDespachar = new Array<number>(this.stock.length);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  onDetalleSelectedClicked() {
    // validar que la cantidad ingresada de cada detalle sea igual a la cantidad aprobada
    let cantidadSeleccionada: number = 0;
    /*this.cantidadDespachar.forEach(o => {
      console.log(o);
      if (typeof o === 'string') {
        o = parseInt(o);
      }
      cantidadSeleccionada += o;
    });*/
    this.despachoPerecedero = [];
    for (var i = 0; i < this.cantidadDespachar.length; i++) {
      if (this.cantidadDespachar[i]) {
        let cantidadDespachar = this.cantidadDespachar[i];
        if (typeof cantidadDespachar === 'string') {
          cantidadDespachar = parseInt(cantidadDespachar);
        }
        cantidadSeleccionada += cantidadDespachar;
        let detallePerecedero: DespachoPerecedero = new DespachoPerecedero;
        detallePerecedero.detalleIngresoId = this.stock[i].detalleIngresoId;
        detallePerecedero.cantidad = cantidadDespachar;
        this.despachoPerecedero.push(detallePerecedero);
      }
    }
    // console.log(this.despachoPerecedero);
    // console.log(this.stock);
    if (cantidadSeleccionada === this.modalRequisicion.detalle[this.seleccionarIndex].cantidadAprobada) {
      this.modalRequisicion.detalle[this.seleccionarIndex].despachoPerecedero = this.despachoPerecedero;
      this.stock = [];
      this.seleccionarIndex = -1;
    } else {
      // mostrar alerta de ingreso diferente a cantidad aprobada
      this.swalCantidadSeleccionada.show();
    }
  }

  onDetalleCanceledClicked() {
    this.stock = [];
    this.seleccionarIndex = -1;
  }
}
