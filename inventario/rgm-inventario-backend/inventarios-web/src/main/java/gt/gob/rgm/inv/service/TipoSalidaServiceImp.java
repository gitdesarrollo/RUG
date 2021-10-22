package gt.gob.rgm.inv.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.TipoSalidaRepository;
import gt.gob.rgm.inv.model.TipoSalida;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class TipoSalidaServiceImp implements TipoSalidaService {

	@Inject
	private TipoSalidaRepository tipoSalidaDao;
	
	@Override
	public ResponseRs listTipoSalidas() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoSalidaDao.findAllTipoSalidas());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getTipoSalida(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoSalidaDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addTipoSalida(TipoSalida tipoSalida) {
		ResponseRs response = new ResponseRs();
		
		try {
			tipoSalida.setEstado(MessagesInv.ACTIVO);
			tipoSalidaDao.save(tipoSalida);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoSalida);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateTipoSalida(Long id, TipoSalida tipoSalida) {
		ResponseRs response = new ResponseRs();
		try {
			TipoSalida vTipoSalida = tipoSalidaDao.findById(id);
			if(vTipoSalida != null) {
				
				if(tipoSalida.getNombre()!=null)
					vTipoSalida.setNombre(tipoSalida.getNombre());
				
				tipoSalidaDao.update(vTipoSalida);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vTipoSalida);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "TipoSalida")
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
	public ResponseRs deleteTipoSalida(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			TipoSalida vTipoSalida = tipoSalidaDao.findById(id);
			if(vTipoSalida != null) {
				vTipoSalida.setEstado(MessagesInv.INACTIVO);
				tipoSalidaDao.update(vTipoSalida);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vTipoSalida);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "TipoSalida")
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
	public TipoSalidaRepository getTipoSalidaDao() {
		return tipoSalidaDao;
	}

	public void setTipoArticuloDao(TipoSalidaRepository tipoSalidaDao) {
		this.tipoSalidaDao = tipoSalidaDao;
	}
}
