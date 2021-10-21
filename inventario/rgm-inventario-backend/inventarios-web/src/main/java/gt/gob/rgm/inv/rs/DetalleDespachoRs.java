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
import gt.gob.rgm.inv.model.DetalleDespacho;
import gt.gob.rgm.inv.service.DetalleDespachoService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/detalle-despachos")
@RequestScoped
public class DetalleDespachoRs {

	@Inject
	private DetalleDespachoService detalleDespachoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDetalleDespachos(){
		return detalleDespachoService.listDetalleDespachos();
	}
	
	@GET
	@Path("/{id}")
	@SecuredResource
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDetalleDespacho(@PathParam("id") Long id) {
		return detalleDespachoService.getDetalleDespacho(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addDetalleDespacho(DetalleDespacho detalleDespacho) {
		return detalleDespachoService.addDetalleDespacho(detalleDespacho);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateDetalleDespacho(@PathParam("id") Long id, DetalleDespacho detalleDespacho) {
		return detalleDespachoService.updateDetalleDespacho(id, detalleDespacho);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteDetalleDespacho(@PathParam("id") Long id) {
		return detalleDespachoService.deleteDetalleDespacho(id);
	}
}
