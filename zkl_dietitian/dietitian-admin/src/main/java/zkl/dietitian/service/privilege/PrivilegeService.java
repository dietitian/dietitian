package zkl.dietitian.service.privilege;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.privilege.Privilege;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;




public interface PrivilegeService {
	public void save(Privilege privilege);
	public void update(Privilege privilege);
	public void delete(Privilege privilege);
	public Privilege getById(Integer id);
	public List<Privilege> getAll();
	public CommonPaginatedList<Privilege> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	public List<Privilege>  getByCriteria(DetachedCriteria criteria);
}
