package gt.gob.rgm.inv.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import gt.gob.rgm.inv.model.Articulo;

@ApplicationScoped
public class ArticuloRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Articulo> findAllArticulos(){
		return em.createNamedQuery("Articulo.findAll").getResultList();
	}
	
	public List<Articulo> findReporteInventario(Map<String,Object> params){
		Integer page = (Integer) params.get("page");
		Integer size = (Integer) params.get("size");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Articulo> criteria = cb.createQuery(Articulo.class);
        Root<Articulo> articulos = criteria.from(Articulo.class);
        Predicate tipo = cb.equal(articulos.get("tipoArticulo").get("id"), params.get("tipoArticulo"));          
		
        if(Long.valueOf(params.get("tipoArticulo").toString())==0L) {
        	criteria.select(articulos);
        } else {        	
        	criteria.select(articulos).where(tipo);
        }
    
	    TypedQuery<Articulo> query = em.createQuery(criteria);
	    if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public List<Articulo> findReporteMinimos(Map<String,Object> params){
		Integer page = (Integer) params.get("page");
		Integer size = (Integer) params.get("size");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Articulo> criteria = cb.createQuery(Articulo.class);
        Root<Articulo> articulos = criteria.from(Articulo.class);
        Predicate tipo = cb.lessThanOrEqualTo(articulos.get("stock"), articulos.get("minimoExistencia"));
		
        criteria.select(articulos)
    		.where(cb.and(tipo, cb.notEqual(articulos.get("stock"), 0L)));
    
	    TypedQuery<Articulo> query = em.createQuery(criteria);
	    if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}
	
	public List<Articulo> findWithFilter(Map<String, Object> filter, Integer page, Integer size) throws ParseException{
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Articulo> criteria = cb.createQuery(Articulo.class);
		Root<Articulo> articulos = criteria.from(Articulo.class);
		Predicate restrictions = null;
		
		restrictions = cb.equal(articulos.get("estado"), "A");
					
	    filter.remove("fechaInicio");
        filter.remove("fechaFin");
        
        if(filter.get("texto")!=null) {
        	restrictions = cb.and(restrictions,cb.or(cb.like(articulos.get("codigo"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("descripcion"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("tipoArticulo").get("nombre"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("marca").get("nombre"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("unidadMedida").get("nombre"), "%" + filter.get("texto") + "%")));
        }
        filter.remove("texto");
		
		for (Map.Entry<String, Object> entry : filter.entrySet()) {
			restrictions = cb.and(restrictions, cb.equal(articulos.get(""+entry.getKey()+""), entry.getValue()));
	    }
		
		//criteria = criteria.select(articulos);
		Expression<String> codeStr = cb.substring(articulos.<String>get("codigo"), cb.sum(cb.locate(articulos.<String>get("codigo"), "-"),1)).as(String.class);
		Expression<Integer> codeInt = cb.function("TO_NUMBER", Integer.class, cb.substring(articulos.<String>get("codigo"), cb.literal(1), cb.sum(cb.locate(articulos.<String>get("codigo"), "-"),-1))).as(Integer.class);
		
		criteria = criteria.select(cb.construct(Articulo.class, 
					articulos.get("codigo"),
					codeStr,
					codeInt,
					articulos.get("codigoBarras"),
					articulos.get("descripcion"),
					articulos.get("fechaVencimiento"),
					articulos.get("minimoExistencia"),
					articulos.get("perecedero"),
					articulos.get("valor"),
					articulos.get("correlativo"),
					articulos.get("stock"),
					articulos.get("estado"),
					articulos.get("marcaId"),
					articulos.get("marca"),
					articulos.get("proveedorId"),
					articulos.get("proveedor"),
					articulos.get("tipoArticuloId"),
					articulos.get("tipoArticulo"),
					articulos.get("unidadMedidaId"),
					articulos.get("unidadMedida")
					));
		
		if(restrictions != null) {
			criteria = criteria.where(restrictions)
					.orderBy(cb.asc(codeStr), cb.asc(codeInt));					
		}
		TypedQuery<Articulo> query = em.createQuery(criteria);
		if(page != null) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
		}			
		
		return query.getResultList();
	}
	
	public Long countWithFilter(Map<String, Object> filter) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<Articulo> articulos = criteria.from(Articulo.class);
		Predicate restrictions = null;
		
		restrictions = cb.equal(articulos.get("estado"), "A");
		
		filter.remove("fechaInicio");
        filter.remove("fechaFin");
        
        if(filter.get("texto")!=null) {
        	restrictions = cb.and(restrictions,cb.or(cb.like(articulos.get("codigo"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("descripcion"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("tipoArticulo").get("nombre"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("marca").get("nombre"), "%" + filter.get("texto") + "%"),
					 cb.like(articulos.get("unidadMedida").get("nombre"), "%" + filter.get("texto") + "%")));
        }
        filter.remove("texto");
		
		for (Map.Entry<String, Object> entry : filter.entrySet()) {
			restrictions = cb.and(restrictions, cb.equal(articulos.get(""+entry.getKey()+""), entry.getValue()));
	    }
		
		criteria = criteria.select(cb.construct(Long.class, cb.count(articulos.get("codigo"))));
		if(restrictions != null) {
			criteria = criteria.where(restrictions);
		}
		TypedQuery<Long> query = em.createQuery(criteria);
		return (Long) query.getSingleResult();
	}
	
	public Articulo findById(String id) {
		return em.find(Articulo.class, id);
	}
	
	public void save(Articulo articulo) {
		em.persist(articulo);
	}
	
	public void update(Articulo articulo) {
		em.merge(articulo);
	}
	
	public void delete(Articulo articulo) {
		em.remove(articulo);
	}
}
