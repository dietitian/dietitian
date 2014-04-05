package zkl.dietitian.service.privilege.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.privilege.Role;
import zkl.dietitian.entity.privilege.User;
import zkl.dietitian.service.privilege.UserService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;


@Transactional
@Service("userService")
public class UserServiceImpl implements UserService
{
	@Resource(name="userDao")
	private CommonDao<User, Integer> userDao;
	@Resource(name="roleDao")
	private CommonDao<Role, Integer> roleDao;
	@Override
	public void create(User user)
	{
		userDao.create(user);
	}

	@Override
	public void update(User user)
	{
		userDao.update(user);
	}

	@Override
	public void deleteById(int id)
	{
		userDao.deleteById(id);
	}
	@Override
	public void delete(User user)
	{
		userDao.delete(user);
	}
	@Override
	public User getById(int id)
	{
		return userDao.getById(id);
	}

	@Override
	public List<User> getAll()
	{
		return userDao.getAll();
	}

	@Override
	public List<User> search(DetachedCriteria detachedCriteria)
	{
		return userDao.getByCriteria(detachedCriteria);
	}

	@Override
	public CommonPaginatedList<User> search(DetachedCriteria detachedCriteria, int pageIndex,
			int pageSize)
	{
		return userDao.getPaginatedListByCriteria(detachedCriteria, pageIndex, pageSize);
	}

	@Override
	public void deleteRoles(String[] ids, Integer id) {
		User user = userDao.getById(id);
		for (int i = 0; i < ids.length; i++) {
			Role role =  roleDao.getById(Integer.valueOf(ids[i]));
			user.getRoles().remove(role);
		}
		userDao.update(user);
	}

	@Override
	public void addRoles(String[] ids, Integer id) {
		User user = userDao.getById(id);
		for (int i = 0; i < ids.length; i++) {
			Role role =  roleDao.getById(Integer.valueOf(ids[i]));
			user.getRoles().add(role);
		}
		
		userDao.update(user);
	}

	@Override
	public User login(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("name", user.getName()));
		List<User> userList = userDao.getByCriteria(criteria);
		User existUser = null;
		if(userList.size()!=0){
			existUser = userList.get(0);
		}
		return existUser;
	}

	@Override
	public boolean hasUser(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("name", user.getName()));
		List<User> userList = userDao.getByCriteria(criteria);
		
		if(userList.size()!=0){
			return true;
		}
		return false;
	}
}
