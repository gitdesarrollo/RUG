package mx.gob.se.rug.dto;

/*
 * RolEmpresa.java        01/05/2009
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


import mx.gob.economia.dgi.framework.base.dto.AbstractBaseDTO;

/**
 * 
 * 
 * 
 * @version 0.1
 * @author Alfonso Esquivel
 * 
 */
@SuppressWarnings("serial")
public abstract class RolEmpresa extends AbstractBaseDTO {
	
	private int id;
	
	public enum Rol {
		ADMINISTRADOR, SOCIO_ACCIONISTA, COMISARIO, REPRESENTANTE, RESPONSABLE_SANITARIO;

	}

	private Rol rol;

	/**
	 * 
	 * @return Rol de la persona.
	 */
	public Rol getRol() {
		return this.rol;
	}

	protected void setRol(Rol rol) {
		this.rol = rol;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
