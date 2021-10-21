package gt.gob.rgm.inv.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.SerieRepository;
import gt.gob.rgm.inv.model.Serie;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class SerieServiceImp implements SerieService {

	@Inject
	private SerieRepository serieDao;
	
	@Override
	public ResponseRs listSeries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseRs getSerie(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseRs addSerie(Serie serie) {
		// TODO Auto-generated method stub
		ResponseRs response = new ResponseRs();
		
		try {
			//serie.setEstado(MessagesInv.ACTIVO);
			serieDao.save(serie);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(serie);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateSerie(Serie Serie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseRs deleteSerie(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public SerieRepository getSerieDao() {
		return serieDao;
	}

	public void setSerieDao(SerieRepository serieDao) {
		this.serieDao = serieDao;
	}

}
