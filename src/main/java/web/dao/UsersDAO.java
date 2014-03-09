/*
 * $Id$
 */
package web.dao;

import web.entity.Boards_Users;
import web.entity.Users;

import java.util.List;

/**
 * 
 * @version $Revision$
 * @since   1.0
 */
public interface UsersDAO {
	
	/**
	 * Saves or Updates an existing ub entity instance.
	 * 
	 * @param entity	the ub entity
	 * @return		the managed ub entity instance
	 */
	public void save(Object entity);
    boolean doesLoginIdExists(String email);
    Users getUserByLoginId(String email);

    public List findAll(Object hibernateObject); // Farhan - general method to populate all data from the given object
    public Object findById(Object object, Long id); // Farhan - general method to find table by primary key
    public List findByProperty(Object hibernateObject, String propertyName, Object value); // Farhan - general method to populate list against specific property
    public List findByTwoProperties(Object hibernateObject,String propertyName, Object value, String secondPropertyName, Object secondValue); // Farhan
    public Object findByTwoPropertiesUnique(Object hibernateObject,String propertyName, Object value, String secondPropertyName, Object secondValue); // Farhan
    public void remove(Object entity);

}
