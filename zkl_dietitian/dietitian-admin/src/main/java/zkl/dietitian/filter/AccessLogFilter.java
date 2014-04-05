package zkl.dietitian.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import zkl.dietitian.utils.base.StringUtils;
import zkl.dietitian.utils.datebase.JDBCUtils;


public class AccessLogFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		// 转换参数
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		// 5.访问路径uri
		String visitpageuri = request.getRequestURI();
		if (visitpageuri.contains(".")) {
			chain.doFilter(request, response);
			return;
		}

		// 获取网络信息
		// 1.当前时间
		Date nowdate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String visitedateString = formatter.format(nowdate);
		
		// 数型时间
		Long nowdate2 = nowdate.getTime();
		// 2.获得访问者ip地址
		String ip = request.getRemoteAddr();

		// 3.访问模式--HTTP等
		String schme = request.getScheme();

		// 4.接受请求的端口号
		Integer port = request.getServerPort();

		// 6.访问路径url
		String viditpageurl = request.getRequestURL().toString();
		
		// 7.得到访问者的浏览器名
		String Agent = request.getHeader("User-Agent");
		StringTokenizer st = new StringTokenizer(Agent, ";");
		String visiterbrowser;
		try {
			st.nextToken();
			visiterbrowser = st.nextToken();
		} catch (Exception e2) {
			visiterbrowser = "截取浏览器出错！";
		}

		// 8.得到访问者的操作系统名
		String visiterOS = "";
		try {
			visiterOS = st.nextToken();
		} catch (Exception e1) {
			visiterOS = "截取操作系统出错!";
		}

		// 9.得到标题
		String visitpagetitle = (String) request.getAttribute("title");
		if (visitpagetitle == null || visitpagetitle.equals("")) {
			visitpagetitle = "无标题";
		}
		// 10.得到父页面
		String backIP = request.getHeader("Referer");
		if (backIP == null || backIP.equals("")) {
			backIP = "用户直接访问";
		}
		String id = StringUtils.getUUID();

		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		// ook(id,price,title,author,imgurl,description,category_id)
		String sql = "insert into"
				+ " access_log(id,visitTime,userHostAddress,urlAbsoluteUri,physicalPath,"
				+ "userAgent,httpMethod,urlPort,isLocal,browserType,browserPlatform,visitTime2)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { id, visitedateString, ip, visitpageuri,
				viditpageurl, Agent, schme, port, backIP, visiterbrowser,
				visiterOS, nowdate2 };

		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	/**
	 * 得到远程主机IP
	 * 
	 * @return string
	 * */
	public String getRemoteIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (!checkIP(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public boolean checkIP(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
				|| ip.split(".").length != 4) {
			return false;
		}
		return true;
	}
}
