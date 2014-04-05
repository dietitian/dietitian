package zkl.dietitian.service.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.system.Code;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;

/**
 * 代码表
 * @author Lenovo
 *
 */
public interface CodeService {
	public void create(Code code);

	public void update(Code code);

	public void delete(int id);

	public Code getById(int id);

	public List<Code> getAll();

	public List<Code> search(DetachedCriteria detachedCriteria);

	public CommonPaginatedList<Code> search(DetachedCriteria detachedCriteria, int pageIndex,
			int pageSize);
}
