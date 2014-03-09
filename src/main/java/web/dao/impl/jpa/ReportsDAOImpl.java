
package web.dao.impl.jpa;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dao.BoardsDAO;
import web.dao.ReportsDAO;
import web.entity.Boards;
import web.entity.Tasks;
import web.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class ReportsDAOImpl implements ReportsDAO {
	

    public List<Tasks> getTasksByDateRangeLong(Long boardId, List<String> orderByParamList, Date startDate, Date endDate){
        List results = new ArrayList();
        return results;
    }
	
	
}
