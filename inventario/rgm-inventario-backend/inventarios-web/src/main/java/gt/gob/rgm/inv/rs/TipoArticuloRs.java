package gt.gob.rgm.inv.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.rgm.inv.annotation.SecuredResource;
import gt.gob.rgm.inv.model.TipoArticulo;
import gt.gob.rgm.inv.service.TipoArticuloService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/tipos-articulos")
@RequestScoped
public class TipoArticuloRs {

	@Inject
	private TipoArticuloService tipoArticuloService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getTipoArticulos(){
		return tipoArticuloService.listTiposArticulo();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getTipoArticulo(@PathParam("id") Long id) {
		return tipoArticuloService.getTipoArticulo(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addTipoArticulo(TipoArticulo tipoArticulo) {
		return tipoArticuloService.addTipoArticulo(tipoArticulo);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateTipoArticulo(@PathParam("id") Long id, TipoArticulo tipoArticulo) {
		return tipoArticuloService.updateTipoArticulo(id, tipoArticulo);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteTipoArticulo(@PathParam("id") Long id) {
		return tipoArticuloService.deleteTipoArticulo(id);
	}
}
