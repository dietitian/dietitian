package zkl.dietitian.service.privilege.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.privilege.Privilege;
import zkl.dietitian.service.privilege.PrivilegeService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;



@Transactional
@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {

	@Resource(name="privilegeDao")
	private CommonDao<Privilege,Integer> privilegeDao;
	@Override
	public void save(Privilege privilege) {
		privilegeDao.create(privilege);
	}

	@Override
	public void update(Privilege privilege) {
		privilegeDao.update(privilege);		
	}

	@Override
	public void delete(Privilege privilege) {
		privilegeDao.delete(privilege);
	}

	@Override
	public Privilege getById(Integer id) {
		return privilegeDao.getById(id);
	}

	@Override
	public CommonPaginatedList<Privilege> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo) {

		CommonPaginatedList<Privilege> paginatedList =  privilegeDao.getPaginatedListByCriteria(detachedCriteria, pageInfo.getPageIndex(), pageInfo.getPageSize());
		

		return paginatedList;
	}

	@Override
	public List<Privilege> getByCriteria(DetachedCriteria criteria) {
		return privilegeDao.getByCriteria(criteria);
	}

	@Override
	public List<Privilege> getAll() {
		
		return privilegeDao.getAll();
	}

}
