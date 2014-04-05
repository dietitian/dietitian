package zkl.dietitian.web.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zkl.dietitian.entity.log.AccessLog;
import zkl.dietitian.entity.log.MapEntity;
import zkl.dietitian.service.log.AccessLogService;
import zkl.dietitian.utils.displaytag.PageInfo;



@Controller
@RequestMapping("/accesslog")
public class AccessLogController {
	@Resource
	private AccessLogService accessLogService;
	/*
	 * 分页查询所有功能
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request)
	{
		//判断是否首次访问
		String time = request.getParameter("time");
		
		HttpSession session = request.getSession(false);
		AccessLog accesslog =null;
		
		//判断session是否为空
		if(session!=null){
			accesslog = (AccessLog)session.getAttribute("accesslogSearch");
		}
		
		//首次进入将查询条件置空
		if(time!="1"&&accesslog!=null){
			request.getSession().removeAttribute("accesslogSearch");
		}
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AccessLog.class);
		//添加查询条件
		if(time!="1"&&accesslog!=null&&accesslog.getVisitTime()!=null&&accesslog.getVisitTime()!=""){
			detachedCriteria.add(Restrictions.ilike("visitTime","%"+accesslog.getVisitTime()+"%"));
		}
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("accesslog").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(20);
		
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("accessLogList", accessLogService.getPaginatedList(detachedCriteria, pageInfo));

		return "log/accesslog/accessLogList";
	}
	/*
	 * 分页查询所有功能带条件
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String Alist(AccessLog accessLog,HttpServletRequest request)
	{
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AccessLog.class);
		//添加条件并将查询条件放入session
//			detachedCriteria.add(Restrictions.ilike("name","%"+accessLog+"%"));
			request.getSession().setAttribute("accessLogSearch", accessLog);
		
		
		PageInfo pageInfo = new PageInfo();
		String pageIndexName = new ParamEncoder("accessLog").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageIndex = request.getParameter(pageIndexName);
		if (pageIndex == null)
		{
			pageIndex = "1";
		}
		pageInfo.setPageIndex(Integer.valueOf(pageIndex).intValue());
		pageInfo.setPageSize(20);
		
		
		request.setAttribute("pageSize", pageInfo.getPageSize());
		request.setAttribute("accessLogList", accessLogService.getPaginatedList(detachedCriteria, pageInfo));

		return "log/accesslog/accessLogList";
	}
	
	//网络日志折线图页面
	@RequestMapping(value = "/focusmap", method = RequestMethod.GET)
	public String accessLogFocusMap(HttpServletRequest request){
		
		return "log/accesslog/logfocusmap";
	}
	
	//网络日志折线图数据查询
			@RequestMapping(value = "/getmaplist", method = RequestMethod.GET)
			public void accessLogFocusMap(String type ,String startTime , String endTime,HttpServletRequest request,HttpServletResponse response){
				//数组的个数
				int arrayNum ;
				String stringSQl = null;
				String stringSQlT = null;
				String result ;
				MapEntity mapEntity = new MapEntity();
				MapEntity mapEntityT = new MapEntity();
//				AboutUsMapService aboutUsMapService = new AboutUsMapService();
				if(type.equals("day")){
//				   stringSQl = "select count(DISTINCT(a.userHostAddress)) as addressNum,DATE_FORMAT(a.visitTime,'%Y%m%d')as visitTime,a.urlAbsoluteUri "
//						        +" FROM access_log a " 
//						        +" where a.urlAbsoluteUri like '%%' and ( date_format(a.visitTime,'%Y-%m-%d') BETWEEN  '"+
//						        startTime+"'  AND  '"+endTime+"' ) "
//						        +" group by DATE_FORMAT(a.visitTime,'%Y-%m-%d') ";
//				   stringSQlT = "select count(a.userHostAddress) as addressNum,DATE_FORMAT(a.visitTime,'%Y%m%d')as visitTime,a.urlAbsoluteUri "
//					        +" FROM access_log a " 
//					        +" where a.urlAbsoluteUri like '%%' and ( date_format(a.visitTime,'%Y-%m-%d') BETWEEN  '"+
//					        startTime+"'  AND  '"+endTime+"' ) "
//					        +" group by DATE_FORMAT(a.visitTime,'%Y-%m-%d') ";
//				   mapEntity= aboutUsMapService.getMapDay(startTime, endTime, stringSQl);
//				   mapEntityT= aboutUsMapService.getMapDay(startTime, endTime, stringSQlT);
					
					HashMap<String, Object> focusMap = accessLogService.getFocusMap(type, startTime, endTime);
					mapEntity = (MapEntity) focusMap.get("mapEntity");
					mapEntityT = (MapEntity) focusMap.get("mapEntityT");
				}else if(type.equals("month")){
//					 stringSQl = "select count(DISTINCT(a.userHostAddress)) as addressNum,DATE_FORMAT(a.visitTime,'%Y%m')as visitTime,a.urlAbsoluteUri "
//						        +" FROM access_log a " 
//						        +" where a.urlAbsoluteUri like '%%' and ( date_format(a.visitTime,'%Y-%m') BETWEEN  '"+
//						        startTime+"'  AND  '"+endTime+"' ) "
//						        +" group by DATE_FORMAT(a.visitTime,'%Y-%m') ";
//					 stringSQlT = "select count(a.userHostAddress) as addressNum,DATE_FORMAT(a.visitTime,'%Y%m')as visitTime,a.urlAbsoluteUri "
//						        +" FROM access_log a " 
//						        +" where a.urlAbsoluteUri like '%%' and ( date_format(a.visitTime,'%Y-%m') BETWEEN  '"+
//						        startTime+"'  AND  '"+endTime+"' ) "
//						        +" group by DATE_FORMAT(a.visitTime,'%Y-%m') ";
//				   mapEntity= aboutUsMapService.getMapMonth(startTime, endTime, stringSQl);
//				   mapEntityT= aboutUsMapService.getMapMonth(startTime, endTime, stringSQlT);
					HashMap<String, Object>  focusMap = accessLogService.getFocusMap(type, startTime, endTime);
					mapEntity = (MapEntity) focusMap.get("mapEntity");
					mapEntityT = (MapEntity) focusMap.get("mapEntityT");
				}else if(type.equals("year")){
//					 stringSQl = "select count(DISTINCT(a.userHostAddress)) as addressNum,DATE_FORMAT(a.visitTime,'%Y')as visitTime,a.urlAbsoluteUri "
//						        +" FROM access_log a " 
//						        +" where a.urlAbsoluteUri like '%%' and ( date_format(a.visitTime,'%Y') BETWEEN  '"+
//						        startTime+"'  AND  '"+endTime+"' ) "
//						        +" group by DATE_FORMAT(a.visitTime,'%Y') ";
//					 stringSQlT = "select count(a.userHostAddress) as addressNum,DATE_FORMAT(a.visitTime,'%Y')as visitTime,a.urlAbsoluteUri "
//						        +" FROM access_log a " 
//						        +" where a.urlAbsoluteUri like '%%' and ( date_format(a.visitTime,'%Y') BETWEEN  '"+
//						        startTime+"'  AND  '"+endTime+"' ) "
//						        +" group by DATE_FORMAT(a.visitTime,'%Y') ";
//				   mapEntity= aboutUsMapService.getMapYear(startTime, endTime, stringSQl);
//				   mapEntityT= aboutUsMapService.getMapYear(startTime, endTime, stringSQlT);
					HashMap<String, Object>  focusMap = accessLogService.getFocusMap(type, startTime, endTime);
					mapEntity = (MapEntity) focusMap.get("mapEntity");
					mapEntityT = (MapEntity) focusMap.get("mapEntityT");
				}
				
				result = "{\"result\":\"error\","
				+"\"las\":\""+mapEntity.getStringLabels()+"\","
				+"\"Ins\":\""+mapEntity.getIntdatesets()+"\","
				+"\"lasT\":\""+mapEntityT.getStringLabels()+"\","
				+"\"InsT\":\""+mapEntityT.getIntdatesets()+"\","
				+"\"num\":\""+mapEntity.getArrayNum()+"\""
				+"}";
				System.out.println(result);
				request.setAttribute("mapEntity", mapEntity);
				response.setContentType("application/json");
				try {
					PrintWriter out = response.getWriter();
					out.write(result);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
	
}
