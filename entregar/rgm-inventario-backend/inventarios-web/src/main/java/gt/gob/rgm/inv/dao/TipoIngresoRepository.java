package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.TipoIngreso;

@ApplicationScoped
public class TipoIngresoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<TipoIngreso> findAllTipoIngresos(){
		return em.createNamedQuery("TipoIngreso.findAll").getResultList();
	}
	
	public TipoIngreso findById(Long id) {
		return em.find(TipoIngreso.class, id);
	}
	
	public void save(TipoIngreso TipoIngreso) {
		em.persist(TipoIngreso);
	}
	
	public void update(TipoIngreso TipoIngreso) {
		em.merge(TipoIngreso);
	}
	
	public void delete(TipoIngreso TipoIngreso) {
		em.remove(TipoIngreso);
	}
}