package zkl.dietitian.service.privilege.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.entity.privilege.Role;
import zkl.dietitian.service.privilege.RoleService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;



@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Resource(name="roleDao")
	private CommonDao<Role,Integer> roleDao;
	@Resource(name="functionDao")
	private CommonDao<Function,Integer> functionDao;
	@Override
	public void save(Role role) {
		roleDao.create(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	public Role getById(Integer id) {
		return roleDao.getById(id);
	}

	@Override
	public CommonPaginatedList<Role> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo) {

		CommonPaginatedList<Role> paginatedList =  roleDao.getPaginatedListByCriteria(detachedCriteria, pageInfo.getPageIndex(), pageInfo.getPageSize());

		return paginatedList;
	}

	@Override
	public List<Role> getByCriteria(DetachedCriteria criteria) {
		
		
		return null;
	}

	@Override
	public List<Role> getAll() {
		return roleDao.getAll();
	}

	@Override
	public void addFunctions(String[] ids, Integer id) {
		Role role = roleDao.getById(id);
		for (int i = 0; i < ids.length; i++) {
			Function function =  functionDao.getById(Integer.valueOf(ids[i]));
			role.getFunctions().add(function);
		}
		
		roleDao.update(role);
	}

	@Override
	public void deleteFunctions(String[] ids, Integer id) {
		Role role = roleDao.getById(id);
		for (int i = 0; i < ids.length; i++) {
			Function function =  functionDao.getById(Integer.valueOf(ids[i]));
			role.getFunctions().remove(function);
		}
		
		roleDao.update(role);
	}

	
}
