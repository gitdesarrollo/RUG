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

public class ArticuloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	ArticuloService articuloService;
	
	@Inject
	UsuarioService usuarioService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
		Integer tipoReporte = Integer.valueOf(req.getParameter("tipoReporte"));		
		String usuario = String.valueOf(req.getParameter("usuario"));
		//Integer page = Integer.valueOf(req.getParameter("page"));
		//Integer size = Integer.valueOf(req.getParameter("size"));
		
		Map<String, Object> params = new HashMap<String, Object>();
		//page = null;
		//size = null;
		if(tipoReporte==1) {
			Long tipoArticulo = 0L;			
			if(req.getParameter("tipoArticulo")==null) {
				// do Nothing
			} else {
				tipoArticulo = Long.valueOf(req.getParameter("tipoArticulo"));
			}
			String fechaInicio = String.valueOf(req.getParameter("fechaInicio"));
			String fechaFin = String.valueOf(req.getParameter("fechaFin"));
			params.put("tipoArticulo", tipoArticulo);
			params.put("fechaInicio", fechaInicio); 
			params.put("fechaFin", fechaFin); 
		}
		params.put("usuario", ((Usuario) usuarioService.getUsuario(new Long(usuario)).getValue()).getNombre());
		//params.put("page", page);
		//params.put("size", size);
		
		byte[] pdf = null;
		if(tipoReporte==2) {
			pdf = articuloService.getReporteMinimos(params);
		} else {
			pdf = articuloService.getInventarioGeneralPdf(params);
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
