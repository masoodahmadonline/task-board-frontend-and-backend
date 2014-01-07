/*
 * $Id$
 */
package web.dao;

import web.entity.Companies;
import web.entity.Users;

/**
 * 
 * @version $Revision$
 * @since   1.0
 */
public interface CompaniesDAO {
	
	/**
	 * Saves or Updates an existing ub entity instance.
	 * 
	 * @param ub	the ub entity
	 * @return		the managed ub entity instance
	 */
	Companies save(Companies company);
        boolean doesCompanyExists(String name);
        Companies getCompanyByName(String name);
}
