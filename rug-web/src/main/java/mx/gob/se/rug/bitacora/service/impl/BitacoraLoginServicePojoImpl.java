/*
 * BitacoraLoginService.java        10/08/2009
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

package mx.gob.se.rug.bitacora.service.impl;

import mx.gob.economia.dgi.framework.base.service.AbstractBaseService;
import mx.gob.economia.dgi.framework.dao.exception.DaoException;
import mx.gob.economia.dgi.framework.exception.ExceptionMessage;
import mx.gob.se.rug.bitacora.dao.BitacoraLoginDao;
import mx.gob.se.rug.bitacora.dto.BitacoraLogin;
import mx.gob.se.rug.bitacora.exception.UsuarioNoBitacoradoException;
import mx.gob.se.rug.bitacora.service.BitacoraLoginService;

public class BitacoraLoginServicePojoImpl extends AbstractBaseService implements
		BitacoraLoginService {

	private BitacoraLoginDao bitacoraLoginDao;

	public void setBitacoraLoginDao(BitacoraLoginDao bitacoraLoginDao) {
		this.bitacoraLoginDao = bitacoraLoginDao;
	}

	@Override
	public boolean bitacoraLogin(BitacoraLogin bitacoraLogin)
			throws UsuarioNoBitacoradoException {
		logger.debug("bitacoraLogin");
		try {
			return bitacoraLoginDao.bitacoraLogin(bitacoraLogin);
		} catch (DaoException daoe) {
			logger.debug("bitacoraLogin " + daoe);
			throw new UsuarioNoBitacoradoException(new ExceptionMessage(
					"Error al bitacorar usuario"), daoe);
		}
	}
}
