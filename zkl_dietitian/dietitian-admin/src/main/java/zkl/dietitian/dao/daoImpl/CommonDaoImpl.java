package zkl.dietitian.dao.daoImpl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;


public class CommonDaoImpl<TEntity, TId extends Serializable> implements CommonDao<TEntity, TId>
{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Class<TEntity> domainClass;

	@SuppressWarnings("unchecked")
	public CommonDaoImpl(String domainClassName)
	{
		try
		{
			this.domainClass = (Class<TEntity>) Class.forName(domainClassName);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void create(TEntity obj)
	{
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public void update(TEntity obj)
	{
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(TEntity obj)
	{
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void deleteById(TId id)
	{
		TEntity entity = getById(id);
		delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TEntity getById(TId id)
	{
		return (TEntity) sessionFactory.getCurrentSession().get(domainClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TEntity loadById(TId id)
	{
		return (TEntity) sessionFactory.getCurrentSession().load(domainClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TEntity> getAll()
	{
		String hql = "from " + this.domainClass.getSimpleName();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TEntity> getByNamedQuery(String namedQuery, Object... args)
	{
		Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQuery);
		for (int i = 0; i < args.length; i++)
		{
			query.setParameter(i, args[i]);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TEntity> getByCriteria(DetachedCriteria detachedCriteria)
	{
		Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory
				.getCurrentSession());
		return criteria.list();
	}

	@Override
	public int getTotalCountByCriteria(DetachedCriteria detachedCriteria)
	{
		Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory
				.getCurrentSession());
		criteria.setProjection(Projections.rowCount());
		Long totalCount = (Long) criteria.uniqueResult();
		criteria.setProjection(null);
		return totalCount.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TEntity> getByCriteria(DetachedCriteria detachedCriteria, int firstResult,
			int maxResults)
	{
		Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory
				.getCurrentSession());
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public CommonPaginatedList<TEntity> getPaginatedListByCriteria(
			DetachedCriteria detachedCriteria, int pageIndex, int pageSize)
	{
		int totalCount = getTotalCountByCriteria(detachedCriteria);
		List<TEntity> list = getByCriteria(detachedCriteria, (pageIndex - 1) * pageSize, pageSize);

		CommonPaginatedList<TEntity> paginatedList = new CommonPaginatedList<TEntity>();
		paginatedList.setObjectsPerPage(pageSize);
		paginatedList.setFullListSize(totalCount);
		paginatedList.setPageNumber(pageIndex);
		paginatedList.setList(list);

		return paginatedList;
	}
}
