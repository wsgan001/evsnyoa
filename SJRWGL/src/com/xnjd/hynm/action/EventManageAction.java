/**
 * 
 */
package com.xnjd.hynm.action;


import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.service.EventManageService;
import com.xnjd.hynm.util.PageBean;
import com.xnjd.hynm.util.Tools;

import com.xnjd.hynm.service.UsersManageService; //添加用户引用服务
 
import java.util.Date; 


/**
 * @author STONE
 *
 */
public class EventManageAction extends ActionSupport implements ModelDriven<Event> {

	/**
	 * 
	 */
	private List<Account> userlist;
	private UsersManageService usersManageService;//为实现下拉列表添加使用者
	
	/**
	 * @param usersManageService the usersManageService to set
	 */
	public void setUsersManageService(UsersManageService usersManageService) {
		this.usersManageService = usersManageService;
	}
	private int currentIndex=1;
	private final int PAGE_SIZE=16;
	private final int PAGE_STATE=100;
	private final int now_State=3;
	private static final long serialVersionUID = 1L;
	private EventManageService eventManageService;
	private Event event=new Event();
	/** 上传图片文件的属性 */
	private File eventFile;	//上传的文件
	private String eventFileContentType;	//上传文件的类型
	private String eventFileFileName;		//上传文件的文件名
	private int page;
	private int page_tj;
	private int eventEngineerId;
	private String search_eventEngineer;
	private String search_startDate;
	private String search_endDate;
	private String actionMsg;
	private int eventShId;
	private Account user=new Account();
	private String account;
	private String pwd;
	
	private File upload[];
	private String uploadContentType[];
	private String uploadFileName[];
	private String uploadPath;
	private String uploadName;
	
	private int eventID;
    private PageBean pageBean;
    private PageBean pageBean_tj;
    private PageBean pageBean_qt;
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the user
	 */
	public Account getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(Account user) {
		this.user = user;
	}
	
	/**
	 * @return the eventShId
	 */
	public int getEventShId() {
		return eventShId;
	}
	/**
	 * @param eventShId the eventShId to set
	 */
	public void setEventShId(int eventShId) {
		this.eventShId = eventShId;
	}
	/**
	 * @return the eventEngineerId
	 */
	public int getEventEngineerId() {
		return eventEngineerId;
	}
	/**
	 * @param eventEngineerId the eventEngineerId to set
	 */
	public void setEventEngineerId(int eventEngineerId) {
		this.eventEngineerId = eventEngineerId;
	}
	
    
	@Override
	public Event getModel() {
		return event;
	}
	
	
	public File[] getUpload() {
		return upload;
	}
	
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	
	public String[] getUploadContentType() {
		return uploadContentType;
	}
	
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public String[] getUploadFileName() {
		return uploadFileName;
	}
	
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	
	
	public String addEvent() throws UnsupportedEncodingException{
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		event.setAccountByDjsjId(account);
		event.setCompleteState(false);
		event.setDispatchState(false);
		event.setApplyState(false);
		event.setPassState("0");
		event.setEmailfpState(false);
		event.setEmailyqState(false);
		event.setNowstate(0);
		
		Date df =new Date();
		Timestamp timeStamp = new Timestamp(df.getTime());
		event.setDgdate(timeStamp); //设置登记时间
		Calendar ca = Calendar.getInstance();//用于增加天数3天
		ca.add(Calendar.DATE, 3);
		df=ca.getTime();
		event.setPlanTime1(df);
			if(eventManageService.addEvent(event)){	
				actionMsg="登记事件成功！ ";
				actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
				return "addEvent";
			}
			else {
				actionMsg="登记事件失败！ ";
				actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
				
				return "addEventError";
			}
	}
	
	public String dispatchEvent() throws UnsupportedEncodingException{
		Event temp = eventManageService.loadEventByID(eventID);
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		temp.setAccountByFpsjId(account);
		//temp.setAccountByClsjId(account);
		temp.setDispatchState(true);
	    temp.setApplyState(false);
		//temp.setEventEngineer(event.getEventEngineer());
		temp.setPlanTime1(event.getPlanTime1());
		temp.setPlanTime2(event.getPlanTime2());
		Account Clsj = usersManageService.loadwUsersById(eventEngineerId);
		temp.setAccountByClsjId(Clsj);
		temp.setEventEngineer(Clsj.getName());
		Account Shsj = usersManageService.loadwUsersById(eventShId);
		temp.setAccountByShsjId(Shsj);
		temp.setVisitName(Shsj.getName());
		
		if(eventManageService.updateEvent(temp))actionMsg="事件分配成功！";
		else actionMsg="事件分配失败！";
		actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		return "dispatchEvent";
	}
	
