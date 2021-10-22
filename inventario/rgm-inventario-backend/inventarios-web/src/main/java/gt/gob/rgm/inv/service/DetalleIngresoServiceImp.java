package gt.gob.rgm.inv.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.ArticuloRepository;
import gt.gob.rgm.inv.dao.DetalleIngresoRepository;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.DetalleIngreso;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class DetalleIngresoServiceImp implements DetalleIngresoService {

	@Inject
	private DetalleIngresoRepository detalleIngresoDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Override
	public ResponseRs listDetalleIngresos() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleIngresoDao.findAllDetalleIngresos());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getDetalleIngreso(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleIngresoDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs getDetalleIngresoByCodigo(String codigo) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleIngresoDao.findByCodigo(codigo));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs addDetalleIngreso(DetalleIngreso detalleIngreso) {
		ResponseRs response = new ResponseRs();
		
		try {
			detalleIngresoDao.save(detalleIngreso);
			/** aumenta stock **/
			Map<String, Object> filtro = new HashMap<String, Object>();
			filtro.put("codigo", detalleIngreso.getCodigoArticulo());
			Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);
			
			articulo.setStock(articulo.getStock() + detalleIngreso.getCantidad().longValue());
			articuloDao.update(articulo);
			
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleIngreso);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateDetalleIngreso(Long id, DetalleIngreso detalleIngreso) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleIngreso vDetalleIngreso = detalleIngresoDao.findById(id);
			if(vDetalleIngreso != null) {		
				
				if(detalleIngreso.getFechaVencimiento()!=null)
					vDetalleIngreso.setFechaVencimiento(detalleIngreso.getFechaVencimiento());
				
				if(detalleIngreso.getPrecio()!=null)
					vDetalleIngreso.setPrecio(detalleIngreso.getPrecio());
				
				detalleIngresoDao.update(vDetalleIngreso);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vDetalleIngreso);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Ingreso")
						.replace("%id%", detalleIngreso.getDetalleIngresoId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	@Override
	public ResponseRs deleteDetalleIngreso(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleIngreso vDetalleIngreso = detalleIngresoDao.findById(id);
			if(vDetalleIngreso != null) {								
				/** disminuye stock **/
				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("codigo", vDetalleIngreso.getCodigoArticulo());
				Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);
				
				articulo.setStock(articulo.getStock() - vDetalleIngreso.getCantidad().longValue());
				if(articulo.getStock()<0) {
					response.setCode(1L);
					response.setReason("ERROR LA CANTIDAD NO SE PUEDE DISMINUIR");
					return response;
				}
				detalleIngresoDao.delete(vDetalleIngreso);
				articuloDao.update(articulo);
				
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Ingreso")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	public DetalleIngresoRepository getDetalleIngresoDao() {
		return detalleIngresoDao;
	}

	public void setDetalleIngresoDao(DetalleIngresoRepository detalleIngresoDao) {
		this.detalleIngresoDao = detalleIngresoDao;
	}

	public ArticuloRepository getArticuloDao() {
		return articuloDao;
	}

	public void setArticuloDao(ArticuloRepository articuloDao) {
		this.articuloDao = articuloDao;
	}

}
