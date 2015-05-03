package com.xnjd.hynm.filter;

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

import com.xnjd.hynm.model.Account;

public class AuthFilter implements Filter {

	String url = null;
	FilterConfig filterConfig = null;

	// 初始化方法
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.url = filterConfig.getInitParameter("LOGIN_URL");
	}

	// 过滤处理方法
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		   HttpServletResponse resp = (HttpServletResponse) response;    
	        HttpServletRequest req=(HttpServletRequest)request;  
	        HttpSession session = req.getSession(true);    
	        String path  = req.getContextPath();
	        Account admin = (Account) session.getAttribute("admin");//  
	        String url=req.getRequestURI(); 
	            if(url.equals("/")||url.equals("/login.jsp")){
	            	if(req.isRequestedSessionIdValid()&&admin!=null){
	            	  resp.sendRedirect("/sjrw/index.jsp");
	            		return;
	            	}
	            }
	        	if(url.contains("/admin")){
		        	 if(!req.isRequestedSessionIdValid()||admin==null || admin.equals(""))  
		 	        {  
		 	            //判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转  
		 	            if(url!=null && !url.equals("") && ( url.indexOf("Login")<0 && url.indexOf("login")<0 ))  
		 	            {  
		 	            	resp.setContentType("text/html;charset=utf-8");  
		 	            	String meg="<script>alert('您尚未登陆！')</script>"+"<script>top.location.href='"+path+this.url+"'</script>";
		 	            	//resp.sendRedirect(path+this.url);  
		 	            	resp.getWriter().println(meg);
		 	            	return;
		 	            } 
  
		 	        }
		        	
		        }  
	   
		        chain.doFilter(request, response);//已通过验证，用户访问继续       
	       
		   
	}

	// 销毁方法
	public void destroy() {
		this.url = null;
		this.filterConfig = null;
	}

}
