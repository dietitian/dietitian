package zkl.dietitian.web.privilege;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import zkl.dietitian.entity.privilege.Function;
import zkl.dietitian.entity.privilege.Privilege;
import zkl.dietitian.entity.privilege.Role;
import zkl.dietitian.entity.privilege.User;
import zkl.dietitian.service.privilege.RoleService;
import zkl.dietitian.service.privilege.UserService;
import zkl.dietitian.utils.base.MD5Utils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private Validator beanValidator;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	
	/*
	 * 用户注销
	 */
	@RequestMapping(value="/loginOut", method = RequestMethod.GET)
	public void loginOut(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("existUser");
		try {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * 检查用户是否存在
	 */
	@RequestMapping(value="/hasUser", method = RequestMethod.POST)
	public void hasUser(User user, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean hasUser = userService.hasUser(user);
		if(hasUser){
			result = "{\"result\":\"hasUser\"}";
		}else{
			result = "{\"result\":\"success\"}";
		}
		try {
			out.write(result);
		} catch (Exception e1) {
			out.write(result);
			e1.printStackTrace();
		}

	}


	/*
	 * 登录
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(User user, HttpServletRequest request,HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		response.setContentType("application/json");
		PrintWriter out = null;
		User existUser = userService.login(user);
		
		String checkCodeSession = (String) request.getSession().getAttribute("key");
		//判断验证码是否正确
		if(checkCodeSession==null||!user.getCheckcode().equals(checkCodeSession)){
			result = "{\"result\":\"checkCodeError\"}";
		//用户存在
		}else  if (existUser != null && user.getCheckcode().equals(checkCodeSession)) {
				String password = user.getPassword();
				if (MD5Utils.md5(password).equals(existUser.getPassword())) {
					List<Role> roles = existUser.getRoles();
					for (int i = 0; i < roles.size(); i++) {
						Role role = roles.get(i);
						List<Function> functions = role.getFunctions();
						for (int j = 0; j < functions.size(); j++) {
							Function function = functions.get(j);
							List<Privilege> privileges = function.getPrivileges();
							for (int k = 0; k < privileges.size(); k++) {
								Privilege privilege = privileges.get(k);
								existUser.getPrivilegePaths().add(
										privilege.getPath());
							}
						}
					}
					request.getSession().setAttribute("existUser", existUser);
					result = "{\"result\":\"success\"}";
				} else {
					result = "{\"result\":\"passWordError\"}";
				}
			//用户不存在
			}else if(existUser == null && user.getCheckcode().equals(checkCodeSession)){
				result = "{\"result\":\"nameError\"}";
			}
		request.getSession().removeAttribute("key");
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * 分页查询
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		// 判断是否首次访问
		String time = request.getParameter("time");

		HttpSession session = request.getSession(false);
		User searchUser = null;

		// 判断session是否为空
		if (session != null) {
			searchUser = (User) session.getAttribute("userSearch");
		}

		// 首次进入将查询条件置空
		if (time != "1" && searchUser != null) {
			request.getSession().removeAttribute("userSearch");
		}

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(User.class);
		// 添加查询条件
		if (time != "1" && searchUser != null
				&& searchUser.getName().trim() != null
				&& searchUser.getName().trim() != "") {
			detachedCriteria.add(Restrictions.ilike("name", "%"
					+ searchUser.getName().trim() + "%"));
		}
		int pageSize = 5;

		String pageIndexName = new ParamEncoder("user")
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String strPageIndex = request.getParameter(pageIndexName);
		if (strPageIndex == null) {
			strPageIndex = "1";
		}
		int pageIndex = Integer.valueOf(strPageIndex).intValue();
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("userList",
				userService.search(detachedCriteria, pageIndex, pageSize));

		return "user/list";
	}

	/*
	 * 带条件分页查询
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listq(User searchUser, HttpServletRequest request) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(User.class);
		// 添加条件并将查询条件放入session
		detachedCriteria.add(Restrictions.ilike("name", "%"
				+ searchUser.getName().trim() + "%"));
		request.getSession().setAttribute("userSearch", searchUser);

		// 每页记录条数
		int pageSize = 5;
		String pageIndexName = new ParamEncoder("user")
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String strPageIndex = request.getParameter(pageIndexName);
		if (strPageIndex == null) {
			strPageIndex = "1";
		}
		int pageIndex = Integer.valueOf(strPageIndex).intValue();

		request.setAttribute("pageSize", pageSize);
		request.setAttribute("userList",
				userService.search(detachedCriteria, pageIndex, pageSize));

		return "user/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute(new User());
		return "user/create";
	}

	/*
	 * 新建hibernate验证
	 */
	// @RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	// public String doCreate(@Valid User user, BindingResult binding,
	// HttpServletRequest request)
	// {
	// if(binding.hasErrors()){
	// return "/user/create";
	// }
	// userService.create(user);
	//
	// return "redirect:/user/list";
	// }
	/*
	 * 新建springmvc验证
	 */
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(@Valid @ModelAttribute("user") User user,
			BindingResult binding, HttpServletRequest request, Model model) {

		System.out.println("进入验证");
		beanValidator.validate(user, binding);
		if (binding.hasErrors()) {
			return "/user/create";
		} else {
			// 密码加密
			user.setPassword(MD5Utils.md5(user.getPassword()));
			userService.create(user);
			return "redirect:/user/list";
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request) {
		request.setAttribute("user", userService.getById(id));

		return "/user/update";
	}

	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@Valid @ModelAttribute("user") User user,
			BindingResult binding, HttpServletRequest request, Model model) {

		User oldUser = userService.getById(user.getId());
		beanValidator.validate(user, binding);
		
		
		if (binding.hasErrors()) {
			return "/user/create";
		} else {
			 if(user.getPassword().equals(oldUser.getPassword())){
				 oldUser.setName(user.getName());
				
				 oldUser.setAge(user.getAge());
				 oldUser.setPassword(user.getPassword());
				 oldUser.setDescription(user.getDescription());
				 
				 oldUser.setRealname(user.getRealname());
				 oldUser.setGender(user.getGender());
				 oldUser.setTelephone(user.getTelephone());
				 oldUser.setEmail(user.getEmail());
				 userService.update(oldUser);
				
				 }else{
				 oldUser.setName(user.getName());
				 oldUser.setAge(user.getAge());
				 oldUser.setDescription(user.getDescription());
				 oldUser.setPassword(MD5Utils.md5(user.getPassword()));
				 oldUser.setRealname(user.getRealname());
				 oldUser.setGender(user.getGender());
				 oldUser.setTelephone(user.getTelephone());
				 oldUser.setEmail(user.getEmail());
				 userService.update(oldUser);
				 }
			
			return "redirect:/user/list";
		}

	}

	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			userService.deleteById(id);
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e1) {
			out.write(result);
			e1.printStackTrace();
		}

	}

	/*
	 * 进入授权页面
	 */
	@RequestMapping("/managerRole/{id}")
	public String managerPrivilege(@PathVariable int id,
			HttpServletRequest request) {
		User user = userService.getById(id);
		// 查询所有权限资源
		List<Role> roles = roleService.getAll();
		// 获得已有权限
		List<Role> hasRoles = user.getRoles();
		// 遍历对比，排除已存在权限资源
		for (int i = 0; i < hasRoles.size(); i++) {
			Role hRole = hasRoles.get(i);
			for (int j = 0; j < roles.size(); j++) {
				if (hRole.getId().equals(roles.get(j).getId())) {
					roles.remove(j);
				}
			}
		}
		request.setAttribute("noRoles", roles);
		request.setAttribute("user", user);

		return "user/managerRole";
	}

	/*
	 * 添加权限资源
	 */
	@RequestMapping(value = "/addRoles", method = RequestMethod.POST)
	public void addPrivileges(String ids, Integer id,
			HttpServletResponse response) {
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
			userService.addRoles(ids2, id);
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
	@RequestMapping(value = "/deleteRoles", method = RequestMethod.POST)
	public void deletePrivileges(String ids, String id,
			HttpServletResponse response) {
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
			userService.deleteRoles(ids2, Integer.valueOf(id));
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e) {
			out.write(result);
			e.printStackTrace();
		}
	}

	/*
	 * 暂时不清楚作用 猜测 1.数据类型的自定义，进行验证类型匹配，最直观的是日期类型的匹配
	 */
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		System.out.println("binder定义");
		binder.registerCustomEditor(Integer.class, null,
				new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(
				Long.class, null, true));
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
				dateFormat, true));
	}
}
