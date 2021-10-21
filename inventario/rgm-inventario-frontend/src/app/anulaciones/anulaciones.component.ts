import { Component, OnInit, EventEmitter } from '@angular/core';
import { Requisicion } from '../shared/requisicion.model';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import { RequisicionesService } from '../shared/requisiciones.service';
import { LoadingService } from '../shared/loading.service';
import { Filtro } from '../shared/filtro.model';

@Component({
  selector: 'app-anulaciones',
  templateUrl: './anulaciones.component.html',
  styleUrls: ['./anulaciones.component.css']
})
export class AnulacionesComponent implements OnInit {
  public loading = false;
  requisiciones: Requisicion[];
  httpSubscription: Subscription;
  localSubscription: Subscription;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalRequisicion: Requisicion;
  rechazo: boolean;
  editIndex: number;
  motivo: string;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  estado: string = 'S';
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
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onAnularClicked() {
    // cambiar el estado de la requisicion
    this.modalRequisicion.estado = 'C';
    // actualizar detalle con las cantidades aprobadas
    this.onSubmit();
  }

  onSubmit() {
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
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }
}
