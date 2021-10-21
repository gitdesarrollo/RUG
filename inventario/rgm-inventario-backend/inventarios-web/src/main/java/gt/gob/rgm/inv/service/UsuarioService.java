package gt.gob.rgm.inv.service;

import java.util.List;
import java.util.Map;

import gt.gob.rgm.inv.model.Usuario;
import gt.gob.rgm.inv.util.ResponseRs;

public interface UsuarioService {

	public ResponseRs listUsuarios();
	public ResponseRs getUsuario(Long id);
	public ResponseRs addUsuario(Usuario usuario);
	public ResponseRs updateUsuario(Usuario usuario);
	public ResponseRs deleteUsuario(Long id);
	public byte[] getReporteGeneralPdf(Map<String,Object> params);
	public ResponseRs recoverUsuario(String email);
	public List<Usuario> getUsuariosByRole(String role);
}
