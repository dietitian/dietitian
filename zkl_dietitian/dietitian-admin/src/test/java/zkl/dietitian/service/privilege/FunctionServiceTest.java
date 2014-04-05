package zkl.dietitian.service.privilege;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import zkl.dietitian.BaseTest;
import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.entity.privilege.Privilege;


public class FunctionServiceTest extends BaseTest{
	@Resource
	private FunctionService functionService;
	@Test
	public void addPrivilege(){
		int num = 0;
		String[] ids = {"1"} ;
		Integer id = 1;
		functionService.addPrivileges(ids, id);
		Function function = functionService.getById(id);
		List<Privilege> privilegeList = function.getPrivileges();
		for (int i = 0; i < ids.length; i++) {
			
		
			Privilege privilege = (Privilege) privilegeList.get(i);
			Integer nid =  privilege.getId();
			if(nid == id){
				num++;
			}
		}
		Assert.assertEquals(1,num);
	}
	@Test
	public void deletePrivilege(){
		int num = 0;
		
		String[] ids = {"1"} ;
		Integer id = 1;
		functionService.deletePrivileges(ids, id);
		Function function = functionService.getById(id);
		List<Privilege> privilegeList = function.getPrivileges();
		for (int i = 0; i < ids.length; i++) {
			Privilege privilege = (Privilege) privilegeList.get(i);
			Integer nid =  privilege.getId();
			if(nid == id){
				num++;
			}
		}
		Assert.assertEquals(0,num);
	}
}
