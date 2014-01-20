
package web.dao.impl.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dao.CompaniesDAO;
import web.entity.Companies;




@Repository
public class CompaniesDAOImpl implements CompaniesDAO {
	
	/**
	 * The JPA entity manager
	 */
//        @Autowired
	private EntityManager entityManager;
	

	public Companies save(Companies company) {
        return entityManager.merge(company);
	}

    public boolean doesCompanyExists(String name){
        String queryString = "SELECT user FROM Companies AS company " +
                     "WHERE company.name = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        List<?> list = query.getResultList();
        if(list == null || list.size() == 0){
            return false;
        }else{
            return true;
        }
    }

    public Companies getCompanyByName(String name){
        Companies companyToBeReturned = null;
        System.out.println("company name entered for lookup was : "+name);
        String queryString = "SELECT user FROM Companies AS company " +
                     "WHERE company.name = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        List<?> list = query.getResultList();
        //query.getSingleResult(); can also be used.{M-A}
        //getResultList() is never returned, despite that:{M-A}
        if(list != null && list.size() > 0){
            companyToBeReturned = (Companies)list.get(0);
            System.out.println(companyToBeReturned.getName());
            System.out.println("company name retrieved is" +((Companies)list.get(0)).getName() );
        }
        return companyToBeReturned;
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
