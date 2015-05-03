package com.xnjd.hynm.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XmlServlet extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<String> list = new ArrayList<String>();
	
   public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.setContentType("text/xml;charset=utf-8");
		// 禁止缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("expires", 0);

		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<keys>");
		request.setCharacterEncoding("utf-8");
		String key = request.getParameter("key");
		if (key != null && key.trim().length() > 0) 
		{
			for (String k : list) 
			{
				if (k.startsWith(key)) 
				{
					sb.append("<key>");
					sb.append(k);
					sb.append("</key>");
				}
			}
		}
		sb.append("</keys>");
		out.print(sb.toString());
		out.flush();
		out.close();
	}
}
