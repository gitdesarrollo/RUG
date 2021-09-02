package gt.gob.rgm.inv.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.ParametroConfRepository;
import gt.gob.rgm.inv.model.ParametroConf;

@Stateless
public class ParametroConfServiceImp implements ParametroConfService {

	@Inject
	private ParametroConfRepository parametroDao;

	@Override
	public ParametroConf getParam(String key) {
		return parametroDao.findByKey(key);
	}

}
