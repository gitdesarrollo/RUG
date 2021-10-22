import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { TipoIngreso } from "./tipo-ingreso.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";

@Injectable()
export class TiposIngresoService {
  tipos: TipoIngreso[] = [];
  tiposChanged = new Subject<TipoIngreso[]>();

  constructor(private http: HttpClient) {}

  getTipoIngresos() {
    return this.tipos.slice();
  }

  addTipoIngreso(tipo: TipoIngreso) {
    this.saveData(tipo).subscribe(
      (response) => {
        let savedTipoIngreso: TipoIngreso = response.value;
        this.tipos.push(savedTipoIngreso);
        this.tiposChanged.next(this.getTipoIngresos());
      },
      err => console.error(err)
    );
  }

  addTipoIngresos(tipos: TipoIngreso[]) {
    this.tipos.push(...tipos);
    this.tiposChanged.next(this.getTipoIngresos());
  }

  updateTipoIngreso(index: number, newTipoIngreso: TipoIngreso) {
    this.updateData(newTipoIngreso).subscribe(
      (response) => {
        let updatedTipoIngreso: TipoIngreso = response.value;
        this.tipos[index] = updatedTipoIngreso;
        this.tiposChanged.next(this.getTipoIngresos());
      }
    );
  }

  fetchData() {
    return this.http.get<ResponseRs>(environment.api_url + '/tipos-ingresos');
  }

  saveData(tipo: TipoIngreso) {
    return this.http.post<ResponseRs>(environment.api_url + '/tipos-ingresos', tipo);
  }

  updateData(tipo: TipoIngreso) {
    return this.http.put<ResponseRs>(environment.api_url + '/tipos-ingresos/' + tipo.tipoIngresoId, tipo);
  }
}
