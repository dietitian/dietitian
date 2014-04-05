package zkl.dietitian.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.system.Code;
import zkl.dietitian.service.system.CodeService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;



/**
 * 代码表  
 * @author Lenovo
 *
 */
@Transactional
@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Resource(name="codeDao")
	private CommonDao<Code, Integer> codeDao;
	
	@Override
	public void create(Code code) {
		codeDao.create(code);
	}

	@Override
	public void update(Code code) {
		codeDao.update(code);
	}

	@Override
	public void delete(int id) {
		codeDao.deleteById(id);
	}

	@Override
	public Code getById(int id) {
		return codeDao.getById(id);
	}

	@Override
	public List<Code> getAll() {
		return codeDao.getAll();
	}

	@Override
	public List<Code> search(DetachedCriteria detachedCriteria) {
		return codeDao.getByCriteria(detachedCriteria);
	}

	@Override
	public CommonPaginatedList<Code> search(
			DetachedCriteria detachedCriteria, int pageIndex, int pageSize) {
		return codeDao.getPaginatedListByCriteria(detachedCriteria, pageIndex, pageSize);
	}

}
