/*
 * HomeServicePojoImpl.java        04/08/2010
 *
 * Copyright (c) 2009 Secretar�a de Econom�a
 * Alfonso Reyes No. 30 Col. Hip�dromo Condesa C.P. 06140,
 * Delegaci�n Cuauht�moc, M�xico, D.F.
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo de la
 * Secretar�a de Econom�a.
 *
 */
package mx.gob.se.rug.common.service.impl;

import mx.gob.economia.dgi.framework.base.service.AbstractBaseService;
import mx.gob.economia.dgi.framework.dao.exception.DaoException;
import mx.gob.economia.dgi.framework.exception.ExceptionMessage;
import mx.gob.economia.dgi.framework.security.user.dto.User;
import mx.gob.se.rug.common.dao.HomeDao;
import mx.gob.se.rug.common.exception.UserException;
import mx.gob.se.rug.common.service.HomeService;

/**
 * @author Alfonso Esquivel
 * 
 */
public class HomeServicePojoImpl extends AbstractBaseService implements
		HomeService {

	private HomeDao homeDao;

	public void setHomeDao(HomeDao homeDao) {
		this.homeDao = homeDao;
	}

	@Override
	public User getUser(String principal) throws UserException {
		try {
			return homeDao.getUser(principal);
		} catch (DaoException daoe) {
			throw new UserException(
					new ExceptionMessage(
							"Ocurrio un error al intentar consultar los datos del usuario."),
					daoe);
		}
	}

}
