
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
import web.dao.BoardsDAO;
import web.dao.CompaniesDAO;
import web.dao.UsersDAO;
import web.entity.Boards;
import web.entity.Companies;
import web.entity.Users;



/**
 * 
 * @version $Revision$
 * @since   1.0
 */
@Repository
public class BoardsDAOImpl implements BoardsDAO {
	
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
	public Boards save(Boards board) {
		
            Boards boardToBeReturned = entityManager.merge(board);
            return boardToBeReturned;
	}
        
        
        
        public Boards getBoardById(Long id){
            System.out.println("board id entered for lookup was : "+id);
            System.out.println("debug --- a");
            String queryString = "SELECT board FROM Boards AS board " +
                         "WHERE board.id = :id";
            Query query = entityManager.createQuery(queryString);
            System.out.println("debug --- b");
            query.setParameter("id", id);

            List<?> list = query.getResultList();
            
            System.out.println("board name retrieved is" +((Boards)list.get(0)).getTitle().toString() );
            System.out.println("debug --- c");
            if(list == null || list.size() == 0) throw new UsernameNotFoundException("Board not found");
           Boards boardToBeReturned = (Boards)list.get(0);
           System.out.println("debug --- d");
           System.out.println(boardToBeReturned.getTitle());
           return boardToBeReturned;
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
	
        
        public List<Boards> getBoardListByUser(Users user){
            
            return null;
        }
	
	
}
