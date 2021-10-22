package gt.gob.rgm.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;

import gt.gob.rgm.dao.BitacoraOperacionesRepository;
import gt.gob.rgm.dao.RelUsuAcreedorRepository;
import gt.gob.rgm.dao.RugPersonasFisicasRepository;
import gt.gob.rgm.dao.RugPersonasRepository;
import gt.gob.rgm.dao.RugRelGrupoAcreedorRepository;
import gt.gob.rgm.dao.RugSecuPerfilesUsuarioRepository;
import gt.gob.rgm.dao.RugSecuUsuariosExternosRepository;
import gt.gob.rgm.dao.RugSecuUsuariosRepository;
import gt.gob.rgm.domain.Usuario;
import gt.gob.rgm.model.BitacoraOperaciones;
import gt.gob.rgm.model.RelUsuAcreedor;
import gt.gob.rgm.model.RelUsuAcreedorPK;
import gt.gob.rgm.model.RugPersonas;
import gt.gob.rgm.model.RugPersonasFisicas;
import gt.gob.rgm.model.RugRelGrupoAcreedor;
import gt.gob.rgm.model.RugSecuPerfilesUsuario;
import gt.gob.rgm.model.RugSecuUsuarios;
import gt.gob.rgm.model.RugSecuUsuariosExternos;
import gt.gob.rgm.util.HashUtils;

@Scope("prototype")
public class UsuariosServiceImp implements UsuariosService {
	
	private RugPersonasRepository personaDao;
	
	private RugPersonasFisicasRepository personaFisicaDao;
	
	private RugSecuUsuariosRepository secuUsuarioDao;
	
	private RugSecuPerfilesUsuarioRepository secuPerfilUsuarioDao;
	
	private RelUsuAcreedorRepository relUsuAcreedorDao;
	
	private RugRelGrupoAcreedorRepository rugRelGrupoAcreedorDao;
	
	private RugSecuUsuariosExternosRepository secuUsuarioExtDao;
		
	private BitacoraOperacionesRepository bitacoraDao;

	@Override
	public void addBitacora(BitacoraOperaciones bitacora) {
		bitacoraDao.save(bitacora);
	}
	
