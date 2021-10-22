import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import { environment } from '../../environments/environment';
import { UsersService } from '../shared/users.service';
import { Subscription } from 'rxjs/Subscription';
import { NgxDateRangePickerOptions, NgxDateRangePickerComponent } from 'ngx-daterangepicker';
import * as moment from 'moment';
import { Despacho } from '../shared/despacho.model';
import { DespachosService } from '../shared/despachos.service';
import { LoadingService } from '../shared/loading.service';
import { Filtro } from '../shared/filtro.model';
import { MaterializeAction } from 'angular2-materialize';
import { User } from '../shared/user.model';

@Component({
  selector: 'app-reporte-despachos',
  templateUrl: './reporte-despachos.component.html',
  styleUrls: ['./reporte-despachos.component.css']
})
export class ReporteDespachosComponent implements OnInit {
  public loading = false;
  options: NgxDateRangePickerOptions;
  @Input() dateRange: NgxDateRangePickerComponent;
  rangoFechas: string | any;
  fechaInicio: string;
  fechaFin: string;
  despachos: Despacho[];
  httpSubscription: Subscription;
  urlReporteDespachos: string;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  filtro: Filtro;
  editIndex: number;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalDespacho: Despacho;
  authenticatedUser: User;
  urlBase: string;

  constructor(private despachosService: DespachosService,
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

    this.urlReporteDespachos = environment.base_url + '/reporte-despacho?usuario=' + this.usersService.authenticatedUser.usuarioId + '&estado=A&fechaInicio=' + this.filtro.fechaInicio + '&fechaFin=' + this.filtro.fechaFin;
    this.urlBase = environment.base_url;
    this.despachos = [];
    this.httpSubscription = this.despachosService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        
        this.despachos = data.value;
        this.total = data.total;
      }
    );
  }

  onRangeChanged(event) {
    
    if (this.filtro.fechaInicio !== event.from || this.filtro.fechaFin !== event.to) {
      this.filtro.fechaInicio = event.from;
      this.filtro.fechaFin = event.to;
      this.refreshData();

      this.urlReporteDespachos = environment.base_url + '/reporte-despacho?usuario=' + this.usersService.authenticatedUser.usuarioId + '&estado=A&fechaInicio=' + this.filtro.fechaInicio + '&fechaFin=' + this.filtro.fechaFin;
    }
  }

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.despachos = [];
    this.httpSubscription = this.despachosService.fetchData(this.filtro, page, this.pageSize).subscribe(
      data => {
        this.despachos = data.value;
        this.currentPage = page;
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  refreshData() {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.despachos = [];
    this.httpSubscription = this.despachosService.fetchData(this.filtro, 1, this.pageSize).subscribe(
      data => {
        console.log("data", data)
        this.despachos = data.value;
        this.total = data.total;
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }

  onViewDetalleClicked(index: number) {
    this.editIndex = index;
    this.modalDespacho = this.despachos[index];
    console.log(this.modalDespacho);
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onCloseClicked() {
    this.modalActions.emit({action:"modal", params:["close"]});
    this.modalDespacho = null;
  }
}
