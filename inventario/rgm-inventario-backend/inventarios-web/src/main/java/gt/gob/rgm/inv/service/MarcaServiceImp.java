package gt.gob.rgm.inv.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.MarcaRepository;
import gt.gob.rgm.inv.model.Marca;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class MarcaServiceImp implements MarcaService {

	@Inject
	private MarcaRepository marcaDao;
	
	@Override
	public ResponseRs listMarcas(Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			List<Marca> marcas = marcaDao.findAllMarcas(page, size); 
			response.setValue(marcas);
			response.setTotal(marcaDao.countAllMarcas().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getMarca(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(marcaDao.findById(id.intValue()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addMarca(Marca marca) {
		ResponseRs response = new ResponseRs();
		
		try {
			marca.setEstado(MessagesInv.ACTIVO);
			marcaDao.save(marca);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(marca);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateMarca(Long id, Marca marca) {
		ResponseRs response = new ResponseRs();
		try {
			Marca vMarca = marcaDao.findById(id.intValue());
			if(vMarca != null) {
				if(marca.getNombre()!=null){
					vMarca.setNombre(marca.getNombre());
				}
				marcaDao.update(vMarca);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vMarca);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Marca")
						.replace("%id%", marca.getId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	@Override
	public ResponseRs deleteMarca(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Marca vMarca = marcaDao.findById(id.intValue());
			if(vMarca != null) {
				vMarca.setEstado(MessagesInv.INACTIVO);
				marcaDao.update(vMarca);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vMarca);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Marca")
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
	public MarcaRepository getMarcaDao() {
		return marcaDao;
	}

	public void setMarcaDao(MarcaRepository marcaDao) {
		this.marcaDao = marcaDao;
	}
}
