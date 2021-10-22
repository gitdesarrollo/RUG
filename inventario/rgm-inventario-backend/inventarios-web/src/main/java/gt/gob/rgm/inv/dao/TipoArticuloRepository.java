package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.TipoArticulo;

@ApplicationScoped
public class TipoArticuloRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<TipoArticulo> findAllTiposArticulo(){
		return em.createNamedQuery("TipoArticulo.findAll").getResultList();
	}
	
	public TipoArticulo findById(Integer id) {
		return em.find(TipoArticulo.class, id);
	}
	
	public void save(TipoArticulo tipoArticulo) {
		em.persist(tipoArticulo);
	}
	
	public void update(TipoArticulo tipoArticulo) {
		em.merge(tipoArticulo);
	}
	
	public void delete(TipoArticulo tipoArticulo) {
		em.persist(tipoArticulo);
	}
}
