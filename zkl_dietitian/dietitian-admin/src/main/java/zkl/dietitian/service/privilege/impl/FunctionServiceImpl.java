package zkl.dietitian.service.privilege.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.entity.privilege.Privilege;
import zkl.dietitian.service.privilege.FunctionService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;

@Transactional
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
	
	@Resource(name="functionDao")
	private CommonDao<Function,Integer> functionDao;
	@Resource(name="privilegeDao")
	private CommonDao<Privilege,Integer> privilegeDao;
	@Override
	public void save(Function function) {
		functionDao.create(function);
	}

	@Override
	public void update(Function function) {
		functionDao.update(function);
	}

	@Override
	public void delete(Function function) {
		functionDao.delete(function);
	}

	@Override
	public Function getById(Integer id) {
		return functionDao.getById(id);
	}

	@Override
	public CommonPaginatedList<Function> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo) {

		CommonPaginatedList<Function> paginatedList =  functionDao.getPaginatedListByCriteria(detachedCriteria, pageInfo.getPageIndex(), pageInfo.getPageSize());
		
		return paginatedList;
	}

	@Override
	public List<Function> getByCriteria(DetachedCriteria criteria) {
		return null;
	}
	/*
	 * 添加权限资源
	 * @see 
	 */
	@Override
	public void addPrivileges(String[] ids, Integer id) {
		Function function = functionDao.getById(id);
		for (int i = 0; i < ids.length; i++) {
			Privilege privilege =  privilegeDao.getById(Integer.valueOf(ids[i]));
			function.getPrivileges().add(privilege);
		}
		
		functionDao.update(function);
	}

	@Override
	public void deletePrivileges(String[] ids, Integer id) {
		Function function = functionDao.getById(id);
		for (int i = 0; i < ids.length; i++) {
			Privilege privilege =  privilegeDao.getById(Integer.valueOf(ids[i]));
			function.getPrivileges().remove(privilege);
		}
		
		functionDao.update(function);
	}

	@Override
	public List<Function> getAll() {
		return functionDao.getAll();
	}

}
