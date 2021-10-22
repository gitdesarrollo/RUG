package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.EstadoOperacion;

@ApplicationScoped
public class EstadoOperacionRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<EstadoOperacion> findAllEstadoOperaciones(){
		return em.createNamedQuery("EstadoOperacion.findAll").getResultList();
	}
	
	public EstadoOperacion findById(Integer id) {
		return em.find(EstadoOperacion.class, id);
	}
	
	public void save(EstadoOperacion EstadoOperacion) {
		em.persist(EstadoOperacion);
	}
	
	public void delete(EstadoOperacion EstadoOperacion) {
		em.remove(EstadoOperacion);
	}
}
