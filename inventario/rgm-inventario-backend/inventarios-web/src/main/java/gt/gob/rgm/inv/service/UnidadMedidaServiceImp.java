package gt.gob.rgm.inv.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.UnidadMedidaRepository;
import gt.gob.rgm.inv.model.UnidadMedida;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class UnidadMedidaServiceImp implements UnidadMedidaService {

	@Inject
	private UnidadMedidaRepository UnidadMedidaDao;
	
	@Override
	public ResponseRs listUnidadesMedida(Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			List<UnidadMedida> unidades = UnidadMedidaDao.findAllUnidadesMedida(page, size); 			
			response.setValue(unidades);
			response.setTotal(UnidadMedidaDao.countAllUnidadesMedida().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getUnidadMedida(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(UnidadMedidaDao.findById(id.intValue()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addUnidadMedida(UnidadMedida unidadMedida) {
		ResponseRs response = new ResponseRs();
		
		try {
			unidadMedida.setEstado(MessagesInv.ACTIVO);
			UnidadMedidaDao.save(unidadMedida);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(unidadMedida);
		} catch (Exception e) {
			//e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateUnidadMedida(Long id, UnidadMedida unidadMedida) {
		ResponseRs response = new ResponseRs();
		try {
			UnidadMedida vUnidadMedida = UnidadMedidaDao.findById(id.intValue());
			if(vUnidadMedida != null) {	
				
				if(unidadMedida.getNombre() != null) {
					vUnidadMedida.setNombre(unidadMedida.getNombre());
				}
				
				UnidadMedidaDao.update(vUnidadMedida);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vUnidadMedida);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "UnidadMedida")
						.replace("%id%", unidadMedida.getId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	@Override
	public ResponseRs deleteUnidadMedida(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			UnidadMedida vUnidadMedida = UnidadMedidaDao.findById(id.intValue());
			if(vUnidadMedida != null) {
				vUnidadMedida.setEstado(MessagesInv.INACTIVO);
				UnidadMedidaDao.update(vUnidadMedida);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "UnidadMedida")
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
	public UnidadMedidaRepository getUnidadMedidaDao() {
		return UnidadMedidaDao;
	}

	public void setUnidadMedidaDao(UnidadMedidaRepository UnidadMedidaDao) {
		this.UnidadMedidaDao = UnidadMedidaDao;
	}

}
