/*
 * UserException.java        20/11/2009
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
package mx.gob.se.rug.common.exception;

import mx.gob.economia.dgi.framework.exception.ExceptionMessage;

/**
 * @author Alfonso Esquivel
 * 
 */
@SuppressWarnings("serial")
public class UserException extends PortalException {

	public UserException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public UserException(ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}

}
