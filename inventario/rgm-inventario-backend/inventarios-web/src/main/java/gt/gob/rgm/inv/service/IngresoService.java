package gt.gob.rgm.inv.service;

import java.util.Map;

import gt.gob.rgm.inv.model.Ingreso;
import gt.gob.rgm.inv.util.ResponseRs;

public interface IngresoService {
	
	public ResponseRs listIngresos(Map<String, Object> filter, Integer page, Integer size);
	public ResponseRs getIngreso(Long id);
	public ResponseRs addIngreso(Ingreso ingreso);
	public ResponseRs updateIngreso(Long id, Ingreso ingreso);
	public ResponseRs deleteIngreso(Long id);
	public byte[] getReporteGeneralPdf(Map<String,Object> params);
	public byte[] getReporteIngresoPdf(Map<String,Object> params);
}
