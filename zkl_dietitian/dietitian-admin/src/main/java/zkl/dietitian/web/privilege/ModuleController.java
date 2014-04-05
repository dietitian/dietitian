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
import zkl.dietitian.entity.privilege.Module;
import zkl.dietitian.service.privilege.FunctionService;
import zkl.dietitian.service.privilege.ModuleService;
import zkl.dietitian.utils.displaytag.PageInfo;


@Controller
@RequestMapping("/module")
public class ModuleController {
	
	@Resource
	private ModuleService moduleService;
	@Resource
	private FunctionService functionService;
	/*
	 * 分页查询所有功能
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		//判断是否首次访问
				String time = request.getParameter("time");
				
				HttpSession session = request.getSession(false);
				Module module =null;
				
				//判断session是否为空
				if(session!=null){
					module = (Module)session.getAttribute("moduleSearch");
				}
				
				//首次进入将查询条件置空
				if(time!="1"&&module!=null){
					request.getSession().removeAttribute("moduleSearch");
				}
				
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Module.class);
				//添加查询条件
				if(time!="1"&&module!=null&&module.getName().trim()!=null&&module.getName().trim()!=""){
					detachedCriteria.add(Restrictions.ilike("name","%"+module.getName().trim()+"%"));
				}
				PageInfo pageInfo = new PageInfo();
				String pageIndexName = new ParamEncoder("module").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
				String pageIndex = request.getParameter(pageIndexName);
				if (pageIndex == null)
				{
					pageIndex = "1";
				}
				pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
				pageInfo.setPageSize(5);
				
				request.setAttribute("pageSize", pageInfo.getPageSize());
				request.setAttribute("moduleList", moduleService.getPaginatedList(detachedCriteria, pageInfo));

		
		return "/module/moduleList";
	}
	/*
	 * 分页查询所有功能带条件
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String Alist(Module module,HttpServletRequest request)
	{
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Module.class);
		//添加条件并将查询条件放入session
			detachedCriteria.add(Restrictions.ilike("name","%"+module.getName().trim()+"%"));
			request.getSession().setAttribute("moduleSearch", module);
		
		
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("module").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		
		
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("moduleList", moduleService.getPaginatedList(detachedCriteria, pageInfo));

		return "/module/moduleList";
	}
	
	/*
	 * 执行新建
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doCreate(Module module, HttpServletRequest request)
	{
		moduleService.save(module);

		return "redirect:/module/list";
	}
	
	/*
	 * 进入修改页面
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request)
	{
			request.setAttribute("module", moduleService.getById(id));
			return "/module/update";
	}
	
	
	/*
	 * 修改
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(Module module, HttpServletRequest request)
	{
		
		try {
			Module oldModule = moduleService.getById(module.getId());
			oldModule.setName(module.getName());
			oldModule.setDescription(module.getDescription());
			moduleService.update(oldModule);
			return "redirect:/module/list";
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
		Module module = new Module();
		module.setId(id);
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			moduleService.delete(module);
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
	@RequestMapping("/managerFunction/{id}")
	public String managerFunction(@PathVariable int id,HttpServletRequest request)
	{
		Module module = moduleService.getById(id);
		//查询所有模块
		List<Module> modules = moduleService.getAll();
		
		//查询所有功能
		List<Function> functions = functionService.getAll();
		
		//排除所有已归类功能
		for (int i = 0; i < modules.size(); i++) {
			Module mModule = modules.get(i);
			//获得已有权限
			List<Function> hasFunctions =  mModule.getFunctions();
			//遍历对比，排除已存在权限资源
			for (int k = 0; k < hasFunctions.size(); k++) {
				Function function = (Function) hasFunctions.get(k);
				for (int j = 0; j < functions.size(); j++) {
					Function funtionl = (Function) functions.get(j);
					if(function.getId().equals(funtionl.getId())){
						functions.remove(j);
					}
				}
			}
		}
		
		request.setAttribute("noFunctions", functions);
		request.setAttribute("module", module);
		
		return "module/managerFunction";
	}
	/*
	 * 添加权限资源
	 */
	@RequestMapping(value="/addFunctions" , method = RequestMethod.POST)
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
			moduleService.addFunctions(ids2, id);
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
	@RequestMapping(value="/deleteFunctions" , method = RequestMethod.POST)
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
			moduleService.deleteFunctions(ids2, Integer.valueOf(id));
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e) {
			out.write(result);
			e.printStackTrace();
		}
	}
	
	
}
