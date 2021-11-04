import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Requisicion } from "./requisicion.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";
import { Filtro } from "./filtro.model";
import { UsersService } from "./users.service";

@Injectable()
export class RequisicionesService {
  requisiciones: Requisicion[] = [];
  requisicionesChanged = new Subject<Requisicion[]>();
  requisicionCreada = new Subject<Requisicion>();

  constructor(private http: HttpClient,
    private usersService: UsersService) {}

  getRequisiciones() {
    return this.requisiciones.slice();
  }

  addRequisicion(requisicion: Requisicion) {
    this.saveData(requisicion).subscribe(
      (response) => {
        let savedRequisicion: Requisicion = response.value;
        this.requisiciones.push(savedRequisicion);
        this.requisicionCreada.next(savedRequisicion);
      },
      err => console.error(err)
    );
  }

  addRequisiciones(requisiciones: Requisicion[]) {
    this.requisiciones.push(...requisiciones);
    this.requisicionesChanged.next(this.getRequisiciones());
  }

  updateRequisicion(index: number, newRequisicion: Requisicion) {
    this.updateData(newRequisicion).subscribe(
      (response) => {
        let updatedRequisicion: Requisicion = response.value;
        this.requisiciones.splice(index, 1);
        this.requisicionesChanged.next(this.getRequisiciones());
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
    return this.http.get<ResponseRs>(environment.api_url + '/requisiciones' + param);
  }

  fetchDataById(id: number) {
    let param = '';    
    return this.http.get<ResponseRs>(environment.api_url + '/requisiciones/' + id);
  }


  saveData(requisicion: Requisicion) {
    return this.http.post<ResponseRs>(environment.api_url + '/requisiciones', requisicion);
  }

  updateData(requisicion: Requisicion) {
    return this.http.put<ResponseRs>(environment.api_url + '/requisiciones/' + requisicion.requisicionId, requisicion);
  }

  downloadPdf(requisicionId: number) {
    return this.http.get(environment.base_url + '/reporte-requisiciones?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&idRequisicion=' + requisicionId, { responseType: 'blob' });
  }
}
