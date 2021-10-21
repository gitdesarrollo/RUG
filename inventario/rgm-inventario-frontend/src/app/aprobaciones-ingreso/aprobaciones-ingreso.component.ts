import { Component, OnInit, EventEmitter, ViewChild } from '@angular/core';
import { Ingreso } from '../shared/ingreso.model';
import { IngresosService } from '../shared/ingresos.service';
import { LoadingService } from '../shared/loading.service';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import { Filtro } from '../shared/filtro.model';
import { SwalComponent } from '@toverux/ngx-sweetalert2';
import { environment } from '../../environments/environment';
import { UsersService } from '../shared/users.service';

@Component({
  selector: 'app-aprobaciones-ingreso',
  templateUrl: './aprobaciones-ingreso.component.html',
  styleUrls: ['./aprobaciones-ingreso.component.css']
})
export class AprobacionesIngresoComponent implements OnInit {
  public loading = false;
  ingresos: Ingreso[];
  httpSubscription: Subscription;
  localSubscription: Subscription;
  approvedSubscription: Subscription;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  editIndex: number;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalIngreso: Ingreso;
  filtro: Filtro;
  cantidadAprobada: number[];
  @ViewChild('swalDetalleObligatorios') private swalDetalleObligatorios: SwalComponent;
  urlIngresoPdf: string;

  constructor(private ingresosService: IngresosService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.urlIngresoPdf = null;
    this.ingresos = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.filtro = new Filtro;
    this.filtro.estado = 'S';
    this.httpSubscription = this.ingresosService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        this.ingresos = data.value;
        this.total = data.total;
        this.ingresosService.addIngresos(this.ingresos);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.localSubscription = this.ingresosService.ingresosChanged.subscribe(
      (ingresos: Ingreso[]) => {
        // this.ingresos = ingresos;
        this.modalActions.emit({action:"modal",params:['close']});
        this.editIndex = -1;
        this.onPageChange(1);
      }
    );
    this.approvedSubscription = this.ingresosService.ingresoAprobado.subscribe(
      (ingresoId: number) => {
        this.urlIngresoPdf = environment.base_url + '/reporte-ingreso?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&id=' + ingresoId;
        // llamar a servlet para imprimir formato de ingreso
        this.ingresosService.downloadPdf(ingresoId).subscribe(
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

  onViewIngresoClicked(index: number) {
    this.urlIngresoPdf = null;
    this.editIndex = index;
    this.modalIngreso = this.ingresos[index];
    this.cantidadAprobada = new Array<number>(this.modalIngreso.detalle.length);
    this.modalIngreso.detalle.forEach((det, index) => this.cantidadAprobada[index] = det.cantidad);
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onApprovedClicked() {
    const cantidadAprobada = this.cantidadAprobada;
    let aprobadas = 0;
    cantidadAprobada.forEach(o => {
      if (o) {
        aprobadas++;
      }
    });
    if (this.cantidadAprobada.length != aprobadas) {
      this.swalDetalleObligatorios.show();
      return;
    }
    // cambiar el estado del ingreso
    this.modalIngreso.estado = 'A';
    // actualizar detalle con las cantidades aprobadas
    this.modalIngreso.detalle.forEach((o, i) => o.cantidad = cantidadAprobada[i]);
    this.modalIngreso.solicitante = null;
    this.ingresosService.updateIngreso(this.editIndex, this.modalIngreso);
  }

  onRejectedClicked() {
    this.urlIngresoPdf = null;
    // cambiar el estado del ingreso
    this.modalIngreso.estado = 'R';
    this.modalIngreso.solicitante = null;
    this.ingresosService.updateIngreso(this.editIndex, this.modalIngreso);
  }

  onPageChange(page: number) {
    this.urlIngresoPdf = null;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.ingresos = [];
    this.httpSubscription = this.ingresosService.fetchData(this.filtro, page, this.pageSize).subscribe(
      data => {
        this.ingresos = data.value;
        this.currentPage = page;
        this.ingresosService.addIngresos(this.ingresos);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }
}
