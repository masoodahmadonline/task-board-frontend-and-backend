
package web.dao.impl.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dao.BoxesDAO;
import web.entity.Boxes;




@Repository
public class BoxesDAOImpl implements BoxesDAO {
	
	/**
	 * The JPA entity manager
	 */
//        @Autowired
	private EntityManager entityManager;

	public Boxes save(Boxes box) {
        return entityManager.merge(box);
	}
        
    public boolean deleteBox(Long id){
        boolean b = false;
        Boxes box = getBoxById(id);
        System.out.println("box fetched for deletion: "+ box.getId());
         if(box != null){
            entityManager.remove(box);
            b = true;
         }
         return b;
    }
        
    public Boxes getBoxById(Long id){
        return entityManager.find(Boxes.class, id);
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
