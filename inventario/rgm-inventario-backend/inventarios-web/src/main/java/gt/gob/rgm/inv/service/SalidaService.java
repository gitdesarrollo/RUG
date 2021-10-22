package gt.gob.rgm.inv.service;

import java.util.Map;

import gt.gob.rgm.inv.model.Salida;
import gt.gob.rgm.inv.util.ResponseRs;

public interface SalidaService {
	
	public ResponseRs listSalidas(Map<String, Object> filter, Integer page, Integer size);
	public ResponseRs getSalida(Long id);
	public ResponseRs addSalida(Salida salida);
	public ResponseRs updateSalida(Long id, Salida salida);
	public ResponseRs deleteSalida(Long id);
	public byte[] getReporteGeneralPdf(Map<String,Object> params);
	public byte[] getReporteSalidaPdf(Map<String,Object> params);
}