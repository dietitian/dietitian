package zkl.dietitian.dao.daoInterface;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import zkl.dietitian.utils.displaytag.CommonPaginatedList;


/**
 * 通用数据访问接口。
 * 
 * @param <TEntity>
 *            实体类型。
 * @param <TId>
 *            主键类型。
 */
public interface CommonDao<TEntity, TId extends Serializable>
{
	/**
	 * 新增实体。
	 * 
	 * @param entity
	 *            实体对象。
	 */
	public void create(TEntity entity);

	/**
	 * 修改实体。
	 * 
	 * @param entity
	 *            实体对象。
	 */
	public void update(TEntity entity);

	/**
	 * 删除实体。
	 * 
	 * @param entity
	 *            实体对象。
	 */
	public void delete(TEntity entity);

	/**
	 * 删除指定标识的实体。
	 * 
	 * @param id
	 *            实体标识。
	 */
	public void deleteById(TId id);

	/**
	 * 获取指定标识的实体对象。
	 * 
	 * @param id
	 *            实体的标识。
	 * @return 指定标识的实体对象。
	 */
	public TEntity getById(TId id);

	/**
	 * 加载指定标识的实体对象。
	 * 
	 * @param id
	 *            实体的标识。
	 * @return 指定标识的实体对象。
	 */
	public TEntity loadById(TId id);

	/**
	 * 获取所有实体对象。
	 * 
	 * @param id
	 *            实体的标识。
	 * @return 指定标识的实体对象。
	 */
	public List<TEntity> getAll();

	/**
	 * 对实体进行命名查询。
	 * 
	 * @param namedQuery
	 *            命名查询。
	 * @param args
	 *            查询所需的参数。
	 * @return 符合条件的实体对象。
	 */
	public List<TEntity> getByNamedQuery(String namedQuery, Object... args); // 进行条件查询，使用hql，

	/**
	 * 获取指定条件的实体对象。
	 * 
	 * @param detachedCriteria
	 *            查询条件。
	 * @return 指定条件的实体对象。
	 */
	public List<TEntity> getByCriteria(DetachedCriteria criteria);// 各种各样条件查询， 添加排序

	/**
	 * 获取指定条件的实体对象的总数。
	 * 
	 * @param detachedCriteria
	 *            查询条件。
	 * @return 指定条件的实体对象的总数。
	 */
	public int getTotalCountByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 获取指定条件的实体对象的部分结果。
	 * 
	 * @param detachedCriteria
	 *            查询条件。
	 * @param firstResult
	 *            第一条记录的位置。
	 * @param maxResults
	 *            要获取的最大记录数。
	 * @return 指定条件的实体对象的部分结果。
	 */
	public List<TEntity> getByCriteria(DetachedCriteria detachedCriteria, int firstResult,
			int maxResults);

	/**
	 * 获取指定条件的实体对象的分页结果。
	 * 
	 * @param detachedCriteria
	 *            查询条件。
	 * @param pageIndex
	 *            第几页。
	 * @param pageSize
	 *            每页最大记录数。
	 * @return 指定条件的实体对象的部分结果。
	 */
	public CommonPaginatedList<TEntity> getPaginatedListByCriteria(
			DetachedCriteria detachedCriteria, int pageIndex, int pageSize);
}