	@Override
	public Usuario add(Usuario usuario) {
		LocalDate now = LocalDate.now();
		// TODO: hacer validaciones sobre los datos ingresados
		// TODO: que el NIT no exista con otro usuario
		// TODO: que el DPI no exista con otro usuario
		// que el correo no exista con otro usuario
		RugSecuUsuarios usuarioExiste = secuUsuarioDao.findByCveUsuario(usuario.getCveUsuario());
		if(usuarioExiste != null) {
			usuario.setMensajeError("El correo ingresado ya está siendo utilizado por otro usuario");
			return usuario;
		}
		
		// insertar en RugPersonas
		RugPersonas persona = new RugPersonas();
		persona.setIdNacionalidad(usuario.getIdNacionalidad());
		persona.setPerJuridica(usuario.getTipoPersona());
		persona.setFhRegistro(Date.valueOf(now));
		persona.setProcedencia("NAL");
		persona.setSitPersona("AC");
		persona.setRegTerminado("Y");
		persona.setInscrito(usuario.getInscritoComo());
		persona.setCurpDoc(usuario.getDocId());
		personaDao.save(persona);
		
		// insertar en RugPersonasFisicas
		RugPersonasFisicas personaFisica = new RugPersonasFisicas();
		personaFisica.setIdPersona(persona.getIdPersona());
		personaFisica.setNombrePersona(usuario.getNombre());
		personaFisica.setCurp(usuario.getDocId());
		personaFisicaDao.save(personaFisica);
		
		// insertar en RugSecuUsuarios
		RugSecuUsuarios secuUsuario = new RugSecuUsuarios();
		secuUsuario.setCveInstitucion(usuario.getCveInstitucion());
		secuUsuario.setCveUsuario(usuario.getCveUsuario());
		secuUsuario.setIdPersona(persona.getIdPersona());
		// encriptar password con algoritmo SHA-1
		String password = HashUtils.hash(usuario.getPassword(), "SHA-1");
		secuUsuario.setPassword(password);
		secuUsuario.setFAsignaPsw(Date.valueOf(now));
		secuUsuario.setPregRecuperaPsw(usuario.getPregRecupera());
		secuUsuario.setRespRecuperaPsw(usuario.getRespRecupera());
		secuUsuario.setFhRegistro(Date.valueOf(now));
		secuUsuario.setSitUsuario("IN");
		secuUsuario.setCveUsuarioPadre(usuario.getCveUsuarioPadre());
		secuUsuario.setCveAcreedor(usuario.getCveAcreedor());
		secuUsuario.setIdGrupo(2); // grupo CIUDADANO
		secuUsuario.setIsJudicial(usuario.getIsJudicial());
		/*// generar token
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = now.format(formatter);
		String strToken = formatDateTime + "#" + usuario.getCveUsuario();
		byte[] encodedBytes = Base64.getEncoder().encode(strToken.getBytes());
		String token = new String(encodedBytes);
		secuUsuario.setToken(token);*/
		secuUsuarioDao.save(secuUsuario);
		
		// insertar en RugSecuPerfilesUsuario
		RugSecuPerfilesUsuario secuPerfilUsuario = new RugSecuPerfilesUsuario();
		secuPerfilUsuario.setCveInstitucion(usuario.getCveInstitucion());
		secuPerfilUsuario.setCveUsuario(usuario.getCveUsuario());
		secuPerfilUsuario.setCvePerfil("CIUDADANO");
		secuPerfilUsuario.setCveAplicacion("RugPortal");
		secuPerfilUsuario.setIdPersona(persona.getIdPersona());
		secuPerfilUsuario.setBBloqueado("F");
		secuPerfilUsuarioDao.save(secuPerfilUsuario);
		
		// activar usuario
		this.activar(persona.getIdPersona(), usuario.getIdPersonaPadre(), usuario.getIdGrupo());
		
		usuario.setIdPersona(persona.getIdPersona());
		
		return usuario;
	}
	
	@Override
	public int activar(Long id, Long idAcreedor, Long idGrupo) {
		LocalDate now = LocalDate.now();
		// actualizar el estado de RugSecuUsuarios
		Optional<RugSecuUsuarios> secuUsuario = secuUsuarioDao.findById(id);
		if(secuUsuario.isPresent()) {
			RugSecuUsuarios usuario = secuUsuario.get();
			usuario.setSitUsuario("AC");
			secuUsuarioDao.save(usuario);
			
			// insertar en RelUsuAcreedor
			RelUsuAcreedor relUsuAcreedor = new RelUsuAcreedor();
			RelUsuAcreedorPK relUsuAcreedorPk = new RelUsuAcreedorPK();
			relUsuAcreedorPk.setIdUsuario(id);
			relUsuAcreedorPk.setIdAcreedor(idAcreedor);
			relUsuAcreedor.setId(relUsuAcreedorPk);
			relUsuAcreedor.setBFirmado("Y");
			relUsuAcreedor.setFechaReg(Date.valueOf(now));
			relUsuAcreedor.setStatusReg("AC");
			relUsuAcreedorDao.save(relUsuAcreedor);
			
			// insertar en RugRelGrupoAcreedor
			RugRelGrupoAcreedor rugRelGrupoAcreedor = new RugRelGrupoAcreedor();
			rugRelGrupoAcreedor.setIdAcreedor(idAcreedor);
			rugRelGrupoAcreedor.setIdSubUsuario(id);
			rugRelGrupoAcreedor.setIdUsuario(idAcreedor);
			rugRelGrupoAcreedor.setStatusReg("AC");
			rugRelGrupoAcreedor.setFechaReg(Date.valueOf(now));
			rugRelGrupoAcreedor.setIdGrupo(idGrupo);
			rugRelGrupoAcreedorDao.save(rugRelGrupoAcreedor);		

			return 1;
		}
		return 0;
	}

