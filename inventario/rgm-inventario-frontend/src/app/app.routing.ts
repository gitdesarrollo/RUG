import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { LayoutComponent } from "./layout/layout.component";
import { HomeComponent } from "./home/home.component";
import { UsersComponent } from "./users/users.component";
import { UnitsComponent } from "./units/units.component";
import { BrandsComponent } from "./brands/brands.component";
import { SuppliersComponent } from "./suppliers/suppliers.component";
import { ArticlesComponent } from "./articles/articles.component";
import { TypesComponent } from "./types/types.component";
import { IngresosComponent } from "./ingresos/ingresos.component";
import { SalidasComponent } from "./salidas/salidas.component";
import { RequisicionesComponent } from "./requisiciones/requisiciones.component";
import { AprobacionesComponent } from "./aprobaciones/aprobaciones.component";
import { DespachosComponent } from "./despachos/despachos.component";
import { ReporteInventarioGeneralComponent } from "./reporte-inventario-general/reporte-inventario-general.component";
import { ReporteRequisicionesComponent } from "./reporte-requisiciones/reporte-requisiciones.component";
import { ConsultaRequisicionesComponent } from "./consulta-requisiciones/consulta-requisiciones.component";
import { ConsultaDespachosComponent } from "./consulta-despachos/consulta-despachos.component";
import { AnulacionesComponent } from "./anulaciones/anulaciones.component";
import { ReporteKardexComponent } from "./reporte-kardex/reporte-kardex.component";
import { ReporteProveedoresComponent } from "./reporte-proveedores/reporte-proveedores.component";
import { ReporteIngresosComponent } from "./reporte-ingresos/reporte-ingresos.component";
import { ReporteSalidasComponent } from "./reporte-salidas/reporte-salidas.component";
import { ReporteDespachosComponent } from "./reporte-despachos/reporte-despachos.component";
import { ReporteMinimoExistenciasComponent } from "./reporte-minimo-existencias/reporte-minimo-existencias.component";
import { ReporteUsuariosComponent } from "./reporte-usuarios/reporte-usuarios.component";
import { AprobacionesIngresoComponent } from "./aprobaciones-ingreso/aprobaciones-ingreso.component";
import { AprobacionesSalidaComponent } from "./aprobaciones-salida/aprobaciones-salida.component";
import { ChangePasswordComponent } from "./change-password/change-password.component";
import { RequisicionesEditComponent } from "./requisiciones-edit/requisiciones-edit.component";
import { TipoIngresoComponent } from "./tipo-ingreso/tipo-ingreso.component";
import { TipoSalidaComponent } from "./tipo-salida/tipo-salida.component";
export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    component: LayoutComponent,
    // canActivateChild: [AuthGuard],
    children: [
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'units',
        component: UnitsComponent
      },
      {
        path: 'brands',
        component: BrandsComponent
      },
      {
        path: 'suppliers',
        component: SuppliersComponent
      },
      {
        path: 'articles',
        component: ArticlesComponent
      },
      {
        path: 'types',
        component: TypesComponent
      },
      {
        path: 'ingresos',
        component: IngresosComponent
      },
      {
        path: 'salidas',
        component: SalidasComponent
      },
      {
        path: 'requisiciones',
        component: RequisicionesComponent
      },
      {
        path: 'aprobaciones',
        component: AprobacionesComponent
      },
      {
        path: 'despachos',
        component: DespachosComponent
      },
      {
        path: 'reporte-inventario-general',
        component: ReporteInventarioGeneralComponent
      },
      {
        path: 'reporte-requisiciones',
        component: ReporteRequisicionesComponent
      },
      {
        path: 'reporte-kardex',
        component: ReporteKardexComponent
      },
      {
        path: 'reporte-proveedores',
        component: ReporteProveedoresComponent
      },
      {
        path: 'reporte-usuarios',
        component: ReporteUsuariosComponent
      },
      {
        path: 'reporte-ingresos',
        component: ReporteIngresosComponent
      },
      {
        path: 'reporte-salidas',
        component: ReporteSalidasComponent
      },
      {
        path: 'reporte-despachos',
        component: ReporteDespachosComponent
      },
      {
        path: 'consulta-requisiciones',
        component: ConsultaRequisicionesComponent
      },
      {
        path: 'reporte-minimo-existencias',
        component: ReporteMinimoExistenciasComponent
      },
      {
        path: 'consulta-despachos',
        component: ConsultaDespachosComponent
      },
      {
        path: 'anulaciones',
        component: AnulacionesComponent
      },
      {
        path: 'aprobaciones-ingreso',
        component: AprobacionesIngresoComponent
      },
      {
        path: 'aprobaciones-salida',
        component: AprobacionesSalidaComponent
      },
      {
        path: 'change-password',
        component: ChangePasswordComponent
      },
      {
        path: 'requisicion-edit/:id',
        component: RequisicionesEditComponent
      },
      {
        path: 'tipo-ingreso',
        component: TipoIngresoComponent
      },
      {
        path: 'tipo-salida',
        component: TipoSalidaComponent
      },

    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, {useHash: true}) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {

}
