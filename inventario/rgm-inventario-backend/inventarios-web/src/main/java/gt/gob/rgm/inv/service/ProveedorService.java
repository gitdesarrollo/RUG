package gt.gob.rgm.inv.service;

import java.util.Map;

import gt.gob.rgm.inv.model.Proveedor;
import gt.gob.rgm.inv.util.ResponseRs;

public interface ProveedorService {
	
	public ResponseRs listProveedores();
	public ResponseRs getProveedor(Long id);
	public ResponseRs addProveedor(Proveedor proveedor);
	public ResponseRs updateProveedor(Long id, Proveedor proveedor);
	public ResponseRs deleteProveedor(Long id);
	public byte[] getReporteGeneralPdf(Map<String,Object> params);
}
