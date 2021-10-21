package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.Estado;

@ApplicationScoped
public class EstadoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Estado> findAllEstados(){
		return em.createNamedQuery("Estado.findAll").getResultList();
	}
	
	public Estado findById(Integer id) {
		return em.find(Estado.class, id);
	}
	
	public void save(Estado Estado) {
		em.persist(Estado);
	}
	
	public void delete(Estado Estado) {
		em.remove(Estado);
	}
}
