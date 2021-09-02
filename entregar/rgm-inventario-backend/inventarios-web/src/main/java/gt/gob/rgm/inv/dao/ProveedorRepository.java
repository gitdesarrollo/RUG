package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gt.gob.rgm.inv.model.Proveedor;

@ApplicationScoped
public class ProveedorRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save(Proveedor proveedor) {
		em.persist(proveedor);
	}
	
	public void update(Proveedor proveedor) {
		em.merge(proveedor);
	}
	
	public void delete(Proveedor proveedor) {
		em.remove(proveedor);
	}
	
	public Proveedor findById(Integer id) {
		return em.find(Proveedor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Proveedor> findAllProveedores(){
		return em.createNamedQuery("Proveedor.findAll").getResultList();
	}
}
