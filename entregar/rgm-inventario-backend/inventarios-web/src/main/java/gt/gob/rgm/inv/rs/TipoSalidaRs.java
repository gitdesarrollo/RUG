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
import gt.gob.rgm.inv.model.TipoSalida;
import gt.gob.rgm.inv.service.TipoSalidaService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/tipos-salidas")
@RequestScoped
public class TipoSalidaRs {

	@Inject
	private TipoSalidaService tipoSalidaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getTipoSalidas(){
		return tipoSalidaService.listTipoSalidas();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getTipoSalida(@PathParam("id") Long id) {
		return tipoSalidaService.getTipoSalida(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addTipoIngreso(TipoSalida tipoSalida) {
		return tipoSalidaService.addTipoSalida(tipoSalida);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateTipoSalida(@PathParam("id") Long id, TipoSalida tipoSalida) {
		return tipoSalidaService.updateTipoSalida(id, tipoSalida);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteTipoSalida(@PathParam("id") Long id) {
		return tipoSalidaService.deleteTipoSalida(id);
	}
}
