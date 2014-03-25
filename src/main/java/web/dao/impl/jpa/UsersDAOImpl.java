
package web.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dao.UsersDAO;
import web.entity.Boards_Users;
import web.entity.Users;



@Repository
public class UsersDAOImpl implements UsersDAO {
	
	/**
	 * The JPA entity manager
	 */
//        @Autowired
	private EntityManager entityManager;

	public void save(Object entity) {
        Session session = getHibernateSession();
        //session.beginTransaction();
        session.saveOrUpdate(entity);
        //session.getTransaction().commit();
        //return entity;
	}

    public void remove(Object entity) {
        entityManager.remove(entity);
    }

    public boolean doesLoginIdExists(String email){
        email = email.toLowerCase();
        String queryString = "SELECT user FROM Users AS user " +
                     "WHERE user.email = :email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        List<?> list = query.getResultList();
        if(list == null || list.size() == 0){
            return false;
        }else{
            return true;
        }
    }

    public Users getUserByLoginId(String email){
        Users user = null;
        email = email.toLowerCase();
        System.out.println("email entered for lookup was : "+email);
        String queryString = "SELECT user FROM Users AS user " +
                     "WHERE user.email = :email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        List<?> list = query.getResultList();
        //query.getSingleResult(); can also be used.{M-A}
        //getResultList() is never returned null, despite that:{M-A}
        if(list != null && list.size() > 0){
            user = (Users)list.get(0);
            System.out.println("email retrieved is" + user.getEmail() );
        }else {
            System.out.println("User not found by email");
        }
        return user;
    }

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Helper method to return the hibernate session from the JPA
	 * entity manager implementation.
	 * 
	 * @return the hibernate {#link Session}
	 */
	protected Session getHibernateSession() {
           
		return entityManager.unwrap(Session.class);
	}

    public Object findById(Object object, Long id) {
        Session session = getHibernateSession();
        Object instance = null;
        instance = session.get(object.getClass().getName(), id);
        return instance;
    }

    public List findByProperty(Object hibernateObject, String propertyName,
                               Object value) {
        Session session = getHibernateSession();
        List results = new ArrayList();
        String queryString = "select model from "
                + hibernateObject.getClass().getName()
                + " as model where model." + propertyName + "=:Qparam";
        Query queryObject = entityManager.createQuery(queryString);
        queryObject.setParameter("Qparam", value);
        results = queryObject.getResultList();
        return results;
    }

    public Object findByTwoPropertiesUnique(Object hibernateObject,String propertyName, Object value,
                                            String secondPropertyName, Object secondValue)
    {
        Session session = getHibernateSession();
        List results = new ArrayList();
        Object objectResultant = null;
        String queryString = "select model from "
                + hibernateObject.getClass().getName()
                + " as model where model." + propertyName + "= :Qparam and model." + secondPropertyName + "=:secondQparam";
        Query queryObject = entityManager.createQuery(queryString);
        queryObject.setParameter("Qparam", value);
        queryObject.setParameter("secondQparam", secondValue);

        results = queryObject.getResultList();

        if (results.size() > 0) {
            objectResultant = results.get(0);
        }

        return objectResultant;
    }

    public List findByTwoProperties(Object hibernateObject,String propertyName, Object value,
                                    String secondPropertyName, Object secondValue)
    {
        Session session = getHibernateSession();
        List results = new ArrayList();
        Object objectResultant = null;
        String queryString = "select model from "
                + hibernateObject.getClass().getName()
                + " as model where model." + propertyName + "= :Qparam and model." + secondPropertyName + "=:secondQparam";
        Query queryObject = entityManager.createQuery(queryString);
        queryObject.setParameter("Qparam", value);
        queryObject.setParameter("secondQparam", secondValue);

        results = queryObject.getResultList();
        return results;
    }

    public List findAll(Object hibernateObject) {
        //Session session = getHibernateSession();
        List results = new ArrayList();
        String queryString = "select model from "
                + hibernateObject.getClass().getName() + " as model ";
        Query queryObject = entityManager.createQuery(queryString);
        results = queryObject.getResultList();
        return results;
    }

}
