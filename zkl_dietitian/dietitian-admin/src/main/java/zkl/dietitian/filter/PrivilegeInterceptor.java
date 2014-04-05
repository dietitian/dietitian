package zkl.dietitian.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import zkl.dietitian.entity.privilege.User;


/**
 * 自定义权限控制
 * @author ZKL
 *
 */
public class PrivilegeInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String path = request.getRequestURI().substring(request.getContextPath().length()+1);
		String contextpath = request.getContextPath();
		// 从Session中获得登陆用户信息
		User existUser = (User) request.getSession().getAttribute("existUser");
		
		// 1 判断对方是否在登陆 
		if(path.equals("user/login")||path.equals("validatecode.jsp")||path.equals("user/loginOut")){
			
			// 正在登陆
			 return super.preHandle(request, response, handler);
		
		}else if(existUser== null){ // 2 判断用户是否已经登陆
			if(path.equals(contextpath)){
				// 未登陆
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return false; 
				
			}else{
				// 未登陆
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				
				return false; 
			}
		}else if(existUser.getName().equals("admin")){ // 3 判断用户是否是管理员
			System.out.println(existUser.getName()+"正在进行"+path+"操作。");
			// 已经登陆 是管理员
			 return super.preHandle(request, response, handler); 
		
		}else if(path.equals("validatecode.jsp") ){ // 4 主页、修改密码对所有用户都开放
			
			// 不是管理员 ， 访问通用功能 
			return super.preHandle(request, response, handler);
		
		}else { // 5 、当前用户已经登陆， 不是管理员，访问功能不是开发功能
			System.out.println(existUser.getName()+"正在进行"+path+"操作。");
			boolean pri = false;
			List<String> privileges = existUser.getPrivilegePaths();
			
			for (int i = 0; i < privileges.size(); i++) {
				String privilegePath = privileges.get(i);
				String suffix = privilegePath.substring(privilegePath.lastIndexOf("/")+1);
				if(suffix.equals("*")){
					String prefixD = path.substring(0, path.lastIndexOf("/"));
					String prefixR = privilegePath.substring(0, privilegePath.lastIndexOf("/"));
					if(prefixR.equals(prefixD)){
						System.out.println(privilegePath);
						pri = true;
					}
				}else{
					if(privilegePath.equals(path)){
						System.out.println(privilegePath);
						pri = true;
					}
				}
			}
			
			// 不应该每次 都去访问数据库。在用户登陆后， 将用户具有功能列表 放入 Session
			if(pri){
			
				// 该用户具有权限
				return super.preHandle(request, response, handler); // 代表放行
			
			}else{
				
				// 权限不足
				response.sendRedirect(request.getContextPath()+"/error/403.jsp");
				return false;
			
			}
		}
		
		
	}

	


}
