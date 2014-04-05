package zkl.dietitian.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

import zkl.dietitian.BaseTest;
import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.privilege.User;


public class CommonDAOTest extends BaseTest{
	
	@Resource(name="userDao")
	private CommonDao<User, Integer> userDao;
	
	//通用dao保存测试
	@Test
	public void saveTest(){
		User user = new User();
		user.setAge(13);
		user.setName("小雅");
		
		int oldCountRowsInTable = super.countRowsInTable("t_user");	
		userDao.create(user);
		int newCountRowsInTable = super.countRowsInTable("t_user");
		Assert.assertEquals(1, newCountRowsInTable-oldCountRowsInTable);
	}
	
	
	//get方法查询测试
	@Test
	public void getByIdTest(){
		User user = userDao.getById(1);
		System.out.println(user.getName());
		Assert.assertNotNull(user);
	}
	
	
	//load方法查询测试
	@Test
	public void loadByIdTest(){
		User user = userDao.loadById(2);
		Assert.assertNotNull(user);
	}
	//删除测试
		@Test
		public void deleteTest(){
			User user = new User();
			user.setId(2);
			userDao.delete(user);
			User newUser = userDao.getById(2);
			Assert.assertNull(newUser);
		}
	
	//修改测试
	@Test
	public void updateTest(){
		User user = new User();
		user.setId(2);
		user.setAge(170);
		
		userDao.update(user);
		
		User newUser = userDao.loadById(2);
		
		Assert.assertNotSame(180, newUser.getAge());
	}
	
	//查询所有测试
	@Test
	public void findAllTest(){
		List<User> lis = userDao.getAll();
		int allCountRowsInTable = super.countRowsInTable("t_user");
		Assert.assertEquals(allCountRowsInTable, lis.size());
	}
	
	//通过Criteria进行条件查询
	@Test
	public void findByCriteria(){
		//查询年龄大于等于3001的人数
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("id", 2));
		List<User> lst = userDao.getByCriteria(criteria);
		Assert.assertEquals(1, lst.size());
	}
	
	//通过Criteria进行分页条件查询
	@Test
	public void findByCriteria2(){
		int CountRowsInTable = super.countRowsInTable("t_user");	
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		int maxResults = 5;
		
		int pageindex = CountRowsInTable/maxResults +1;
		
		int remainCord = CountRowsInTable%maxResults;
		
		List<User> lst = userDao.getByCriteria(criteria, (pageindex-1)*maxResults, maxResults);
		
		Assert.assertEquals(remainCord, lst.size());
		
	}
	
	//查询总数
	@Test
	public void findByTotalCountTest(){
		int CountRowsInTable = super.countRowsInTable("t_user");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		int count = userDao.getTotalCountByCriteria(detachedCriteria);
		
		Assert.assertEquals(CountRowsInTable, count);
		
	}
	//namedQuery 自定义Hql语句查询
	@Test
	public void findByNamedQueryTest(){
		
		List<User> lst = userDao.getByNamedQuery("User.nullDescription");
		int num = 0;
		for (int i = 0; i < lst.size(); i++) {
			if(lst.get(i).getDescription()!=null){
				num = num+1;
			}
		}
		Assert.assertEquals(0, num);
		
		
	}
	
}
