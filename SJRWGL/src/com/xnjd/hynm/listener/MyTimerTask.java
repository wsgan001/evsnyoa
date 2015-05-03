package com.xnjd.hynm.listener;

//import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.service.EventManageService;
import com.xnjd.hynm.util.SendMailUtil;
//import java.util.Collections;

//import com.xnjd.hynm.service.UsersManageService;

public class MyTimerTask implements ServletContextListener {

   
	//protected static final String to = "makairui@evsny.com";
	protected static String sendfuck="";
	protected  static String sendto ="";
	protected  static String sendtocustomer="";
	protected  static String sendtoinfo="";
	protected static String sendtype="";
	protected  static String sendtopost="";
	protected static String sendname="";
	protected static String[] userinfo={};
	protected static String userinfo2="";  //测试单个用户信息是否能够接受发送
	protected static String dispatchstate="";
	protected static String handlestateinfo="";
	protected static String passstate="";
    protected static int  emailfpid=0;
    protected static int  emailyqid=0;
    protected static String riqi="";
	
	  protected static final String[] recipients = {"makairui@evsny.com"};
	  private Timer timer = null;  
	  private Queue<Event> postQueue=null;
	  //private List<String> emailList=null;
	  private int MaxID=-1;
	  private EventManageService eventManageService=null;
	  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	  SimpleDateFormat strplan=new SimpleDateFormat("yyyy-MM-dd");
	  //String strplantime;
	  Date plantime1; 
	 protected Date plantime;
	
