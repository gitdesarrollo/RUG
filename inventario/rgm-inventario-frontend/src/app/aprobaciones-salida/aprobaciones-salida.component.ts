import { Component, OnInit, EventEmitter } from '@angular/core';
import { Salida } from '../shared/salida.model';
import { SalidasService } from '../shared/salidas.service';
import { LoadingService } from '../shared/loading.service';
import { Subscription } from 'rxjs/Subscription';
import { MaterializeAction } from 'angular2-materialize';
import { Filtro } from '../shared/filtro.model';
import { environment } from '../../environments/environment';
import { UsersService } from '../shared/users.service';

@Component({
  selector: 'app-aprobaciones-salida',
  templateUrl: './aprobaciones-salida.component.html',
  styleUrls: ['./aprobaciones-salida.component.css']
})
export class AprobacionesSalidaComponent implements OnInit {
  public loading = false;
  salidas: Salida[];
  httpSubscription: Subscription;
  localSubscription: Subscription;
  approvedSubscription: Subscription;
  pageSize: number = 10;
  currentPage: number = 1;
  total: number;
  editIndex: number;
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalSalida: Salida;
  filtro: Filtro;
  urlSalidaPdf: string;

  constructor(private salidasService: SalidasService,
    private usersService: UsersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.salidas = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.filtro = new Filtro;
    this.filtro.estado = 'S';
    this.httpSubscription = this.salidasService.fetchData(this.filtro, this.currentPage, this.pageSize).subscribe(
      data => {
        this.salidas = data.value;
        this.total = data.total;
        this.salidasService.addSalidas(this.salidas);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.localSubscription = this.salidasService.salidasChanged.subscribe(
      (salidas: Salida[]) => {
        // this.salidas = salidas;
        this.modalActions.emit({action:"modal",params:['close']});
        this.editIndex = -1;
        this.onPageChange(1);
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
    this.approvedSubscription = this.salidasService.salidaAprobada.subscribe(
      (salidaId: number) => {
        this.urlSalidaPdf = environment.base_url + '/reporte-salida?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&id=' + salidaId;
        // llamar a servlet para imprimir formato de salida
        this.salidasService.downloadPdf(salidaId).subscribe(
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

  onViewSalidaClicked(index: number) {
    this.urlSalidaPdf = null;
    this.editIndex = index;
    this.modalSalida = this.salidas[index];
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onApprovedClicked() {
    // cambiar el estado del salida
    this.modalSalida.estado = 'A';
    this.modalSalida.solicitante = null;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.salidasService.updateSalida(this.editIndex, this.modalSalida);
  }

  onRejectedClicked() {
    this.urlSalidaPdf = null;
    // cambiar el estado del salida
    this.modalSalida.estado = 'R';
    this.modalSalida.solicitante = null;
    this.salidasService.updateSalida(this.editIndex, this.modalSalida);
  }

  onPageChange(page: number) {
    this.urlSalidaPdf = null;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.salidas = [];
    this.httpSubscription = this.salidasService.fetchData(this.filtro, page, this.pageSize).subscribe(
      data => {
        this.salidas = data.value;
        this.currentPage = page;
        this.salidasService.addSalidas(this.salidas);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );
  }
}
