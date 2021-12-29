package gt.gob.rgm.inv.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import gt.gob.rgm.inv.model.Kardex;

@ApplicationScoped
public class KardexRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Kardex> findAllKardex(Integer page, Integer size){
		Query query = em.createNamedQuery("Kardex.findAll");
		
		if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public Long findValueByParams(Map<String, Object> params, String tipo) throws ParseException{
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<Kardex> kardexes = criteria.from(Kardex.class);
        
        Predicate articulo = cb.equal(kardexes.get("codigoArticulo"), params.get("codigoArticulo"));
        if(params.get("fechaInicio") != null && !params.get("fechaInicio").toString().isEmpty() && !params.get("fechaInicio").toString().equals("null")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
            Date fechaFin = formatter.parse(params.get("fechaFin").toString());
            articulo = cb.and(articulo,
        		cb.between(kardexes.get("fecha"), fechaInicio, fechaFin)
    		);
        }
		
        TypedQuery<Tuple> queryType;
        
        if(tipo.equalsIgnoreCase("ingreso")) {
        	criteria.multiselect(cb.sum(kardexes.get("entrada")).alias("entrada"))
				.where(articulo);
        	
        	queryType = em.createQuery(criteria);
        	
        	if(queryType.getResultList().size()>0 && queryType.getSingleResult().get("entrada") != null)
        		return ((BigDecimal) queryType.getSingleResult().get("entrada")).longValue();
        	
        } else if(tipo.equalsIgnoreCase("salida")) {
        	criteria.multiselect(cb.sum(kardexes.get("salida")).alias("salida"))
			.where(articulo);
        	
        	queryType = em.createQuery(criteria);
        	
        	if(queryType.getResultList().size()>0 && queryType.getSingleResult().get("salida") != null)
        		return ((BigDecimal) queryType.getSingleResult().get("salida")).longValue();
        	
        } else if(tipo.equalsIgnoreCase("inicial")) {
        	
        	criteria.multiselect(cb.sum(
        							cb.diff(cb.<Long>selectCase().when(kardexes.get("existencia").isNotNull(), cb.function("TO_NUMBER",Long.class, kardexes.get("existencia"))).otherwise(cb.literal(0L)),
        									cb.<Long>selectCase().when(kardexes.get("entrada").isNotNull(), cb.function("TO_NUMBER", Long.class, kardexes.get("entrada"))).otherwise(cb.literal(0L))),
        							cb.<Long>selectCase().when(kardexes.get("salida").isNotNull(), cb.function("TO_NUMBER", Long.class, kardexes.get("salida"))).otherwise(cb.literal(0L))).alias("total"))
        		.where(articulo)
        		.orderBy(cb.asc(kardexes.get("kardexId")));
        	
        	queryType = em.createQuery(criteria);
        	
        	if(queryType.getResultList().size()>0)
        		return ((BigDecimal) queryType.getResultList().get(0).get("total")).longValue();
        	
        } 
        else if(tipo.equalsIgnoreCase("inicial_reporte")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = formatter.parse("2017-01-01");
            Date fechaFin = formatter.parse("2020-12-31");
            
            articulo = cb.and(articulo,
        		cb.between(kardexes.get("fecha"), fechaInicio, fechaFin)
    		);
            
            
        	criteria.multiselect(cb.sum(
        							cb.diff(cb.<Long>selectCase().when(kardexes.get("entrada").isNotNull(), cb.function("TO_NUMBER",Long.class, kardexes.get("entrada"))).otherwise(cb.literal(0L)),
        								cb.<Long>selectCase().when(kardexes.get("salida").isNotNull(), cb.function("TO_NUMBER", Long.class, kardexes.get("salida"))).otherwise(cb.literal(0L)))).alias("total"))
        		.where(articulo)
        		.orderBy(cb.asc(kardexes.get("kardexId")));
        	
        	queryType = em.createQuery(criteria);
        	try{
                    if(queryType.getResultList().size()>0)
        		return ((BigDecimal) queryType.getResultList().get(0).get("total")).longValue();
                }
                catch(Exception e) {
                    return 0L;
                }
        	
        }else if(tipo.equalsIgnoreCase("final")) {
        	
        	criteria.multiselect(kardexes.get("existencia").alias("existencia"))
        		.where(articulo)
        		.orderBy(cb.desc(kardexes.get("kardexId")));
        	
        	queryType = em.createQuery(criteria);
        	
        	if(queryType.getResultList().size()>0)
        		return ((BigDecimal) queryType.getResultList().get(0).get("existencia")).longValue();
        }
                       
        return 0L;
	}
	
	public List<String> findByParams(Map<String, Object> params) throws ParseException{		
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Kardex> criteria = cb.createQuery(Kardex.class);
        Root<Kardex> kardexes = criteria.from(Kardex.class);
		       
    	if(params.get("fechaInicio") != null && !params.get("fechaInicio").toString().isEmpty() && !params.get("fechaInicio").toString().equals("null")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
            Date fechaFin = formatter.parse(params.get("fechaFin").toString());
            Predicate fechas = cb.between(kardexes.get("fecha"), fechaInicio, fechaFin);
            
            criteria.select(kardexes.get("codigoArticulo"))
    		.distinct(true)
    		.where(fechas)
    		.orderBy(cb.asc(kardexes.get("codigoArticulo")));
        } else {        	
        	criteria.select(kardexes.get("codigoArticulo"))
        		.distinct(true)
        		.orderBy(cb.asc(kardexes.get("codigoArticulo")));
        }        		
        
	    Query query = em.createQuery(criteria);
	    
		return query.getResultList();
	}	
	
	public List<Kardex> findByParams(Map<String, Object> params, Integer page, Integer size) throws ParseException{
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Kardex> criteria = cb.createQuery(Kardex.class);
        Root<Kardex> kardexes = criteria.from(Kardex.class);
        
        Predicate articulo = cb.equal(kardexes.get("codigoArticulo"), params.get("codigoArticulo"));
        if(params.get("fechaInicio") != null && !params.get("fechaInicio").toString().isEmpty() && !params.get("fechaInicio").toString().equals("null")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = formatter.parse(params.get("fechaInicio").toString());
            Date fechaFin = formatter.parse(params.get("fechaFin").toString());
            articulo = cb.and(articulo,
        		cb.between(kardexes.get("fecha"), fechaInicio, fechaFin)
    		);
        }
        
        criteria.select(kardexes)
        		.where(articulo)
        		.orderBy(cb.asc(kardexes.get("kardexId")));
    
	    TypedQuery<Kardex> query = em.createQuery(criteria);
	    if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public Kardex findById(Integer id) {
		return em.find(Kardex.class, id);
	}
	
	public void save(Kardex kardex) {
		em.persist(kardex);
	}
	
	public void delete(Kardex kardex) {
		em.remove(kardex);
	}
}
