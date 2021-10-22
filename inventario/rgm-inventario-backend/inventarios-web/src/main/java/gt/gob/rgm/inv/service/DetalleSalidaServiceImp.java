package gt.gob.rgm.inv.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.ArticuloRepository;
import gt.gob.rgm.inv.dao.DetalleSalidaRepository;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.DetalleSalida;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class DetalleSalidaServiceImp implements DetalleSalidaService {

	@Inject
	private DetalleSalidaRepository detalleSalidaDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Override
	public ResponseRs listDetalleSalidas() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleSalidaDao.findAllDetalleSalidas());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getDetalleSalida(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleSalidaDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs addDetalleSalida(DetalleSalida detalleSalida) {
		ResponseRs response = new ResponseRs();
		
		try {
			/** disminuye stock **/
			Map<String, Object> filtro = new HashMap<String, Object>();
			filtro.put("codigo", detalleSalida.getCodigoArticulo());
			Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);			
			articulo.setStock(articulo.getStock() - detalleSalida.getCantidad().longValue());
			
			if(articulo.getStock()<0) {
				response.setCode(1L);
				response.setReason("ERROR LA CANTIDAD INGRESADA NO SE PUEDE DISMINUIR");
				return response;
			}
			
			detalleSalidaDao.save(detalleSalida);
			articuloDao.update(articulo);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleSalida);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateDetalleSalida(Long id, DetalleSalida detalleSalida) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleSalida vDetalleSalida = detalleSalidaDao.findById(id);
			if(vDetalleSalida != null) {						
				detalleSalidaDao.update(vDetalleSalida);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vDetalleSalida);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Salida")
						.replace("%id%", detalleSalida.getDetalleSalidaId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	@Override
	public ResponseRs deleteDetalleSalida(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleSalida vDetalleSalida = detalleSalidaDao.findById(id);
			if(vDetalleSalida != null) {	
				/** aumenta stock **/
				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("codigo", vDetalleSalida.getCodigoArticulo());
				Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);			
				articulo.setStock(articulo.getStock() + vDetalleSalida.getCantidad().longValue());
				
				detalleSalidaDao.delete(vDetalleSalida);
				articuloDao.update(articulo);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vDetalleSalida);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Salida")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	public DetalleSalidaRepository getDetalleSalidaDao() {
		return detalleSalidaDao;
	}

	public void setDetalleSalidaDao(DetalleSalidaRepository detalleSalidaDao) {
		this.detalleSalidaDao = detalleSalidaDao;
	}

	public ArticuloRepository getArticuloDao() {
		return articuloDao;
	}

	public void setArticuloDao(ArticuloRepository articuloDao) {
		this.articuloDao = articuloDao;
	}

}
