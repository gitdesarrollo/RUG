import { Article } from "./article.model";

export class DetalleIngreso {
  detalleIngresoId: number;
  cantidad: number;
  stock: number;
  codigoArticulo: string;
  fechaVencimiento: string;
  ingresoId: number;
  precio: number;
  articulo: Article;
}
