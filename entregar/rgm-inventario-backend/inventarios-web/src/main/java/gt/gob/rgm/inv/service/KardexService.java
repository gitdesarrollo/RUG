package gt.gob.rgm.inv.service;

import java.util.Map;

import gt.gob.rgm.inv.util.ResponseRs;

public interface KardexService {
	
	public ResponseRs listKardex(Map<String, Object> params, Integer page, Integer size);
	public ResponseRs getKardex(Long id);	
	public byte[] getReporteGeneralPdf(Map<String,Object> params);
	
}
