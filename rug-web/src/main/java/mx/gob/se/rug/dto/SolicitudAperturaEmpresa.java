/*
 * SolicitudAperturaEmpresa.java        21/05/2009
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
package mx.gob.se.rug.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * Tr�mite correspondiente a la apertura r�pida de empresas.
 * 
 * @version 0.1
 * @author Alfonso Esquivel
 * 
 */
@SuppressWarnings("serial")
public class SolicitudAperturaEmpresa extends Tramite {
	private String idControlUnico;
	private PersonaMoral empresa;
	private Date fechaRegistro;
	private String estatus;
	private boolean aplicoPagos;
	private Calificacion calificacion;
	private List<String> empresas;

	public enum Calificacion {
		DISPONIBLE, PALABRA_RESERVADA, PALABRA_ALTISONANTE, NODISPONIBLE, DUPLICADA,
	}

	public SolicitudAperturaEmpresa() {
		super();
	}

	/**
	 * @param idControlUnico
	 * @param empresa
	 */
	public SolicitudAperturaEmpresa(String idControlUnico, PersonaMoral empresa) {
		this();
		setIdControlUnico(idControlUnico);
		setEmpresa(empresa);
	}

	/**
	 * @return the idControlUnico
	 */
	public String getIdControlUnico() {
		return idControlUnico;
	}

	/**
	 * @param idControlUnico
	 *            the idControlUnico to set
	 */
	public void setIdControlUnico(String idControlUnico) {
		this.idControlUnico = idControlUnico;
	}

	/**
	 * @return the empresa
	 */
	public PersonaMoral getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(PersonaMoral empresa) {
		this.empresa = empresa;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setAplicoPagos(boolean aplicoPagos) {
		this.aplicoPagos = aplicoPagos;
	}

	public boolean isAplicoPagos() {
		return aplicoPagos;
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	public List<String> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<String> empresas) {
		this.empresas = empresas;
	}

}
