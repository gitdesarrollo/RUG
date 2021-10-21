package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.DetalleIngreso;
import gt.gob.rgm.inv.util.ResponseRs;

public interface DetalleIngresoService {
	
	public ResponseRs listDetalleIngresos();
	public ResponseRs getDetalleIngreso(Long id);
	public ResponseRs getDetalleIngresoByCodigo(String codigo);
	public ResponseRs addDetalleIngreso(DetalleIngreso detalleIngreso);
	public ResponseRs updateDetalleIngreso(Long id, DetalleIngreso detalleIngreso);
	public ResponseRs deleteDetalleIngreso(Long id);
}