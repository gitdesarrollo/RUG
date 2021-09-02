package gt.gob.rgm.inv.rs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import gt.gob.rgm.inv.annotation.SecuredResource;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.service.ArticuloService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/articulos")
@RequestScoped
public class ArticuloRs {

	@Inject
	private ArticuloService articuloService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getArticulos(@QueryParam(value="page") Integer page, 
								   @QueryParam(value="size") Integer size, 
								   @QueryParam(value="codigo") String codigo,
								   @QueryParam(value="codigoBarras") String codigoBarras,
								   @QueryParam(value="correlativo") Long correlativo,
								   @QueryParam(value="stock") Long stock,
								   @QueryParam(value="fechaVencimiento") Date fechaVencimiento,
								   @QueryParam(value="minimoExistencia") Integer minimoExistencia,
								   @QueryParam(value="perecedero") Boolean perecedero,
								   @QueryParam(value="valor") Long valor,
								   @QueryParam(value="marcaId") Long marcaId,
								   @QueryParam(value="proveedorId") Long proveedorId,
								   @QueryParam(value="tipoArticuloId") Long tipoArticuloId,
								   @QueryParam(value="unidadMediadId") Long unidadMediadId,
								   @QueryParam(value="fechaInicio") String fechaInicio,
								   @QueryParam(value="fechaFin") String fechaFin,
								   @QueryParam(value="texto") String texto
								   ){
		
		
		Map<String,Object> articuloFilter = new HashMap<String,Object>();
		if(codigo!=null) articuloFilter.put("codigo", codigo);
		if(codigoBarras!=null) articuloFilter.put("codigoBarras", codigoBarras);
		if(correlativo!=null) articuloFilter.put("correlativo", correlativo);
		if(stock!=null) articuloFilter.put("stock", stock);
		if(fechaVencimiento!=null) articuloFilter.put("fechaVencimiento", fechaVencimiento);
		if(minimoExistencia!=null) articuloFilter.put("minimoExistencia", minimoExistencia);
		if(perecedero!=null) articuloFilter.put("predecedero", perecedero);
		if(valor!=null) articuloFilter.put("valor", valor);
		if(marcaId!=null) articuloFilter.put("marcaId", marcaId);
		if(proveedorId!=null) articuloFilter.put("proveedorId", proveedorId);
		if(tipoArticuloId!=null) articuloFilter.put("tipoArticuloId", tipoArticuloId);
		if(unidadMediadId!=null) articuloFilter.put("unidadMediadId", unidadMediadId);
		if(fechaInicio!=null) articuloFilter.put("fechaInicio", fechaInicio);
		if(fechaFin!=null) articuloFilter.put("fechaFin", fechaFin);
		if(texto!=null) articuloFilter.put("texto", texto);
		
		return articuloService.listArticulos(articuloFilter, page, size);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getArticulo(@PathParam("codigo") String id) {
		return articuloService.getArticulo(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addArticulo(Articulo articulo) {
		return articuloService.addArticulo(articulo);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateArticulo(@PathParam("id") String id, Articulo articulo) {
		return articuloService.updateArticulo(id, articulo);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteArticulo(@PathParam("id") String id) {
		return articuloService.deleteArticulo(id);
	}
}
