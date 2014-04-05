package zkl.dietitian.service.privilege;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.privilege.Module;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;




public interface ModuleService {
	public void save(Module module);
	public void update(Module module);
	public void delete(Module module);
	public Module getById(Integer id);
	public CommonPaginatedList<Module> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	public List<Module>  getByCriteria(DetachedCriteria criteria);
	public List<Module> getAll();
	public void addFunctions(String[] ids,Integer id);
	public void deleteFunctions(String[] ids,Integer id);
}
