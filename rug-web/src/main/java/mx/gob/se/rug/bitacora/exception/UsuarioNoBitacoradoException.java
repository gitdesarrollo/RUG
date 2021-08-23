/*
 * UsuarioNoBitacoradoException.java        10/08/2009
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


package mx.gob.se.rug.bitacora.exception;

import mx.gob.economia.dgi.framework.exception.ExceptionMessage;


@SuppressWarnings("serial")
public class UsuarioNoBitacoradoException extends BitacoraLoginException {
	public UsuarioNoBitacoradoException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	/**
	 * @param exceptionMessage
	 * @param cause
	 */
	public UsuarioNoBitacoradoException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}
