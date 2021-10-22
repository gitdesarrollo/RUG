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

import gt.gob.rgm.inv.model.Ingreso;

@ApplicationScoped
public class IngresoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Ingreso> findAllIngresos(){
		return em.createNamedQuery("Ingreso.findAll").getResultList();
	}
	
	public Long countWithFilter(Map<String, Object> params) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Ingreso> ingresos = criteria.from(Ingreso.class);
        Predicate restrictions = null;
		
		restrictions = cb.equal(cb.literal(1), 1);
        
        if(params.get("estado") != null && !params.get("estado").equals("T")) {  	
        	restrictions = cb.and(restrictions, cb.equal(ingresos.get("estado"), params.get("estado")));
        }
        if(params.get("fechaInicio")!=null && params.get("fechaInicio")!=null) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
	        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
	        
	        restrictions = cb.and(restrictions, cb.between(ingresos.get("fecha"), fechaInicio, fechaFin));
        }
        
        criteria.select(cb.construct(Long.class, cb.count(ingresos.get("correlativo"))))
        	.where(restrictions);
        
        TypedQuery<Long> query = em.createQuery(criteria);
		return (Long) query.getSingleResult();
        
	}
	
	public List<Ingreso> findWithFilter(Map<String, Object> params, Integer page, Integer size) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ingreso> criteria = cb.createQuery(Ingreso.class);
        Root<Ingreso> ingresos = criteria.from(Ingreso.class);
        Predicate restrictions = null;
		
		restrictions = cb.equal(cb.literal(1), 1);
        
        if(params.get("estado") != null && !params.get("estado").equals("T")) {        	
        	restrictions = cb.and(restrictions, cb.equal(ingresos.get("estado"), params.get("estado")));
        }
        if(params.get("fechaInicio")!=null && params.get("fechaInicio")!=null) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
	        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
	        
	        restrictions = cb.and(restrictions, cb.between(ingresos.get("fecha"), fechaInicio, fechaFin));
        }
        
        criteria.select(ingresos)
        	.where(restrictions);
        
        TypedQuery<Ingreso> query = em.createQuery(criteria);
        if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public List<Ingreso> findAllByYear(String year){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ingreso> criteria = cb.createQuery(Ingreso.class);
        Root<Ingreso> ingresos = criteria.from(Ingreso.class);
        Predicate anio = cb.equal(
        		cb.function("TO_CHAR",
        					String.class,ingresos.get("fecha"), 
        					cb.literal("yyyy")), year);
        
        criteria.select(ingresos)
    	.where(anio);
    
        TypedQuery<Ingreso> query = em.createQuery(criteria);
        
        return query.getResultList();
        
	}
	
	public List<Ingreso> findByParams(Map<String,Object> params) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ingreso> criteria = cb.createQuery(Ingreso.class);
        Root<Ingreso> ingresos = criteria.from(Ingreso.class);
        Predicate estado = null;
        if(params.get("estado").equals("T")) {
        	estado = cb.isNotNull(ingresos.get("estado"));
        } else {
        	estado = cb.equal(ingresos.get("estado"), params.get("estado"));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
        
        criteria.select(ingresos)
        	.where(
        			estado,
        			cb.between(ingresos.get("fecha"), fechaInicio, fechaFin)
        		  );
        return em.createQuery(criteria).getResultList();
	}
	
	public Ingreso findById(Long id) {
		return em.find(Ingreso.class, id);
	}
	
	public void save(Ingreso Ingreso) {
		em.persist(Ingreso);
	}
	
	public void update(Ingreso Ingreso) {
		em.merge(Ingreso);
	}
	
	public void delete(Ingreso Ingreso) {
		em.remove(Ingreso);
	}
}
