package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.TipoSalida;

@ApplicationScoped
public class TipoSalidaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<TipoSalida> findAllTipoSalidas(){
		return em.createNamedQuery("TipoSalida.findAll").getResultList();
	}
	
	public TipoSalida findById(Long id) {
		return em.find(TipoSalida.class, id);
	}
	
	public void save(TipoSalida TipoSalida) {
		em.persist(TipoSalida);
	}
	
	public void update(TipoSalida TipoSalida) {
		em.merge(TipoSalida);
	}
	
	public void delete(TipoSalida TipoSalida) {
		em.remove(TipoSalida);
	}
}
