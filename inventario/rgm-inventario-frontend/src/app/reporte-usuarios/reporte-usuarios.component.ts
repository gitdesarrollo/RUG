import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { UsersService } from '../shared/users.service';

@Component({
  selector: 'app-reporte-usuarios',
  templateUrl: './reporte-usuarios.component.html',
  styleUrls: ['./reporte-usuarios.component.css']
})
export class ReporteUsuariosComponent implements OnInit {
  public loading = false;
  urlReporteUsuarios: string;

  constructor(private usersService: UsersService) { }

  ngOnInit() {
    this.urlReporteUsuarios = environment.base_url + '/reporte-usuario?usuario=' + this.usersService.authenticatedUser.usuarioId;
  }

}
