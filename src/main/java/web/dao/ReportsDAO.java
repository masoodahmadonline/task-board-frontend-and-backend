package web.dao;

import java.util.Date;
import java.util.List;

import web.entity.Tasks;

public interface ReportsDAO {
	public List<Tasks> getTasksByDateRangeLong(Long boardId, List<String> orderByParamList, Date startDate, Date endDate);
}
