package zkl.dietitian.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.system.CodeMain;
import zkl.dietitian.service.system.CodeMainService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;


/**
 * 代码表  主表  serviece
 * @author Lenovo
 *
 */
@Transactional
@Service("codeMainService")
public class CodeMainServiceImpl implements CodeMainService {
	
	@Resource(name="codeMainDao")
	private CommonDao<CodeMain, Integer> codeMainDao;
	
	@Override
	public void create(CodeMain codeMain) {
		codeMainDao.create(codeMain);
	}

	@Override
	public void update(CodeMain codeMain) {
		codeMainDao.update(codeMain);
	}

	@Override
	public void delete(int id) {
		codeMainDao.deleteById(id);
	}

	@Override
	public CodeMain getById(int id) {
		return codeMainDao.getById(id);
	}

	@Override
	public List<CodeMain> getAll() {
		return codeMainDao.getAll();
	}

	@Override
	public List<CodeMain> search(DetachedCriteria detachedCriteria) {
		return codeMainDao.getByCriteria(detachedCriteria);
	}

	@Override
	public CommonPaginatedList<CodeMain> search(
			DetachedCriteria detachedCriteria, int pageIndex, int pageSize) {
		return codeMainDao.getPaginatedListByCriteria(detachedCriteria, pageIndex, pageSize);
	}

	@Override
	public void delete(CodeMain codeMain) {
		codeMainDao.delete(codeMain);
	}
	
	/**
	 * 通过代码类型获得
	 */
	@Override
	public CodeMain getByCodeType(String codeType) {
		CodeMain codeMain = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeMain.class);
		detachedCriteria.add(Restrictions.eq("codeType", codeType));
		List<CodeMain> codemains =  codeMainDao.getByCriteria(detachedCriteria);
		if(codemains.size()!=0){
			codeMain = codemains.get(0);
		}
		return codeMain;
	}
}
