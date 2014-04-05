package zkl.dietitian.service.dietitian;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.entity.datastore.Nutrition;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;

public interface NutritionService {
	public void create(Nutrition nutrition);
	public void update(Nutrition nutrition);
	public void delete(Nutrition nutrition);
	public Nutrition getById(String id);
	public List<Nutrition> getAll();
	public CommonPaginatedList<Nutrition> getPaginatedList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	public List<Nutrition>  getByCriteria(DetachedCriteria criteria);
}
