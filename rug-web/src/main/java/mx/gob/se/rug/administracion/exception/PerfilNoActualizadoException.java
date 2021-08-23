/*
 * PerfilNoActualizadoException.java        09/11/2009
 *
 * Copyright (c) 2009 Secretar�a de Econom�a
 * Alfonso Reyes No. 30 Col. Hip�dromo Condesa C.P. 06140, 
 * Delegaci�n Cuauht�moc, M�xico, D.F.
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y contiene informaci�n perteneciente
 * a la Secretar�a de Econom�a.
 * 
 */
package mx.gob.se.rug.administracion.exception;

import mx.gob.economia.dgi.framework.exception.ExceptionMessage;
import mx.gob.se.rug.common.exception.PortalException;


/**
 * @author Alfonso Esquivel
 * 
 */
@SuppressWarnings("serial")
public class PerfilNoActualizadoException extends PortalException {

	public PerfilNoActualizadoException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public PerfilNoActualizadoException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}