    public String loadEventToDispatch(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
    	userlist = usersManageService.loadUser(); //新添加的用户列表中用于下拉列表显示
		//userlist=service.findAll();
		//this.setUserlist(userlist);
		return "loadEventToDispatch";
	}  
    public String loadPeriodicEventToDispatch(){
    	System.out.println("==================================================================");
    	userlist = usersManageService.loadUser(); //新添加的用户列表中用于下拉列表显示
		//userlist=service.findAll();
		//this.setUserlist(userlist);
		return "loadPeriodicEventToDispatch";
	}  
   /* public String loadEventToHandle(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
    	return "loadEventToHandle";
    }
    
   public String HandleEvent() {
    	Event temp = eventManageService.loadEventByID(eventID);
    	temp.setHandleState(true);
    	
    	if(eventManageService.updateEvent(temp))actionMsg="事件完成申请提交成功！";
		else actionMsg="事件完成申请提交失败！";
		return "dispatchEvent";
    }*/
    
    //审核事件通过
    public String passEvent()throws UnsupportedEncodingException{
    	Event temp = eventManageService.loadEventByID(eventID);
    	Account account=(Account)ActionContext.getContext().getSession().get("admin");
    	temp.setAccountByShsjId(account);
        temp.setEventEffect(event.getEventEffect());
    	temp.setPassState("1");
    	//temp.setCompleteTime(event.getCompleteTime());
    	temp.setCompleteState(true);
    	temp.setReviewAdvice(event.getReviewAdvice());
    	if(eventManageService.updateEvent(temp))actionMsg="事件审核处理成功！";
		else actionMsg="事件审核处理失败！";
    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
    	return "passEvent";
    }
    //审核事件不通过
    public String noPassEvent()throws UnsupportedEncodingException{
    	Event temp = eventManageService.loadEventByID(eventID);
    	temp.setApplyState(false);
    	temp.setFileState(false);
    	temp.setReviewAdvice(event.getReviewAdvice());
    	if(eventManageService.updateEvent(temp))actionMsg="事件审核处理成功！";
		else actionMsg="事件审核处理失败！";
    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
    	return "noPassEvent";
    	}
    
    //申请事件完成
    public String applyEvent()throws IOException{
    	
    	Event temp = eventManageService.loadEventByID(eventID);
    	temp.setApplyState(true);
    	temp.setCompleteTime(event.getCompleteTime());
    	if(eventFile==null)temp.setFileState(false);
		else if(Tools.isEnableUploadType(eventFileFileName)){
			String tempname=this.getEventFileFileName();
			String tempfilename =new  SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime())+Tools.getFileExtName(this.getEventFileFileName());
			String filename =ServletActionContext.getServletContext().getRealPath("/uploads/files").replaceAll("\\\\", "/")+"/"+tempfilename;
			//System.out.println("filename="+filename+" =="+picFileName);
			FileOutputStream fos = new FileOutputStream(filename);
			FileInputStream fis = new FileInputStream(eventFile);
			byte[] buf = new byte[1024];
			int len = 0;
			while((len=fis.read(buf))>0){
				fos.write(buf,0,len);
			}
			if (fis!=null)fis.close();
			if (fos!=null)fos.close();
			temp.setFilePath("uploads/files/"+tempfilename);
			temp.setFileState(true);
			temp.setFileName(tempname);
			
			//model.setHjgy(Tools.escape(model.getHjgy().trim()));
		}
    	if(eventManageService.updateEvent(temp))actionMsg="申请事件完成成功！";
    	
