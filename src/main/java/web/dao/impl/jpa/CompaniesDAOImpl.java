
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
import web.dao.CompaniesDAO;
import web.dao.UsersDAO;
import web.entity.Companies;
import web.entity.Users;



/**
 * 
 * @version $Revision$
 * @since   1.0
 */
@Repository
public class CompaniesDAOImpl implements CompaniesDAO {
	
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
	public Companies save(Companies company) {
		
            Companies companyToBeReturned = entityManager.merge(company);
            return companyToBeReturned;
	}
        
        public boolean doesCompanyExists(String name){
            System.out.println("debug --- a");
            String queryString = "SELECT user FROM Companies AS company " +
                         "WHERE company.name = :name";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("name", name);
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
        
        public Companies getCompanyByName(String name){
            System.out.println("company name entered for lookup was : "+name);
            System.out.println("debug --- a");
            String queryString = "SELECT user FROM Companies AS company " +
                         "WHERE company.name = :name";
            Query query = entityManager.createQuery(queryString);
            System.out.println("debug --- b");
            query.setParameter("name", name);

            List<?> list = query.getResultList();
            
            System.out.println("company name retrieved is" +((Companies)list.get(0)).getName() );
            System.out.println("debug --- c");
            if(list == null || list.size() == 0) throw new UsernameNotFoundException("Company not found");
           Companies companyToBeReturned = (Companies)list.get(0);
           System.out.println("debug --- d");
           System.out.println(companyToBeReturned.getName());
           return companyToBeReturned;
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
