/*
 * $Id$
 */
package web.dao;

import java.util.Date;
import java.util.List;

import web.entity.Attachment;
import web.entity.Boxes;
import web.entity.Tasks;

/**
 * 
 * @version $Revision$
 * @since   1.0
 */
public interface TasksDAO {
	
	/**
	 * Saves or Updates an existing ub entity instance.
	 * 
	 * @param ub	the ub entity
	 * @return		the managed ub entity instance
	 */
    public Tasks save(Tasks box);
    public Tasks getTaskById(Long id);
    public boolean deleteTask(Long id);
    public Attachment saveAttachment(Attachment attachment);
    public Attachment getAttachmentById(Long id);
    public boolean deleteAttachment(Long id);
        
}
