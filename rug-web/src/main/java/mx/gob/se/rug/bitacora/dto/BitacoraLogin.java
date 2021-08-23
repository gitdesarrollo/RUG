/*
 * BitacoraLogin.java        09/09/2009
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

package mx.gob.se.rug.bitacora.dto;

import mx.gob.economia.dgi.framework.base.dto.AbstractBaseDTO;


@SuppressWarnings("serial")
public class BitacoraLogin extends AbstractBaseDTO {
	
	private String cveUsuario;
	private int idEvento;
	private String comentario;
	private String requestURI;
	private String requestAddress;
	
	public String getCveUsuario() {
		return cveUsuario;
	}
	public void setCveUsuario(String cveUsuario) {
		this.cveUsuario = cveUsuario;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getRequestURI() {
		return requestURI;
	}
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	public String getRequestAddress() {
		return requestAddress;
	}
	public void setRequestAddress(String requestAddress) {
		this.requestAddress = requestAddress;
	}
	
}