    	else actionMsg="申请事件完成失败";
    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
    	return "applyEvent";
    }
    
    public String applyEventMorefile() throws IOException{
    	
    	Event temp = eventManageService.loadEventByID(eventID);
    	temp.setApplyState(true);
    	temp.setCompleteTime(event.getCompleteTime());
    	if(eventFile==null)temp.setFileState(false);
		else {
			
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploads/files");
			
			
			// 写到指定路径
			File file = new File(path);
			//判断指定的路径下是否有uplaod，如果没有，自动创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				for(int i = 0;i<upload.length;i++){
				FileUtils.copyFile(upload[i], new File(file, uploadFileName[i]));
				uploadPath = uploadFileName + uploadFileName[i].substring(uploadFileName[i].lastIndexOf(".")); 
				uploadName=uploadFileName[i]+",";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp.setFilePath(uploadPath);
			temp.setFileState(true);
			temp.setFileName(uploadName);
	
		}
    	if(eventManageService.updateEvent(temp))actionMsg="申请事件完成成功！";
    	else actionMsg="申请事件完成失败";
    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
    	return "applyEventMorefile";
    	
    }
     //申请延期处理
    public String applyEventLate()throws UnsupportedEncodingException{
    	Event temp = eventManageService.loadEventByID(eventID);
    	temp.setClsjLate(true);
    	temp.setClsjLatehand("0");
    	temp.setPlanTime3(event.getPlanTime3());
    	temp.setClsjLatereason(event.getClsjLatereason());
    	temp.setClsjLateplan(event.getClsjLateplan());
    	if(eventManageService.updateEvent(temp))actionMsg="延期申请成功！";
		else actionMsg="延期申请失败！";
    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
    	return "applyEventLate";
    }
    //申请延期后审核的处理
    public String applyEventLateHand()throws UnsupportedEncodingException{
    	Event temp = eventManageService.loadEventByID(eventID);
    	temp.setClsjLatehand(event.getClsjLatehand());
    	if(eventManageService.updateEvent(temp))actionMsg="申请处理成功！";
		else actionMsg="申请处理失败！";
    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
    	return "applyEventLateHand";
    }
    
    
     public String loadEventToApply(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
		return "loadEventToApply";
    }
    public String loadEventToPass(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
		return "loadEventToPass";
	}
    //延期事件申请
    public String loadEventToLate(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
		return "loadEventToLate";
    	}
    //延期事件申请后的处理
    public String loadEventToLateHand(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
		return "loadEventToLateHand";
    	}
    
    /*增加根据id进行编辑功能*/
	 public String loadEventToEdit(){
		 Event temp = eventManageService.loadEventByID(eventID);
	     BeanUtils.copyProperties(temp, event);
	     this.eventID=temp.getId();
	     userlist = usersManageService.loadUser();  //修改1
		 return "loadEventToEdit";
				}
    
    public String loadEventToView(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
		return "loadEventToView";
	}
    //增加显示内容
    public String loadEventView(){
    	if(usersManageService.validateUser(account, pwd)){
			ActionContext.getContext().getSession().put("admin",usersManageService.loadwUsersByName(account));
			ActionContext.getContext().getSession().put("startTime", System.currentTimeMillis());
			return "loadEventToView";
		}
		else{
			addActionError("账号或密码错误，请核对后再登陆！");
			return "login";
		}
    	
    	}
    
    public String loadEventByTjView(){
    	Event temp = eventManageService.loadEventByID(eventID);
    	BeanUtils.copyProperties(temp, event);
		return "loadEventByTjView";
	}
	//新增评价显示
     public String loadEventByPjView(){
    	 Event temp = eventManageService.loadEventByID(eventID);
    	 BeanUtils.copyProperties(temp, event);
 		return "loadEventByPjView";
     }
	/**
	 * @return
	 * @throws UnsupportedEncodingException
	 * 加载所有未分配的事件
	 */
	public String loadUndispatchEvent() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		setPageBean(eventManageService.LoadAllUndispatchedEvent(page, PAGE_SIZE));
		return "loadUndispatchEvent";
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException
	 * 加载所有已完成的事件
	 */
	public String loadCompletedEvent() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		//this.setUserlist(userlist);
		//userlist = usersManageService.loadUser(); //新添加的用户列表中用于下拉列表显示
		currentIndex=1;
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getSjsh())setPageBean_tj(eventManageService.LoadAllCompletedEvent(page_tj, PAGE_SIZE,search_eventEngineer.equals("-1")?null:search_eventEngineer,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate));
		else  setPageBean_tj(eventManageService.LoadCompletedEvent(page_tj, PAGE_SIZE, account.getId(),null,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate));
			
		return "loadCompletedEvent";
	}
	
	public String loadCompletedExport() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		//userlist = usersManageService.loadUser(); //新添加的用户列表中用于下拉列表显示
		currentIndex=2;
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getSjsh())eventManageService.LoadAllCompletedExport(search_eventEngineer.equals("-1")?null:search_eventEngineer,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate);
		else  eventManageService.LoadCompletedExport(account.getId(),null,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate);
		return "loadCompletedExport";
		
	}
	/**
	 * 加载所有未评价的事件
	 * 
	 * 
	 * **/
	public String loadEvaluationEvent() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		//this.setUserlist(userlist);
		//userlist = usersManageService.loadUser(); //新添加的用户列表中用于下拉列表显示
		currentIndex=1;
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getSjsh())setPageBean_tj(eventManageService.LoadAllCompletedEvent(page_tj, PAGE_SIZE,search_eventEngineer.equals("-1")?null:search_eventEngineer,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate));
		else  setPageBean_tj(eventManageService.LoadCompletedEvent(page_tj, PAGE_SIZE, account.getId(),null,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate));
			
		return "loadEvaluationEvent";
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException
	 * 加载所有未审核的事件
	 */
	public String loadUnPassedEvent() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getQxfp())setPageBean(eventManageService.LoadAllUnpassedEvent(page, PAGE_SIZE));
		else setPageBean(eventManageService.LoadUnpassedEvent(page, PAGE_SIZE, account.getId()));
		return "loadUnPassedEvent";
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException
	 * 加载登记过的事件
	 */
	public String loadAllEvent() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		currentIndex=1;
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		//setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId()));
		if(account.getSjfp())setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE));
		else if(account.getKfgl())setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId(), now_State));
		else setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId()));
		
		//actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		return "loadAllEvent";
	}
	/**
	 * 增加显示其他事件的方法
	 * */
	public String loadOtherEvent() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		currentIndex=1;
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		//setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId()));
		if(account.getSjfp())setPageBean_qt(eventManageService.LoadOtherEvent(page, PAGE_SIZE));
		//else if(account.getKfgl())setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId(), now_State));
		//else setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId()));
		
		//actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		return "loadOtherEvent";
	}
	
	
	
	
	/**
	 *  增加单独的完成事件设置
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String loadAllEventIndex() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		currentIndex=1;
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getSjfp())setPageBean(eventManageService.LoadAllEvent(page, PAGE_STATE));
		else if(account.getKfgl())setPageBean(eventManageService.LoadAllEvent(page, PAGE_STATE, account.getId(), now_State));
		else setPageBean(eventManageService.LoadAllEvent(page, PAGE_STATE, account.getId()));
		//actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		return "loadAllEventIndex";
	}
	
	
	public String selectulist()throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		currentIndex=1;
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		this.setUserlist(userlist);
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getSjfp())setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE));
		else setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId()));
		return "true";
	}
	
	//新增将结果查询出来以便评价
	
	public String evaluationlist()throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		currentIndex=1;
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		this.setUserlist(userlist);
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getSjfp())setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE));
		else setPageBean(eventManageService.LoadAllEvent(page, PAGE_SIZE, account.getId()));
		return "true";
	}
	
	
	public int getPage() {
		return page;
	}
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
	 * @return the eventFile
	 */
	public File getEventFile() {
		return eventFile;
	}
	/**
	 * @param eventFile the eventFile to set
	 */
	public void setEventFile(File eventFile) {
		this.eventFile = eventFile;
	}
	/**
	 * @return the eventFileContentType
	 */
	public String getEventFileContentType() {
		return eventFileContentType;
	}
	/**
	 * @param eventFileContentType the eventFileContentType to set
	 */
	public void setEventFileContentType(String eventFileContentType) {
		this.eventFileContentType = eventFileContentType;
	}
	/**
	 * @return the eventFileFileName
	 */
	public String getEventFileFileName() {
		return eventFileFileName;
	}
	/**
	 * @param eventFileFileName the eventFileFileName to set
	 */
	public void setEventFileFileName(String eventFileFileName) {
		this.eventFileFileName = eventFileFileName;
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
	
      //以下添加的代码获取下拉列表框的用户的名字
	    public List<Account> getUserlist() {
		  return userlist; 
		 }

		 public void setUserlist(List<Account> userlist) {
		  this.userlist = userlist;
		 }
		/**
		 * @return the pageBean_tj
		 */
		public PageBean getPageBean_tj() {
			return pageBean_tj;
		}
		/**
		 * @param pageBean_tj the pageBean_tj to set
		 */
		public void setPageBean_tj(PageBean pageBean_tj) {
			this.pageBean_tj = pageBean_tj;
		}
		/**
		 * @return the page_tj
		 */
		public int getPage_tj() {
			return page_tj;
		}
		/**
		 * @param page_tj the page_tj to set
		 */
		public void setPage_tj(int page_tj) {
			this.page_tj = page_tj;
		}
		/**
		 * @return the currentIndex
		 */
		public int getCurrentIndex() {
			return currentIndex;
		}
		/**
		 * @param currentIndex the currentIndex to set
		 */
		public void setCurrentIndex(int currentIndex) {
			this.currentIndex = currentIndex;
		}
		/**
		 * @return the search_eventEngineer
		 */
		public String getSearch_eventEngineer() {
			return search_eventEngineer;
		}
		/**
		 * @param search_eventEngineer the search_eventEngineer to set
		 */
		public void setSearch_eventEngineer(String search_eventEngineer) {
			this.search_eventEngineer = search_eventEngineer;
		}
		/**
		 * @return the search_startDate
		 */
		public String getSearch_startDate() {
			return search_startDate;
		}
		/**
		 * @param search_startDate the search_startDate to set
		 */
		public void setSearch_startDate(String search_startDate) {
			this.search_startDate = search_startDate;
		}
		/**
		 * @return the search_endDate
		 */
		public String getSearch_endDate() {
			return search_endDate;
		}
		/**
		 * @param search_endDate the search_endDate to set
		 */
		public void setSearch_endDate(String search_endDate) {
			this.search_endDate = search_endDate;
		}
		
		public String setEvent(){
			return setEvent();
		}
		/*增加toevent跳转的内容*/
		public String deleteEventById() throws UnsupportedEncodingException{
			if(eventManageService.deleteEventByID(eventID))actionMsg="删除事件成功！";
			else actionMsg="删除事件失败";
			actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
			return "deleteEventById";
		}
		/*增加事件处理*/
		  public String loadEventToHand(){
		    	Event temp = eventManageService.loadEventByID(eventID);
		    	BeanUtils.copyProperties(temp, event);
				return "loadEventToHand";
			}
		
	  /*编辑事件功能*/
		  public String updateEvent() throws UnsupportedEncodingException{
			  Event temp = eventManageService.loadEventByID(eventID);
			  temp.setEventCustomer(event.getEventCustomer());
			  temp.setEventProduct(event.getEventProduct());
			  temp.setEventType(event.getEventType());
			  temp.setEventDate(event.getEventDate());
			  temp.setEventInfo(event.getEventInfo());
			  Account Clsj = usersManageService.loadwUsersById(eventEngineerId);
			  temp.setAccountByClsjId(Clsj);
			  temp.setEventEngineer(Clsj.getName());
			  //event.setId(eventID);
			 // event.setAccountByDjsjId(temp.getAccountByDjsjId());
			  if(eventManageService.updateEvent(temp))actionMsg="更新事件信息成功！";
			  actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
			  return "updateEvent";
			
		  }
		  
		  public void updateEmailfpState( int emailstate){
			  
			    Event temp = eventManageService.loadEventByID(emailstate);
		    	temp.setEmailfpState(true);
		    	eventManageService.updateEvent(temp);
			  
		  }
		  public void updateEmailyqState(int  emailstate){
			  Event temp = eventManageService.loadEventByID(emailstate);
		    	temp.setEmailyqState(true);
		    	eventManageService.updateEvent(temp);
			  }
		/**
		 * @return the pageBean_qt
		 */
		public PageBean getPageBean_qt() {
			return pageBean_qt;
		}
		/**
		 * @param pageBean_qt the pageBean_qt to set
		 */
		public void setPageBean_qt(PageBean pageBean_qt) {
			this.pageBean_qt = pageBean_qt;
		}
		
		
}