	@Override
	public int update(Long id, Usuario usuario) {
		LocalDateTime now = LocalDateTime.now();
		// actualizar en RugPersonas
		Optional<RugPersonas> persona = personaDao.findById(id);
		if(persona.isPresent()) {
			RugPersonas personaActualizar = persona.get();
			if(usuario.getIdNacionalidad() != null) {
				personaActualizar.setIdNacionalidad(usuario.getIdNacionalidad());
			}
			if(usuario.getTipoPersona() != null) {
				personaActualizar.setPerJuridica(usuario.getTipoPersona());
			}
			//personaActualizar.setFhRegistro(java.sql.Timestamp.valueOf(now));
			if(usuario.getInscritoComo() != null) {
				personaActualizar.setInscrito(usuario.getInscritoComo());
			}
			personaActualizar.setCurpDoc(usuario.getDocId());
			personaActualizar.setRfc(usuario.getRfc());
			personaDao.save(personaActualizar);
			
			// actualizar en RugPersonasFisicas
			RugPersonasFisicas personaFisica = personaFisicaDao.findById(id).get();
			personaFisica.setNombrePersona(usuario.getNombre());
			personaFisica.setCurp(usuario.getDocId());
			personaFisicaDao.save(personaFisica);
			
			// actualizar en RugSecuUsuarios
			RugSecuUsuarios secuUsuario = secuUsuarioDao.findById(id).get();
			secuUsuario.setCveUsuario(usuario.getCveUsuario());
			// encriptar password con algoritmo SHA-1
			String password = HashUtils.hash(usuario.getPassword(), "SHA-1");
			secuUsuario.setPassword(password);
			secuUsuario.setPregRecuperaPsw(usuario.getPregRecupera());
			secuUsuario.setRespRecuperaPsw(usuario.getRespRecupera());
			//secuUsuario.setFhRegistro(java.sql.Timestamp.valueOf(now));
			secuUsuario.setSitUsuario(usuario.getSitUsuario());
			// generar token
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formatDateTime = now.format(formatter);
			String strToken = formatDateTime + "#" + usuario.getCveUsuario();
			byte[] encodedBytes = Base64.getEncoder().encode(strToken.getBytes());
			String token = new String(encodedBytes);
			secuUsuario.setToken(token);
			secuUsuarioDao.save(secuUsuario);
			
			// actualizar en RugSecuPerfilesUsuario
			RugSecuPerfilesUsuario secuPerfilUsuario = secuPerfilUsuarioDao.findById(id).get();
			secuPerfilUsuario.setCveUsuario(usuario.getCveUsuario());
			secuPerfilUsuarioDao.save(secuPerfilUsuario);
			
			return 1;
		}
		return 0;
	}
	
	@Override
	public int updateState(Long id, String state) {
		Optional<RugSecuUsuarios> secuUsuario = secuUsuarioDao.findById(id);
		if(secuUsuario.isPresent()) {
			RugSecuUsuarios usuario = secuUsuario.get();
			usuario.setSitUsuario(state);
			secuUsuarioDao.save(usuario);
			
			return 1;
		}
		return 0;
	}

	@Override
	public List<Usuario> getSubusuarios(Long id) {
		List<Usuario> usuarios = new ArrayList<>();
		Optional<RugSecuUsuarios> usuarioLogeado = secuUsuarioDao.findById(id);
		List<RugSecuUsuarios> secuUsuarios = secuUsuarioDao.findByCveUsuarioPadreAndSitUsuario(usuarioLogeado.get().getCveUsuario(), "AC");
		for(RugSecuUsuarios secuUsuario : secuUsuarios) {
			Usuario usuario = transformSecuToUsuario(secuUsuario);
			
			usuarios.add(usuario);
		}
				
		return usuarios;
	}

	@Override
	public Usuario getUsuario(Long id) {
		Optional<RugSecuUsuarios> secuUsuario = secuUsuarioDao.findById(id);

		return transformSecuToUsuario(secuUsuario.get());
	}
	
