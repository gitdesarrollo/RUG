package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.DetalleRequisicion;
import gt.gob.rgm.inv.util.ResponseRs;

public interface DetalleRequisicionService {
	
	public ResponseRs listDetalleRequisiciones();
	public ResponseRs getDetalleRequisicion(Long id);
	public ResponseRs addDetalleRequisicion(DetalleRequisicion detalleRequisicion);
	public ResponseRs updateDetalleRequisicion(Long id, DetalleRequisicion detalleRequisicion);
	public ResponseRs deleteDetalleRequisicion(Long id);
}
