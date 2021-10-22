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
import gt.gob.rgm.inv.model.Proveedor;
import gt.gob.rgm.inv.service.ProveedorService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/proveedores")
@RequestScoped
public class ProveedorRs {

	@Inject
	private ProveedorService proveedorService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getProveedores(){
		return proveedorService.listProveedores();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getProveedor(@PathParam("id") Long id) {
		return proveedorService.getProveedor(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addProveedor(Proveedor proveedor) {
		return proveedorService.addProveedor(proveedor);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateProveedor(@PathParam("id") Long id, Proveedor proveedor) {
		return proveedorService.updateProveedor(id, proveedor);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteProveedor(@PathParam("id") Long id) {
		return proveedorService.deleteProveedor(id);
	}
}
