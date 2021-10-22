package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.DetalleRequisicion;

@ApplicationScoped
public class DetalleRequisicionRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DetalleRequisicion> findAllDetalleRequisiciones(){
		return em.createNamedQuery("DetalleRequisicion.findAll").getResultList();
	}
	
	public DetalleRequisicion findById(Long id) {
		return em.find(DetalleRequisicion.class, id);
	}
	
	public void save(DetalleRequisicion DetalleRequisicion) {
		em.persist(DetalleRequisicion);
	}
	
	public void update(DetalleRequisicion DetalleRequisicion) {
		em.merge(DetalleRequisicion);
	}
	
	public void delete(DetalleRequisicion DetalleRequisicion) {
		em.remove(DetalleRequisicion);
	}
}
