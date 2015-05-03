/**
 * 
 */
package com.xnjd.hynm.action;



import java.io.File;
import java.io.InputStream;


import org.apache.struts2.ServletActionContext;



import com.opensymphony.xwork2.ActionSupport;

//文件下载
public class FileDownloadAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pathName;
	private String fileName;
	
	

	

	
	
	/**
	 * @param pathName the pathName to set
	 */
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	/**
	 * @return the pathName
	 */
	public String getPathName() {
	
		return pathName;
	}

	/**
	 * @param pathName the pathName to set
	 */
	/*public void setPathName(String pathName) {
		
		String fname = ServletActionContext.getRequest().getParameter("filePath");
		try{
	    fname= new String(fname.getBytes("ISO-8859-1"), "UTF-8");
	} catch (Exception e)
	{
		e.printStackTrace();
	}
		this.pathName = fname;
	}
    */

	//返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
	public InputStream getDownloadFile() throws Exception
	{
		   //获取资源路径
		 //  pathName=pathName;
		   return ServletActionContext.getServletContext().getResourceAsStream(pathName) ;
	}	
	

	@Override
	public String execute() throws Exception {
		int pos=pathName.lastIndexOf("/");
		if(pos>0)fileName=pathName.substring(pos+1);
		  File file=new File(ServletActionContext.getServletContext().getRealPath(pathName));
		   if(file.exists())
		return SUCCESS;
		   else return "faileddownload";
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
