package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.UnidadMedida;
import gt.gob.rgm.inv.util.ResponseRs;

public interface UnidadMedidaService {
	
	public ResponseRs listUnidadesMedida(Integer page, Integer size);
	public ResponseRs getUnidadMedida(Long id);
	public ResponseRs addUnidadMedida(UnidadMedida unidadMedida);
	public ResponseRs updateUnidadMedida(Long id, UnidadMedida unidadMedida);
	public ResponseRs deleteUnidadMedida(Long id);
}
