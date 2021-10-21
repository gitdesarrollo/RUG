import { DetalleIngreso } from "./detalle-ingreso.model";
import { User } from "./user.model";

export class Ingreso {
  ingresoId: number;
  correlativo: string;
  estado: string;
  fecha: string;
  observaciones: string;
  referencia: string;
  tipoIngresoId: number;
  usuarioId: number;
  detalle: DetalleIngreso[];
  solicitante: User;
}
