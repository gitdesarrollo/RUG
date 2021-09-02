package gt.gob.rgm.inv.rs;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import gt.gob.rgm.inv.annotation.SecuredResource;
import gt.gob.rgm.inv.service.KardexService;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/kardexes")
@RequestScoped
public class KardexRs {

	@Inject
	private KardexService kardexService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getRequisiciones(@QueryParam(value="page") Integer page, 
			   						   @QueryParam(value="size") Integer size,
			   						   @QueryParam(value="codigoArticulo") String codigoArticulo,
			   						   @QueryParam(value="fechaInicio") String fechaInicio,
			   						   @QueryParam(value="fechaFin") String fechaFin){
		Map<String,Object> params = new HashMap<String,Object>();
		if(codigoArticulo!=null) params.put("codigoArticulo", codigoArticulo);
		if(fechaInicio!=null) params.put("fechaInicio", fechaInicio);
		if(fechaFin!=null) params.put("fechaFin", fechaFin);
		
		return kardexService.listKardex(params, page, size);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getRequisicion(@PathParam("id") Long id) {
		return kardexService.getKardex(id);
	}
}
