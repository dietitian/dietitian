package zkl.dietitian.service.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.system.CodeMain;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;

/**
 * 代码表  主表
 * @author Lenovo
 *
 */
public interface CodeMainService {
	public void create(CodeMain codeMain);

	public void update(CodeMain codeMain);

	public void delete(int id);

	public void delete(CodeMain codeMain);
	
	public CodeMain getById(int id);

	public List<CodeMain> getAll();

	public List<CodeMain> search(DetachedCriteria detachedCriteria);

	public CommonPaginatedList<CodeMain> search(DetachedCriteria detachedCriteria, int pageIndex,
			int pageSize);
	public CodeMain getByCodeType(String codeType);
}
