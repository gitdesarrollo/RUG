import { DetalleRequisicion } from "./detalle-requisicion.model";
import { User } from "./user.model";

export class Requisicion {
  requisicionId: number;
  correlativo: string;
  comentario: string;
  estado: string;
  fecha: string;
  observaciones: string;
  usuarioId: number;
  detalle: DetalleRequisicion[];
  solicitante: User;
}
