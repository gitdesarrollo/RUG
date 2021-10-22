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

import gt.gob.rgm.inv.model.Salida;

@ApplicationScoped
public class SalidaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Salida> findAllSalidas(){
		return em.createNamedQuery("Salida.findAll").getResultList();
	}
	
	public Long countWithFilter(Map<String, Object> params) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Salida> salidas = criteria.from(Salida.class);
        Predicate restrictions = null;
		
		restrictions = cb.equal(cb.literal(1), 1);
        
        if(params.get("estado") !=null && !params.get("estado").equals("T")) {     	
        	restrictions = cb.and(restrictions, cb.equal(salidas.get("estado"), params.get("estado")));
        }
        if(params.get("fechaInicio")!=null && params.get("fechaFin")!=null) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
	        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
	        
	        restrictions = cb.and(restrictions, cb.between(salidas.get("fecha"), fechaInicio, fechaFin));
        }
        
        criteria.select(cb.construct(Long.class, cb.count(salidas.get("correlativo"))))
        	.where(restrictions);
        
        TypedQuery<Long> query = em.createQuery(criteria);
		return (Long) query.getSingleResult();
        
	}
	
	public List<Salida> findWithFilter(Map<String, Object> params, Integer page, Integer size) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Salida> criteria = cb.createQuery(Salida.class);
        Root<Salida> salidas = criteria.from(Salida.class);
        Predicate restrictions = null;
		
		restrictions = cb.equal(cb.literal(1), 1);
        
        if(params.get("estado") !=null && !params.get("estado").equals("T")) {        	
        	restrictions = cb.and(restrictions, cb.equal(salidas.get("estado"), params.get("estado")));
        }
        if(params.get("fechaInicio")!=null && params.get("fechaFin")!=null) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
	        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
	        
	        restrictions = cb.and(restrictions, cb.between(salidas.get("fecha"), fechaInicio, fechaFin));
        }
        
        criteria.select(salidas)
        	.where(restrictions);
        
        TypedQuery<Salida> query = em.createQuery(criteria);
        if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public List<Salida> findAllByYear(String year){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Salida> criteria = cb.createQuery(Salida.class);
        Root<Salida> salidas = criteria.from(Salida.class);
        Predicate anio = cb.equal(
        		cb.function("TO_CHAR",
        					String.class,salidas.get("fecha"), 
        					cb.literal("yyyy")), year);
        
        criteria.select(salidas)
    	.where(anio);
    
        TypedQuery<Salida> query = em.createQuery(criteria);
        
        return query.getResultList();
        
	}
	
	public List<Salida> findByParams(Map<String,Object> params) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Salida> criteria = cb.createQuery(Salida.class);
        Root<Salida> salidas = criteria.from(Salida.class);
        Predicate estado = null;
        if(params.get("estado").equals("T")) {
        	estado = cb.isNotNull(salidas.get("estado"));
        } else {
        	estado = cb.equal(salidas.get("estado"), params.get("estado"));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
        Date fechaFin = formatter.parse(params.get("fechaFin").toString());
        
        criteria.select(salidas)
        	.where(
        			estado,
        			cb.between(salidas.get("fecha"), fechaInicio, fechaFin)
        		  )
        	.orderBy(cb.desc(salidas.get("fecha")));
        return em.createQuery(criteria).getResultList();
	}
	
	public Salida findById(Long id) {
		return em.find(Salida.class, id);
	}
	
	public void save(Salida Salida) {
		em.persist(Salida);
	}
	
	public void update(Salida Salida) {
		em.merge(Salida);
	}
	
	public void delete(Salida Salida) {
		em.remove(Salida);
	}
}
