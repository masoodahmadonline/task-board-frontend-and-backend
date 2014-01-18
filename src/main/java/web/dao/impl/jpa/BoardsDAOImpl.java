
package web.dao.impl.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dao.BoardsDAO;
import web.entity.Boards;
import web.entity.Users;


@Repository
public class BoardsDAOImpl implements BoardsDAO {
	
	/**
	 * The JPA entity manager
	 */
//  @Autowired
	private EntityManager entityManager;

	public Boards save(Boards board) {
        return entityManager.merge(board);
	}
        

    public Boards getBoardById(Long id){
        System.out.println("board id entered for lookup was : "+id);
        return entityManager.find(Boards.class, id);
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
