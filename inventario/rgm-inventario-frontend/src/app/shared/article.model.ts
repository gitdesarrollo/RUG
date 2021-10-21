import { Brand } from "./brand.model";
import { Supplier } from "./supplier.model";
import { Type } from "./type.model";
import { Unit } from "./unit.model";

export class Article {
  codigo: string;
	codigoBarras: string;
	descripcion: string;
	fechaVencimiento: string;
	minimoExistencia: number;
	perecedero: boolean;
	valor: number;
	correlativo: number;
	stock: number;
  existencia: number;
	estado: string;
	marcaId: number;
  marca: Brand;
	proveedorId: number;
  proveedor: Supplier;
	tipoArticuloId: number;
  tipoArticulo: Type;
	unidadMedidaId: number;
  unidadMedida: Unit;
}
