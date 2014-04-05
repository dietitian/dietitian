package zkl.dietitian;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
/**
 * 测试配置父类
 * @author zkl
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Before
	public void before(){
		//为了hibernate缓存，要求立即刷新；否则，事务结束时程序不执行dml
	//hibernateTemplate.setFlushMode(HibernateTemplate.FLUSH_EAGER);
	//	sessionFactory.getCurrentSession().setFlushMode();
//		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
//		MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
//		MockServletContext servletContext = new MockServletContext();
//		MockHttpSession httpSession = new MockHttpSession(servletContext);
		
	}
	
	@Test
	public void baseTest(){
		Assert.assertTrue(true);
	}
	
	
}
