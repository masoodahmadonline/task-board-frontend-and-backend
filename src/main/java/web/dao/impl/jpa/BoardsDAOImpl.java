
package web.dao.impl.jpa;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dao.BoardsDAO;
import web.entity.Boards;
import web.entity.Users;
import web.entity.Boxes;
import web.entity.Attachment;
import web.entity.Boards_Users;
import web.entity.Tasks;
import web.entity.Tasks_Users_Updated;
import web.service.common.ValidationUtility;


@Repository
public class BoardsDAOImpl implements BoardsDAO {
	
	/**
	 * The JPA entity manager
	 */
//  @Autowired
	private EntityManager entityManager;

	public Boards save(Boards board) {
        Session session = getHibernateSession();
        session.saveOrUpdate(board);
        return board;
	}

    public Boards getBoardById(Long id){
        System.out.println("board id entered for lookup was : "+id);
        return entityManager.find(Boards.class, id);
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

    public List<Boards> getBoardListByUser(Users user){

        return null;
    }

    public Long getBoxesCount(Long boardId) {
        Session session = getHibernateSession();
        BigInteger countInt = null;
        Long count = null;
        String queryString = "WITH RECURSIVE q " +
                "       AS (SELECT  b.id, b.parentboard_id " +
                "           FROM boxes b WHERE " +
                "           b.parentboard_id = "+boardId+
                "       UNION " +
                "           SELECT gm.id, gm.parentboard_id " +
                "           FROM boxes gm " +
                "           JOIN q ON " +
                "           gm.parentbox_id = q.id) " +
                "       SELECT count(id) " +
                "       FROM q ";
        /*String queryString = "SELECT count(boxes.id) " +
                "FROM Boxes as boxes " +
                "WHERE boxes.parentBoard.id = "+boardId+" OR " +
                "boxes.parentBox.parentBoard.id = "+boardId;*/
        Query queryObject = session.createSQLQuery(queryString);
        if(ValidationUtility.isExists(queryObject.uniqueResult())){
            countInt = (BigInteger)queryObject.uniqueResult();
            count = countInt.longValue();
        }
        return count;

    }

    public Long getAttachmentCount(Long boardId) {
        Session session = getHibernateSession();
        BigInteger countInt = null;
        Long count = null;
        String queryString = " WITH RECURSIVE q AS ( " +
                " SELECT att.id, b1.parentbox_id FROM attachment att, boxes b1 WHERE b1.parentboard_id = "+ boardId +
                " UNION " +
                " SELECT att.id, b1.parentbox_id FROM attachment att, boxes b1 JOIN q ON b1.parentbox_id = q.id) " +
                " SELECT count(id) FROM q;";
        Query queryObject = session.createSQLQuery(queryString);
        if(ValidationUtility.isExists(queryObject.uniqueResult())){
            countInt = (BigInteger)queryObject.uniqueResult();
            count = countInt.longValue();
        }
        return count;
    }

    public Long getTaskUserCount(Long boardId) {
        Session session = getHibernateSession();
        BigInteger countInt = null;
        Long count = null;
        String queryString = " WITH RECURSIVE q AS ( " +
                " SELECT t1.id, b1.parentbox_id FROM tasks_users_updated t1, boxes b1 WHERE b1.parentboard_id = "+ boardId +
                " UNION " +
                " SELECT t1.id, b1.parentbox_id FROM tasks_users_updated t1, boxes b1 JOIN q ON b1.parentbox_id = q.id) " +
                " SELECT count(id) FROM q;";
        Query queryObject = session.createSQLQuery(queryString);
        if(ValidationUtility.isExists(queryObject.uniqueResult())){
            countInt = (BigInteger)queryObject.uniqueResult();
            count = countInt.longValue();
        }
        return count;
    }

    public Long getTaskCount(Long boardId) {
        Session session = getHibernateSession();
        BigInteger countInt = null;
        Long count = null;
        String queryString = " WITH RECURSIVE q AS ( " +
                " SELECT t1.id, b1.parentbox_id FROM tasks t1, boxes b1 WHERE b1.parentboard_id = "+ boardId +
                " UNION " +
                " SELECT t1.id, b1.parentbox_id FROM tasks t1, boxes b1 JOIN q ON b1.parentbox_id = q.id) " +
                " SELECT count(id) FROM q;";
        Query queryObject = session.createSQLQuery(queryString);
        if(ValidationUtility.isExists(queryObject.uniqueResult())){
            countInt = (BigInteger)queryObject.uniqueResult();
            count = countInt.longValue();
        }
        return count;
    }
	
	
}
