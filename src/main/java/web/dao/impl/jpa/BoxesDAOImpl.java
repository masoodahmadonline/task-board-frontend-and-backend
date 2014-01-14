
package web.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import web.dao.BoardsDAO;
import web.dao.BoxesDAO;
import web.dao.CompaniesDAO;
import web.dao.UsersDAO;
import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Companies;
import web.entity.Users;



/**
 * 
 * @version $Revision$
 * @since   1.0
 */
@Repository
public class BoxesDAOImpl implements BoxesDAO {
	
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
	public Boxes save(Boxes box) {
		
            Boxes boardToBeReturned = entityManager.merge(box);
            return boardToBeReturned;
	}
        
    public boolean deleteBox(Long id){
        boolean b = false;
        Boxes box = entityManager.find(Boxes.class, id);
        System.out.println("box fetched for deletion: "+ box.getId());
         if(box != null){
              try {
                entityManager.remove(box);
                b = true;
              }
              catch(IllegalArgumentException e) {
                System.out.println(e);

              }
              catch(TransactionRequiredException e) {
                System.out.println(e);

              }

         }

         return b;

    }
        
        public Boxes getBoxById(Long id){
            
            String queryString = "SELECT box FROM Boxes AS box " +
                         "WHERE box.id = :id";
            Query query = entityManager.createQuery(queryString);
            
            query.setParameter("id", id);

            List<?> list = query.getResultList();
            if(list.size() > 0){
                System.out.println("box name retrieved is" +((Boxes)list.get(0)).getTitle().toString() );
            }

            
            if(list == null || list.size() == 0) throw new UsernameNotFoundException("box not found");
           Boxes boxToBeReturned = (Boxes)list.get(0);
         
           System.out.println(boxToBeReturned.getTitle());
           return boxToBeReturned;
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
