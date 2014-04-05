package zkl.dietitian.service.privilege;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.privilege.User;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;


public interface UserService
{
	public void create(User user);

	public void update(User user);

	public void deleteById(int id);
	
	public void delete(User user);

	public User getById(int id);

	public List<User> getAll();

	public List<User> search(DetachedCriteria detachedCriteria);

	public CommonPaginatedList<User> search(DetachedCriteria detachedCriteria, int pageIndex,
			int pageSize);
	
	public void deleteRoles(String[] ids,Integer id);
	
	public void addRoles(String[] ids,Integer id);
	
	public User login(User user);
	
	public boolean hasUser(User user);
}
