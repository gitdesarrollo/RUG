import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Despacho } from "./despacho.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";
import { UsersService } from "./users.service";
import { Filtro } from "./filtro.model";

@Injectable()
export class DespachosService {
  despachos: Despacho[] = [];
  despachosChanged = new Subject<Despacho[]>();
  despachoCreado = new Subject<Despacho>();

  constructor(private http: HttpClient,
    private usersService: UsersService) {}

  getDespachos() {
    return this.despachos.slice();
  }

  addDespacho(despacho: Despacho) {
    this.saveData(despacho).subscribe(
      (response) => {
        let savedDespacho: Despacho = response.value;
        this.despachos.push(savedDespacho);
        this.despachoCreado.next(savedDespacho);
      },
      err => console.error(err)
    );
  }

  addDespachos(despachos: Despacho[]) {
    this.despachos.push(...despachos);
    this.despachosChanged.next(this.getDespachos());
  }

  updateDespacho(index: number, newDespacho: Despacho) {
    this.updateData(newDespacho).subscribe(
      (response) => {
        let updatedDespacho: Despacho = response.value;
        this.despachos.splice(index, 1);
        this.despachosChanged.next(this.getDespachos());
      }
    );
  }

  fetchData(filtro: Filtro, page: number, size: number) {
    let param = '';
    if (filtro) {
      param += '?' + filtro.getFiltro();
    }
    if (page) {
      param += (param ? '&' : '?') + 'page=' + page + '&size=' + size;
    }
    return this.http.get<ResponseRs>(environment.api_url + '/despachos' + param);
  }

  saveData(despacho: Despacho) {
    return this.http.post<ResponseRs>(environment.api_url + '/despachos', despacho);
  }

  updateData(despacho: Despacho) {
    return this.http.put<ResponseRs>(environment.api_url + '/despachos/' + despacho.despachoId, despacho);
  }

  downloadPdf(despachoId: number) {
    return this.http.get(environment.base_url + '/reporte-despacho?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&id=' + despachoId, { responseType: 'blob' });
  }
}
