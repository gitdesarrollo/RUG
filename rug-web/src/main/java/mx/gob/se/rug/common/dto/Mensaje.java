/*
 * Mensaje.java        22/05/2009
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

package mx.gob.se.rug.common.dto;

import java.util.Map;

import mx.gob.economia.dgi.framework.base.dto.AbstractBaseDTO;

/**
 * 
 * @version 0.1
 * @author Alfonso Esquivel
 * 
 */
@SuppressWarnings("serial")
public class Mensaje extends AbstractBaseDTO {

	private String mensaje;

	private int respuesta;
	
	private String id;
	
	private String mensajeOculto;
	
	private Map<String,Object> params ;

	public Mensaje() {

	}

	public Mensaje(int respuesta, String mensaje) {
		super();
		this.mensaje = mensaje;
		this.respuesta = respuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}

	public void setId(String id) {		
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}

	public Map<String,Object> getParams() {
		return params;
	}

	public String getMensajeOculto() {
		return mensajeOculto;
	}

	public void setMensajeOculto(String mensajeOculto) {
		this.mensajeOculto = mensajeOculto;
	}

}
