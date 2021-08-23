/*
 * PerfilService.java        09/11/2009
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
package mx.gob.se.rug.administracion.service;

import mx.gob.se.rug.administracion.dto.Perfil;
import mx.gob.se.rug.administracion.exception.PerfilNoActualizadoException;
import mx.gob.se.rug.administracion.exception.UsuarioException;
import mx.gob.se.rug.common.dto.Mensaje;
import mx.gob.se.rug.dto.PersonaFisica;

/**
 * @author Alfonso Esquivel
 *
 */
public interface PerfilService {


	public Mensaje update(Perfil perfil, String claveUsuario, PersonaFisica personaFisica) throws PerfilNoActualizadoException;
	
	public PersonaFisica getConsultaRfcUsuario(PersonaFisica personaFisica)throws PerfilNoActualizadoException;
}
