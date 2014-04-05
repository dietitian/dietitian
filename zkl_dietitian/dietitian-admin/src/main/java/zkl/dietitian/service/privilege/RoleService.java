package zkl.dietitian.service.privilege;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.privilege.Role;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;



public interface RoleService {
	public void save(Role role);
	public void update(Role role);
	public void delete(Role role);
	public Role getById(Integer id);
	public CommonPaginatedList<Role> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	public List<Role>  getByCriteria(DetachedCriteria criteria);
	public List<Role> getAll();
	public void addFunctions(String[] ids,Integer id);
	public void deleteFunctions(String[] ids,Integer id);
}
