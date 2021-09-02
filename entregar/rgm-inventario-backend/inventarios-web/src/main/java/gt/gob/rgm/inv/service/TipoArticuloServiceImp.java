package gt.gob.rgm.inv.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.TipoArticuloRepository;
import gt.gob.rgm.inv.model.TipoArticulo;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class TipoArticuloServiceImp implements TipoArticuloService {

	@Inject
	private TipoArticuloRepository tipoArticuloDao;
	
	@Override
	public ResponseRs listTiposArticulo() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoArticuloDao.findAllTiposArticulo());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getTipoArticulo(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoArticuloDao.findById(id.intValue()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addTipoArticulo(TipoArticulo tipoArticulo) {
		ResponseRs response = new ResponseRs();
		
		try {
			tipoArticulo.setEstado(MessagesInv.ACTIVO);
			tipoArticuloDao.save(tipoArticulo);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(tipoArticulo);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateTipoArticulo(Long id, TipoArticulo tipoArticulo) {
		ResponseRs response = new ResponseRs();
		try {
			TipoArticulo vTipoArticulo = tipoArticuloDao.findById(id.intValue());
			if(vTipoArticulo != null) {
				
				if(tipoArticulo.getNombre()!=null)
					vTipoArticulo.setNombre(tipoArticulo.getNombre());
				
				tipoArticuloDao.update(vTipoArticulo);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vTipoArticulo);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "TipoArticulo")
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
	public ResponseRs deleteTipoArticulo(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			TipoArticulo vTipoArticulo = tipoArticuloDao.findById(id.intValue());
			if(vTipoArticulo != null) {
				vTipoArticulo.setEstado(MessagesInv.INACTIVO);
				tipoArticuloDao.update(vTipoArticulo);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vTipoArticulo);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "TipoArticulo")
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
	public TipoArticuloRepository getTipoArticuloDao() {
		return tipoArticuloDao;
	}

	public void setTipoArticuloDao(TipoArticuloRepository tipoArticuloDao) {
		this.tipoArticuloDao = tipoArticuloDao;
	}

}
