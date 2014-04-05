package zkl.dietitian.service.log.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.log.AccessLog;
import zkl.dietitian.service.log.AccessLogService;
import zkl.dietitian.utils.displaytag.CommonPaginatedList;
import zkl.dietitian.utils.displaytag.PageInfo;

@Transactional
@Service("accessLogService")
public class AccessLogServiceImpl implements AccessLogService{
	@Resource(name="accessLogDao")
	private CommonDao<AccessLog,String> accessLogDao;
	@Override
	public void save(AccessLog accessLog) {
		accessLogDao.create(accessLog);
	}

	@Override
	public void update(AccessLog accessLog) {
		accessLogDao.update(accessLog);
		
	}

	@Override
	public void delete(AccessLog accessLog) {
		accessLogDao.delete(accessLog);
		
	}

	@Override
	public AccessLog getById(String id) {
		return accessLogDao.getById(id);
	}

	@Override
	public CommonPaginatedList<AccessLog> getPaginatedList(
			DetachedCriteria detachedCriteria, PageInfo pageInfo) {
		CommonPaginatedList<AccessLog> paginatedList =  accessLogDao.getPaginatedListByCriteria(detachedCriteria, pageInfo.getPageIndex(), pageInfo.getPageSize());
		
		return paginatedList;
	}

	@Override
	public List<AccessLog> getByCriteria(DetachedCriteria criteria) {
		return accessLogDao.getByCriteria(criteria);
	}

	@Override
	public List<AccessLog> getAll() {
		return accessLogDao.getAll();
	}
	/*
	 * 获取focusmap的数据
	 * @see 
	 */
	@Override
	public HashMap<String, Object> getFocusMap(String type ,String startTime , String endTime) {
		
		HashMap<String, Object> focusMap = new HashMap<String, Object>();
		
		if(type.equals("quarter")){
			String starttime = startTime+"-01 00:00:00";
			String endtime = endTime+"-01 00:00:00";
			
		}else if(type.equals("month")){
			
			
			
		}else if(type.equals("weeks")){
			
			
			
		}
		
		
		
		ArrayList<AccessLog> accessLogsList = (ArrayList<AccessLog>) accessLogDao.getAll();
		for (int i = 0; i < accessLogsList.size(); i++) {
			AccessLog accessLog = accessLogsList.get(i);
			String time = accessLog.getVisitTime();
			
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = formatDate.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		return focusMap;
	}
	
	public static void main(String[] args) {
		String time = "2014-02-27 18:24:37";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
		   try {
		    date = format.parse(time);
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		   DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
	        System.out.println(df1.format(date));
	        DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒
	        System.out.println(df2.format(date));
	        DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒
	        System.out.println(df3.format(date));
	        DateFormat df4 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL); //显示日期，周，上下午，时间（精确到秒） 
	        System.out.println(df4.format(date));  
	        DateFormat df5 = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG); //显示日期,上下午，时间（精确到秒） 
	        System.out.println(df5.format(date));
	        DateFormat df6 = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT); //显示日期，上下午,时间（精确到分） 
	        System.out.println(df6.format(date));
	        DateFormat df7 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM); //显示日期，时间（精确到分）
	        System.out.println(df7.format(date));
	}
}
