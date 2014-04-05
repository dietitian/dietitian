package zkl.dietitian.service.privilege;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import zkl.dietitian.BaseTest;
import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.entity.privilege.Module;


public class ModuleServiceTest extends BaseTest{
	
	@Resource
	private ModuleService moduleService;
	
//	@Test
//	public void addFunctions(){
//		int num = 0;
//		
//		String[] ids = {"1"};
//		int id = 1;
//		moduleService.addFunctions(ids, id);
//		
//		Module module =  moduleService.getById(1);
//		List<Function> functions = module.getFunctions();
//		for (int i = 0; i < functions.size(); i++) {
//			Function function = functions.get(i);
//			if(function.getId() == 1){
//				num++;
//			}
//		}
//			
//		
//		Assert.assertEquals(1, num);
//	}
	@Test
	public void deleteFunctions(){
		int num = 0;
		
		String[] ids = {"1"};
		int id = 1;
		moduleService.deleteFunctions(ids, id);
		
		Module module =  moduleService.getById(1);
		List<Function> functions = module.getFunctions();
		for (int i = 0; i < functions.size(); i++) {
			Function function = functions.get(i);
			if(function.getId() == 1){
				num++;
			}
		}
		Assert.assertEquals(0, num);
	}
}
