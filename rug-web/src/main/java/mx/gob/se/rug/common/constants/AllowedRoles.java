/*
 * AllowedRoles.java        04/08/2010
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
package mx.gob.se.rug.common.constants;

/**
 * @author Alfonso Esquivel
 * 
 */
public enum AllowedRoles {

	Default("Default"), AuxiliarFedatario("AuxiliarFedatario"), Fedatario(
			"Fedatario");

	private String role;

	private AllowedRoles(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
