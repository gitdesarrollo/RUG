import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { DetalleIngreso } from "./detalle-ingreso.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";

@Injectable()
export class DetalleIngresosService {
  constructor(private http: HttpClient) {}

  updateDetalleIngreso(index: number, newDetalleIngreso: DetalleIngreso) {
    this.updateData(newDetalleIngreso).subscribe(
      (response) => {
        let updatedDetalleIngreso: DetalleIngreso = response.value;
        // notificar el cambio
      }
    );
  }

  fetchData(codigo: string) {
    return this.http.get<ResponseRs>(environment.api_url + '/detalle-ingresos?codigo=' + codigo);
  }

  updateData(detalle: DetalleIngreso) {
    return this.http.put<ResponseRs>(environment.api_url + '/detalle-ingresos/' + detalle.detalleIngresoId, detalle);
  }
}
