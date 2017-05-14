package cn.com.xbb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain  chain)
			throws IOException, ServletException {
		try{
		// TODO Auto-generated method stub
		 HttpServletRequest servletRequest = (HttpServletRequest) request;
		 HttpServletResponse servletResponse = (HttpServletResponse) response;
		 HttpSession session = servletRequest.getSession();
		 // 获得用户请求的URI
		 String path = servletRequest.getRequestURI();
		 // 从session里取员工工号信息
		 String email = (String) session.getAttribute("email");
		 System.out.println("out:" + email);
		  /*创建类Constants.java，里面写的是无需过滤的页面
		  for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {
		  	if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
		 		chain.doFilter(servletRequest, servletResponse);
		 		return;
		 	}
		 }*/
		// 登陆页面无需过滤
		if(path.indexOf("/login.jsp") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		 }
		  
		// 判断如果没有取到员工信息,就跳转到登陆页面
		if (email == null || "".equals(email)) {
			// 跳转到登陆页面\
			servletResponse.sendRedirect("/RemoteSystem/jsp/login.jsp?status=401");
		}
		else {
			// 已经登陆,继续此次请求
			chain.doFilter(request, response);
		}
	}
		catch(Exception e){
			e.printStackTrace();
		}
	
}
		

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
