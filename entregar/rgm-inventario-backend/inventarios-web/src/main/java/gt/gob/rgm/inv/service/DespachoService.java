package gt.gob.rgm.inv.service;

import java.util.Map;

import gt.gob.rgm.inv.model.Despacho;
import gt.gob.rgm.inv.util.ResponseRs;

public interface DespachoService {
	
	public ResponseRs listDespachos(Map<String, Object> filter, Integer page, Integer size);
	public ResponseRs getDespacho(Long id);
	public ResponseRs addDespacho(Despacho despacho);
	public ResponseRs updateDespacho(Long id, Despacho despacho);
	public ResponseRs deleteDespacho(Long id);
	public byte[] getReporteGeneralPdf(Map<String,Object> params) ;
	public byte[] getReporteDespachoPdf(Map<String,Object> params);
}