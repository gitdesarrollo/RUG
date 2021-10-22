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
import gt.gob.rgm.inv.model.TipoIngreso;
import gt.gob.rgm.inv.service.TipoIngresoService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/tipos-ingresos")
@RequestScoped
public class TipoIngresoRs {

	@Inject
	private TipoIngresoService tipoIngresoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getTipoIngresos(){
		return tipoIngresoService.listTipoIngresos();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getTipoIngreso(@PathParam("id") Long id) {
		return tipoIngresoService.getTipoIngreso(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addTipoIngreso(TipoIngreso tipoIngreso) {
		return tipoIngresoService.addTipoIngreso(tipoIngreso);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateTipoIngreso(@PathParam("id") Long id, TipoIngreso tipoIngreso) {
		return tipoIngresoService.updateTipoIngreso(id, tipoIngreso);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteTipoIngreso(@PathParam("id") Long id) {
		return tipoIngresoService.deleteTipoIngreso(id);
	}
}