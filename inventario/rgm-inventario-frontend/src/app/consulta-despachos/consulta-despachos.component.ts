import { Component, OnInit, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { NgxDateRangePickerOptions, NgxDateRangePickerComponent } from 'ngx-daterangepicker';
import * as moment from 'moment';
import { Despacho } from '../shared/despacho.model';
import { DespachosService } from '../shared/despachos.service';
import { LoadingService } from '../shared/loading.service';

@Component({
  selector: 'app-consulta-despachos',
  templateUrl: './consulta-despachos.component.html',
  styleUrls: ['./consulta-despachos.component.css']
})
export class ConsultaDespachosComponent implements OnInit {
  public loading = false;
  options: NgxDateRangePickerOptions;
  @Input() dateRange: NgxDateRangePickerComponent;
  rangoFechas: string | any;
  fechaInicio: string;
  fechaFin: string;
  despachos: Despacho[];
  httpSubscription: Subscription;

  constructor(private despachosService: DespachosService,
    private loadingService: LoadingService) { }

  ngOnInit() {
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

    this.despachos = [];
    /*this.httpSubscription = this.despachosService.fetchData().subscribe(
      data => {
        this.despachos = data.value;
      }
    );*/
  }

  onRangeChanged(event) {
    if (this.fechaInicio !== event.from || this.fechaFin !== event.to) {
      this.fechaInicio = event.from;
      this.fechaFin = event.to;
      // this.refreshData();
    }
  }

}
