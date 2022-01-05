import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app.routing';

import { MaterializeModule } from "angular2-materialize";
import { SweetAlert2Module } from '@toverux/ngx-sweetalert2';
import { LoadingModule, ANIMATION_TYPES } from 'ngx-loading';
import { NgxDateRangePickerModule } from 'ngx-daterangepicker';
import { NgxPaginationModule } from 'ngx-pagination';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { UsersService } from './shared/users.service';
import { LoadingService } from './shared/loading.service';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './home/home.component';
import { UnitsComponent } from './units/units.component';
import { BrandsComponent } from './brands/brands.component';
import { SuppliersComponent } from './suppliers/suppliers.component';
import { ArticlesComponent } from './articles/articles.component';
import { UnitsService } from './shared/units.service';
import { BrandsService } from './shared/brands.service';
import { SuppliersService } from './shared/suppliers.service';
import { TypesComponent } from './types/types.component';
import { TypesService } from './shared/types.service';
import { ArticlesService } from './shared/articles.service';
import { IngresosComponent } from './ingresos/ingresos.component';
import { IngresosService } from './shared/ingresos.service';
import { TiposIngresoService } from './shared/tipos-ingreso.service';
import { SalidasComponent } from './salidas/salidas.component';
import { TiposSalidaService } from './shared/tipos-salida.service';
import { SalidasService } from './shared/salidas.service';
import { RequisicionesComponent } from './requisiciones/requisiciones.component';
import { RequisicionesService } from './shared/requisiciones.service';
import { AprobacionesComponent } from './aprobaciones/aprobaciones.component';
import { DespachosComponent } from './despachos/despachos.component';
import { ReporteInventarioGeneralComponent } from './reporte-inventario-general/reporte-inventario-general.component';
import { ReporteRequisicionesComponent } from './reporte-requisiciones/reporte-requisiciones.component';
import { DespachosService } from './shared/despachos.service';
import { AutocompleteDirective } from './shared/autocomplete.directive';
import { ConsultaRequisicionesComponent } from './consulta-requisiciones/consulta-requisiciones.component';
import { ConsultaDespachosComponent } from './consulta-despachos/consulta-despachos.component';
import { EstadoRequisicionPipe } from './shared/estado-requisicion.filter';
import { AnulacionesComponent } from './anulaciones/anulaciones.component';
import { ReporteKardexComponent } from './reporte-kardex/reporte-kardex.component';
import { ReporteProveedoresComponent } from './reporte-proveedores/reporte-proveedores.component';
import { ReporteIngresosComponent } from './reporte-ingresos/reporte-ingresos.component';
import { ReporteSalidasComponent } from './reporte-salidas/reporte-salidas.component';
import { ReporteDespachosComponent } from './reporte-despachos/reporte-despachos.component';
import { ReporteMinimoExistenciasComponent } from './reporte-minimo-existencias/reporte-minimo-existencias.component';
import { ReporteUsuariosComponent } from './reporte-usuarios/reporte-usuarios.component';
import { DetalleIngresosService } from './shared/detalle-ingresos.service';
import { AprobacionesIngresoComponent } from './aprobaciones-ingreso/aprobaciones-ingreso.component';
import { AprobacionesSalidaComponent } from './aprobaciones-salida/aprobaciones-salida.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { EstadoUsuarioPipe } from './shared/estado-usuario.filter';
import { RolPipe } from './shared/rol.filter';
import { RequisicionesEditComponent } from './requisiciones-edit/requisiciones-edit.component';
import { TipoIngresoComponent } from './tipo-ingreso/tipo-ingreso.component';
import { TipoSalidaComponent } from './tipo-salida/tipo-salida.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LayoutComponent,
    UsersComponent,
    HomeComponent,
    UnitsComponent,
    BrandsComponent,
    SuppliersComponent,
    ArticlesComponent,
    TypesComponent,
    IngresosComponent,
    SalidasComponent,
    RequisicionesComponent,
    AprobacionesComponent,
    DespachosComponent,
    ReporteInventarioGeneralComponent,
    ReporteRequisicionesComponent,
    AutocompleteDirective,
    ConsultaRequisicionesComponent,
    ConsultaDespachosComponent,
    EstadoRequisicionPipe,
    EstadoUsuarioPipe,
    RolPipe,
    AnulacionesComponent,
    ReporteKardexComponent,
    ReporteProveedoresComponent,
    ReporteIngresosComponent,
    ReporteSalidasComponent,
    ReporteDespachosComponent,
    ReporteMinimoExistenciasComponent,
    ReporteUsuariosComponent,
    AprobacionesIngresoComponent,
    AprobacionesSalidaComponent,
    ChangePasswordComponent,
    RequisicionesEditComponent,    
    TipoIngresoComponent, TipoSalidaComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterializeModule,
    SweetAlert2Module.forRoot({
      buttonsStyling: false,
      customClass: 'modal-content',
      confirmButtonClass: 'btn waves-effect waves-light indigo',
      cancelButtonClass: 'btn waves-effect waves-light red darken-4 swal-2'
    }),
    LoadingModule.forRoot({
      animationType: ANIMATION_TYPES.threeBounce,
      backdropBackgroundColour: 'rgba(0,0,0,0.3)',
      backdropBorderRadius: '0',
      primaryColour: '#0D47A1',
      secondaryColour: '#1E88E5',
      tertiaryColour: '#1565C0'
    }),
    NgxDateRangePickerModule,
    NgxPaginationModule
  ],
  providers: [
    UsersService,
    UnitsService,
    BrandsService,
    SuppliersService,
    TypesService,
    ArticlesService,
    IngresosService,
    TiposIngresoService,
    SalidasService,
    TiposSalidaService,
    RequisicionesService,
    DespachosService,
    DetalleIngresosService,
    LoadingService,
    EstadoRequisicionPipe,
    EstadoUsuarioPipe,
    RolPipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
