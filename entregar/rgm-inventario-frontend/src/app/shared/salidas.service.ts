import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Salida } from "./salida.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";
import { UsersService } from "./users.service";
import { Filtro } from "./filtro.model";

@Injectable()
export class SalidasService {
  salidas: Salida[] = [];
  salidasChanged = new Subject<Salida[]>();
  salidaCreada = new Subject<Salida>();
  salidaAprobada = new Subject<number>();

  constructor(private http: HttpClient,
    private usersService: UsersService) {}

  getSalidas() {
    return this.salidas.slice();
  }

  addSalida(salida: Salida) {
    this.saveData(salida).subscribe(
      (response) => {
        let savedSalida: Salida = response.value;
        this.salidas.push(savedSalida);
        this.salidaCreada.next(savedSalida);
      },
      err => console.error(err)
    );
  }

  addSalidas(salidas: Salida[]) {
    this.salidas.push(...salidas);
    // this.salidasChanged.next(this.getSalidas());
  }

  updateSalida(index: number, newSalida: Salida) {
    this.updateData(newSalida).subscribe(
      (response) => {
        let updatedSalida: Salida = response.value;
        this.salidas[index] = updatedSalida;
        this.salidasChanged.next(this.getSalidas());
        if (updatedSalida.estado == 'A') {
          this.salidaAprobada.next(updatedSalida.salidaId);
        }
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
    return this.http.get<ResponseRs>(environment.api_url + '/salidas' + param);
  }

  saveData(salida: Salida) {
    return this.http.post<ResponseRs>(environment.api_url + '/salidas', salida);
  }

  updateData(salida: Salida) {
    return this.http.put<ResponseRs>(environment.api_url + '/salidas/' + salida.salidaId, salida);
  }

  downloadPdf(salidaId: number) {
    return this.http.get(environment.base_url + '/reporte-salida?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&id=' + salidaId, { responseType: 'blob' });
  }
}
