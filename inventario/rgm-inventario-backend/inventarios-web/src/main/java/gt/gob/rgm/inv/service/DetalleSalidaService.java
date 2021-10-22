package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.DetalleSalida;
import gt.gob.rgm.inv.util.ResponseRs;

public interface DetalleSalidaService {
	
	public ResponseRs listDetalleSalidas();
	public ResponseRs getDetalleSalida(Long id);
	public ResponseRs addDetalleSalida(DetalleSalida detalleSalida);
	public ResponseRs updateDetalleSalida(Long id, DetalleSalida detalleSalida);
	public ResponseRs deleteDetalleSalida(Long id);
}
