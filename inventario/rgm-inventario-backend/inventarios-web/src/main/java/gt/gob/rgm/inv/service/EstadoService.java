package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.Estado;
import gt.gob.rgm.inv.util.ResponseRs;

public interface EstadoService {
	
	public ResponseRs listEstados();
	public ResponseRs getEstado(String id);
	public ResponseRs addEstado(Estado Estado);
	public ResponseRs updateEstado(Estado Estado);
	public ResponseRs deleteEstado(String id);
}