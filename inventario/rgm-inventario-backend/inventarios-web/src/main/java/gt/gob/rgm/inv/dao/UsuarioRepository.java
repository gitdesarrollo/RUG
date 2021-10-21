package gt.gob.rgm.inv.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import gt.gob.rgm.inv.model.Usuario;
import gt.gob.rgm.inv.util.MessagesInv;

@ApplicationScoped
public class UsuarioRepository {

	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios(){
		return em.createNamedQuery("Usuario.findAll").getResultList();
	}

    public Usuario findById(Integer id) {
        return em.find(Usuario.class, id);
    }
    
    @Transactional
    public void update(Usuario usuario) {
    	em.merge(usuario);
    }
    
    public void save(Usuario usuario) {
    	em.persist(usuario);
    }
    
    public void delete(Usuario usuario) {
		em.remove(usuario);
	}

    public List<Usuario> findByStatusOrderedByNombre(String status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuarios = criteria.from(Usuario.class);
        criteria.select(usuarios)
        	.where(cb.equal(usuarios.get("estado"), status))
        	.orderBy(cb.asc(usuarios.get("creado")));
        return em.createQuery(criteria).getResultList();
    }
    
    public Usuario findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuarios = criteria.from(Usuario.class);
        criteria.select(usuarios)
        	.where(cb.equal(usuarios.get("email"), email));
        Usuario encontrado = null;
		try {
			encontrado = em.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
		}
        return encontrado;
    }
    
    public List<Usuario> findByRole(String role) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuarios = criteria.from(Usuario.class);
        criteria.select(usuarios)
        	.where(cb.and(cb.equal(usuarios.get("rol"), role),
        			      cb.equal(usuarios.get("estado"), MessagesInv.ACTIVO)));
        	
        
		return em.createQuery(criteria).getResultList();		
    }
}
