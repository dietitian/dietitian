package zkl.dietitian.web.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zkl.dietitian.entity.privilege.Privilege;
import zkl.dietitian.service.privilege.PrivilegeService;
import zkl.dietitian.utils.data.DateStyle;
import zkl.dietitian.utils.displaytag.PageInfo;


/**
 * 菜单
 * @author Lenovo
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	private PrivilegeService privilegeService;

	// 代码表主表 主页
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer parentId,HttpServletRequest request) {
		int pageSize = 5;

		String pageIndexName = new ParamEncoder("menu")
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String strPageIndex = request.getParameter(pageIndexName);
		if (strPageIndex == null) {
			strPageIndex = "1";
		}
		int pageIndex = Integer.valueOf(strPageIndex).intValue();

	
		
		
		DetachedCriteria dc = DetachedCriteria.forClass(Privilege.class);
		dc.add(Restrictions.eq("parentId", parentId));
		dc.add(Restrictions.eq("generateMenu", "1"));
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("parentId", parentId);
		request.setAttribute("menuList",privilegeService.getPaginatedList(dc, pageInfo));
		
		
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Privilege.class);
		detachedCriteria.add(Restrictions.eq("generateMenu", "1"));
		List<Privilege> menuList = privilegeService.getByCriteria(detachedCriteria);
		
		
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < menuList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			Privilege menu = menuList.get(i);
			jsonObject.accumulate("id",menu.getId());
			jsonObject.accumulate("name",menu.getName());
			jsonObject.accumulate("pId",menu.getParentId());
			jsonObject.accumulate("state", menu.getState());
			jsonObject.accumulate("url",menu.getPath());
			jsonArray.add(jsonObject);
		}
		request.setAttribute("menuListTree", jsonArray);
		return "/systemManagement/menuList";
	}

	// 新增初始化
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Integer parentId,HttpServletRequest request) {
		request.setAttribute("parentId", parentId);
		return "/systemManagement/menuAdd";
	}

	// 新增
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(Privilege privilege, HttpServletRequest request) {
		privilege.setCreatePerson("admin");
		privilege.setCreateTime(new DateStyle().getSystemTime());
		privilege.setGenerateMenu("1");
		privilegeService.save(privilege);
		return "redirect:/menu/list?parentId="+privilege.getParentId();
	}

	// 修改初始化
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request) {
		request.setAttribute("menu", privilegeService.getById(id));
		return "/systemManagement/menuUpdate";
	}

	// 修改
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(Privilege privilege,Integer parentId, HttpServletRequest request) {
		try {
			privilege.setGenerateMenu("1");
			privilegeService.update(privilege);
			privilege = privilegeService.getById(privilege.getId());
			request.setAttribute("menu", privilege);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/menu/list?parentId="+parentId;
	}

	// 详情
	@RequestMapping(value = "/describe/{id}", method = RequestMethod.GET)
	public String describe(@PathVariable int id, HttpServletRequest request) {
		request.setAttribute("menu", privilegeService.getById(id));
		return "/systemManagement/menuDescribe";
	}
	
	//删除
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id, HttpServletResponse response,HttpServletRequest request) {
		try {
			Privilege privilege = new Privilege();
			privilege.setId(id);
			privilegeService.delete(privilege);
			DetachedCriteria dc = DetachedCriteria.forClass(Privilege.class);
			dc.add(Restrictions.eq("id", id));
			dc.add(Restrictions.eq("generateMenu", "1"));
			List<Privilege> menu = privilegeService.getByCriteria(dc);
			if(menu.size()==0){
				String result = "{\"result\":\"error\"}";
				privilegeService.getById(id);
				result = "{\"result\":\"success\"}";
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
