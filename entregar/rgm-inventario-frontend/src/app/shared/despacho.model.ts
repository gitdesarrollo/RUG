import { DetalleDespacho } from "./detalle-despacho.model";

export class Despacho {
  despachoId: number;
  contador: number;
  correlativo: string;
  fecha: string;
  estado: string;
  observaciones: string;
  requisicionId: number;
  detalle: DetalleDespacho[];
}
