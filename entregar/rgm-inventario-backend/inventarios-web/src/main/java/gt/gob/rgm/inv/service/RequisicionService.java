package gt.gob.rgm.inv.service;

import java.util.Map;

import gt.gob.rgm.inv.model.Requisicion;
import gt.gob.rgm.inv.util.ResponseRs;

public interface RequisicionService {
	
	public ResponseRs listRequisiciones(Map<String, Object> params, Integer page, Integer size);
	public ResponseRs getRequisicion(Long id);
	public ResponseRs addRequisicion(Requisicion requisicion);
	public ResponseRs updateRequisicion(Long id, Requisicion requisicion);
	public ResponseRs deleteRequisicion(Long id);
	public byte[] getReporteGeneralPdf(Map<String,Object> params);
	public byte[] getReporteRequisicionPdf(Map<String,Object> params);
}
