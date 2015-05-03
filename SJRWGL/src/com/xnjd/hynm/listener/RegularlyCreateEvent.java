/**
 * 
 */
package com.xnjd.hynm.listener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.hibernate.SessionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.model.PeriodicEvent;
import com.xnjd.hynm.service.UsersManageService;
import com.xnjd.hynm.util.SendMailUtil;

/**
 * @author my
 *
 */
public class RegularlyCreateEvent extends QuartzJobBean{
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	private UsersManageService usersManageService; 
	String updateEventStateHql = null;//update结束状态语句
	String mCEConHql = null;//返回满足创建事件的事件
	List<PeriodicEvent> actualPEList = new ArrayList<PeriodicEvent>();//存储满足创建事件的周期性事件集合
	/*private BeanFactory beanFactory = new ClassPathXmlApplicationContext("conf/spring/event/di-action.xml");*/
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("开始");
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		//1.检测是否有满足应该创建事件的周期性事件1.查询数据库，遍历满足条件的，得到该条事件，像数据库插入数据。
		//并且将周期性表的创建事件的时间更改为下一次发送时间，如果下一次创建日期超过该事件的结束日期那么就不再插入
		Date date = new Date();
		String dateString = formatTime(date);
		String mCEConHql = "from PeriodicEvent p where p.eventIsEnd!=true and p.createEventDate='"+dateString+"'";
		@SuppressWarnings("unchecked")
		List<PeriodicEvent> periodicEventList = hibernateTemplate.find(mCEConHql);//获取满足创建事件的时间集合
		if(periodicEventList!=null&&!periodicEventList.isEmpty()){
			for(PeriodicEvent periodicEvent:periodicEventList){
				//如果事件开启了就直接创建。
				if(periodicEvent.isEventIsOpen()){
        		Event event = this.returnPeriodicEvent(periodicEvent);
        		hibernateTemplate.save(event);//向事件表中插入数据
        		Calendar calendar = Calendar.getInstance();
        		calendar.setTime(periodicEvent.getCreateEventDate());
        		//将创建日期改为下一次创建日期
        		calendar.add(periodicEvent.getIntervalType(), periodicEvent.getTimeInterval());
        		periodicEvent.setCreateEventDate(calendar.getTime());
        		hibernateTemplate.update(periodicEvent);//将创建日期修改。
				}
				//如果事件暂停了就只修改事件创建时间
				else{
	        		Calendar calendar = Calendar.getInstance();
	        		calendar.setTime(periodicEvent.getCreateEventDate());
	        		//将创建日期改为下一次创建日期
	        		calendar.add(periodicEvent.getIntervalType(), periodicEvent.getTimeInterval());
	        		periodicEvent.setCreateEventDate(calendar.getTime());
	        		hibernateTemplate.update(periodicEvent);//将创建日期修改。	
				}
		}
		}
 //2.检测是否有事件结束，并更改事件结束字段。发送结束邮件，并更改是否发送邮件状态
       Date currentDate = new Date();
       String currentDateString = formatTime(currentDate);
       String Hql2 = "from PeriodicEvent p where p.eventEndTime='"+currentDateString+"'";
       System.out.println(Hql2);
       //查询结束时间和当天时间相同的相等的时间
       @SuppressWarnings("unchecked")
       List<PeriodicEvent> periodicEvents = this.getHibernateTemplate().find(Hql2);
       if(periodicEvents!=null&&!periodicEvents.isEmpty()){
	       for(int i =0;i<periodicEvents.size();i++){
	    	   PeriodicEvent periodicEvent = periodicEvents.get(i);
	    	   if(!periodicEvent.isEventIsEnd()){//判断事件是否结束
	    		   periodicEvent.setEventIsEnd(true);//给邮件结束赋值
	    		   int CLAccountId = periodicEvent.getAccountByClsjId();//用来获取处理人的邮箱，用于发件人
	    		   Account account = usersManageService.loadwUsersById(CLAccountId);
	    		   String CLAccountEmail = account.getEmail();//获取处理人邮箱   
	    		   String [] SHAccountEmailArr = this.returnEmailArr();//获取有执行权限的人的邮箱
	    		   String emailContent = this.returnEmailContent(periodicEvents.get(i).getId());//获取邮件的内容
	    		   String sendFuck = periodicEvent.getEventType()+"事件->已结束";//获取事件的主题
	    		   System.out.println(CLAccountEmail+SHAccountEmailArr+emailContent+sendFuck);
	    		   sendEmail(CLAccountEmail, sendFuck, SHAccountEmailArr, emailContent);//发送邮件 
	    		   if(!periodicEvent.isEventEndMailIsSended()){
	    			   periodicEvent.setEventEndMailIsSended(true);
	    			   this.getHibernateTemplate().merge(periodicEvent);//更新是否发邮件状态和是否结束状态
	    		   }
	    	   }
	       }
	      }
	}
	 //返回需要定时插入的event对象
	public Event returnPeriodicEvent(PeriodicEvent periodicEvent){
    	Event event = new Event();
		/*event.setEventDate(periodicEvent.getCreateEventDate());
		event.setPeriodicEventId(periodicEvent.getId());
		event.setEventCustomer(periodicEvent.getEventCustomer());*/
		Account djAccount = new Account();
		djAccount.setId(periodicEvent.getAccountByDjsjId());
		event.setAccountByDjsjId(djAccount);
		
		Account fpAccount = new Account();
		fpAccount.setId(periodicEvent.getAccountByFpsjId());
		event.setAccountByFpsjId(fpAccount);
		
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
		Calendar ca = Calendar.getInstance();//用于增加天数3天
		ca.setTime(periodicEvent.getEventStartTime());
		ca.add(5, 3);
		df=ca.getTime();
		event.setPlanTime1(df);//计划完成时间
		event.setEventCustomer(periodicEvent.getEventCustomer());
		event.setEventType(periodicEvent.getEventType());
		event.setEventProduct(periodicEvent.getEventProduct());
		event.setEventDate(periodicEvent.getEventStartTime());
		event.setEventInfo(periodicEvent.getEventInfo());
		event.setPeriodicEventId(periodicEvent.getId());
		
		Account ClAccount = new Account();
		ClAccount.setId(periodicEvent.getAccountByClsjId());
		Account Clsj = usersManageService.loadwUsersById(periodicEvent.getAccountByClsjId());	
		event.setAccountByClsjId(ClAccount);
		event.setEventEngineer(Clsj.getName());
		
		Account Shsj = usersManageService.loadwUsersById(periodicEvent.getAccountByShsjId());
		event.setAccountByShsjId(Shsj);
		event.setVisitName(Shsj.getName());
    	return event;
    }
	//发送邮件
	public void sendEmail(String sendto,String sendfuck, String[] recipients,String sendtopost){
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
		try {
			sendmail.sendEventMail();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
  }
	/*//修改结束状态
	@SuppressWarnings("unchecked")
	public boolean updateEmailIsEndState(List<PeriodicEvent> periodicEvents){
		for(int i=0;i<=periodicEvents.size();i++){
			updateEventStateHql="update PeriodicEvent set eventIsEnd=1 where id ="+periodicEvents.get(i).getId();//插入数据
			int updateId =0;
			updateId = this.getHibernateTemplate().execute(new HibernateCallback() {
	    	    (non-Javadoc)
	    	 * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
	    	 
	    	 (non-Javadoc)
	    	 * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
	    	 
	    	@Override
	    	public Object doInHibernate(Session session) throws HibernateException,
	    			SQLException {
	    		// TODO Auto-generated method stub
	    		Query query = session.createQuery(updateEventStateHql);
	    		return query.executeUpdate();	
	    	}	
		});
		if(updateId!=0){
			continue;
		}else{
			return false;
		}
		}
		return true;
	}*/
	//获取具有执行权限的的人的邮箱，并拼接成数组
	@SuppressWarnings("null")
	public String[] returnEmailArr(){
		String [] SHEmailArr = null;
		String HQL = "select new Account(a.email) from Account a where a.sjsh=1";//具有审核事件权限的人
		@SuppressWarnings("unchecked")
		List<Account> SHEmailList = this.getHibernateTemplate().find(HQL);
		StringBuffer sb = new StringBuffer();
		if(SHEmailList!=null&&!SHEmailList.isEmpty()){
			for(int i=0;i<SHEmailList.size();i++){
				sb.append(SHEmailList.get(i).getEmail().trim()+",");
			}
		String emailArr = sb.toString().substring(0,sb.toString().length()-1);
		SHEmailArr= emailArr.split(",");
		}
		return SHEmailArr;
	}
	//获取事件的发件内容
	public String returnEmailContent(int eventId){
		String findEC = "from PeriodicEvent p where p.id="+eventId;
		@SuppressWarnings("unchecked")
		List<PeriodicEvent>  periodicEvents = this.getHibernateTemplate().find(findEC);
		if(periodicEvents!=null&&!periodicEvents.isEmpty()){
			PeriodicEvent periodicEvent = periodicEvents.get(0);
			StringBuffer sb = new StringBuffer();
			sb.append("事件类型(周期性事件):"+periodicEvent.getEventType()+" ");
			sb.append("事件用户:"+periodicEvent.getEventCustomer()+" ");
			sb.append("事件产品:"+periodicEvent.getEventProduct()+" ");
			sb.append("事件执行人:"+periodicEvent.getEventEngineer()+" ");
			sb.append("事件审核人:"+periodicEvent.getVisitName()+" ");
			sb.append("事件开始日期:"+periodicEvent.getEventStartTime()+" ");
			sb.append("事件结束时间:"+periodicEvent.getEventEndTime()+" ");
			sb.append("时间间隔:"+periodicEvent.getTimeInterval()+"天");
			return sb.toString();
		}
		return "";
	}
	//格式化时间为yyyy-MM-dd 00:00:00
	public String formatTime(Date date){
		return (new SimpleDateFormat("yyyy-MM-dd 00:00:00")).format(date);
	}
	
	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * @param hibernateTemplate the hibernateTemplate to set
	 */
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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

}
