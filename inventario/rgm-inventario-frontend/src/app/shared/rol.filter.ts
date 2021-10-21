import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: 'rol'
})
export class RolPipe implements PipeTransform {
  transform(value: any, args?: any): any {
    switch (value) {
      case 'A':
        return 'Administrador';
      case 'F':
        return 'Financiero';
      case 'I':
        return 'Inventario';
      case 'U':
        return 'Usuario';
    }
  }
}
