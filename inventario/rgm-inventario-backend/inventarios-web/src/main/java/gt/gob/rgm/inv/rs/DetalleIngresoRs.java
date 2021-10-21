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
import gt.gob.rgm.inv.model.DetalleIngreso;
import gt.gob.rgm.inv.service.DetalleIngresoService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/detalle-ingresos")
@RequestScoped
public class DetalleIngresoRs {

	@Inject
	private DetalleIngresoService detalleIngresoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDetalleIngresos(@QueryParam("codigo") String codigo){
		if(codigo == null) {
			return detalleIngresoService.listDetalleIngresos();
		} else {
			return detalleIngresoService.getDetalleIngresoByCodigo(codigo);
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDetalleIngreso(@PathParam("id") Long id) {
		return detalleIngresoService.getDetalleIngreso(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addDetalleIngreso(DetalleIngreso detalleIngreso) {
		return detalleIngresoService.addDetalleIngreso(detalleIngreso);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateDetalleIngreso(@PathParam("id") Long id, DetalleIngreso detalleIngreso) {
		return detalleIngresoService.updateDetalleIngreso(id, detalleIngreso);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteDetalleIngreso(@PathParam("id") Long id) {
		return detalleIngresoService.deleteDetalleIngreso(id);
	}
}
