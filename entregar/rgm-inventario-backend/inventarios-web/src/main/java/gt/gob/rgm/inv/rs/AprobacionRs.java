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
import gt.gob.rgm.inv.model.Aprobacion;
import gt.gob.rgm.inv.service.AprobacionService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/aprobaciones")
@RequestScoped
public class AprobacionRs {
	
	@Inject
	private AprobacionService aprobacionService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getAprobaciones(){
		return aprobacionService.listAprobaciones();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getAprobacion(@PathParam("id") Long id) {
		return aprobacionService.getAprobacion(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addAprobacion(Aprobacion aprobacion) {
		return aprobacionService.addAprobacion(aprobacion);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateAprobacion(@PathParam("id") Long id, Aprobacion aprobacion) {
		return aprobacionService.updateAprobacion(id, aprobacion);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteAprobacion(@PathParam("id") Long id) {
		return aprobacionService.deleteAprobacion(id);
	}
}
