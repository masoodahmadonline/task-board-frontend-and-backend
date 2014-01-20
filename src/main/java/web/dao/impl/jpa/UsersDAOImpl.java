
package web.dao.impl.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dao.UsersDAO;
import web.entity.Users;



@Repository
public class UsersDAOImpl implements UsersDAO {
	
	/**
	 * The JPA entity manager
	 */
//        @Autowired
	private EntityManager entityManager;

	public Users save(Users user) {
        return entityManager.merge(user);
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
            System.out.println("email retrieved is" +((Users)list.get(0)).getEmail() );
            System.out.println(user.getEmail());
            user = (Users)list.get(0);
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

}
