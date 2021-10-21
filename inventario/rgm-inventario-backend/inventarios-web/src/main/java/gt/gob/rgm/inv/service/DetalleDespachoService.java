package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.DetalleDespacho;
import gt.gob.rgm.inv.util.ResponseRs;

public interface DetalleDespachoService {
	
	public ResponseRs listDetalleDespachos();
	public ResponseRs getDetalleDespacho(Long id);
	public ResponseRs addDetalleDespacho(DetalleDespacho detalleDespacho);
	public ResponseRs updateDetalleDespacho(Long id, DetalleDespacho detalleDespacho);
	public ResponseRs deleteDetalleDespacho(Long id);
}
