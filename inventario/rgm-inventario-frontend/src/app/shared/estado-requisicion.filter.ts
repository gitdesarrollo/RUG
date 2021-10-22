import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: 'estadoRequisicion'
})
export class EstadoRequisicionPipe implements PipeTransform {
  transform(value: any, args?: any): any {
    switch (value) {
      case 'S':
        return 'Pendiente de aprobación';
      case 'A':
        return 'Aprobada';
      case 'C':
        return 'Rechazada';
      case 'D':
        return 'Despachada';
    }
  }
}
