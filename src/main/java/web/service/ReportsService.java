package web.service;

import java.util.Date;
import java.util.List;

import web.entity.Tasks;

public interface ReportsService {
	
	public List<Tasks> getTasksByDateRangeLong(Long boardId, String orderBy, Date startDate, Date endDate);

}
