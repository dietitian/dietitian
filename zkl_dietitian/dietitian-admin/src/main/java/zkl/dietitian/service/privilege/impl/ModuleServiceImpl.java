package zkl.dietitian.service.privilege.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.entity.privilege.Module;
import zkl.dietitian.service.privilege.ModuleService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;

@Transactional
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService{
	
	@Resource(name="moduleDao")
	private CommonDao<Module,Integer> moduleDao;
	@Resource(name="functionDao")
	private CommonDao<Function,Integer> functionDao;
	@Override
	public void save(Module module) {
		moduleDao.create(module);
	}

	@Override
	public void update(Module module) {
		moduleDao.update(module);
	}

	@Override
	public void delete(Module module) {
		module.getFunctions().clear();
		moduleDao.update(module);
		moduleDao.delete(module);
	}

	@Override
	public Module getById(Integer id) {
		
		return moduleDao.getById(id);
	}

	@Override
	public CommonPaginatedList<Module> getPaginatedList(DetachedCriteria detachedCriteria , PageInfo pageInfo) {

		CommonPaginatedList<Module> paginatedList =  moduleDao.getPaginatedListByCriteria(detachedCriteria, pageInfo.getPageIndex(), pageInfo.getPageSize());

		return paginatedList;
	}

	@Override
	public List<Module> getByCriteria(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Module> getAll() {
		return moduleDao.getAll();
	}

	@Override
	public void addFunctions(String[] ids, Integer id) {
		Module module = moduleDao.getById(id);
		for (int i = 0; i < ids.length; i++) {
			Function function = functionDao.getById(Integer.valueOf(ids[i]));
			function.setModule(module);
			function.getPrivileges();
			functionDao.update(function);
		}
	}

	@Override
	public void deleteFunctions(String[] ids, Integer id) {
		for (int i = 0; i < ids.length; i++) {
			Function function = functionDao.getById(Integer.valueOf(ids[i]));
			function.setModule(null);
			functionDao.update(function);
		}
	}

}
