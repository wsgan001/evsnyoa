/**
 * 
 */
package com.xnjd.hynm.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * @author my
 *
 */
public class AjaxResponseViewInfo {
	/**
	 * 检查返回内容并输出到前台
	 * 
	 * @param emailInfo
	 */
	public void reponseViewInfo(Object emailInfo) {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		try {
			if (!"".equals(emailInfo) && emailInfo != null) {
				out = response.getWriter();
				System.out.println(emailInfo);
				out.print(emailInfo);
			} else
				out = response.getWriter();
				out.print("");	
		} catch (IOException e) {
			// TODO: handle exception
		}
		finally{
			//关闭out资源
			closeOut(out);
		}
	}
	public void closeOut(PrintWriter out) {
		out.flush();
		out.close();
	}
}
