package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.Aprobacion;
import gt.gob.rgm.inv.util.ResponseRs;

public interface AprobacionService {
	
	public ResponseRs listAprobaciones();
	public ResponseRs getAprobacion(Long id);
	public ResponseRs addAprobacion(Aprobacion aprobacion);
	public ResponseRs updateAprobacion(Long id, Aprobacion aprobacion);
	public ResponseRs deleteAprobacion(Long id);
}
