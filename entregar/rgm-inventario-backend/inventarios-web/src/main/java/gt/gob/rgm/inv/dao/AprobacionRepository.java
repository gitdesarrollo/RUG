package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.Aprobacion;

@ApplicationScoped
public class AprobacionRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Aprobacion> findAllAprobaciones(){
		return em.createNamedQuery("Aprobacion.findAll").getResultList();
	}
	
	public Aprobacion findById(Long id) {
		return em.find(Aprobacion.class, id);
	}
	
	public void save(Aprobacion Aprobacion) {
		em.persist(Aprobacion);
	}
	
	public void update(Aprobacion Aprobacion) {
		em.merge(Aprobacion);
	}
	
	public void delete(Aprobacion Aprobacion) {
		em.remove(Aprobacion);
	}
}
