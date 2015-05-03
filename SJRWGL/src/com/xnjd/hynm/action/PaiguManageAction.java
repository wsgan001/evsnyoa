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


import com.xnjd.hynm.model.Paigu;
import com.xnjd.hynm.service.EventManageService;

import com.xnjd.hynm.service.PaiguManageService;
import com.xnjd.hynm.util.Tools;


/**
 * @author Administrator
 *
 */
public class PaiguManageAction extends ActionSupport implements ModelDriven<Paigu>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EventManageService eventManageService;
	private Paigu paigu=new Paigu();
	private Event event=new Event();
	private PaiguManageService paiguManageService;
	private int eventID;
	private int paiguID;
	
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	private File paiguFile;
	private String paiguFileContentType;	//上传文件的类型
	private String paiguFileFileName;		//上传文件的文件名
	private String actionMsg;
	private String guzhangInfo;
	private String guzhangType;
	private String paiguInfo;
	
	

	/**
	 * @return the guzhangInfo
	 */
	public String getGuzhangInfo() {
		return guzhangInfo;
	}
	/**
	 * @param guzhangInfo the guzhangInfo to set
	 */
	public void setGuzhangInfo(String guzhangInfo) {
		this.guzhangInfo = guzhangInfo;
	}
	/**
	 * @return the paiguFile
	 */
	public File getPaiguFile() {
		return paiguFile;
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
	 * @param paiguFile the paiguFile to set
	 */
	public void setPaiguFile(File paiguFile) {
		this.paiguFile = paiguFile;
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
	 * @return the eventManageService
	 */
	public EventManageService getEventManageService() {
		return eventManageService;
	}
	/**
	 * @param eventManageService the eventManageService to set
	 */
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	/**
	 * @return the paiguManageService
	 */
	public PaiguManageService getPaiguManageService() {
		return paiguManageService;
	}
	/**
	 * @param paiguManageService the paiguManageService to set
	 */
	public void setPaiguManageService(PaiguManageService paiguManageService) {
		this.paiguManageService = paiguManageService;
	}
	/**
	 * @return the paigu
	 */
	public Paigu getPaigu() {
		return paigu;
	}
	/**
	 * @param paigu the paigu to set
	 */
	public void setPaigu(Paigu paigu) {
		this.paigu = paigu;
	}

public String addPaigu() throws UnsupportedEncodingException {
		Event tempevent = eventManageService.loadEventByID(eventID);
		tempevent.setNowstate(1);
		eventManageService.updateEvent(tempevent);
	    paigu.setEvent(tempevent);
	    paigu.setGuzhangInfo(guzhangInfo);
	    
	    if (paiguManageService.addPaigu(paigu))actionMsg="记录故障描述成功！";
	   /* if(paiguManageService.addPaigu(paigu))
			return "addJournal";*/
	    
	    else actionMsg="记录故障描述失败";
	    actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		return "addPaigu";	
	}
/* (non-Javadoc)
 * @see com.opensymphony.xwork2.ModelDriven#getModel()
 */
@Override
public Paigu getModel() {
	// TODO Auto-generated method stub
	return null;
}

public String loadPaiguToHand(){
	Event tempevent=eventManageService.loadEventByID(eventID);
	
	//Paigu temp = paiguManageService.loadPaiguByPaiguId(eventID);
	BeanUtils.copyProperties(tempevent, paigu);
	return "loadPaiguToHand";
}

public String loadPaiguByEventId(){
    Paigu tempevent=paiguManageService.loadPaiguByEventId(eventID);
	BeanUtils.copyProperties(tempevent, paigu);
	return "loadPaiguByEventId";
}
public String paiguHand() throws IOException{
	
       Paigu temp =paiguManageService.loadPaiguById(paiguID);
       Event tempevent = eventManageService.loadEventByID(temp.getEvent().getId());
		tempevent.setNowstate(2);
		eventManageService.updateEvent(tempevent);
       
        temp.setGuzhangType(guzhangType);
        temp.setPaiguInfo(paiguInfo);
        if(paiguFile==null)temp.setPaigufanganState(false);
		else if(Tools.isEnableUploadType(paiguFileFileName)){
			String tempname=this.getPaiguFileFileName();
			String tempfilename =new  SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime())+Tools.getFileExtName(this.getPaiguFileFileName());
			String filename =ServletActionContext.getServletContext().getRealPath("/uploads/files").replaceAll("\\\\", "/")+"/"+tempfilename;
			//System.out.println("filename="+filename+" =="+picFileName);
			FileOutputStream fos = new FileOutputStream(filename);
			FileInputStream fis = new FileInputStream(paiguFile);
			byte[] buf = new byte[1024];
			int len = 0;
			while((len=fis.read(buf))>0){
				fos.write(buf,0,len);
			}
			if (fis!=null)fis.close();
			if (fos!=null)fos.close();
			temp.setPaigufanganPath("uploads/files/"+tempfilename);
			temp.setPaigufanganState(true);
			temp.setPaiguqianziName(tempname);
			
			//model.setHjgy(Tools.escape(model.getHjgy().trim()));
		}
    	if(paiguManageService.updatePaigu(temp))actionMsg="排故方案添加成功！";
    	
    	else actionMsg="排故方案添加失败";
    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
        
	
	return "paiguHand";
	
}

public String loadPaiguToDevice(){
	Paigu tempevent=paiguManageService.loadPaiguByEventId(eventID);
	BeanUtils.copyProperties(tempevent, paigu);
	return "loadPaiguToDevice";
}

/**
 * @return the paiguFileContentType
 */
public String getPaiguFileContentType() {
	return paiguFileContentType;
}
/**
 * @param paiguFileContentType the paiguFileContentType to set
 */
public void setPaiguFileContentType(String paiguFileContentType) {
	this.paiguFileContentType = paiguFileContentType;
}
/**
 * @return the paiguFileFileName
 */
public String getPaiguFileFileName() {
	return paiguFileFileName;
}
/**
 * @param paiguFileFileName the paiguFileFileName to set
 */
public void setPaiguFileFileName(String paiguFileFileName) {
	this.paiguFileFileName = paiguFileFileName;
}
/**
 * @return the guzhangType
 */
public String getGuzhangType() {
	return guzhangType;
}
/**
 * @param guzhangType the guzhangType to set
 */
public void setGuzhangType(String guzhangType) {
	this.guzhangType = guzhangType;
}
/**
 * @return the paiguID
 */
public int getPaiguID() {
	return paiguID;
}
/**
 * @param paiguID the paiguID to set
 */
public void setPaiguID(int paiguID) {
	this.paiguID = paiguID;
}
/**
 * @return the paiguInfo
 */
public String getPaiguInfo() {
	return paiguInfo;
}
/**
 * @param paiguInfo the paiguInfo to set
 */
public void setPaiguInfo(String paiguInfo) {
	this.paiguInfo = paiguInfo;
}
	
}
