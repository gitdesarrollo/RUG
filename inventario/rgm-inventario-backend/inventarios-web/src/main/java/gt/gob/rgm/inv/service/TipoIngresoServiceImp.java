package gt.gob.rgm.inv.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.TipoIngresoRepository;
import gt.gob.rgm.inv.model.TipoIngreso;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class TipoIngresoServiceImp implements TipoIngresoService {

	@Inject
	private TipoIngresoRepository tipoIngresoDao;
	
	@Override
	public ResponseRs listTipoIngresos() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoIngresoDao.findAllTipoIngresos());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getTipoIngreso(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoIngresoDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addTipoIngreso(TipoIngreso tipoIngreso) {
		ResponseRs response = new ResponseRs();
		
		try {
			tipoIngreso.setEstado(MessagesInv.ACTIVO);
			tipoIngresoDao.save(tipoIngreso);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoIngreso);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateTipoIngreso(Long id, TipoIngreso tipoIngreso) {
		ResponseRs response = new ResponseRs();
		try {
			TipoIngreso vTipoIngreso = tipoIngresoDao.findById(id);
			if(vTipoIngreso != null) {
				
				if(tipoIngreso.getNombre()!=null)
					vTipoIngreso.setNombre(tipoIngreso.getNombre());
				
				tipoIngresoDao.update(vTipoIngreso);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vTipoIngreso);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "TipoIngreso")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	@Override
	public ResponseRs deleteTipoIngreso(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			TipoIngreso vTipoIngreso = tipoIngresoDao.findById(id);
			if(vTipoIngreso != null) {
				vTipoIngreso.setEstado(MessagesInv.INACTIVO);
				tipoIngresoDao.update(vTipoIngreso);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vTipoIngreso);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "TipoIngreso")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	/** getters and setters **/
	public TipoIngresoRepository getTipoIngresoDao() {
		return tipoIngresoDao;
	}

	public void setTipoArticuloDao(TipoIngresoRepository tipoIngresoDao) {
		this.tipoIngresoDao = tipoIngresoDao;
	}


}
