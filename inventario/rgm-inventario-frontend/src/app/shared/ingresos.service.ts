import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Ingreso } from "./ingreso.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";
import { UsersService } from "./users.service";
import { Filtro } from "./filtro.model";

@Injectable()
export class IngresosService {
  ingresos: Ingreso[] = [];
  ingresosChanged = new Subject<Ingreso[]>();
  ingresoCreado = new Subject<Ingreso>();
  ingresoAprobado = new Subject<number>();

  constructor(private http: HttpClient,
    private usersService: UsersService) {}

  getIngresos() {
    return this.ingresos.slice();
  }

  addIngreso(ingreso: Ingreso) {
    this.saveData(ingreso).subscribe(
      (response) => {
        let savedIngreso: Ingreso = response.value;
        this.ingresos.push(savedIngreso);
        this.ingresoCreado.next(savedIngreso);
      },
      err => console.error(err)
    );
  }

  addIngresos(ingresos: Ingreso[]) {
    this.ingresos.push(...ingresos);
    // this.ingresosChanged.next(this.getIngresos());
  }

  updateIngreso(index: number, newIngreso: Ingreso) {
    this.updateData(newIngreso).subscribe(
      (response) => {
        let updatedIngreso: Ingreso = response.value;
        this.ingresos[index] = updatedIngreso;
        this.ingresosChanged.next(this.getIngresos());
        if (updatedIngreso.estado == 'A') {
          this.ingresoAprobado.next(updatedIngreso.ingresoId);
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
    return this.http.get<ResponseRs>(environment.api_url + '/ingresos' + param);
  }

  saveData(ingreso: Ingreso) {
    return this.http.post<ResponseRs>(environment.api_url + '/ingresos', ingreso);
  }

  updateData(ingreso: Ingreso) {
    return this.http.put<ResponseRs>(environment.api_url + '/ingresos/' + ingreso.ingresoId, ingreso);
  }

  downloadPdf(ingresoId: number) {
    return this.http.get(environment.base_url + '/reporte-ingreso?tipoReporte=2&usuario=' + this.usersService.authenticatedUser.usuarioId + '&id=' + ingresoId, { responseType: 'blob' });
  }
}
