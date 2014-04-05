package zkl.dietitian.service.dietitian.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.datastore.Nutrition;
import zkl.dietitian.service.dietitian.NutritionService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;


@Transactional
@Service("nutritionService")
public class NutritionServiceImpl implements NutritionService{
	@Resource(name="nutritionDao")
	private CommonDao<Nutrition, String> nutritionDao;
	
	
	@Override
	public void create(Nutrition nutrition) {
		nutritionDao.create(nutrition);
	}

	@Override
	public void update(Nutrition nutrition) {
		nutritionDao.update(nutrition);
	}

	@Override
	public void delete(Nutrition nutrition) {
		nutritionDao.delete(nutrition);
	}

	@Override
	public Nutrition getById(String id) {
		return nutritionDao.getById(id);
	}

	@Override
	public List<Nutrition> getAll() {
		return nutritionDao.getAll();
	}

	@Override
	public CommonPaginatedList<Nutrition> getPaginatedList(
			DetachedCriteria detachedCriteria, PageInfo pageInfo) {
		CommonPaginatedList<Nutrition> paginatedList =  nutritionDao.getPaginatedListByCriteria(detachedCriteria, pageInfo.getPageIndex(), pageInfo.getPageSize());
		return paginatedList;
	}

	@Override
	public List<Nutrition> getByCriteria(DetachedCriteria criteria) {
		return nutritionDao.getByCriteria(criteria);
	}

}
