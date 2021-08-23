package mx.gob.se.rug.dto;

/*
 * DomicilioInternacional.java        01/05/2009
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


/**
 * 
 * 
 * 
 * @version 0.1
 * @author Alfonso Esquivel
 * 
 */
@SuppressWarnings("serial")
public class DomicilioInternacional extends Domicilio {
	private String ubicacionUno;
	private String ubicacionDos;
	private String zonaPostal;
	private String poblacion;

	public DomicilioInternacional() {
		super();
	}

	/**
	 * 
	 * @param idDomicilio
	 */
	public DomicilioInternacional(String idDomicilio) {
		this();
		setIdDomicilio(idDomicilio);
	}

	/**
	 * 
	 * @param idDomicilio
	 * @param tipoDomicilio
	 */
	public DomicilioInternacional(String idDomicilio, String tipoDomicilio) {
		this(idDomicilio);
		setTipoDomicilio(tipoDomicilio);
	}

	/**
	 * @return the ubicacionUno
	 */
	public String getUbicacionUno() {
		return ubicacionUno;
	}

	/**
	 * @param ubicacionUno
	 *            the ubicacionUno to set
	 */
	public void setUbicacionUno(String ubicacionUno) {
		this.ubicacionUno = ubicacionUno;
	}

	/**
	 * @return the ubicacionDos
	 */
	public String getUbicacionDos() {
		return ubicacionDos;
	}

	/**
	 * @param ubicacionDos
	 *            the ubicacionDos to set
	 */
	public void setUbicacionDos(String ubicacionDos) {
		this.ubicacionDos = ubicacionDos;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion
	 *            the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public void setZonaPostal(String zonaPostal) {
		this.zonaPostal = zonaPostal;
	}

	public String getZonaPostal() {
		return zonaPostal;
	}

	
}

