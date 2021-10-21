package gt.gob.rgm.inv.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gt.gob.rgm.inv.model.Usuario;

public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	UsuarioService usuarioService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String usuario = String.valueOf(req.getParameter("usuario"));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", ((Usuario) usuarioService.getUsuario(new Long(usuario)).getValue()).getNombre());
		
		byte[] pdf = usuarioService.getReporteGeneralPdf(params);
		ByteArrayInputStream in = new ByteArrayInputStream(pdf);
		OutputStream out = res.getOutputStream();
		
		byte[] buf = new byte[1024];
		int count = 0;
		while((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.flush();
		out.close();
	}
}
