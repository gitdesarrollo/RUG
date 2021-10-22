package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.TipoSalida;
import gt.gob.rgm.inv.util.ResponseRs;

public interface TipoSalidaService {
	
	public ResponseRs listTipoSalidas();
	public ResponseRs getTipoSalida(Long id);
	public ResponseRs addTipoSalida(TipoSalida TipoSalida);
	public ResponseRs updateTipoSalida(Long id, TipoSalida TipoSalida);
	public ResponseRs deleteTipoSalida(Long id);
}
