package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.EstadoOperacion;
import gt.gob.rgm.inv.util.ResponseRs;

public interface EstadoOperacionService {
	
	public ResponseRs listEstadoOperaciones();
	public ResponseRs getEstadoOperacion(String id);
	public ResponseRs addEstadoOperacion(EstadoOperacion EstadoOperacion);
	public ResponseRs updateEstadoOperacion(EstadoOperacion EstadoOperacion);
	public ResponseRs deleteEstadoOperacion(String id);
}
