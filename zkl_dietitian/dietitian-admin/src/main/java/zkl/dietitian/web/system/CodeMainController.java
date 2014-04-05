package zkl.dietitian.web.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import zkl.dietitian.entity.system.CodeMain;
import zkl.dietitian.service.system.CodeMainService;
import zkl.dietitian.service.system.CodeService;
import zkl.dietitian.utils.data.DateStyle;


/**
 * 代码表 主表
 * 
 * @author Lenovo
 */
@Controller
@RequestMapping("/codeMain")
public class CodeMainController {
	
	@Resource
	private CodeMainService codeMainService;
	
	@Resource
	private CodeService codeService;

	// 代码表主表 主页
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		int pageSize = 5;

		String pageIndexName = new ParamEncoder("codeMain")
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String strPageIndex = request.getParameter(pageIndexName);
		if (strPageIndex == null) {
			strPageIndex = "1";
		}
		int pageIndex = Integer.valueOf(strPageIndex).intValue();

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(CodeMain.class);
		// TODO: 根据页面传入的查询条件构造 detachedCriteria

		request.setAttribute("pageSize", pageSize);
		request.setAttribute("codeMainList",
				codeMainService.search(detachedCriteria, pageIndex, pageSize));

		return "/systemManagement/codeMainList";
	}

	// 新增初始化
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/systemManagement/codeMainAdd";
	}

	// 新增
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(CodeMain codeMain, HttpServletRequest request) {
		codeMain.setCreatePerson("admin");
		codeMain.setCreateTime(new DateStyle().getSystemTime());
		codeMainService.create(codeMain);

		return "redirect:/codeMain/list";
	}

	// 修改初始化
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, HttpServletRequest request) {
		request.setAttribute("codeMain", codeMainService.getById(id));
		return "/systemManagement/codeMainUpdate";
	}

	// 修改
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(CodeMain codeMain,String codeTypeOld, HttpServletRequest request) {
		try {
			codeMain.setUpdatePerson("admin");
			codeMain.setUpdateTime(new DateStyle().getSystemTime());
			codeMainService.update(codeMain);
			
			//获取子表关联数据
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Code.class);
			detachedCriteria.add(Restrictions.eq("codeType",codeTypeOld));
			List<Code> codeList = codeService.search(detachedCriteria);	
			
			//判断是否修改子表codeType
			if(!codeTypeOld.equals(codeMain.getCodeType())){
				for (int i = 0; i < codeList.size(); i++) {
					Code codeObj = codeList.get(i);
					//设置codeType
					codeObj.setCodeType(codeMain.getCodeType());
					codeService.update(codeObj);
				}
			}
			codeMain = codeMainService.getById(codeMain.getId());
			request.setAttribute("codeMain", codeMain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/codeMain/list";
	}

	// 详情
	@RequestMapping(value = "/describe/{id}", method = RequestMethod.GET)
	public String describe(@PathVariable int id, HttpServletRequest request) {
		request.setAttribute("codeMain", codeMainService.getById(id));
		return "/systemManagement/codeMainDescribe";
	}
	
	//删除
	@RequestMapping("/delete")
	public void delete(Integer id,String codeType, HttpServletResponse response) {
		try {
			String result = "{\"result\":\"error\"}";
			codeMainService.delete(codeMainService.getById(id));
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Code.class);
//			detachedCriteria.add(Restrictions.eq("codeType", codeType));
//			List<Code> codeList = codeService.search(detachedCriteria);	
//			for (int i = 0; i < codeList.size(); i++) {
//				codeService.delete(codeList.get(i).getId());
//			}
			result = "{\"result\":\"success\"}";
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
