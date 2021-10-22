package gt.gob.rgm.inv.rs;

import java.util.HashMap;
import java.util.Map;

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
import gt.gob.rgm.inv.model.Despacho;
import gt.gob.rgm.inv.service.DespachoService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/despachos")
@RequestScoped
public class DespachoRs {

	@Inject
	private DespachoService despachoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDespachos(@QueryParam(value="page") Integer page, 
				  @QueryParam(value="size") Integer size,
				  @QueryParam(value="estado") String estado,
				  @QueryParam(value="fechaInicio") String fechaInicio,
				  @QueryParam(value="fechaFin") String fechaFin
			  	  ){
		
		Map<String,Object> params = new HashMap<String,Object>();		
		if(estado!=null) params.put("estado", estado);
		if(fechaInicio!=null) params.put("fechaInicio", fechaInicio);
		if(fechaFin!=null) params.put("fechaFin", fechaFin);
		
		return despachoService.listDespachos(params, page, size);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getDespacho(@PathParam("id") Long id) {
		return despachoService.getDespacho(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addDespacho(Despacho despacho) {
		return despachoService.addDespacho(despacho);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateDespacho(@PathParam("id") Long id, Despacho despacho) {
		return despachoService.updateDespacho(id, despacho);
	}
	
	@DELETE
	@Path("/{id}")
	@SecuredResource
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteDespacho(@PathParam("id") Long id) {
		return despachoService.deleteDespacho(id);
	}
}

