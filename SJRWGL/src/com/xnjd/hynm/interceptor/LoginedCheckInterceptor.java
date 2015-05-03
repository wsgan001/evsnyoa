package com.xnjd.hynm.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xnjd.hynm.model.Account;

/** session过期、登录有效性及操作的权限验证拦截器 */
public class LoginedCheckInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 拦截请求并进行登录有效性验证 */
	public String intercept(ActionInvocation ai) throws Exception {
		//取得请求的URL
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		Account admin = null;
		//验证Session是否过期
		if(!ServletActionContext.getRequest().isRequestedSessionIdValid()){
			//session过期,转向session过期提示页,最终跳转至登录页面
			System.out.println(1);
			return "tologin";
		}else{
			//对登录与注销请求直接放行,不予拦截
			if (url.indexOf("login.action")!=-1 || url.indexOf("logout.action")!=-1){
				System.out.println(2);
				return ai.invoke();
				
			}else{
				admin = (Account)ServletActionContext.getRequest().getSession().getAttribute("admin");
				//验证是否已经登录
				if (admin==null){
					//尚未登录,跳转至登录页面
					System.out.println(3);
					return "tologin";
				}else{
						//其它不需要权限验证的请求直接放行
					System.out.println(4);
						return ai.invoke();
					}					
				}				
						
		}
	}
}