	  public void contextDestroyed(ServletContextEvent event) 
	  {  
	   timer.cancel(); 
	   event.getServletContext().log("定时器销毁");  
	  }   
	  public void contextInitialized(final ServletContextEvent event) 
	  { 
		  //emailList=new ArrayList<String>(); 
		    //Account admin=event.getServletContext().
		  //Collections.addAll(emailList, event.getServletContext().getInitParameter("Email").split(","));
		  BeanFactory factory = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		  eventManageService = (EventManageService) factory.getBean("eventManageService");
		  postQueue=new LinkedList<Event>();
	   //在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能 
		  
		
	   timer = new Timer(true); 
	   event.getServletContext().log("定时器已启动");//添加日志，可在tomcat日志中查看到 
	   //调用exportHistoryBean，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时；
	   timer.schedule(new TimerTask() {
		
	
		@Override
		public void run() {
			event.getServletContext().log("扫描开始！");  
			
			List<Event> listEvent = eventManageService.getNeedPostEvent(MaxID);
			Date today=Calendar.getInstance().getTime();
			String strtoday= format.format(today);
			
			for (Event tempEvent2 :listEvent){
				 
				 plantime1=tempEvent2.getPlanTime1();
				 String strplantime=strplan.format(plantime1);
				 String strplantime2=format.format(plantime1);
				 int result = strtoday.compareTo(strplantime2);
				// if(tempEvent2.getPlanTime1().after(today)&&tempEvent2.getEmailfpState()==false&&tempEvent2.getId()>MaxID){
				 //if(strtoday.compareTo(strplantime)<0&&tempEvent2.getEmailfpState()==false&&tempEvent2.getId()>MaxID){
					 if((result<0||result==0)&&tempEvent2.getEmailfpState()==false&&tempEvent2.getId()>MaxID){
						//plantime=tempEvent2.getPlanTime1();
						//riqi=format.format(plantime);
					    emailfpid=tempEvent2.getId();
				    	sendto=tempEvent2.getAccountByClsjId().getEmail();//添加的语句，通过时事件关联的用户表获取到用户的email地址，进行发送。
						sendname=tempEvent2.getAccountByClsjId().getName(); //添加的显示事件执行人情况的消息
						sendtocustomer=tempEvent2.getEventCustomer();
						sendtoinfo=tempEvent2.getEventInfo();
						sendtype=tempEvent2.getEventType();
						sendtopost= sendname+" "+sendtocustomer+sendtype+"; "+sendtoinfo+" "+"计划完成时间:"+strplantime+".";
						sendfuck = sendtocustomer+sendtype+" "+sendname;
						//userinfo[i]= tempEvent2.getEventCustomer();
						updateEmailfpState(emailfpid);
				    
				    	
					    if(postQueue.offer(tempEvent2)){
					    	MaxID=tempEvent2.getId();
					    }//将需要发动邮件提醒的事件放在队列中
					    
					    if (postQueue.peek()!=null){
					    	
					    	 //System.out.println(tempEvent2.getId());
							event.getServletContext().log("开始发送邮件！");
						    Event tempEvent=postQueue.remove();
							sendEmail(tempEvent,sendto,sendfuck,recipients,sendtopost);
							event.getServletContext().log("邮件发送完成！");
							
						   }
					    
				 }
				//else if(tempEvent2.getPlanTime1().before(today)&&tempEvent2.getEmailyqState()==false&&tempEvent2.getEmailfpState()==true){
				 else if((result>0)&&(tempEvent2.getEmailyqState()==false)&&tempEvent2.getId()>MaxID){
					        emailyqid=tempEvent2.getId();
					        updateEmailypState(emailyqid);
				            sendto=tempEvent2.getAccountByClsjId().getEmail();//添加的语句，通过时事件关联的用户表获取到用户的email地址，进行发送。
							sendname=tempEvent2.getAccountByClsjId().getName(); //添加的显示事件执行人情况的消息
							sendtocustomer=tempEvent2.getEventCustomer();
							sendtoinfo=tempEvent2.getEventInfo();
							sendtype=tempEvent2.getEventType();
							sendtopost= sendname+" "+sendtocustomer+sendtype+";"+sendtoinfo+" "+"计划完成时间:"+strplantime+".";
							sendfuck = sendtocustomer+sendtype+" "+sendname+"事件超期!";
							//userinfo[i]= tempEvent2.getEventCustomer();
							if(postQueue.offer(tempEvent2)) MaxID=tempEvent2.getId();//将需要发动邮件提醒的时间放在队列中
						    if (postQueue.peek()!=null){
								event.getServletContext().log("开始发送邮件！");
								Event tempEvent=postQueue.remove();
								sendEmail(tempEvent,sendto,sendfuck,recipients,sendtopost);
								event.getServletContext().log("邮件发送完成！");
								}
						   }
				
			  }
			   
		event.getServletContext().log("扫描结束！");  
		}
	 },0,2*60*1000);  
	  } 
	  public void sendEmail(Event event,String sendto,String sendfuck, String[] recipients,String sendtopost){
		    SendMailUtil sendmail=new SendMailUtil();
			sendmail.setSMTPHost("smtp.evsny.com");
			sendmail.setUser("sjrwmanger@evsny.com");
			sendmail.setPassword("111111");
			sendmail.setFrom("sjrwmanger@evsny.com");
			//sendmail.setTo(model.getEmail());
			sendmail.setTo(sendto);
			sendmail.setCC(recipients);
			sendmail.setSubject(sendfuck);
			//sendmail.setContent("<p>您好！当前有事件分配给您尚未完成；请查看系统联系相关人员确定，并及时处理。以免延期影响考评。</p>");
			sendmail.setContent(sendtopost);
			sendmail.send();	
	  }
	  public  String sendMessage() {
		  return sendname;
			   }
	  public String[] userMessage(){
		 return userinfo;
		  
	  }
	  public String commerMessage(){
		  return userinfo2;
		  
	  }
	  public void updateEmailfpState( int emailstate){
		  
		    Event temp = eventManageService.loadEventByID(emailstate);
		   
	    	temp.setEmailfpState(true);
	    	eventManageService.updateEvent(temp);
		  }
	  
	  public void updateEmailypState( int emailstate){
		  
		    
		    Event temp = eventManageService.loadEventByID(emailstate);
		   
	    	temp.setEmailyqState(true);
	    	eventManageService.updateEvent(temp);
		  }
	  
	  
	  
}