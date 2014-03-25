package web.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import web.dao.ReportsDAO;
import web.entity.Tasks;

@Repository
public class ReportsDAOImpl implements ReportsDAO{
//  @Autowired
private EntityManager entityManager;
	
	public List<Tasks> getTasksByDateRangeLong(Long boardId, List<String> orderByParamList, Date startDate, Date endDate){
    	System.out.println("boardId: " + boardId);
//    	System.out.println("grouBy: " + groupBy);
    	System.out.println("startDate: " + startDate.toString());
    	System.out.println("endDate: " + endDate.toString());
    	
    	StringBuffer queryString = new StringBuffer();
    			queryString.append( "SELECT task FROM Tasks AS task ");
    			queryString.append( "WHERE task.parentBox.parentBoard.id = :boardId ");
    			queryString.append(  "AND task.creationDateTime BETWEEN :startDate AND :endDate " );
    			queryString.append(  "ORDER BY task.creationDateTime " );
    			if(orderByParamList != null){
    				if(orderByParamList.size()>0){
    					for(String orderByString : orderByParamList){
    						queryString.append(", task.");
    						queryString.append(orderByString);
    					}
    				}
    			}

    	System.out.println(queryString + "--------------------");		
    	//groupBy is pending
    	Query query = entityManager.createQuery(queryString.toString());
    	query.setParameter("boardId", boardId);
//    	query.setParameter("groupBy", groupBy);
    	query.setParameter("startDate", startDate);
    	query.setParameter("endDate", endDate);
    	List<Tasks> taskList = query.getResultList();
    	return taskList;
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
