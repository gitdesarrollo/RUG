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

import gt.gob.rgm.inv.model.Despacho;

@ApplicationScoped
public class DespachoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Despacho> findAllDespachos(){
		return em.createNamedQuery("Despacho.findAll").getResultList();
	}
	
	public Long countWithFilter(Map<String, Object> params) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Despacho> despachos = criteria.from(Despacho.class);
        Predicate restrictions = null;
		
		restrictions = cb.equal(cb.literal(1), 1);
                
                
        
		if(params.get("estado") !=null && !params.get("estado").equals("T")) {
        	restrictions = cb.and(restrictions, cb.equal(despachos.get("estado"), params.get("estado")));
        }
		if(params.get("fechaInicio")!=null && params.get("fechaFin")!=null) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
	        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
	        
	        restrictions = cb.and(restrictions, cb.between(despachos.get("fecha"), fechaInicio, fechaFin));
                
                
        }
                
                
        
        criteria.select(cb.construct(Long.class, cb.count(despachos.get("correlativo"))))
        	.where(restrictions);
        
        TypedQuery<Long> query = em.createQuery(criteria);
        
		return (Long) query.getSingleResult();
        
	}
	
	public List<Despacho> findWithFilter(Map<String, Object> params, Integer page, Integer size) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Despacho> criteria = cb.createQuery(Despacho.class);
        Root<Despacho> despachos = criteria.from(Despacho.class);
        Predicate restrictions = null;
		
		restrictions = cb.equal(cb.literal(1), 1);
        
        if(params.get("estado") !=null && !params.get("estado").equals("T")) {        	
        	restrictions = cb.and(restrictions, cb.equal(despachos.get("estado"), params.get("estado")));
        }
        if(params.get("fechaInicio")!=null && params.get("fechaFin")!=null) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
	        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
	        
	        restrictions = cb.and(restrictions, cb.between(despachos.get("fecha"), fechaInicio, fechaFin));
        }
        
        criteria.select(despachos)
        	.where(restrictions);
        
        TypedQuery<Despacho> query = em.createQuery(criteria);
            
        if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
        
                
		return query.getResultList();
                
                
	}
	
	public List<Despacho> findAllByYear(String year){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Despacho> criteria = cb.createQuery(Despacho.class);
        Root<Despacho> despachos = criteria.from(Despacho.class);
        Predicate anio = cb.equal(
        		cb.function("TO_CHAR",
        					String.class,despachos.get("fecha"), 
        					cb.literal("yyyy")), year);
        
        criteria.select(despachos)
    	.where(anio);
    
        TypedQuery<Despacho> query = em.createQuery(criteria);
        
        return query.getResultList();
        
	}
	
	public List<Despacho> findByParams(Map<String,Object> params) throws ParseException {
		Integer page = (Integer) params.get("page");
		Integer size = (Integer) params.get("size");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Despacho> criteria = cb.createQuery(Despacho.class);
        Root<Despacho> despachos = criteria.from(Despacho.class);
        Predicate estado = null;
        if(params.get("estado").equals("T")) {
        	estado = cb.isNotNull(despachos.get("estado"));
        } else {
        	estado = cb.equal(despachos.get("estado"), params.get("estado"));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
        
        criteria.select(despachos)
        	.where(
        			estado,
        			cb.between(despachos.get("fecha"), fechaInicio, fechaFin)
        		  )
        	.orderBy(cb.desc(despachos.get("fecha")));
        
        TypedQuery<Despacho> query = em.createQuery(criteria);
        if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public Despacho findById(Long id) {
		return em.find(Despacho.class, id);
	}
	
	public void save(Despacho Despacho) {
		em.persist(Despacho);
	}
	
	public void update(Despacho Despacho) {
		em.merge(Despacho);
	}
	
	public void delete(Despacho Despacho) {
		em.remove(Despacho);
	}
}
