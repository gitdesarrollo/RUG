import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: 'estadoUsuario'
})
export class EstadoUsuarioPipe implements PipeTransform {
  transform(value: any, args?: any): any {
    switch (value) {
      case 'A':
        return 'Activo';
      case 'I':
        return 'Inactivo';
    }
  }
}
