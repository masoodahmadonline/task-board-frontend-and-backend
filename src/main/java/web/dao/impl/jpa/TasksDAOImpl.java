
package web.dao.impl.jpa;

import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import web.dao.BoxesDAO;
import web.dao.TasksDAO;
import web.entity.Attachment;
import web.entity.Tasks;
import web.entity.Boxes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.List;


/**
 * 
 * @version $Revision$
 * @since   1.0
 */
@Repository
public class TasksDAOImpl implements TasksDAO {
	
	/**
	 * The JPA entity manager
	 */
//        @Autowired
	private EntityManager entityManager;
	
	/**
	 * Set the entity manager
	 * 
	 * @param entityManager
     *
	 * Saves or Updates an existing user entity instance.
	 * 
	 * @param user	the user entity
	 * @return		the managed user entity instance
	 */
	public Tasks save(Tasks task) {

        Tasks taskToBeReturned = entityManager.merge(task);
            return taskToBeReturned;
	}

    public Attachment saveAttachment(Attachment attachment){
        Attachment savedAttachment = entityManager.merge(attachment);
        return savedAttachment;

    }
        
    public boolean deleteTask(Long id){
        boolean b = false;
        Tasks task = entityManager.find(Tasks.class, id);
        System.out.println("Task fetched for deletion: "+ task.getId());
         if(task != null){
              try {
                entityManager.remove(task);
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

    public boolean deleteAttachment(Long id){
        boolean b = false;
        Attachment attachment = entityManager.find(Attachment.class, id);
        System.out.println("Task fetched for deletion: "+ attachment.getId());
        if(attachment != null){
            try {
                entityManager.remove(attachment);
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
        
        public Tasks getTaskById(Long id){
            System.out.println("task id supplied to fetch task in dao was ======== "+ id);
            
            String queryString = "SELECT task FROM Tasks AS task " +
                         "WHERE task.id = :id";
            Query query = entityManager.createQuery(queryString);
            
            query.setParameter("id", id);

            List<?> list = query.getResultList();
            
            //System.out.println("task name retrieved is" + ((Tasks) list.get(0)).getTitle().toString());
            System.out.println("====list size for tasks fetch by dao ===== "+ list.size());
            if(list == null || list.size() == 0) throw new UsernameNotFoundException("task not found");
            Tasks taskToBeReturned = (Tasks)list.get(0);
         
           System.out.println("tiltle of task to be returned ==========="+taskToBeReturned.getTitle());
           return taskToBeReturned;
//            return (Users)list.get(0);
            
        }


        public Attachment getAttachmentById(Long id){
            System.out.println("attachment id supplied to fetch task in dao was ======== "+ id);

            String queryString = "SELECT attachment FROM Attachment AS attachment " +
                    "WHERE attachment.id = :id";
            Query query = entityManager.createQuery(queryString);

            query.setParameter("id", id);

            List<?> list = query.getResultList();

            //System.out.println("task name retrieved is" + ((Tasks) list.get(0)).getTitle().toString());
            System.out.println("====list size for attachment fetch by dao ===== "+ list.size());
            if(list == null || list.size() == 0) throw new UsernameNotFoundException("attachment not found");
            Attachment attachmentToBeReturned = (Attachment)list.get(0);

            System.out.println("tiltle of attachment to be returned ==========="+attachmentToBeReturned.getName());
            return attachmentToBeReturned;
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
