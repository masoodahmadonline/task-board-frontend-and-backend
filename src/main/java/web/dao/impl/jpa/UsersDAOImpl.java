
package web.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import web.dao.UsersDAO;
import web.entity.Users;



/**
 * 
 * @version $Revision$
 * @since   1.0
 */
@Repository
public class UsersDAOImpl implements UsersDAO {
	
	/**
	 * The JPA entity manager
	 */
//        @Autowired
	private EntityManager entityManager;
	
	/**
	 * Set the entity manager
	 * 
	 * @param entityManager
	 */
        
        /**
	 * Saves or Updates an existing user entity instance.
	 * 
	 * @param user	the user entity
	 * @return		the managed user entity instance
	 */
	public Users save(Users user) {
		
            Users userToBeReturned = entityManager.merge(user);
            return userToBeReturned;
	}
        
        public boolean doesLoginIdExists(String email){
            System.out.println("debug --- a");
            String queryString = "SELECT user FROM Users AS user " +
                         "WHERE user.email = :email";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("email", email);
            System.out.println("debug --- b");
            List<?> list = query.getResultList();
            if(list == null || list.size() == 0){
                System.out.println("debug --- c");
                return false;
            }else{
                System.out.println("debug --- d");
                return true;
            }
         
            
        }
        
        public Users getUserByLoginId(String email){
            System.out.println("email entered for lookup was : "+email);
            System.out.println("debug --- a");
            String queryString = "SELECT user FROM Users AS user " +
                         "WHERE user.email = :email";
            Query query = entityManager.createQuery(queryString);
            System.out.println("debug --- b");
            query.setParameter("email", email);

            List<?> list = query.getResultList();
            
            System.out.println("email retrieved is" +((Users)list.get(0)).getEmail() );
            System.out.println("debug --- c");
            if(list == null || list.size() == 0) throw new UsernameNotFoundException("User not found");
           Users user = (Users)list.get(0);
           System.out.println("debug --- d");
           System.out.println(user.getEmail());
           return user;
//            return (Users)list.get(0);
            
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