	@Override
	public Usuario getUsuarioExterno(String cveUsuario) {
		RugSecuUsuariosExternos secuUsuario = secuUsuarioExtDao.findByCveUsuario(cveUsuario);
                
		Usuario usuario = new Usuario();
		if(secuUsuario!=null) {
			usuario.setIdPersona(secuUsuario.getIdUsuario());
			usuario.setCveUsuario(secuUsuario.getCveUsuario());
			usuario.setPassword(secuUsuario.getPassword());
			usuario.setSitUsuario(secuUsuario.getSitUsuario());
			usuario.setCvePerfil(secuUsuario.getCvePerfil());
			//System.out.println("usuari" + usuario + "id:" + secuUsuario.getIdUsuario() + "usuari2:" + secuUsuario.getCveUsuario()+ "id:" + secuUsuario.getCvePerfil());
			return usuario;
		}
		
		return null; 
	}
	
	private Usuario transformSecuToUsuario(RugSecuUsuarios secuUsuario) {
		Usuario usuario = new Usuario();
		usuario.setIdPersona(secuUsuario.getIdPersona());
		usuario.setRfc(secuUsuario.getPersona().getRfc());
		usuario.setIdNacionalidad(secuUsuario.getPersona().getIdNacionalidad());
		usuario.setTipoPersona(secuUsuario.getPersona().getPerJuridica());
		usuario.setInscritoComo(secuUsuario.getPersona().getInscrito());
		usuario.setDocId(secuUsuario.getPersonaFisica().getCurp());
		usuario.setNombre(secuUsuario.getPersonaFisica().getNombrePersona());
		usuario.setCveInstitucion(secuUsuario.getCveInstitucion());
		usuario.setCveUsuario(secuUsuario.getCveUsuario());
		usuario.setPregRecupera(secuUsuario.getPregRecuperaPsw());
		usuario.setCveUsuarioPadre(secuUsuario.getCveUsuarioPadre());
		usuario.setCveAcreedor(secuUsuario.getCveAcreedor());
		usuario.setIdGrupo(secuUsuario.getIdGrupo());
		usuario.setFirmado(secuUsuario.getBFirmado());
		usuario.setCvePerfil(secuUsuario.getPerfilUsuario().getCvePerfil());
		usuario.setCveAplicacion(secuUsuario.getPerfilUsuario().getCveAplicacion());
		usuario.setIsJudicial(secuUsuario.getIsJudicial());
		
		return usuario;
	}

	public void setPersonaDao(RugPersonasRepository personaDao) {
		this.personaDao = personaDao;
	}

	public void setPersonaFisicaDao(RugPersonasFisicasRepository personaFisicaDao) {
		this.personaFisicaDao = personaFisicaDao;
	}

	public void setSecuUsuarioDao(RugSecuUsuariosRepository secuUsuarioDao) {
		this.secuUsuarioDao = secuUsuarioDao;
	}

	public void setSecuPerfilUsuarioDao(RugSecuPerfilesUsuarioRepository secuPerfilUsuarioDao) {
		this.secuPerfilUsuarioDao = secuPerfilUsuarioDao;
	}

	public void setRelUsuAcreedorDao(RelUsuAcreedorRepository relUsuAcreedorDao) {
		this.relUsuAcreedorDao = relUsuAcreedorDao;
	}

	public void setRugRelGrupoAcreedorDao(RugRelGrupoAcreedorRepository rugRelGrupoAcreedorDao) {
		this.rugRelGrupoAcreedorDao = rugRelGrupoAcreedorDao;
	}

	public void setSecuUsuarioExtDao(RugSecuUsuariosExternosRepository secuUsuarioExtDao) {
		this.secuUsuarioExtDao = secuUsuarioExtDao;
	}

	public void setBitacoraDao(BitacoraOperacionesRepository bitacoraDao) {
		this.bitacoraDao = bitacoraDao;
	}
}
