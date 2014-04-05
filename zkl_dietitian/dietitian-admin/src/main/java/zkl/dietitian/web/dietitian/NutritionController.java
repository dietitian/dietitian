package zkl.dietitian.web.dietitian;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import zkl.dietitian.entity.datastore.Nutrition;
import zkl.dietitian.service.dietitian.NutritionService;
import zkl.dietitian.utils.displaytag.PageInfo;
import zkl.dietitian.utils.excle.ExcelImprotUtil;


@Controller
@RequestMapping("/nutrition")
public class NutritionController {
	@Resource
	private NutritionService nutritionService;
	
	//子类
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(@RequestParam String pcategory,HttpServletRequest request){
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("nutrition").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Nutrition.class);
		detachedCriteria.add(Restrictions.eq("category", pcategory));
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("nutritionList", nutritionService.getPaginatedList(detachedCriteria, pageInfo));
		request.setAttribute("pcategory", pcategory);
		return "/nutrition/nutritionList";
	}
	
	@RequestMapping(value = "/addNutritionInit", method = RequestMethod.GET)
	public String create(@RequestParam String pcategory, HttpServletRequest request){
		request.setAttribute("pcategory", pcategory);
		return "/nutrition/nutritionAdd";
	}
	
	@RequestMapping(value = "/addNutrition", method = RequestMethod.POST)
	public String doCreate(Nutrition Nutrition, HttpServletRequest request)
	{
		nutritionService.create(Nutrition);

		return "redirect:/nutrition/list?pcategory="+Nutrition.getCategory();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam String id,@RequestParam String pcategory, HttpServletRequest request)
	{
		request.setAttribute("pcategory", pcategory);
		request.setAttribute("nutrition", nutritionService.getById(id));
		return "/nutrition/update";
	}
	
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(Nutrition nutrition, HttpServletRequest request)
	{
		nutritionService.update(nutrition);
		return "redirect:/nutrition/list?pcategory="+nutrition.getCategory();
	}
	
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable String id, HttpServletResponse response)
	{
		String result = "{\"result\":\"error\"}";
		Nutrition Nutrition = new Nutrition();
		Nutrition.setId(id);
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			nutritionService.delete(Nutrition);
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e1) {
			out.write(result);
			e1.printStackTrace();
		}

		
	}
	
	
	
	
	//父类
	@RequestMapping(value = "/listp",method = RequestMethod.GET)
	public String listp(HttpServletRequest request){
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("nutrition").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(5);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Nutrition.class);
		detachedCriteria.add(Restrictions.isNull("category"));
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("nutritionList", nutritionService.getPaginatedList(detachedCriteria, pageInfo));
		
		return "/nutrition/nutritionPList";
	}
	
	
	@RequestMapping(value = "/addNutritionInitp", method = RequestMethod.GET)
	public String createp( HttpServletRequest request){
		
		return "/nutrition/nutritionPAdd";
	}
	
	
	
	
	@RequestMapping(value = "/addNutritionp", method = RequestMethod.POST)
	public String doCreatep(Nutrition Nutrition, HttpServletRequest request)
	{
		nutritionService.create(Nutrition);

		return "redirect:/nutrition/listp";
	}
	
	@RequestMapping(value = "/updatep/{id}", method = RequestMethod.GET)
	public String updatep(@PathVariable String id, HttpServletRequest request)
	{
		
		request.setAttribute("nutrition", nutritionService.getById(id));
		return "/nutrition/updateP";
	}
	
	@RequestMapping(value = "/doUpdatep", method = RequestMethod.POST)
	public String doUpdatep(Nutrition Nutrition, HttpServletRequest request)
	{
		nutritionService.update(Nutrition);
		return "redirect:/nutrition/listp";
	}
	
	
	@RequestMapping("/deletep/{id}")
	public void deletep(@PathVariable String id, HttpServletResponse response)
	{
		String result = "{\"result\":\"error\"}";
		Nutrition Nutrition = new Nutrition();
		Nutrition.setId(id);
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			nutritionService.delete(Nutrition);
			result = "{\"result\":\"success\"}";
			out.write(result);
		} catch (Exception e1) {
			out.write(result);
			e1.printStackTrace();
		}

		
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "nutritionFile", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model){
        String fileName = file.getOriginalFilename();  
        
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
        
        String filepath = realPath+"/"+fileName;
        try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}  
        ExcelImprotUtil<Nutrition> exceltool = new ExcelImprotUtil<Nutrition>("zkl.dietitian.entity.datastore.Nutrition");
		InputStream in = null;
		try {
			in = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String[] colNames={"name","region","edible_part",
							"energy","moisture","protein","fat",
							"df","carbohydrate","vax","vb1",
							"vb2","vb3","ve","na",
							"ca","fe","category","vc",
							"types","cholesterol"};
		String pattern = null;
		List<Nutrition> beans = null;
		try {
			beans = exceltool.excute(in, colNames, pattern,1);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < beans.size(); i++) {
			nutritionService.create(beans.get(i));
		}
        return "redirect:/nutrition/listp";
	}
}
