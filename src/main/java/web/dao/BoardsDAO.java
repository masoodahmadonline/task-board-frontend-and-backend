/*
 * $Id$
 */
package web.dao;

import java.util.List;
import web.entity.Boards;
import web.entity.Companies;
import web.entity.Users;

/**
 * 
 * @version $Revision$
 * @since   1.0
 */
public interface BoardsDAO {
	
	/**
	 * Saves or Updates an existing ub entity instance.
	 * 
	 * @param ub	the ub entity
	 * @return		the managed ub entity instance
	 */
	Boards save(Boards board);
        Boards getBoardById(Long id);
        List<Boards> getBoardListByUser(Users user);

    Long getBoxesCount(Long boardId);
    Long getAttachmentCount(Long boardId);
    Long getTaskUserCount(Long boardId);
    Long getTaskCount(Long boardId);

    Long getTotalBoardCount(Long boardId, Long userId);
}
