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
import gt.gob.rgm.inv.model.UnidadMedida;
import gt.gob.rgm.inv.service.UnidadMedidaService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/unidades-medida")
@RequestScoped
public class UnidadMedidaRs {

	@Inject
	private UnidadMedidaService unidadMedidaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getUnidadMedidas(@QueryParam(value="page") Integer page, 
			                           @QueryParam(value="size") Integer size){		
		return unidadMedidaService.listUnidadesMedida(page, size);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getUnidadMedida(@PathParam("id") Long id) {
		return unidadMedidaService.getUnidadMedida(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addUnidadMedida(UnidadMedida unidadMedida) {
		return unidadMedidaService.addUnidadMedida(unidadMedida);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateUnidadMedida(@PathParam(value="id") Long id, UnidadMedida unidadMedida) {
		return unidadMedidaService.updateUnidadMedida(id, unidadMedida);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteUnidadMedida(@PathParam("id") Long id) {
		return unidadMedidaService.deleteUnidadMedida(id);
	}
}
