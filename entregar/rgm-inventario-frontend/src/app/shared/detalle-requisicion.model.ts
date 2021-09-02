import { Article } from "./article.model";
import { DespachoPerecedero } from "./despacho-perecedero.model";

export class DetalleRequisicion {
  detalleRequisicionId: number;
  cantidad: number;
  cantidadAprobada: number;
  codigoArticulo: string;
  requisicionId: number;
  articulo: Article;
  despachoPerecedero: DespachoPerecedero[];

  constructor () {}
}
