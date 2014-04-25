package web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.BoxesDAO;
import web.dao.ReportsDAO;
import web.dao.TasksDAO;
import web.entity.Tasks;
import web.service.common.ResultImpl;

@Service
@Transactional(readOnly = true)
public class ReportsServiceImpl implements ReportsService{
	
	    
	    @Autowired
	    private ReportsDAO reportDAO;
	    @Autowired
	    private ResultImpl result;
	    
	   
	    @Transactional(readOnly = false)
	    public List<Tasks> getTasksByDateRangeLong(Long boardId, String orderBy, Date startDate, Date endDate){
	    	return reportDAO.getTasksByDateRangeLong( boardId,  orderBy,  startDate,  endDate);
	    }


		
}
