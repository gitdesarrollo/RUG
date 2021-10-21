import { Article } from "./article.model";

export class DetalleDespacho {
  detalleDespachoId: number;
  cantidad: number;
  codigoArticulo: string;
  despachoId: number;
  articulo: Article;

  constructor () {}
}
