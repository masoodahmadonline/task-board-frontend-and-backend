
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

import java.util.Date;
import java.util.List;


@Repository
public class TasksDAOImpl implements TasksDAO {
	
	/**
	 * The JPA entity manager
	 */
//        @Autowired
	private EntityManager entityManager;
	

	public Tasks save(Tasks task) {
        return entityManager.merge(task);
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
            entityManager.remove(task);
            b = true;
         }
         return b;
    }

    public boolean deleteAttachment(Long id){
        boolean b = false;
        Attachment attachment = entityManager.find(Attachment.class, id);
        System.out.println("Task fetched for deletion: "+ attachment.getId());
        if(attachment != null){
            entityManager.remove(attachment);
            b = true;
        }
        return b;
    }
        
    public Tasks getTaskById(Long id){
        System.out.println("task id supplied to fetch task in dao was ======== "+ id);
        return entityManager.find(Tasks.class, id);
    }
    
    

    public Attachment getAttachmentById(Long id){
        System.out.println("attachment id supplied to fetch task in dao was ======== "+ id);
        return entityManager.find(Attachment.class, id);
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
