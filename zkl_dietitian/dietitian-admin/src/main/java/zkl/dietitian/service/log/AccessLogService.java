package zkl.dietitian.service.log;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.log.AccessLog;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;


public interface AccessLogService {
	public void save(AccessLog accessLog);
	public void update(AccessLog accessLog);
	public void delete(AccessLog accessLog);
	public AccessLog getById(String id);
	public CommonPaginatedList<AccessLog> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	public List<AccessLog>  getByCriteria(DetachedCriteria criteria);
	public List<AccessLog> getAll();
	public HashMap<String, Object> getFocusMap(String type ,String startTime , String endTime);
}
