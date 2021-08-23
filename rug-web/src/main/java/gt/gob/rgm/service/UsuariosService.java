package gt.gob.rgm.service;

import java.util.List;

import gt.gob.rgm.domain.Usuario;
import gt.gob.rgm.model.BitacoraOperaciones;

public interface UsuariosService {
	Usuario add(Usuario usuario);
	
	int activar(Long id, Long idAcreedor, Long idGrupo);
	
	int update(Long id, Usuario usuario);
	
	int updateState(Long id, String state);
	
	List<Usuario> getSubusuarios(Long id);
	
	Usuario getUsuario(Long id);
	
	Usuario getUsuarioExterno(String cveUsuario);
	
	void addBitacora(BitacoraOperaciones bitacora);
}
