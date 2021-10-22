import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import { Article } from '../shared/article.model';
import { Subscription } from 'rxjs/Subscription';
import * as moment from 'moment';
import { ArticlesService } from '../shared/articles.service';
import { LoadingService } from '../shared/loading.service';
import { Type } from '../shared/type.model';
import { TypesService } from '../shared/types.service';
import { environment } from '../../environments/environment';
import { Inventory } from '../shared/inventory.model';
import { NgxDateRangePickerOptions, NgxDateRangePickerComponent } from 'ngx-daterangepicker';
import { Filtro } from '../shared/filtro.model';
import { UsersService } from '../shared/users.service';
import { User } from '../shared/user.model';

@Component({
  selector: 'app-reporte-inventario-general',
  templateUrl: './reporte-inventario-general.component.html',
  styleUrls: ['./reporte-inventario-general.component.css']
})
export class ReporteInventarioGeneralComponent implements OnInit {
  public loading = false;
  articles: Inventory[];
  types: Type[];
  httpSubscription: Subscription;
  typesSubscription: Subscription;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  filTipo: number;
  filCodigo: string;
  filDescripcion: string;
  filTexto: string;
  @ViewChild('filTipoInput') filTipoInput: ElementRef;
  @ViewChild('filCodigoInput') filCodigoInput: ElementRef;
  @ViewChild('filDescripcionInput') filDescripcionInput: ElementRef;
  @ViewChild('filTextoInput') filTextoInput: ElementRef;
  reporteUrl: string;
  urlBase: string;
  paramsInventarioUrl: string;
  paramsMinimoUrl: string;
  options: NgxDateRangePickerOptions;
  @Input() dateRange: NgxDateRangePickerComponent;
  rangoFechas: string | any;
  fechaInicio: string;
  fechaFin: string;
  filtro: Filtro;
  authenticatedUser: User;

  constructor(private articlesService: ArticlesService,
    private typesService: TypesService,
    private usersService: UsersService,
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
    this.filtro = new Filtro;
    this.filtro.campoId = 'tipoArticuloId';
    this.filtro.fechaInicio = moment().format('YYYY-MM-') + '01';
    this.filtro.fechaFin = moment().endOf('month').format('YYYY-MM-DD');

    this.authenticatedUser = this.usersService.authenticatedUser;
    this.reporteUrl = environment.base_url + '/reporte-inventario';
    this.paramsInventarioUrl = '?tipoReporte=1' + (this.filTipo ? '&tipoArticulo=' + this.filTipo : '') + '&usuario=' + this.authenticatedUser.usuarioId;
    this.paramsMinimoUrl = '?tipoReporte=2' + (this.filTipo ? '&tipoArticulo=' + this.filTipo : '') + '&usuario=' + this.authenticatedUser.usuarioId;
    this.urlBase = environment.base_url;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.typesService.getTypes();

    this.articlesService.articles = [];
    this.httpSubscription = this.articlesService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        this.articles = data.value;
        this.total = data.total;
        // this.articlesService.addArticles(this.articles);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.typesSubscription = this.typesService.fetchData().subscribe(
      data => {
        this.types = data.value;
      }
    );
  }

  filTextoChanged(filTexto: string) {
    this.filtro.texto = filTexto;
    this.refreshData();
  }

  onFilTexto() {
    let event = new Event('blur', {});
    this.filTextoInput.nativeElement.dispatchEvent(event);
  }

  filTipoChanged(filTipo: number) {
    // this.filTipo = filTipo;
    this.filtro.id = filTipo;
    this.paramsInventarioUrl = '?tipoReporte=1' + (this.filTipo ? '&tipoArticulo=' + this.filTipo : '') + '&usuario=' + this.usersService.authenticatedUser.usuarioId;
    this.paramsMinimoUrl = '?tipoReporte=2' + (this.filTipo ? '&tipoArticulo=' + this.filTipo : '') + '&usuario=' + this.usersService.authenticatedUser.usuarioId;
    this.refreshData();
  }

  refreshData() {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.articles = [];
    this.httpSubscription = this.articlesService.fetchData(this.filtro, 1, this.pageSize).subscribe(
      data => {
        this.articles = data.value;
        this.total = data.total;
        this.currentPage = 1;
        // this.articlesService.addArticles(this.articles);
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
    }
  }

  onPageChange(page: number) {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.articles = [];
    this.httpSubscription = this.articlesService.fetchData(this.filtro, page, this.pageSize).subscribe(
      data => {
        this.articles = data.value;
        this.currentPage = page;
        // this.articlesService.addArticles(this.articles);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }
}
