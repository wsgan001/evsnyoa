/**
 * 
 */
package com.xnjd.hynm.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xnjd.hynm.dao.UsersManageDao;
import com.xnjd.hynm.listener.RegularlyCreateEvent;
import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.model.PeriodicEvent;
import com.xnjd.hynm.service.EventManageService;
import com.xnjd.hynm.service.UsersManageService;
import com.xnjd.hynm.util.PageBean;
import com.xnjd.hynm.util.Tools;

/**
 * @author my
 *
 */
public class PeriodicEventAction extends ActionSupport implements ModelDriven<PeriodicEvent>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EventManageService eventManageService;
	private PeriodicEvent periodicEvent = new PeriodicEvent();
	private Date eventDate;
	private List<Account> userlist;
	private Integer eventEngineerId;
	private Integer eventShId;
	private UsersManageService usersManageService;
	private UsersManageDao usersManageDao;
	private final int PAGE_SIZE=16;
	private int page;
	private PageBean pageBean;
	public List<Event> events = new ArrayList<Event>();
	private String actionMsg;
    private RegularlyCreateEvent regularlyCreateEvent;
    
	/**
	 * @return the periodicEvent
	 */
	public PeriodicEvent getPeriodicEvent() {
		return periodicEvent;
	}
	/**
	 * @return the userlist
	 */
	public List<Account> getUserlist() {
		return userlist;
	}
	/**
	 * @param userlist the userlist to set
	 */
	public void setUserlist(List<Account> userlist) {
		this.userlist = userlist;
	}
	/**
	 * @param regularlyCreateEvent the regularlyCreateEvent to set
	 */
	public void setRegularlyCreateEvent(RegularlyCreateEvent regularlyCreateEvent) {
		this.regularlyCreateEvent = regularlyCreateEvent;
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
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}



	/**
	 * @param events the events to set
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
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
	 * @return the usersManageDao
	 */
	public UsersManageDao getUsersManageDao() {
		return usersManageDao;
	}



	/**
	 * @param usersManageDao the usersManageDao to set
	 */
	public void setUsersManageDao(UsersManageDao usersManageDao) {
		this.usersManageDao = usersManageDao;
	}
	/**
	 * @return the usersManageService
	 */
	public UsersManageService getUsersManageService() {
		return usersManageService;
	}
	/**
	 * @param usersManageService the usersManageService to set
	 */
	public void setUsersManageService(UsersManageService usersManageService) {
		this.usersManageService = usersManageService;
	}
	
	/**
	 * 增加周期性事件
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addPeriodicEvent() throws UnsupportedEncodingException{
		System.out.print("12222--------------------");
		usersManageService.loadUser();
		periodicEvent.setAccountByShsjId(eventShId);
		periodicEvent.setAccountByClsjId(eventEngineerId);
		Date startDate = periodicEvent.getEventStartTime(); 
	        //如果获得事件开始时间是小于或者等于今天，那么先向周期事件表插入一条数据并且获取Id，再向事件表插入一条数据，创建事件时间为开始时间+一个周期
	        Date date = new Date();
	        Account account=(Account)ActionContext.getContext().getSession().get("admin");
	    	periodicEvent.setAccountByDjsjId(account.getId());
	    	periodicEvent.setAccountByFpsjId(account.getId());
	    	Account Clsj = usersManageService.loadwUsersById(periodicEvent.getAccountByClsjId());
	    	periodicEvent.setEventEngineer(Clsj.getName());
	    	Account SHsj = usersManageService.loadwUsersById(periodicEvent.getAccountByShsjId());
	    	periodicEvent.setVisitName(SHsj.getName());
	    	//设置事件开启
	    	periodicEvent.setEventIsOpen(true);
	    	//设置开始时间
	    	Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            String startDateString  = Tools.formatDate(startDate);
            String currentDateString = Tools.formatDate(date);
	        if(startDateString!=("")&&currentDateString!=("")&&startDateString.equals(currentDateString)){
	        	System.out.println("######################################");
	        	//修改创建事件时间
	            	//参数fileds表示年，月，日，1.表示年份.2表示月份.3表示周.5表示天.
	            	calendar.add(periodicEvent.getIntervalType(), periodicEvent.getTimeInterval());//直接讲创建日期变成比当天以后的第一次创建日期
	            	periodicEvent.setCreateEventDate(calendar.getTime());
	            	//控制事件被拖延创建N次的处理
	            	
	    	        	if(eventManageService.addPeriodicEvent(periodicEvent)){
	    	        		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
	    	        		final String sendFuck ="周期性事件:"+periodicEvent.getEventType()+"->已创建";//获取事件的主题
	    	        		System.out.println("sendFuck");
	    	        		threadSendMail(sendFuck);//子线程发送邮件方法
		    	            	//向周期性事件表插入数据
		    	        		Event event = this.returnNeedInsertEvent(periodicEvent,account,1);
		    	        		if(eventManageService.addEvent(event)){
		    	        			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		    	        			actionMsg="事件添加成功！";
		    	        			actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		    	        			return "addPeriodicEventSuccess";
		    	        		}else{
		    	        			actionMsg="事件添加失败！";
		    	        			addActionMessage(actionMsg);
		    	        			actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		    	        			return "addEventError";
		    	        		}
	    	        	}else return "addEventError";		
	            	
	        }else{
	        	//如果获得事件开始时间大于今天，意味者还没有开始，那么直接向周期性事件表插入一条数据，并且创建事件时间为时间开始时间。	        	
	        	periodicEvent.setCreateEventDate(calendar.getTime());
	            if(eventManageService.addPeriodicEvent(periodicEvent)){
	            	final String sendFuck ="周期性事件:"+periodicEvent.getEventType()+"->已创建";//获取事件的主题
	        		threadSendMail(sendFuck);//子线程发送邮件方法
		            actionMsg="事件添加成功！";
		            actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		            return "addPeriodicEventSuccess";
	            }
	            else {
	            	actionMsg="事件添加失败！";
	            	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
	            	return "addEventError";}     
	        }
		
	}
	/**
	 * 通过子线程来发送邮件
	 */
	public void threadSendMail(final String sendFuck){
		new Thread(new Runnable() {
			@Override
			public void run() {
				sendPeriodicEventStartMail(sendFuck);//发送周期性事件创建邮件，使用子线程来发送邮件
			}
		}).start();	
	}
	public final boolean sendPeriodicEventStartMail(String sendFuck){
		int CLAccountId = periodicEvent.getAccountByClsjId();//用来获取处理人的邮箱，用于发件人
		   Account account = usersManageService.loadwUsersById(CLAccountId);
		   String CLAccountEmail = account.getEmail();//获取处理人邮箱   
		   String [] SHAccountEmailArr = regularlyCreateEvent.returnEmailArr();//获取有执行权限的人的邮箱
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   StringBuffer sb = new StringBuffer();
			sb.append("事件类型(周期性事件):"+periodicEvent.getEventType()+" ");
			sb.append("事件用户:"+periodicEvent.getEventCustomer()+" ");
			sb.append("事件产品:"+periodicEvent.getEventProduct()+" ");
			sb.append("事件执行人:"+periodicEvent.getEventEngineer()+" ");
			sb.append("事件审核人:"+periodicEvent.getVisitName()+" ");
			sb.append("事件开始日期:"+format.format(periodicEvent.getEventStartTime())+" ");
			sb.append("事件结束时间:"+format.format(periodicEvent.getEventEndTime())+" ");
			sb.append("时间间隔:"+periodicEvent.getTimeInterval()+"天");
		   String emailContent = sb.toString();//获取邮件的内容
		   System.out.println(CLAccountEmail+SHAccountEmailArr+emailContent+sendFuck);
		   regularlyCreateEvent.sendEmail(CLAccountEmail, sendFuck, SHAccountEmailArr, emailContent);//发送邮件
		return true;	
	}
	
	//返回一个周期性事件需要插入事件表的Event实体
		public Event returnNeedInsertEvent(PeriodicEvent periodicEvent,Account account,int multipleInterval){
			//向事件表插入一条数据,multipleInterval间隔倍数
			Event event = new Event();
			Calendar ca = Calendar.getInstance();//用于增加天数3天
			event.setAccountByDjsjId(account);
			event.setCompleteState(false);
			event.setDispatchState(true);
			event.setApplyState(false);
			event.setPassState("0");
			event.setEmailfpState(false);
			event.setEmailyqState(false);
			event.setNowstate(0);
			Date df =new Date();
			Timestamp timeStamp = new Timestamp(df.getTime());
			event.setDgdate(timeStamp); //设置登记时间
			//设置事件开始日期
			Date eventStartDate = periodicEvent.getEventStartTime();
			ca.setTime(eventStartDate);
			//设置事件开始时间
			ca.add(5, (periodicEvent.getTimeInterval())*(multipleInterval));
			eventStartDate= ca.getTime();
			System.out.println(eventStartDate+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			event.setEventDate(eventStartDate);
			
			//设置计划完成时间
			ca.setTime(ca.getTime());
			ca.add(5, 3);
			df=ca.getTime();
			event.setPlanTime1(df);
			
			event.setEventCustomer(periodicEvent.getEventCustomer());
			event.setEventType(periodicEvent.getEventType());
			event.setEventProduct(periodicEvent.getEventProduct());
			event.setEventInfo(periodicEvent.getEventInfo());
			event.setPeriodicEventId(periodicEvent.getId());
			event.setAccountByDjsjId(account);
			event.setAccountByFpsjId(account);
			System.out.println(periodicEvent.getAccountByClsjId());
			Account Clsj = usersManageService.loadwUsersById(periodicEvent.getAccountByClsjId());	
			event.setAccountByClsjId(Clsj);
			event.setEventEngineer(Clsj.getName());
			Account Shsj = usersManageService.loadwUsersById(periodicEvent.getAccountByShsjId());
			event.setAccountByShsjId(Shsj);
			event.setVisitName(Shsj.getName());
			return event;
		}
		/**
		 * 查询所有周期性事件
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		public String loadAllPeriodicEvent() throws UnsupportedEncodingException{
			if(actionMsg!=null){
				actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
				addActionMessage(actionMsg);
			}
			pageBean = eventManageService.loadAllPeriodicEvent(page,PAGE_SIZE);
		  setPageBean(pageBean);	
		  return "loadAllPeriodicEvent";
		}
		/**
		 * 查询周期性事件关联的小事件
		 */
		public String loadPeriodicEventRelaEvent(){
			pageBean = eventManageService.loadPeriodicEventRelaEvent(periodicEvent.getId(),page,PAGE_SIZE);
			return "loadPeriodicEventRelaEvent";
		}
		/**
		 * 根据id查询数据
		 * @throws UnsupportedEncodingException 
		 */
		public String loadPeriodicEventById() throws UnsupportedEncodingException{
			periodicEvent = eventManageService.loadPeriodicEventById(periodicEvent.getId());
			userlist = usersManageService.loadUser();
			if(periodicEvent!=null){
				return "selectSuccess";
			}
			actionMsg="查询失败!";
			actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
			return "selectError";	
		}
		/**
		 * 根据id删除对应周期性事件
		 * @throws UnsupportedEncodingException 
		 */
		public String deletePeriodicEventById() throws UnsupportedEncodingException{
		    if(eventManageService.deletePeriodicEventById(periodicEvent.getId())){
		    	actionMsg="删除成功!";
		    	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		    	return "deleteSuccess";	
		    }
		    actionMsg="数据异常,删除失败!";
		    actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		    return "deleteError";	
		}
		public String updatePeriodicEventById() throws UnsupportedEncodingException{
			periodicEvent.setId(periodicEvent.getId());
			periodicEvent.setAccountByShsjId(eventShId);
			periodicEvent.setAccountByClsjId(eventEngineerId);
			Date startDate = periodicEvent.getEventStartTime(); 
		        //如果获得事件开始时间是小于或者等于今天，那么先向周期事件表插入一条数据并且获取Id，再向事件表插入一条数据，创建事件时间为开始时间+一个周期
		        Date date = new Date();
		        Account account=(Account)ActionContext.getContext().getSession().get("admin");
		    	periodicEvent.setAccountByDjsjId(account.getId());
		    	periodicEvent.setAccountByFpsjId(account.getId());
		    	Account Clsj = usersManageService.loadwUsersById(periodicEvent.getAccountByClsjId());
		    	periodicEvent.setEventEngineer(Clsj.getName());
		    	Account SHsj = usersManageService.loadwUsersById(periodicEvent.getAccountByShsjId());
		    	periodicEvent.setVisitName(SHsj.getName());
		    	//设置开始时间
		    	Calendar calendar = Calendar.getInstance();
	            calendar.setTime(startDate);
	          //如果获得事件开始时间大于今天，意味者还没有开始，那么直接向周期性事件表插入一条数据，并且创建事件时间为时间开始时间。	        	
	        	periodicEvent.setCreateEventDate(calendar.getTime());
	        	
	            if(eventManageService.updatePeriodicEvent(periodicEvent)){
	            	final String sendFuck ="周期性事件:"+periodicEvent.getEventType()+"->已更新";//获取事件的主题
	        		threadSendMail(sendFuck);//子线程发送邮件方法
		            actionMsg="事件更新成功！";
		            actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		            return "updatePeriodicEventSuccess";
	            }
	            else {
	            	actionMsg="事件更新失败！";
	            	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
	            	return "updatePeriodicEventError";}     
	        }
		public String updateEventIsOpenState() throws UnsupportedEncodingException{
			if(eventManageService.updateEventIsOpenState(periodicEvent)){
				 actionMsg="状态修改成功!";
		         actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		         return "updateIsOpenSuccess";
			}else{
				actionMsg="状态修改失败!";
				actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
				return "updateIsOpenError";
			 }
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
	 * @return the eventEngineerId
	 */
	public Integer getEventEngineerId() {
		return eventEngineerId;
	}

	/**
	 * @param eventEngineerId the eventEngineerId to set
	 */
	public void setEventEngineerId(Integer eventEngineerId) {
		this.eventEngineerId = eventEngineerId;
	}

	/**
	 * @return the eventShId
	 */
	public Integer getEventShId() {
		return eventShId;
	}

	/**
	 * @param eventShId the eventShId to set
	 */
	public void setEventShId(Integer eventShId) {
		this.eventShId = eventShId;
	}


	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public PeriodicEvent getModel() {
		// TODO Auto-generated method stub
		return periodicEvent;
	}

}
