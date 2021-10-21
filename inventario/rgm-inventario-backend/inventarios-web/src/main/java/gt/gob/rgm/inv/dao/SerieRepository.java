package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gt.gob.rgm.inv.model.Serie;

@ApplicationScoped
public class SerieRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Serie> findAllSeries(){
		return em.createNamedQuery("Serie.findAll").getResultList();
	}
	
	public List<String> findAllColumns(){
		String sql = "select view_name from all_views where OWNER = 'RUG'";
		
		Query q = em.createNativeQuery(sql);
		List<String> result = q.getResultList();
		
		return result;
	}
	
	public Serie findById(Integer id) {
		return em.find(Serie.class, id);
	}
	
	public void save(Serie Serie) {
		em.persist(Serie);
	}
	
	public void delete(Serie Serie) {
		em.remove(Serie);
	}
}