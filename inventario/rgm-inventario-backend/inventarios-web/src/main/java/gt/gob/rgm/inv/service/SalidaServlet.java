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

public class SalidaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	SalidaService salidaService;
	
	@Inject
	UsuarioService usuarioService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String usuario = String.valueOf(req.getParameter("usuario"));
		String estado = String.valueOf(req.getParameter("estado"));
		String fechaInicio = String.valueOf(req.getParameter("fechaInicio"));
		String fechaFin = String.valueOf(req.getParameter("fechaFin"));
		
		Long tipoReporte = Long.valueOf(req.getParameter("tipoReporte")==null?"0":req.getParameter("tipoReporte"));
		Long id = null; 
		if(req.getParameter("id")!=null) id = Long.valueOf(req.getParameter("id"));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", ((Usuario) usuarioService.getUsuario(new Long(usuario)).getValue()).getNombre());
		params.put("id", id);
		params.put("estado", estado);
		params.put("fechaInicio", fechaInicio);
		params.put("fechaFin", fechaFin);
		
		byte[] pdf = null;
		
		if(tipoReporte == 2) {
			pdf = salidaService.getReporteSalidaPdf(params);
		} else {
			pdf = salidaService.getReporteGeneralPdf(params);
		}
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
