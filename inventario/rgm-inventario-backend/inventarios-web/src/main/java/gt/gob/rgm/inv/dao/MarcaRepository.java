package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gt.gob.rgm.inv.model.Marca;

@ApplicationScoped
public class MarcaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Marca> findAllMarcas(Integer page, Integer size){
		
		Query query = em.createNamedQuery("Marca.findAll");
		if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public Long countAllMarcas() {
		Query query = em.createNamedQuery("Marca.countAll");
		return (Long) query.getSingleResult();
	}
	
	public Marca findById(Integer id) {
		return em.find(Marca.class, id);
	}
	
	public void save(Marca marca) {
		em.persist(marca);
	}
	
	public void update(Marca marca) {
		em.merge(marca);
	}
	
	public void delete(Marca marca) {
		em.remove(marca);
	}
}
