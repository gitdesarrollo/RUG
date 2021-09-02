package gt.gob.rgm.inv.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.DetalleRequisicionRepository;
import gt.gob.rgm.inv.model.DetalleRequisicion;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class DetalleRequisicionServiceImp implements DetalleRequisicionService {

	@Inject
	private DetalleRequisicionRepository detalleRequisicionDao;
	
	@Override
	public ResponseRs listDetalleRequisiciones() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleRequisicionDao.findAllDetalleRequisiciones());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getDetalleRequisicion(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(detalleRequisicionDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs addDetalleRequisicion(DetalleRequisicion detalleRequisicion) {
		ResponseRs response = new ResponseRs();
		
		try {
			detalleRequisicionDao.save(detalleRequisicion);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateDetalleRequisicion(Long id, DetalleRequisicion detalleRequisicion) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleRequisicion vDetalleRequisicion = detalleRequisicionDao.findById(id);
			if(vDetalleRequisicion != null) {			
				if(detalleRequisicion.getCantidad()!=null)
					vDetalleRequisicion.setCantidad(detalleRequisicion.getCantidad());
				
				detalleRequisicionDao.update(vDetalleRequisicion);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Requisicion")
						.replace("%id%", detalleRequisicion.getDetalleRequisionId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	@Override
	public ResponseRs deleteDetalleRequisicion(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			DetalleRequisicion vDetalleRequisicion = detalleRequisicionDao.findById(id);
			if(vDetalleRequisicion != null) {				
				detalleRequisicionDao.delete(vDetalleRequisicion);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Detalle Requisicion")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	public DetalleRequisicionRepository getDetalleRequisicionDao() {
		return detalleRequisicionDao;
	}

	public void setDetalleRequisicionDao(DetalleRequisicionRepository detalleRequisicionDao) {
		this.detalleRequisicionDao = detalleRequisicionDao;
	}

}
