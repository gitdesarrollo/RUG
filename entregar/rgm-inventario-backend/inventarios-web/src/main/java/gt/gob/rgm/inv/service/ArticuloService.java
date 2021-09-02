package gt.gob.rgm.inv.service;

import java.util.Map;

import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.util.ResponseRs;

public interface ArticuloService {
	
	public ResponseRs listArticulos(Map<String, Object> filter, Integer page, Integer size);
	public ResponseRs getArticulo(String id);
	public ResponseRs addArticulo(Articulo articulo);
	public ResponseRs updateArticulo(String id, Articulo articulo);
	public ResponseRs deleteArticulo(String id);
	public byte[] getInventarioGeneralPdf(Map<String, Object> params);
	public byte[] getReporteMinimos(Map<String,Object> params);
}
