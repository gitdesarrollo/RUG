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
import gt.gob.rgm.inv.model.DetalleRequisicion;
import gt.gob.rgm.inv.service.DetalleRequisicionService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/detalle-requisiciones")
@RequestScoped
public class DetalleRequisicionRs {

	@Inject
	private DetalleRequisicionService detalleRequisicionService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDetalleRequisiciones(){
		return detalleRequisicionService.listDetalleRequisiciones();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDetalleRequisicion(@PathParam("id") Long id) {
		return detalleRequisicionService.getDetalleRequisicion(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addDetalleRequisicion(DetalleRequisicion detalleRequisicion) {
		return detalleRequisicionService.addDetalleRequisicion(detalleRequisicion);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateDetalleRequisicion(@PathParam("id") Long id, DetalleRequisicion detalleRequisicion) {
		return detalleRequisicionService.updateDetalleRequisicion(id, detalleRequisicion);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteDetalleRequisicion(@PathParam("id") Long id) {
		return detalleRequisicionService.deleteDetalleRequisicion(id);
	}
}

