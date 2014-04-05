package zkl.dietitian.web.privilege;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.entity.privilege.Privilege;
import zkl.dietitian.service.privilege.FunctionService;
import zkl.dietitian.service.privilege.PrivilegeService;
import zkl.dietitian.utils.displaytag.PageInfo;


@Controller
@RequestMapping("/function")
public class FunctionController {
	
	@Resource
	private FunctionService functionService;
	@Resource
	private PrivilegeService privilegeService;
	/*
	 * 分页查询所有功能
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request)
	{
		//判断是否首次访问
		String time = request.getParameter("time");
		
		HttpSession session = request.getSession(false);
		Function function =null;
		
		//判断session是否为空
		if(session!=null){
			function = (Function)session.getAttribute("functionSearch");
		}
		
		//首次进入将查询条件置空
		if(time!="1"&&function!=null){
			request.getSession().removeAttribute("functionSearch");
		}
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
		//添加查询条件
		if(time!="1"&&function!=null&&function.getName().trim()!=null&&function.getName().trim()!=""){
			detachedCriteria.add(Restrictions.ilike("name","%"+function.getName().trim()+"%"));
		}
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("function").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("functionList", functionService.getPaginatedList(detachedCriteria, pageInfo));

		return "/function/functionList";
	}
	/*
	 * 分页查询所有功能带条件
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String Alist(Function function,HttpServletRequest request)
	{
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
		//添加条件并将查询条件放入session
			detachedCriteria.add(Restrictions.ilike("name","%"+function.getName().trim()+"%"));
			request.getSession().setAttribute("functionSearch", function);
		
		
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("function").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		
		
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("functionList", functionService.getPaginatedList(detachedCriteria, pageInfo));

		return "/function/functionList";
	}
	/*
	 * 执行新建
	 */
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(Function function, HttpServletRequest request)
	{
		functionService.save(function);

		return "redirect:/function/list";
	}
	/*
	 * 进入修改页面
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request)
	{
			request.setAttribute("function", functionService.getById(id));
			return "/function/update";
	}
	
	
	/*
	 * 修改
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(Function function, HttpServletRequest request)
	{
		
		try {
			Function oldFunction = functionService.getById(function.getId());
			oldFunction.setName(function.getName());
			oldFunction.setDescription(function.getDescription());
			functionService.update(oldFunction);
			return "redirect:/function/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error";
			
		}
		
	}
	
	
	/*
	 * 删除
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id, HttpServletResponse response)
	{
		String result = "{\"result\":\"error\"}";
		Function function = new Function();
		function.setId(id);
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			functionService.delete(function);
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e1) {
			out.write(result);
			e1.printStackTrace();
		}
	}
	/*
	 *进入授权页面
	 */
	@RequestMapping("/managerPrivilege/{id}")
	public String managerPrivilege(@PathVariable int id,HttpServletRequest request)
	{
		Function function = functionService.getById(id);
		//查询所有权限资源
		List<Privilege> privileges = privilegeService.getAll();
		//获得已有权限
		List<Privilege> hasPrivileges =  function.getPrivileges();
		//遍历对比，排除已存在权限资源
		
		for (int j = 0; j < hasPrivileges.size(); j++) {
			Privilege privilege = (Privilege) hasPrivileges.get(j);
			for (int i = 0; i < privileges.size(); i++) {
				Privilege privilegel = (Privilege) privileges.get(i);
				if(privilege.getId().equals(privilegel.getId())){
					privileges.remove(i);
				}
			}
		}
		request.setAttribute("noPrivileges", privileges);
		request.setAttribute("function", function);
		
		return "function/managerPrivilege";
	}
	/*
	 * 添加权限资源
	 */
	@RequestMapping(value="/addPrivileges" , method = RequestMethod.POST)
	public void addPrivileges(String ids,Integer id , HttpServletResponse response)
	{
		String result = "{\"result\":\"error\"}";
		String[] ids2 = ids.split(","); 
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			functionService.addPrivileges(ids2, id);
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e) {
			out.write(result);
			e.printStackTrace();
		}
	}
	/*
	 * 删除权限资源
	 */
	@RequestMapping(value="/deletePrivileges" , method = RequestMethod.POST)
	public void deletePrivileges(String ids,String id , HttpServletResponse response)
	{
		String result = "{\"result\":\"error\"}";
		String[] ids2 = ids.split(","); 
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			functionService.deletePrivileges(ids2, Integer.valueOf(id));
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e) {
			out.write(result);
			e.printStackTrace();
		}
	}
	
	
}
