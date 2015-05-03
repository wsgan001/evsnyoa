/**
 * 
 */
package com.xnjd.hynm.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.model.Journal;
import com.xnjd.hynm.service.EventManageService;

import com.xnjd.hynm.service.JournalManageService;
import com.xnjd.hynm.util.PageBean;
import com.xnjd.hynm.util.Tools;
/**
 * @author Administrator
 *
 */

public class JournalManageAction extends ActionSupport implements ModelDriven<Journal>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private int page;
	private PageBean pageBean;
	
	private EventManageService eventManageService;
	private Journal journal=new Journal();
	private JournalManageService journalManageService;
	private String actionMsg;
	private int eventID;
	private Event myevent;
	private int journalID;
	private int jandeID;
	//上传文件
	private File journalFile;	//上传的文件
	private String journalFileContentType;	//上传文件的类型
	private String journalFileFileName;		//上传文件的文件名
	/**
	 * @return the journalID
	 */
	public int getJournalID() {
		return journalID;
	}



	/**
	 * @param journalID the journalID to set
	 */
	public void setJournalID(int journalID) {
		this.journalID = journalID;
	}



	/**
	 * @param eventManageService the eventManageService to set
	 */
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}


	
	/**
	 * @param journalManageService the journalManageService to set
	 */
	public void setJournalManageService(JournalManageService journalManageService) {
		this.journalManageService = journalManageService;
	}
     //根据id加载日志
    public String loadJournalToView() {
    	//Event tempevent = eventManageService.loadEventByID(eventID);
		//journal.setEvent(tempevent);
    	
		Journal temp = journalManageService.loadJournalById(journalID);
	    BeanUtils.copyProperties(temp,journal);
			
		 return "loadJournalToView";
    	
    }
    
    public String loadJournalToEdit() {
    	//Event tempevent = eventManageService.loadEventByID(eventID);
		//journal.setEvent(tempevent);
    	
		Journal temp = journalManageService.loadJournalById(journalID);
	    BeanUtils.copyProperties(temp,journal);
	    this.journalID=temp.getId();
	    //this.jandeID=temp.getEvent().getId();
	    System.out.println(journalID);
	    //System.out.println(jandeID);
		 return "loadJournalToEdit";
    	
    }
	 //private int eventID;
	
	public String loadJournalList() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		myevent = eventManageService.loadEventByID(eventID);
		setPageBean(journalManageService.queryForPage(10,page==0?1:page,String.valueOf(eventID)));
		return "loadJournalList";
	}
	
	public String loadJournalListAdmin() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		myevent = eventManageService.loadEventByID(eventID);
		setPageBean(journalManageService.queryForPage(10,page==0?1:page,String.valueOf(eventID)));
		return "loadJournalListAdmin";
	}
	

	
	public String addJournal() throws IOException{
		
		Event tempevent = eventManageService.loadEventByID(eventID);
	    journal.setEvent(tempevent);
	   //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	  //journal.setRzdate(new Date());
	    
		
    	if(journalFile==null)journal.setFileState(false);
		else if(Tools.isEnableUploadType(journalFileFileName)){
			String tempname=this.getJournalFileFileName();
			String tempfilename =new  SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime())+Tools.getFileExtName(this.getJournalFileFileName());
			String filename =ServletActionContext.getServletContext().getRealPath("/uploads/files").replaceAll("\\\\", "/")+"/"+tempfilename;
			//System.out.println("filename="+filename+" =="+picFileName);
			FileOutputStream fos = new FileOutputStream(filename);
			FileInputStream fis = new FileInputStream(journalFile);
			byte[] buf = new byte[1024];
			int len = 0;
			while((len=fis.read(buf))>0){
				fos.write(buf,0,len);
			}
			if (fis!=null)fis.close();
			if (fos!=null)fos.close();
			journal.setFilePath("uploads/files/"+tempfilename);
			journal.setFileState(true);
			journal.setFileName(tempname);
			
			//model.setHjgy(Tools.escape(model.getHjgy().trim()));
		}
    	
	   if(journalManageService.addJournal(journal))actionMsg="日志添加成功！";
				else actionMsg="日志添加失败！";
		 actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
			return "addJournal";	
	
		}
	
	public String editJournal() throws UnsupportedEncodingException{
		
		Journal temp = journalManageService.loadJournalById(journalID);
		
		temp.setRzdate(journal.getRzdate());
		 temp.setRzinfo(journal.getRzinfo());
		 this.jandeID=temp.getEvent().getId();
		if(journalManageService.updateJournal(temp))
			actionMsg = "更新日志信息成功！";
			else
				actionMsg = "更新日志信息失败！";
			actionMsg = java.net.URLEncoder.encode(actionMsg, "utf-8");
		return  "editJournal";
		
	}

	public String deleteJournal() throws UnsupportedEncodingException {
		Journal temp = journalManageService.loadJournalById(journalID);
		this.jandeID=temp.getEvent().getId();
		if (journalManageService.deleteJournal(journalID))
			actionMsg = "删除日志成功！";
		else
			actionMsg = "删除日志失败";
		actionMsg = java.net.URLEncoder.encode(actionMsg, "utf-8");
		return "deleteJournal";
	}
	
	

		@Override
	public Journal getModel() {
		// TODO Auto-generated method stub
		return journal;
	}
		
	/**
	 * @return the actionMsg
	 */
	public String getActionMsg() {
		return actionMsg;
	}

	/**
	 * @param actionMsg the actionMsg to set
	 */
	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	 /**
		 * @return the journal
		 */
		public Journal getJournal() {
			return journal;
		}



		/**
		 * @param journal the journal to set
		 */
		public void setJournal(Journal journal) {
			this.journal = journal;
		}



		/**
		 * @return the eventID
		 */
		public int getEventID() {
			return eventID;
		}



		/**
		 * @param eventID the eventID to set
		 */
		public void setEventID(int eventID) {
			this.eventID = eventID;
		}



		/**
		 * @return the page
		 */
		public int getPage() {
			return page;
		}



		/**
		 * @param page the page to set
		 */
		public void setPage(int page) {
			this.page = page;
		}



		/**
		 * @return the pageBean
		 */
		public PageBean getPageBean() {
			return pageBean;
		}



		/**
		 * @param pageBean the pageBean to set
		 */
		public void setPageBean(PageBean pageBean) {
			this.pageBean = pageBean;
		}



		/**
		 * @return the myevent
		 */
		public Event getMyevent() {
			return myevent;
		}



		/**
		 * @param myevent the myevent to set
		 */
		public void setMyevent(Event myevent) {
			this.myevent = myevent;
		}



		public File getJournalFile() {
			return journalFile;
		}


		public void setJournalFile(File journalFile) {
			this.journalFile = journalFile;
		}


		public String getJournalFileContentType() {
			return journalFileContentType;
		}



		public void setJournalFileContentType(String journalFileContentType) {
			this.journalFileContentType = journalFileContentType;
		}


		public String getJournalFileFileName() {
			return journalFileFileName;
		}


		public void setJournalFileFileName(String journalFileFileName) {
			this.journalFileFileName = journalFileFileName;
		}



		/**
		 * @return the jandeID
		 */
		public int getJandeID() {
			return jandeID;
		}



		/**
		 * @param jandeID the jandeID to set
		 */
		public void setJandeID(int jandeID) {
			this.jandeID = jandeID;
		}
		
		
		
}
