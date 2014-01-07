/*
 * $Id$
 */
package web.dao;

import web.entity.Users;

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

}
