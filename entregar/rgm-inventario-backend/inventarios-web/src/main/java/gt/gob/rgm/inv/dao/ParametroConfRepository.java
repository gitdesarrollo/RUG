package gt.gob.rgm.inv.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import gt.gob.rgm.inv.model.ParametroConf;

@ApplicationScoped
public class ParametroConfRepository {

	@PersistenceContext
	private EntityManager em;
	
	public ParametroConf findByKey(String key) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ParametroConf> criteria = cb.createQuery(ParametroConf.class);
        Root<ParametroConf> parametros = criteria.from(ParametroConf.class);
        criteria.select(parametros)
        	.where(cb.equal(parametros.get("cveParametro"), key));
        return em.createQuery(criteria).getResultList().get(0);
    }
}
