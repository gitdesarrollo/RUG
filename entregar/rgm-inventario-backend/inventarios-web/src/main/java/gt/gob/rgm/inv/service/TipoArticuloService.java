package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.TipoArticulo;
import gt.gob.rgm.inv.util.ResponseRs;

public interface TipoArticuloService {
	
	public ResponseRs listTiposArticulo();
	public ResponseRs getTipoArticulo(Long id);
	public ResponseRs addTipoArticulo(TipoArticulo tipoArticulo);
	public ResponseRs updateTipoArticulo(Long id, TipoArticulo tipoArticulo);
	public ResponseRs deleteTipoArticulo(Long id);
}
