import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import { NgxDateRangePickerOptions, NgxDateRangePickerComponent } from 'ngx-daterangepicker';
import * as moment from 'moment';
import { Requisicion } from '../shared/requisicion.model';
import { Subscription } from 'rxjs/Subscription';
import { RequisicionesService } from '../shared/requisiciones.service';
import { LoadingService } from '../shared/loading.service';
import { Filtro } from '../shared/filtro.model';
import { MaterializeAction } from 'angular2-materialize';
import { User } from '../shared/user.model';
import { UsersService } from '../shared/users.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-consulta-requisiciones',
  templateUrl: './consulta-requisiciones.component.html',
  styleUrls: ['./consulta-requisiciones.component.css']
})
export class ConsultaRequisicionesComponent implements OnInit {
  public loading = false;
  options: NgxDateRangePickerOptions;
  @Input() dateRange: NgxDateRangePickerComponent;
  rangoFechas: string | any;
  fechaInicio: string;
  fechaFin: string;
  estado: string;
  requisiciones: Requisicion[];
  httpSubscription: Subscription;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  filtro: Filtro;
  editIndex: number;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalRequisicion: Requisicion;
  authenticatedUser: User;
  urlBase: string;

  constructor(private requisicionesService: RequisicionesService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.authenticatedUser = this.usersService.authenticatedUser;
    const today = moment().toDate();
    this.rangoFechas = {
      from: new Date(today.getFullYear(), today.getMonth(), 1),
      to: new Date(today.getFullYear(), today.getMonth(), 1)
    };
    this.options = {
      theme: 'default',
      range: 'tm', // The alias of item menu
      labels: ['Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab', 'Dom'],
      menu: [
          {alias: 'td', text: 'Hoy', operation: '0d'},
          {alias: 'tm', text: 'Este mes', operation: '0m'},
          {alias: 'lm', text: 'Mes pasado', operation: '-1m'},
          {alias: 'tw', text: 'Esta semana', operation: '0w'},
          {alias: 'lw', text: 'Semana pasada', operation: '-1w'},
          {alias: 'ty', text: 'Este año', operation: '0y'},
          {alias: 'ly', text: 'Año pasado', operation: '-1y'},
      ],
      dateFormat: 'DD/MM/YYYY',
      outputFormat: 'YYYY-MM-DD',
      outputType: "object",
      startOfWeek: 0
    };
    this.filtro = new Filtro;
    this.filtro.fechaInicio = moment().format('YYYY-MM-') + '01';
    this.filtro.fechaFin = moment().endOf('month').format('YYYY-MM-DD');
    this.filtro.estado = 'T';

    this.urlBase = environment.base_url;
    this.requisiciones = [];
    this.httpSubscription = this.requisicionesService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        this.requisiciones = data.value;
        this.total = data.total;
      }
    );
  }

  onEstadoChanged(event) {
    this.filtro.estado = this.estado;
    this.refreshData();
  }

  onRangeChanged(event) {
    if (this.fechaInicio !== event.from || this.fechaFin !== event.to) {
      this.fechaInicio = event.from;
      this.fechaFin = event.to;
      this.filtro.fechaInicio = event.from;
      this.filtro.fechaFin = event.to;
      this.refreshData();
    }
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

  refreshData() {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.requisiciones = [];
    this.httpSubscription = this.requisicionesService.fetchData(this.filtro, 1, this.pageSize).subscribe(
      data => {
        this.requisiciones = data.value;
        this.total = data.total;
        this.currentPage = 1;
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  onViewRequisicionClicked(index: number) {
    this.editIndex = index;
    this.modalRequisicion = this.requisiciones[index];
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onCloseClicked() {
    this.modalActions.emit({action:"modal", params:["close"]});
    this.modalRequisicion = null;
  }
}
