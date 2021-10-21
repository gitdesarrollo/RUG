import { DetalleSalida } from "./detalle-salida.model";
import { User } from "./user.model";

export class Salida {
  salidaId: number;
  correlativo: string;
  estado: string;
  fecha: string;
  observaciones: string;
  tipoSalidaId: number;
  usuarioId: number;
  detalle: DetalleSalida[];
  solicitante: User;
}
