package zkl.dietitian;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import zkl.dietitian.dao.CommonDAOTest;
import zkl.dietitian.service.privilege.FunctionServiceTest;
import zkl.dietitian.service.privilege.ModuleServiceTest;

/**
 * 测试全部测试类
 * @author zkl
 *
 */
@RunWith(Suite.class)
@SuiteClasses({CommonDAOTest.class,
	FunctionServiceTest.class,ModuleServiceTest.class})
public class AllTest {

}
