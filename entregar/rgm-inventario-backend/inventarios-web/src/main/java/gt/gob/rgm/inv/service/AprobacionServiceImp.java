package gt.gob.rgm.inv.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.AprobacionRepository;
import gt.gob.rgm.inv.model.Aprobacion;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class AprobacionServiceImp implements AprobacionService {

	@Inject
	private AprobacionRepository aprobacionDao;
	
	@Override
	public ResponseRs listAprobaciones() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(aprobacionDao.findAllAprobaciones());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getAprobacion(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(aprobacionDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs addAprobacion(Aprobacion aprobacion) {
		ResponseRs response = new ResponseRs();
		
		try {
			aprobacionDao.save(aprobacion);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(aprobacion);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateAprobacion(Long id, Aprobacion aprobacion) {
		ResponseRs response = new ResponseRs();
		try {
			Aprobacion vAprobacion = aprobacionDao.findById(id);
			if(vAprobacion != null) {		
				if(aprobacion.getDocumento()!=null)
					aprobacion.setDocumento(aprobacion.getDocumento());
				
				if(aprobacion.getFecha()!=null)
					aprobacion.setFecha(aprobacion.getFecha());
				
				aprobacionDao.update(vAprobacion);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vAprobacion);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Aprobacion")
						.replace("%id%", aprobacion.getAprobacionId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	@Override
	public ResponseRs deleteAprobacion(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Aprobacion vAprobacion = aprobacionDao.findById(id);
			if(vAprobacion != null) {
				aprobacionDao.delete(vAprobacion);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Aprobacion")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	public AprobacionRepository getAprobacionDao() {
		return aprobacionDao;
	}

	public void setAprobacionDao(AprobacionRepository aprobacionDao) {
		this.aprobacionDao = aprobacionDao;
	}

}
