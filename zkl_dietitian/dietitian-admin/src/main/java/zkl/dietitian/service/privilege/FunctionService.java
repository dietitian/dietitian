package zkl.dietitian.service.privilege;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;




public interface FunctionService {
	public void save(Function function);
	public void update(Function function);
	public void delete(Function function);
	public Function getById(Integer id);
	public CommonPaginatedList<Function> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	public List<Function>  getByCriteria(DetachedCriteria criteria);
	public void addPrivileges(String[] ids,Integer id);
	public void deletePrivileges(String[] ids,Integer id);
	public List<Function> getAll();
}
