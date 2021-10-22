package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.Marca;
import gt.gob.rgm.inv.util.ResponseRs;

public interface MarcaService {

	public ResponseRs listMarcas(Integer page, Integer size);
	public ResponseRs getMarca(Long id);
	public ResponseRs addMarca(Marca marca);
	public ResponseRs updateMarca(Long id, Marca marca);
	public ResponseRs deleteMarca(Long id);
}
