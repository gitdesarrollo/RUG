import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SwalComponent } from '@toverux/ngx-sweetalert2';
import { MaterializeAction, toast } from 'angular2-materialize';
import * as moment from 'moment';
import { Subscription } from 'rxjs';
import swal from 'sweetalert2';
import { Article } from '../shared/article.model';
import { ArticlesService } from '../shared/articles.service';
import { DetalleRequisicion } from '../shared/detalle-requisicion.model';
import { Filtro } from '../shared/filtro.model';
import { LoadingService } from '../shared/loading.service';
import { Requisicion } from '../shared/requisicion.model';
import { RequisicionesService } from '../shared/requisiciones.service';
import { ResponseRs } from '../shared/response.model';
import { UsersService } from '../shared/users.service';
declare var Materialize:any;


@Component({
  selector: 'app-requisiciones-edit',
  templateUrl: './requisiciones-edit.component.html',
  styleUrls: ['./requisiciones-edit.component.css']
})
export class RequisicionesEditComponent implements OnInit {
  public loading = false;
  
  

  @ViewChild('swalRequisicionNoExiste') private swalRequisicionNoExiste: SwalComponent;
  @ViewChild('f') detalleForm: NgForm;
  articles: Article[];
  detalleRequisicion: DetalleRequisicion[];
  modalDetalle: DetalleRequisicion;
  articlesSubscription: Subscription;
  @ViewChild('swalArticuloAgregado') private swalArticuloAgregado: SwalComponent;
  @ViewChild('swalDetalleObligatorios') private swalDetalleObligatorios: SwalComponent;
  @ViewChild('swalRequisicionObligatorios') private swalRequisicionObligatorios: SwalComponent;
  @ViewChild('swalNoDetalle') private swalNoDetalle: SwalComponent;
  @ViewChild('swalMaximoRequisicion') private swalMaximoRequisicion: SwalComponent;
  @ViewChild('swalCero') private swalCero: SwalComponent;
  codigoArticulo: string;
  descripcionArticulo: string;

  autocompleteInit = {
    onAutocomplete: (val) => {
      this.onArticuloSelected(val);
    },
    'property': 'descripcion'
  };
  autocompleteCodigoInit = {
    onAutocomplete: (val) => {
      this.onArticuloSelected(val);
    },
    'property': 'codigo'
  };
  httpSubscription: Subscription;
  requisicionesSubscription: Subscription;
  requisicionForm: FormGroup;
  urlRequisicionPdf: boolean;
  requsicionLoaded: boolean=false;

  modalActions = new EventEmitter<string|MaterializeAction>();
  articulos_eliminados: Array<number>;
  
  requisicion_id: number;

  constructor(private activatedRoute: ActivatedRoute ,
              private requisicionesService: RequisicionesService,
              private articlesService: ArticlesService, 
              private loadingService: LoadingService,
              private usersService: UsersService,
              private route:Router) { }
  

  ngOnInit() {
    this.articlesSubscription = this.articlesService.fetchData(null, null, null).subscribe(
      data => {
        this.articles = data.value;
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );

    this.articulos_eliminados = [];

    this.activatedRoute.params.subscribe(params=>{
      this.httpSubscription = this.requisicionesService.fetchDataById(params.id).subscribe(
        data => {

          //this.requisiciones = data.value;
          // this.total = data.total;
          // this.requisicionesService.addRequisiciones(this.requisiciones);
          if(data.value)  {  
            this.requisicion_id = params.id;        
            this.initForm(data.value);
            this.requsicionLoaded = true;
            
          }
          else
            this.swalRequisicionNoExiste.show();

        }
      );
      
    
    })

    

  }

  onArticuloSelected(articulo: Article) {
    this.modalDetalle.codigoArticulo = articulo.codigo;
    this.modalDetalle.articulo = articulo;
    this.codigoArticulo = articulo.codigo;
    this.descripcionArticulo = articulo.descripcion;
    Materialize.updateTextFields();
  }


  private initForm(data: Requisicion) {
    this.requisicionForm = new FormGroup({
      'correlativo': new FormControl(data.correlativo),
      'fecha': new FormControl( moment(data.fecha, 'YYYY-MM-DDTHH:mm:ss').format('DD/MM/YYYY'), Validators.required),
      'observaciones': new FormControl(data.observaciones)
    });
    this.detalleRequisicion = data.detalle;
  }
  onAddArticleClicked() {
    this.codigoArticulo = '';
    this.descripcionArticulo = '';
    this.modalDetalle = new DetalleRequisicion;
    this.modalActions.emit({action:"modal", params:["open"]});
  }
 
  onAddDetalleClicked() {
    if (!this.modalDetalle.codigoArticulo || !this.detalleForm.value.cantidad) {
      this.swalDetalleObligatorios.show();
      return;
    }
    if (this.detalleForm.value.cantidad > this.modalDetalle.articulo.existencia) {
      this.swalMaximoRequisicion.show();
      return;
    }
    if (this.detalleForm.value.cantidad == 0) {
      this.swalCero.show();
      return;
    }
    const codigoArticulo = this.modalDetalle.codigoArticulo;
    const encontrado = this.detalleRequisicion.find(function (obj) {
      return obj.codigoArticulo == codigoArticulo;
    })
    if (!encontrado) {
      this.modalDetalle.cantidad = this.detalleForm.value.cantidad;
      this.detalleRequisicion.push(this.modalDetalle);
      this.onCancelClicked();
    } else {
      this.swalArticuloAgregado.show();
    }
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal",params:['close']});
    this.detalleForm.reset();
  }

  onClearClicked() {
    this.detalleForm.reset();
    this.requisicionForm.reset();
    this.detalleRequisicion = [];
    this.urlRequisicionPdf = null;
  }

 
  onRemoveArticleClicked(index: number) {
    console.log(this.detalleRequisicion[index].detalleRequisionId);
    
    if (this.detalleRequisicion[index].detalleRequisionId)
      this.articulos_eliminados.push(this.detalleRequisicion[index].detalleRequisionId);

    this.detalleRequisicion.splice(index, 1);
    
  }


  onSubmit() {
    // validar campos obligatorios y detalle
    if (!this.requisicionForm.value.fecha || !this.requisicionForm.value.observaciones) {
      this.swalRequisicionObligatorios.show();
      return;
    }
    if (!this.detalleRequisicion || this.detalleRequisicion.length == 0) {
      this.swalNoDetalle.show();
      return;
    }
    console.log(this.articulos_eliminados);

    let requisicion: Requisicion = new Requisicion;
    requisicion.requisicionId = this.requisicion_id ;
    console.log(this.requisicionForm.value.fecha);
    
    requisicion.fecha = moment(this.requisicionForm.value.fecha, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss');
    requisicion.observaciones = this.requisicionForm.value.observaciones;
    requisicion.usuarioId = this.usersService.authenticatedUser.usuarioId;
    requisicion.detalle = this.detalleRequisicion;
    requisicion.detalle.forEach(o => o.articulo = null);
    requisicion.articulosEliminados=this.articulos_eliminados;
    this.requisicionesService.updateData(requisicion).subscribe(
      (response)=>{
        if (response.code==0)
        {
          Materialize.toast('Actualizacion realizada exitosamente');
          this.onClearClicked();
          this.route.navigate(["/consulta-requisiciones"]);
        }
        else
        {
          Materialize.toast('Actualizacion realizada sin exitos ' + response.reason);
        }
      }


    );
  }

  stopPropagation(event) {
    event.stopPropagation();
  }

}
