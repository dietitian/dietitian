package zkl.dietitian.web.privilege;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zkl.dietitian.entity.privilege.Privilege;
import zkl.dietitian.service.privilege.PrivilegeService;
import zkl.dietitian.utils.displaytag.PageInfo;


@Controller
@RequestMapping("/privilege")
public class PrivilegeController {
	@Resource
	private PrivilegeService privilegeService;
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("privilege").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Privilege.class);
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("privilegeList", privilegeService.getPaginatedList(detachedCriteria, pageInfo));
		
		return "/privilege/privilegeList";
	}
	
	@RequestMapping(value = "/addPrivilege", method = RequestMethod.POST)
	public String doCreate(Privilege privilege, HttpServletRequest request)
	{
		privilegeService.save(privilege);

		return "redirect:/privilege/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request)
	{
		
		request.setAttribute("privilege", privilegeService.getById(id));
		return "/privilege/update";
	}
	
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(Privilege privilege, HttpServletRequest request)
	{
		Privilege oldPrivilege = privilegeService.getById(privilege.getId());
		oldPrivilege.setName(privilege.getName());
		oldPrivilege.setPath(privilege.getPath());
		oldPrivilege.setDescription(privilege.getDescription());
		oldPrivilege.setGenerateMenu(privilege.getGenerateMenu());
		privilegeService.update(oldPrivilege);
		return "redirect:/privilege/list";
	}
	
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id, HttpServletResponse response)
	{
		String result = "{\"result\":\"error\"}";
		Privilege privilege = new Privilege();
		privilege.setId(id);
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			privilegeService.delete(privilege);
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e1) {
			out.write(result);
			e1.printStackTrace();
		}

		
	}
}
