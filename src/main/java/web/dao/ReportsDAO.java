package web.dao;

import java.util.Date;
import java.util.List;

import web.entity.Boards;
import web.entity.Tasks;

public interface ReportsDAO {
	public List<Tasks> getTasksByDateRangeLong(Long boardId, String orderBy, Date startDate, Date endDate);
}
