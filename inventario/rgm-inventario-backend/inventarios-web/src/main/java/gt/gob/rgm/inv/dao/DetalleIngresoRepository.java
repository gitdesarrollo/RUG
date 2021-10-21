package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import gt.gob.rgm.inv.model.DetalleIngreso;

@ApplicationScoped
public class DetalleIngresoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DetalleIngreso> findAllDetalleIngresos(){
		return em.createNamedQuery("DetalleIngreso.findAll").getResultList();
	}
	
	public List<DetalleIngreso> findByCodigo(String codigo){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleIngreso> criteria = cb.createQuery(DetalleIngreso.class);
        Root<DetalleIngreso> detalleIngreso = criteria.from(DetalleIngreso.class);
        Predicate filtro = cb.and(
    		cb.equal(detalleIngreso.get("codigoArticulo"), codigo),
    		cb.greaterThan(detalleIngreso.get("cantidad"), 0)
		);
        
        criteria.select(detalleIngreso).where(filtro).orderBy(cb.asc(detalleIngreso.get("fechaVencimiento")));
        
        return em.createQuery(criteria).getResultList();
	}
	
	public DetalleIngreso findById(Long id) {
		return em.find(DetalleIngreso.class, id);
	}
	
	public void save(DetalleIngreso DetalleIngreso) {
		em.persist(DetalleIngreso);
	}
	
	public void update(DetalleIngreso DetalleIngreso) {
		em.merge(DetalleIngreso);
	}
	
	public void delete(DetalleIngreso DetalleIngreso) {
		em.remove(DetalleIngreso);
	}
}
