package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.DetalleDespacho;

@ApplicationScoped
public class DetalleDespachoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DetalleDespacho> findAllDetalleDespachos(){
		return em.createNamedQuery("DetalleDespacho.findAll").getResultList();
	}
	
	public DetalleDespacho findById(Long id) {
		return em.find(DetalleDespacho.class, id);
	}
	
	public void save(DetalleDespacho DetalleDespacho) {
		em.persist(DetalleDespacho);
	}
	
	public void update(DetalleDespacho DetalleDespacho) {
		em.merge(DetalleDespacho);
	}
	
	public void delete(DetalleDespacho DetalleDespacho) {
		em.remove(DetalleDespacho);
	}
}
