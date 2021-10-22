package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gt.gob.rgm.inv.model.UnidadMedida;

@ApplicationScoped
public class UnidadMedidaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<UnidadMedida> findAllUnidadesMedida(Integer page, Integer size){
		Query query = em.createNamedQuery("UnidadMedida.findAll"); 
		if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public Long countAllUnidadesMedida() {
		Query query = em.createNamedQuery("UnidadMedida.countAll");
		return (Long) query.getSingleResult();
	}
	
	public UnidadMedida findById(Integer id) {
		return em.find(UnidadMedida.class, id);
	}
	
	public void save(UnidadMedida unidadMedida) {
		em.persist(unidadMedida);
	}
	
	public void update(UnidadMedida unidadMedida) {
		em.merge(unidadMedida);
	}
	
	public void delete(UnidadMedida unidadMedida) {
		em.remove(unidadMedida);
	}
}
