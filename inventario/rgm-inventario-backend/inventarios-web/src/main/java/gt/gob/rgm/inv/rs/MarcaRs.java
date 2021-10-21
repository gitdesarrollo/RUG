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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import gt.gob.rgm.inv.annotation.SecuredResource;
import gt.gob.rgm.inv.model.Marca;
import gt.gob.rgm.inv.service.MarcaService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/marcas")
@RequestScoped
public class MarcaRs {
	
	@Inject
	private MarcaService marcaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getMarcas(@QueryParam(value="page") Integer page, 
            					@QueryParam(value="size") Integer size){
		
		return marcaService.listMarcas(page, size);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getMarca(@PathParam("id") Long id) {
		return marcaService.getMarca(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addMarca(Marca marca) {
		return marcaService.addMarca(marca);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateMarca(@PathParam("id") Long id, Marca marca) {
		return marcaService.updateMarca(id, marca);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteMarca(@PathParam("id") Long id) {
		return marcaService.deleteMarca(id);
	}
}
