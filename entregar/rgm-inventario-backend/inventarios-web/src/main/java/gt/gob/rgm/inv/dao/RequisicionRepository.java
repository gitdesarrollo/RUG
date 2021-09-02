package gt.gob.rgm.inv.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import gt.gob.rgm.inv.model.Requisicion;

@ApplicationScoped
public class RequisicionRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Requisicion> findAllRequisiciones(){
		return em.createNamedQuery("Requisicion.findAll").getResultList();
	}
	
	public List<Requisicion> findAllByYear(String year){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Requisicion> criteria = cb.createQuery(Requisicion.class);
        Root<Requisicion> requisiciones = criteria.from(Requisicion.class);
        Predicate anio = cb.equal(
        		cb.function("TO_CHAR",
        					String.class,requisiciones.get("fecha"), 
        					cb.literal("yyyy")), year);
        
        criteria.select(requisiciones)
    	.where(anio);
    
        TypedQuery<Requisicion> query = em.createQuery(criteria);
        
        return query.getResultList();
        
	}
	
	public List<Requisicion> findByParams(Map<String,Object> params, Integer page, Integer size) throws ParseException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Requisicion> criteria = cb.createQuery(Requisicion.class);
        Root<Requisicion> requisiciones = criteria.from(Requisicion.class);
        Predicate estado = null;
        if(params.get("estado").equals("T")) {
        	estado = cb.isNotNull(requisiciones.get("estado"));
        } else {
        	estado = cb.equal(requisiciones.get("estado"), params.get("estado"));
        }
        if(params.get("fechaInicio") != null && !params.get("fechaInicio").toString().isEmpty() && !params.get("fechaInicio").toString().equals("null")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
            Date fechaFin = formatter.parse(params.get("fechaFin").toString());
            estado = cb.and(estado,
        		cb.between(requisiciones.get("fecha"), fechaInicio, fechaFin)
            );
        }
        
        criteria.select(requisiciones)
        	.where(estado)
        	.orderBy(cb.desc(requisiciones.get("fecha")));
        
        TypedQuery<Requisicion> query = em.createQuery(criteria);
        if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
    }
	
	public Long countByParams(Map<String, Object> params) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Requisicion> requisiciones = criteria.from(Requisicion.class);
        Predicate estado = null;
        if(params.get("estado").equals("T")) {
        	estado = cb.isNotNull(requisiciones.get("estado"));
        } else {
        	estado = cb.equal(requisiciones.get("estado"), params.get("estado"));
        }
        if(params.get("fechaInicio") != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
            Date fechaFin = formatter.parse(params.get("fechaFin").toString());
            estado = cb.and(estado,
        		cb.between(requisiciones.get("fecha"), fechaInicio, fechaFin)
            );
        }
        
        criteria.select(cb.construct(Long.class, cb.count(requisiciones.get("requisicionId"))))
        	.where(estado);
        
        TypedQuery<Long> query = em.createQuery(criteria);
		return (Long) query.getSingleResult();
	}
	
	public Requisicion findById(Long id) {
		return em.find(Requisicion.class, id);
	}
	
	public void save(Requisicion Requisicion) {
		em.persist(Requisicion);
	}
	
	public void update(Requisicion Requisicion) {
		em.merge(Requisicion);
	}
	
	public void delete(Requisicion Requisicion) {
		em.remove(Requisicion);
	}
}