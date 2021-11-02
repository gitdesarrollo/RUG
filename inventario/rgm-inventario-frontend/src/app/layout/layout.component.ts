import { Component, OnInit, EventEmitter } from '@angular/core';
import { User } from '../shared/user.model';
import { Subscription } from 'rxjs/Subscription';
import { UsersService } from '../shared/users.service';
import { LoadingService } from '../shared/loading.service';
import { Router } from '@angular/router';
import { OpcionMenu } from '../shared/opcion-menu.model';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  public loading = false;
  public user: User;
  sidenavActions = new EventEmitter<any>();
  sidenavParams = [{edge:'left', closeOnClick:true}];
  loadingSubscription: Subscription;
  opciones: OpcionMenu[];

  constructor(private usersService: UsersService,
    private loadingService: LoadingService,
    private router: Router) { }

  ngOnInit() {
    this.initMenu();

    this.user = this.usersService.authenticatedUser;

    this.loadingSubscription = this.loadingService.loadingChanged.subscribe(
      (isLoading: boolean) => {
        this.loading = isLoading;
      }
    );
  }

  onLogout() {
    this.usersService.logout().subscribe(
      data => {
        this.closeSession();
      },
      err => {
        // aunque el token no sea valido se cierra la sesion en el cliente
        this.closeSession();
      }
    );
  }

  closeSession() {
    this.usersService.authenticatedUser = null;
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }

  public showSidenav(): void {
    this.sidenavActions.emit('SideNav');
  }

  public hideSidenav(): void {
    this.sidenavActions.emit('SideNav');
  }

  private initMenu() {
    // inicializar opciones
    const jsonOpciones = [{
    	etiqueta: 'Catalogos',
      roles: ['A','F' ,'I'],
      opciones: [{
      	etiqueta: 'Usuarios',
      	link: '/users'
    	},
    	{
      	etiqueta: 'Unidades de medida',
      	link: '/units'
    	},
    	{
      	etiqueta: 'Marcas',
      	link: '/brands'
    	},
    	{
      	etiqueta: 'Tipo de artículo',
      	link: '/types'
    	},
    	{
      	etiqueta: 'Proveedores',
      	link: '/suppliers'
    	},
    	{
      	etiqueta: 'Artículos',
      	link: '/articles'
    	}]
    },
    {
    	etiqueta: 'Productos',
      roles: ['A', 'F', 'I'],
    	opciones: [{
    		etiqueta: 'Ingreso',
    		link: '/ingresos'
    	},
    	{
    		etiqueta: 'Salida',
    		link: '/salidas'
    	}]
    },
    {
    	etiqueta: 'Requisiciones',
      roles: ['A', 'F', 'I', 'U'],
    	opciones: [{
    		etiqueta: 'Creacion',
    		link: '/requisiciones'
    	},
    	{
    		etiqueta: 'Pendientes de despacho',
    		link: '/despachos',
        roles: ['A', 'F', 'I']
    	},
    	{
    		etiqueta: 'Anulacion',
    		link: '/anulaciones',
        roles: ['A', 'F']
    	},
    	/*{
    		etiqueta: 'Consulta de requisiciones',
    		link: '/consulta-requisiciones',
        roles: ['A', 'F']
    	},*/
    	/*{
    		etiqueta: 'Consulta de despachos',
    		link: '/consulta-despachos',
        roles: ['A', 'F']
    	}*/]
    },
    {
    	etiqueta: 'Aprobaciones',
      roles: ['A', 'F'],
    	opciones: [{
    		etiqueta: 'Requisiciones',
    		link: '/aprobaciones'
    	},
    	{
    		etiqueta: 'Ingresos',
    		link: '/aprobaciones-ingreso'
    	},
    	{
    		etiqueta: 'Salidas',
    		link: '/aprobaciones-salida'
    	}]
    },
    {
    	etiqueta: 'Reportes',
      roles: ['A', 'F', 'I'],
    	opciones: [{
    		etiqueta: 'Inventario general',
    		link: '/reporte-inventario-general'
    	},
    	{
    		etiqueta: 'Requisiciones',
    		link: '/reporte-requisiciones'
    	},
    	/*{
    		etiqueta: 'Usuarios',
    		link: '/reporte-usuarios'
    	},
    	{
    		etiqueta: 'Proveedores',
    		link: '/reporte-proveedores'
    	},*/
    	{
    		etiqueta: 'Ingresos',
    		link: '/reporte-ingresos'
    	},
    	{
    		etiqueta: 'Salidas',
    		link: '/reporte-salidas'
    	},
    	{
    		etiqueta: 'Despachos',
    		link: '/reporte-despachos'
    	}]
    }];
    this.opciones = <OpcionMenu[]> jsonOpciones;
  }
}
