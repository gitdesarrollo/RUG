import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import { NgxDateRangePickerOptions, NgxDateRangePickerComponent } from 'ngx-daterangepicker';
import * as moment from 'moment';
import { IngresosService } from '../shared/ingresos.service';
import { LoadingService } from '../shared/loading.service';
import { Ingreso } from '../shared/ingreso.model';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import { environment } from '../../environments/environment';
import { UsersService } from '../shared/users.service';
import { User } from '../shared/user.model';
import { Filtro } from '../shared/filtro.model';

@Component({
  selector: 'app-reporte-ingresos',
  templateUrl: './reporte-ingresos.component.html',
  styleUrls: ['./reporte-ingresos.component.css']
})
export class ReporteIngresosComponent implements OnInit {
  public loading = false;
  options: NgxDateRangePickerOptions;
  @Input() dateRange: NgxDateRangePickerComponent;
  rangoFechas: string | any;
  fechaInicio: string;
  fechaFin: string;
  ingresos: Ingreso[];
  httpSubscription: Subscription;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalIngreso: Ingreso;
  urlReporteIngresos: string;
  urlBase: string;
  authenticatedUser: User;
  filtro: Filtro;

  constructor(private ingresosService: IngresosService,
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
    this.fechaInicio = moment().format('YYYY-MM-') + '01';
    this.fechaFin = moment().endOf('month').format('YYYY-MM-DD');
    this.urlReporteIngresos = environment.base_url + '/reporte-ingreso?usuario=' + this.usersService.authenticatedUser.usuarioId + '&estado=A&fechaInicio=' + this.fechaInicio + '&fechaFin=' + this.fechaFin;
    this.urlBase = environment.base_url;

    this.ingresos = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.filtro = new Filtro;
    this.filtro.estado = 'T';
    this.filtro.fechaInicio = moment().format('YYYY-MM-') + '01';
    this.filtro.fechaFin = moment().endOf('month').format('YYYY-MM-DD');
    this.httpSubscription = this.ingresosService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        this.ingresos = data.value;
        this.total = data.total;
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  onRangeChanged(event) {
    if (this.fechaInicio !== event.from || this.fechaFin !== event.to) {
      this.fechaInicio = event.from;
      this.fechaFin = event.to;
      this.filtro.fechaInicio = event.from;
      this.filtro.fechaFin = event.to;
      this.refreshData();
      this.urlReporteIngresos = environment.base_url + '/reporte-ingreso?usuario=' + this.usersService.authenticatedUser.usuarioId + '&estado=A&fechaInicio=' + this.fechaInicio + '&fechaFin=' + this.fechaFin;
    }
  }

  onViewIngresoClicked(index: number) {
    this.modalIngreso = this.ingresos[index];
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  refreshData() {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.ingresos = [];
    this.httpSubscription = this.ingresosService.fetchData(this.filtro, 1, this.pageSize).subscribe(
      data => {
        this.ingresos = data.value;
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

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.ingresos = [];
    this.httpSubscription = this.ingresosService.fetchData(this.filtro, page, this.pageSize).subscribe(
      data => {
        this.ingresos = data.value;
        this.currentPage = page;
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  onCloseClicked() {
    this.modalActions.emit({action:"modal", params:["close"]});
    this.modalIngreso = null;
  }
}
