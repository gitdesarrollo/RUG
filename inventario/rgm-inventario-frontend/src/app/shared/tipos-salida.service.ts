import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { TipoSalida } from "./tipo-salida.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";

@Injectable()
export class TiposSalidaService {
  tipos: TipoSalida[] = [];
  tiposChanged = new Subject<TipoSalida[]>();

  constructor(private http: HttpClient) {}

  getTipoSalidas() {
    return this.tipos.slice();
  }

  addTipoSalida(tipo: TipoSalida) {
    this.saveData(tipo).subscribe(
      (response) => {
        let savedTipoSalida: TipoSalida = response.value;
        this.tipos.push(savedTipoSalida);
        this.tiposChanged.next(this.getTipoSalidas());
      },
      err => console.error(err)
    );
  }

  addTipoSalidas(tipos: TipoSalida[]) {
    this.tipos.push(...tipos);
    this.tiposChanged.next(this.getTipoSalidas());
  }

  updateTipoSalida(index: number, newTipoSalida: TipoSalida) {
    this.updateData(newTipoSalida).subscribe(
      (response) => {
        let updatedTipoSalida: TipoSalida = response.value;
        this.tipos[index] = updatedTipoSalida;
        this.tiposChanged.next(this.getTipoSalidas());
      }
    );
  }

  fetchData() {
    return this.http.get<ResponseRs>(environment.api_url + '/tipos-salidas');
  }

  saveData(tipo: TipoSalida) {
    return this.http.post<ResponseRs>(environment.api_url + '/tipos-salidas', tipo);
  }

  updateData(tipo: TipoSalida) {
    return this.http.put<ResponseRs>(environment.api_url + '/tipos-salidas/' + tipo.tipoSalidaId, tipo);
  }
}
