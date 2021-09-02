import { Component, OnInit, EventEmitter, ViewChild } from '@angular/core';
import { Requisicion } from '../shared/requisicion.model';
import { Subscription } from 'rxjs/Subscription';
import { RequisicionesService } from '../shared/requisiciones.service';
import { MaterializeAction } from 'angular2-materialize';
import { LoadingService } from '../shared/loading.service';
import { SwalComponent } from '@toverux/ngx-sweetalert2';
import { Filtro } from '../shared/filtro.model';

@Component({
  selector: 'app-aprobaciones',
  templateUrl: './aprobaciones.component.html',
  styleUrls: ['./aprobaciones.component.css']
})
export class AprobacionesComponent implements OnInit {
  public loading = false;
  requisiciones: Requisicion[];
  httpSubscription: Subscription;
  localSubscription: Subscription;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalRequisicion: Requisicion;
  cantidadAprobada: number[];
  rechazo: boolean;
  editIndex: number;
  motivo: string;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  estado: string = 'S';
  @ViewChild('swalMotivoRechazo') private swalMotivoRechazo: SwalComponent;
  @ViewChild('swalDetalleObligatorios') private swalDetalleObligatorios: SwalComponent;
  filtro: Filtro;

  constructor(private requisicionesService: RequisicionesService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.filtro = new Filtro;
    this.filtro.estado = 'S';
    this.rechazo = false;
    this.requisiciones = [];
    this.requisicionesService.requisiciones = [];
    this.httpSubscription = this.requisicionesService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        this.requisiciones = data.value;
        this.total = data.total;
        this.requisicionesService.addRequisiciones(this.requisiciones);
      }
    );
    this.localSubscription = this.requisicionesService.requisicionesChanged.subscribe(
      (requisiciones: Requisicion[]) => {
        this.requisiciones = requisiciones;
        this.modalActions.emit({action:"modal",params:['close']});
        this.editIndex = -1;
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
    // cambiar el estado de la requisicion
    this.modalRequisicion.estado = 'A';
    // actualizar detalle con las cantidades aprobadas
    this.modalRequisicion.detalle.forEach((o, i) => o.cantidadAprobada = cantidadAprobada[i]);
    this.onSubmit();
  }

  onRejectedClicked() {
    this.rechazo = true;
  }

  onSaveRejectClicked() {
    if (!this.motivo) {
      this.swalMotivoRechazo.show();
      return;
    }
    this.modalRequisicion.estado = 'C';
    this.modalRequisicion.comentario = this.motivo;
    this.onSubmit();
  }

  onSubmit() {
    this.modalRequisicion.solicitante = null;
    this.requisicionesService.updateRequisicion(this.editIndex, this.modalRequisicion);
  }

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.requisiciones = [];
    this.httpSubscription = this.requisicionesService.fetchData(this.filtro, page, this.pageSize).subscribe(
      data => {
        this.requisiciones = data.value;
        this.currentPage = page;
        this.requisicionesService.addRequisiciones(this.requisiciones);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }
}
