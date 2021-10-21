package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.DetalleSalida;

@ApplicationScoped
public class DetalleSalidaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DetalleSalida> findAllDetalleSalidas(){
		return em.createNamedQuery("DetalleSalida.findAll").getResultList();
	}
	
	public DetalleSalida findById(Long id) {
		return em.find(DetalleSalida.class, id);
	}
	
	public void save(DetalleSalida DetalleSalida) {
		em.persist(DetalleSalida);
	}
	
	public void update(DetalleSalida DetalleSalida) {
		em.merge(DetalleSalida);
	}
	
	public void delete(DetalleSalida DetalleSalida) {
		em.remove(DetalleSalida);
	}
}
