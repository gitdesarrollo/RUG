package gt.gob.rgm.inv.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.rgm.inv.dao.UsuarioRepository;
import gt.gob.rgm.inv.domain.Principal;
import gt.gob.rgm.inv.model.Usuario;
import gt.gob.rgm.inv.util.CryptoUtils;
import gt.gob.rgm.inv.util.JWTUtils;
import gt.gob.rgm.inv.util.MessagesInv;

@Stateless
public class SecurityServiceImp implements SecurityService {

	@Inject
	UsuarioRepository usuarioDao;

	@Override
	public Principal login(Principal principal) {
		Usuario usuarioActual = usuarioDao.findByEmail(principal.getEmail());
		
		if(usuarioActual == null) {
			principal.setCode(Principal.USER_NOT_FOUND);
		} else {
			principal.setPassword(CryptoUtils.hash(principal.getPassword(), new StringBuffer(principal.getEmail()).reverse().toString()));
			if(usuarioActual.getPassword().equals(principal.getPassword())) {
				principal.setUsuarioId(usuarioActual.getUsuarioId());
				principal.setNombre(usuarioActual.getNombre());
				principal.setRol(usuarioActual.getRol());
				switch(usuarioActual.getEstado()) {
					case "A":
						principal.setCode(Principal.LOGIN_SUCCESS);
						Map<String, Object> payload = new HashMap<>();
						payload.put("nombre", principal.getNombre());
						payload.put("email", principal.getEmail());
						String tokenGenerado = JWTUtils.generateToken(principal.getEmail(), payload, MessagesInv.SESSION_DURATION);
						principal.setToken(tokenGenerado);
						break;
					case "H":
						principal.setCode(principal.USER_TEMPORARILY_DISABLED);
						break;
					case "I":
						principal.setCode(principal.USER_DISABLED);
						break;
				}
			} else {
				principal.setCode(principal.AUTHENTICATION_FAILED);
			}
		}
		principal.setPassword("");
		return principal;
	}

}
