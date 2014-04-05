package zkl.dietitian.web.privilege;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import zkl.dietitian.entity.privilege.Role;
import zkl.dietitian.service.privilege.FunctionService;
import zkl.dietitian.service.privilege.ModuleService;
import zkl.dietitian.service.privilege.RoleService;
import zkl.dietitian.utils.displaytag.PageInfo;


@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="functionService")
	private FunctionService functionService;
	
	@Resource(name="moduleService")
	private ModuleService moduleService;
	/*
	 * 分页查询所有功能
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request)
	{
		//判断是否首次访问
		String time = request.getParameter("time");
		
		HttpSession session = request.getSession(false);
		Role role =null;
		
		//判断session是否为空
		if(session!=null){
			role = (Role)session.getAttribute("roleSearch");
		}
		
		//首次进入将查询条件置空
		if(time!="1"&&role!=null){
			request.getSession().removeAttribute("roleSearch");
		}
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
		//添加查询条件
		if(time!="1"&&role!=null&&role.getName().trim()!=null&&role.getName().trim()!=""){
			detachedCriteria.add(Restrictions.ilike("name","%"+role.getName().trim()+"%"));
		}
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("role").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("roleList", roleService.getPaginatedList(detachedCriteria, pageInfo));

		return "/role/roleList";
	}
	/*
	 * 分页查询所有功能带条件
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String Alist(Role role,HttpServletRequest request)
	{
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
		//添加条件并将查询条件放入session
			detachedCriteria.add(Restrictions.ilike("name","%"+role.getName().trim()+"%"));
			request.getSession().setAttribute("roleSearch", role);
		
		
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("role").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		
		
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("roleList", roleService.getPaginatedList(detachedCriteria, pageInfo));

		return "/role/roleList";
	}
	/*
	 * 执行新建
	 */
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(Role role, HttpServletRequest request)
	{
		roleService.save(role);

		return "redirect:/role/list";
	}
	/*
	 * 进入修改页面
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request)
	{
			request.setAttribute("role", roleService.getById(id));
			return "/role/update";
	}
	
	
	/*
	 * 修改
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(Role role, HttpServletRequest request)
	{
		Role oldRole = roleService.getById(role.getId());
		oldRole.setName(role.getName());
		oldRole.setDescription(role.getDescription());
		try {
			roleService.update(oldRole);
			return "redirect:/role/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error";
			
		}
		
	}
	
	/*
	 * 添加角色
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public String addRole(Role role, HttpServletRequest request){
		roleService.save(role);
		return "redirect:/role/list";
		
	}
	/*
	 * 删除
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id, HttpServletResponse response)
	{
		String result = "{\"result\":\"error\"}";
		Role role = new Role();
		role.setId(id);
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			roleService.delete(role);
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
	public String managerPrivilege(@PathVariable int id,HttpServletRequest request)
	{
		Role role = roleService.getById(id);

		//获得角色所有功能
		List<Function> functionList = role.getFunctions();
		
 		//获得所有功能
		List<Function> allFunctions = functionService.getAll();
		
		//存储已有功能放入模块
				List<Module> hModuleList = new ArrayList<Module>();
		
	//1.获得所有已经选择的功能所有的模块
		//1。1不判断没有模块的功能
		//1.1遍历所有已选功能
		for (int i = 0; i < functionList.size(); i++) {
			//获得功能
			Function func = functionList.get(i);
			//获得该功能对应模块
			Module mo = func.getModule();
			
			//添加模块不处理模块为空情况
			if(hModuleList.size()==0 && mo!=null){//存放容器为空
				hModuleList.add(mo);
			}else if(hModuleList.size()!=0 && mo!=null){//存放容器不为空
				for (int j = 0; j < hModuleList.size(); j++) {
					Module mo2 = hModuleList.get(j);
					if(mo.getId()!=mo2.getId()){
						hModuleList.add(mo);
					}
				}
			}
		}
		//准备缓存模块
		Module tempMo = new Module();
		tempMo.setId(99999999);
		tempMo.setName("其他");
		tempMo.setDescription("不在模块中的功能");
		List<Function> tempFunctions = new ArrayList<Function>();
		tempMo.setFunctions(tempFunctions);
		
		//清空角色现有模块中所有数据
		for (int i = 0; i < hModuleList.size(); i++) {
			hModuleList.get(i).getFunctions().clear();
		}
		//将角色已有功能放入相应模块--遍历所有功能
		for (int i = 0; i < functionList.size(); i++) {
			Function fun  = functionList.get(i);
			Module mo1 = fun.getModule();
			if(mo1!=null){//功能模块不为空
				for (int j = 0; j < hModuleList.size(); j++) {//遍历角色所有模块
					Module mo2 = hModuleList.get(j);
					if(mo2.getId() == mo1.getId()){
						mo2.getFunctions().add(fun);
					}
					hModuleList.set(j, mo2);
				}
			}else{//模块为空
				List<Function> tempFunctions2 = tempMo.getFunctions();
				if(tempFunctions2.size()==0){//如果功能为空
					tempFunctions2.add(fun);
				}else{//功能不为空
					int num = 0;
					//重复功能不添加
					for (int j = 0; j < tempFunctions2.size(); j++) {
						Function tempFun1 =  tempFunctions2.get(j);
						if(fun.getId()!=tempFun1.getId()){//不相同添加
							num++;
						}
					}
					if(num!=0){
						tempFunctions2.add(fun);
					}
				}
				//将增加后的功能集合添加到模块
				tempMo.setFunctions(tempFunctions2);
			}
		}
		
		//判断缓存模块中是否有功能
		if(tempMo.getFunctions().size()!=0){
			
			hModuleList.add(tempMo);
		}
		
//2.制作角色没有的功能模板集合
		//2.1
		List<Module> nModuleList = new ArrayList<Module>();
		
		//缓存所有未授权功能
		List<Function> noFunctionList = new ArrayList<Function>();
		//遍历所有功能与已有功能对比，将角色没有的功能放入相应功能集合
		for (int i = 0; i < allFunctions.size(); i++) {
			Function func  = allFunctions.get(i);
			int num = 0;
			//遍历所有已有功能进行判定
			for (int j = 0; j < functionList.size(); j++) {
				Function nfunc = functionList.get(j);
				if(nfunc.getId()==func.getId()){
					num++;
				}
			}
			//判断，将不在已有功能中的功能放入新的功能集合中
			if(num==0){
				noFunctionList.add(func);
			}
			
		}
		//遍历角色没有的功能能重做module集合
		for (int i = 0; i < noFunctionList.size(); i++) {
			Function func = noFunctionList.get(i);
			Module mo = func.getModule();
			//判断功能是否定义模块
			if(mo!=null){
				
				
				//判断是否第一次添加
				if(nModuleList.size()==0){
					Module temoMo = new Module();
					temoMo.setId(mo.getId()+1000000);
					temoMo.setDescription(mo.getDescription());
					temoMo.setName(mo.getName());
					List<Function> functions = new ArrayList<Function>();
					temoMo.setFunctions(functions);
					nModuleList.add(temoMo);
				}else{//不是第一次添加，对已有模块进行对比，不存在的添加
					int num = 0;
					for (int j = 0; j < nModuleList.size(); j++) {
						Module mo2 = nModuleList.get(j);
						if(mo.getId() == (mo2.getId()-1000000)){ 
							num++;
						}
					}
					if(num==0){
						Module temoMo = new Module();
						temoMo.setId(mo.getId()+1000000);
						temoMo.setDescription(mo.getDescription());
						temoMo.setName(mo.getName());
						List<Function> functions = new ArrayList<Function>();
						temoMo.setFunctions(functions);
						nModuleList.add(temoMo);
					}
					
				}
			}
		}
		
		//清空模块中的功能
		//清空角色现有模块中所有数据
			for (int i = 0; i < nModuleList.size(); i++) {
				nModuleList.get(i).getFunctions().clear();
			}
			
		//将角色没有功能放入相应模块--遍历所有功能
		for (int i = 0; i < noFunctionList.size(); i++) {
			Function fun  = noFunctionList.get(i);
			Module mo1 = fun.getModule();
			if(mo1!=null){//功能模块不为空
				for (int j = 0; j < nModuleList.size(); j++) {//遍历角色所有模块
					Module mo2 = nModuleList.get(j);
					if((mo2.getId()-1000000) == mo1.getId()){
						mo2.getFunctions().add(fun);
					}
					nModuleList.set(j, mo2);
				}
			}
		}
		
		//准备缓存模块
				Module tempMo2 = new Module();
				tempMo2.setId(99999998);
				tempMo2.setName("其他");
				tempMo2.setDescription("不在模块中的功能");
				List<Function> tempFunctions3 = new ArrayList<Function>();
				
		//将不在模块中且不是已有的功能放入缓存模块
		for (int i = 0; i < functionList.size(); i++) {
			Function nFunc = functionList.get(i);
			//计数器
			Integer num = null;
			for (int j = 0; j < allFunctions.size(); j++) {//遍历所有功能
				Function aFunc = allFunctions.get(j);
				if(aFunc!=null){
					if(nFunc.getId() == aFunc.getId()){//判断所有是所有功能的当前功能
						num = j;
					}
				}
			}
			//移除所有功能中找到的角色已经有的功能
			if(num!=null){
				allFunctions.set(num, null);
				num = null;
			}
		}
		//当前功能是角色所没有的功能
		for (int i = 0; i < allFunctions.size(); i++) {
			Function nfunc = allFunctions.get(i);
			if(nfunc!=null){
				//判断当前功能是否存在模块
				if(nfunc.getModule() == null){//不存在放入缓存
					tempFunctions3.add(nfunc);
				}
			}
		}
		//将新的无模块，未授权功能放入缓存模块
		tempMo2.setFunctions(tempFunctions3);
		
		//判断缓存模块中是否有功能
		if(tempMo2.getFunctions().size()!=0){
			nModuleList.add(tempMo2);
		}
		
		
		//返回值
		request.setAttribute("role", role);
		request.setAttribute("nModulelist", nModuleList);
		request.setAttribute("hModuleList", hModuleList);
		
		return "role/managerFunction";
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
			roleService.addFunctions(ids2, id);
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
			roleService.deleteFunctions(ids2, Integer.valueOf(id));
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e) {
			out.write(result);
			e.printStackTrace();
		}
	}
	//深克隆
	public Object deepClone(Object obj) throws IOException, ClassNotFoundException {  
		//将对象写到流里     
		ByteArrayOutputStream bo=new ByteArrayOutputStream();  
		ObjectOutputStream oo=new ObjectOutputStream(bo);    
		oo.writeObject(obj);     
		//从流里读出来     
		ByteArrayInputStream bi=new  ByteArrayInputStream(bo.toByteArray());     
		ObjectInputStream oi=new ObjectInputStream(bi);     
		return(oi.readObject());     
	} 
}
