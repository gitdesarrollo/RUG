package gt.gob.rgm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gt.gob.rgm.model.RugSecuUsuarios;

public interface RugSecuUsuariosRepository extends JpaRepository<RugSecuUsuarios, Long> {
	
	List<RugSecuUsuarios> findByCveUsuarioPadreAndSitUsuario(String cveUsuarioPadre, String sitUsuario);
	
	RugSecuUsuarios findByCveUsuario(String cveUsuario);
}
