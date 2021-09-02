package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.TipoIngreso;
import gt.gob.rgm.inv.util.ResponseRs;

public interface TipoIngresoService {
	
	public ResponseRs listTipoIngresos();
	public ResponseRs getTipoIngreso(Long id);
	public ResponseRs addTipoIngreso(TipoIngreso TipoIngreso);
	public ResponseRs updateTipoIngreso(Long id, TipoIngreso TipoIngreso);
	public ResponseRs deleteTipoIngreso(Long id);
}
