/*
 * $Id$
 */
package web.dao;

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
	 * @param ub	the ub entity
	 * @return		the managed ub entity instance
	 */
	Users save(Users user);
        boolean doesLoginIdExists(String email);
        Users getUserByLoginId(String email);

    public List findAll(Object hibernateObject); // Farhan - general method to populate all data from the given object
    public Object findById(Object object, Long id); // Farhan - general method to find table by primary key


}
