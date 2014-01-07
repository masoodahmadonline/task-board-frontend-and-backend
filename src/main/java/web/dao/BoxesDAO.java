/*
 * $Id$
 */
package web.dao;

import java.util.List;
import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Companies;
import web.entity.Users;

/**
 * 
 * @version $Revision$
 * @since   1.0
 */
public interface BoxesDAO {
	
	/**
	 * Saves or Updates an existing ub entity instance.
	 * 
	 * @param ub	the ub entity
	 * @return		the managed ub entity instance
	 */
	Boxes save(Boxes box);
        Boxes getBoxById(Long id);
        boolean deleteBox(Long id);
        
        
}
