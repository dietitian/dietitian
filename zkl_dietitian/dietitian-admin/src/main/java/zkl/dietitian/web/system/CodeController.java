package zkl.dietitian.web.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zkl.dietitian.entity.system.Code;
import zkl.dietitian.service.system.CodeService;
import zkl.dietitian.utils.data.DateStyle;


/**
 * 代码表子表
 * 
 * @author Lenovo
 */
@Controller
@RequestMapping("/code")
public class CodeController {

	@Resource
	private CodeService codeService;
	
	// 查询
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(String codeType, HttpServletRequest request) {
		int pageSize = 5;

		String pageIndexName = new ParamEncoder("code")
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String strPageIndex = request.getParameter(pageIndexName);
		if (strPageIndex == null) {
			strPageIndex = "1";
		}
		int pageIndex = Integer.valueOf(strPageIndex).intValue();

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Code.class);
		detachedCriteria.add(Restrictions.eq("codeType", codeType));
		
		request.setAttribute("codeType", codeType);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("codeList",codeService.search(detachedCriteria, pageIndex, pageSize));

		return "/systemManagement/codeList";
	}

	// 新增初始化
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(String codeType, HttpServletRequest request) {
		request.setAttribute("codeType", codeType);
		return "/systemManagement/codeAdd";
	}

	// 新增
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(Code code, HttpServletRequest request) {
		code.setCreatePerson("admin");
		code.setCreateTime(new DateStyle().getSystemTime());
		codeService.create(code);

		return "redirect:/code/list?codeType=" + code.getCodeType();
	}

	// 修改初始化
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request) {

		request.setAttribute("code", codeService.getById(id));

		return "/systemManagement/codeUpdate";
	}

	// 修改
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(Code code, HttpServletRequest request) {
		try {
			code.setUpdatePerson("admin");
			code.setUpdateTime(new DateStyle().getSystemTime());
			codeService.update(code);
			code = codeService.getById(code.getId());
			request.setAttribute("code", code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/code/list?codeType="+code.getCodeType();
	}

	// 详情
	@RequestMapping(value = "/describe/{id}", method = RequestMethod.GET)
	public String describe(@PathVariable int id, HttpServletRequest request) {
		request.setAttribute("code", codeService.getById(id));
		return "/systemManagement/codeDescribe";
	}
	
	//删除
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id, HttpServletResponse response) {
		try {
			String result = "{\"result\":\"error\"}";
			codeService.delete(id);
			result = "{\"result\":\"success\"}";
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
