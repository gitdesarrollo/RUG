import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { UsersService } from '../shared/users.service';

@Component({
  selector: 'app-reporte-proveedores',
  templateUrl: './reporte-proveedores.component.html',
  styleUrls: ['./reporte-proveedores.component.css']
})
export class ReporteProveedoresComponent implements OnInit {
  public loading = false;
  urlReporteProveedores: string;

  constructor(private usersService: UsersService) { }

  ngOnInit() {
    this.urlReporteProveedores = environment.base_url + '/reporte-proveedor?usuario=' + this.usersService.authenticatedUser.usuarioId;
  }

}
