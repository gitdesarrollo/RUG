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
import gt.gob.rgm.inv.model.DetalleSalida;
import gt.gob.rgm.inv.service.DetalleSalidaService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/detalle-salidas")
@RequestScoped
public class DetalleSalidaRs {

	@Inject
	private DetalleSalidaService detalleSalidaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDetalleSalidas(){
		return detalleSalidaService.listDetalleSalidas();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDetalleSalida(@PathParam("id") Long id) {
		return detalleSalidaService.getDetalleSalida(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addDetalleSalida(DetalleSalida detalleSalida) {
		return detalleSalidaService.addDetalleSalida(detalleSalida);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateDetalleSalida(@PathParam("id") Long id, DetalleSalida detalleSalida) {
		return detalleSalidaService.updateDetalleSalida(id, detalleSalida);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteDetalleSalida(@PathParam("id") Long id) {
		return detalleSalidaService.deleteDetalleSalida(id);
	}
}
