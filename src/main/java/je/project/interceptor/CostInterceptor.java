package je.project.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import je.project.domain.Staff;

/**
 * 拦截客户信息模块
 */
@Component
public class CostInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				response.setCharacterEncoding("UTF-8");

		//管理首页权限值
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("staff") != null) {
            Staff staff= (Staff) session.getAttribute("staff");
            if(staff.getPosition()==0||staff.getPosition()==1||staff.getPosition()==4){
                return true;
			}
			
			response.sendRedirect("/privilege.html");
            return false;
		} else {

			PrintWriter printWriter = response.getWriter();
			printWriter.write("{resuCode:5,resuMessage:\"not login!\"}");
			response.sendRedirect("/login/login");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet
		// 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}

}