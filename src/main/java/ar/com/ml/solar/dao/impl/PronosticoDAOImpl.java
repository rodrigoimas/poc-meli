package ar.com.ml.solar.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ar.com.ml.solar.dao.PronosticoDAO;
import ar.com.ml.solar.model.Pronostico;

public class PronosticoDAOImpl implements PronosticoDAO {

	SessionFactory sf;
	EntityManager em;
	
	private EntityManager getEntityManager()	{
		if (null==this.em)	{
	        this.sf = getSessionFactory();
	        this.em = sf.createEntityManager();
			this.em.setFlushMode(FlushModeType.COMMIT);
		}	
			return this.em;
	}
	
	private SessionFactory getSessionFactory()	{
		if (null==this.sf)	{
			this.sf = new Configuration().configure().buildSessionFactory(getServiceRegistry());
		}
		return this.sf;
	}
	
	private StandardServiceRegistry getServiceRegistry() {
		Map<String,String> jdbcUrlSettings = new HashMap<>();
		String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
		if (null != jdbcDbUrl) {
		  jdbcUrlSettings.put("hibernate.connection.url", jdbcDbUrl);
		  System.out.println("JDBC_DATABASE_URL ----> " + jdbcDbUrl);
		}
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
		    configure("hibernate.cfg.xml").
		    applySettings(jdbcUrlSettings).
		    build();
		return registry;
	}

	@Override
	public Pronostico buscarPronostico(long dia) {
		// TODO Auto-generated method stub
//		Criteria filtro;
//		em.createQuery(filtro);
//		em.createQuery()
//		
//		
//		bob bb=null;
//
//		CriteriaQuery<Pronostico> criteria = em.getCriteriaBuilder().createQuery(Pronostico.class);
//		criteria.equals(obj)
//		em.createQuery(arg0)
//		em.getCriteriaBuilder().createQuery();
//	    Criteria criteria = em.getCriteriaBuilder().
//	    getEntityManager().createQuery(arg0)
//	    criteria.add(Restrictions.eq("id",dia));
//	    bb = (bob) criteria.uniqueResult();
		return null;
	}

	@Override
	public void grabarPronostico(Pronostico pronostico) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(pronostico);
		tx.commit();
	}

	@Override
	public void borrarPronosticos() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		CriteriaDelete<Pronostico> criteria = getEntityManager().getCriteriaBuilder().createCriteriaDelete(Pronostico.class);
        criteria.from(Pronostico.class);
        getEntityManager().createQuery(criteria).executeUpdate();
        tx.commit();
	}

}
