package gt.gob.rgm.inv.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.ArticuloRepository;
import gt.gob.rgm.inv.dao.DetalleDespachoRepository;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.DetalleDespacho;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class DetalleDespachoServiceImp implements DetalleDespachoService {
	
	@Inject
	private DetalleDespachoRepository detalleDespachoDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Override
	public ResponseRs listDetalleDespachos() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleDespachoDao.findAllDetalleDespachos());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getDetalleDespacho(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleDespachoDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs addDetalleDespacho(DetalleDespacho detalleDespacho) {
		ResponseRs response = new ResponseRs();
		
		try {
			/** disminuye stock **/
			Map<String, Object> filtro = new HashMap<String, Object>();
			filtro.put("codigo", detalleDespacho.getCodigoArticulo());
			Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);			
			articulo.setStock(articulo.getStock() - detalleDespacho.getCantidad().longValue());
			
			if(articulo.getStock()<0) {
				response.setCode(1L);
				response.setReason("ERROR LA CANTIDAD INGRESADA NO SE PUEDE DISMINUIR");
				return response;
			}
			
			detalleDespachoDao.save(detalleDespacho);
			articuloDao.update(articulo);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleDespacho);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateDetalleDespacho(Long id, DetalleDespacho detalleDespacho) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleDespacho vDetalleDespacho = detalleDespachoDao.findById(id);
			if(vDetalleDespacho != null) {	
				if(detalleDespacho.getCantidad()!=null)
					vDetalleDespacho.setCantidad(detalleDespacho.getCantidad());
				
				detalleDespachoDao.update(vDetalleDespacho);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vDetalleDespacho);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Despacho")
						.replace("%id%", detalleDespacho.getDetalleDespachoId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	@Override
	public ResponseRs deleteDetalleDespacho(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleDespacho vDetalleDespacho = detalleDespachoDao.findById(id);
			if(vDetalleDespacho != null) {	
				/** aumenta stock **/
				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("codigo", vDetalleDespacho.getCodigoArticulo());
				Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);			
				articulo.setStock(articulo.getStock() + vDetalleDespacho.getCantidad().longValue());
				
				detalleDespachoDao.update(vDetalleDespacho);
				articuloDao.update(articulo);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vDetalleDespacho);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Despacho")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	public DetalleDespachoRepository getDetalleDespachoDao() {
		return detalleDespachoDao;
	}

	public void setDetalleDespachoDao(DetalleDespachoRepository detalleDespachoDao) {
		this.detalleDespachoDao = detalleDespachoDao;
	}

	public ArticuloRepository getArticuloDao() {
		return articuloDao;
	}

	public void setArticuloDao(ArticuloRepository articuloDao) {
		this.articuloDao = articuloDao;
	}

}
