/**
 * 
 */
package com.xnjd.hynm.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xnjd.hynm.dao.DataAccessUtil;
import com.xnjd.hynm.dao.EventManageDao;
import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.model.PeriodicEvent;

/**
 * @author STONE
 *
 */
public class EventManageDaoImpl extends HibernateDaoSupport implements EventManageDao {
	private DataAccessUtil dataAccessUtil;
	
	/**
	 * @param dataAccessUtil the dataAccessUtil to set
	 */
	public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
		this.dataAccessUtil = dataAccessUtil;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#updateEvent(com.xnjd.hynm.model.Event)
	 */
	@Override
	public boolean updateEvent(Event event) {
		boolean status = false;
		try{
			dataAccessUtil.update(event);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#addEvent(com.xnjd.hynm.model.Event)
	 */
	@Override
	public boolean addEvent(Event event) {
		try
		{
			dataAccessUtil.create(event);
		} 
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#deleteEvent(com.xnjd.hynm.model.Event)
	 */
	@Override
	public boolean deleteEvent(Event event) {
		boolean status = false;
		try{
			dataAccessUtil.delete(event);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#loadEventByID(int)
	 */
	@Override
	public Event loadEventByID(int id) {
		// TODO Auto-generated method stub
		return (Event) dataAccessUtil.findById(Event.class,id);
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#deleteEventByID(int)
	 */
	@Override
	public boolean deleteEventByID(int id) {
		boolean status = false;
		try{
			dataAccessUtil.deleteById(Event.class,id);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#queryForPage(int, int, int, java.lang.String, java.lang.String, java.lang.String, int, int, char)
	 */
	@Override
	public List<Object> queryForPage(int offset, int length, int account_id,int account_type,
			String product_name, String customer_name, String event_type,
			int complete_state, int dispatch_state, String pass_state) {
		
		String hql = "from Event e where 1=1"; //查询语句
		
		if (account_id!=-1){
			hql+="and e.accountByClsjId.id="+account_id;
			if(account_type==1)//事件登记者			
			//hql+=" and (e.accountByDjsjId.id="+account_id+" or e.accountByClsjId.id="+account_id+")";
			//hql+="and e.accountByDjsjId.id="+account_id;
			hql+=" or (e.accountByDjsjId.id="+account_id+" and e.dispatchState="+dispatch_state+")";
			if(account_type==2)//事件分配者
				hql+=" and e.accountByFpsjId.id="+account_id;
			if(account_type==3)//事件审核者
				hql+=" and e.accountByShsjId.id="+account_id;
		}

		if(product_name!=null)hql+=" and e.eventProduct like %'"+product_name+"'%";
		if(customer_name!=null)hql+=" and e.eventCustomer like %'"+customer_name+"'%";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";
		hql+="Order by e.visitName asc,e.eventEngineer asc";
	     return dataAccessUtil.queryForPage(hql,offset, length);
	}
	
	/***
	 * 显示其他事件
	 * 
	 * 
	 */
	public List<Object> queryOtherForPage(int offset, int length, int account_id,int account_type,
			String product_name, String customer_name, String event_type,
			int complete_state, int dispatch_state, String pass_state) {
		
		String hql = "from Event e where 1=1"; //查询语句
		
		if (account_id!=-1){
			//hql+=" and (e.accountByDjsjId.id!="+account_id+" or e.accountByClsjId.id!="+account_id+")";
			hql+="and e.accountByClsjId.id="+account_id;
			if(account_type==1)//事件登记者			
			//hql+=" and (e.accountByDjsjId.id="+account_id+" or e.accountByClsjId.id="+account_id+")";
			//hql+="and e.accountByDjsjId.id="+account_id;
			hql+=" or (e.accountByDjsjId.id="+account_id+" and e.dispatchState="+dispatch_state+")";
			if(account_type==2)//事件分配者
				hql+=" and e.accountByFpsjId.id="+account_id;
			if(account_type==3)//事件审核者
				hql+=" and e.accountByShsjId.id="+account_id;
		}

		if(product_name!=null)hql+=" and e.eventProduct like %'"+product_name+"'%";
		if(customer_name!=null)hql+=" and e.eventCustomer like %'"+customer_name+"'%";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";
	     return dataAccessUtil.queryForPage(hql,offset, length);
	}
	

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#getRowCount(int, java.lang.String, java.lang.String, java.lang.String, int, int, char)
	 */
	//比较测试····
	@Override
	public int getRowCount(int account_id, int account_type,String product_name,
			String customer_name, String event_type, int complete_state,
			int dispatch_state, String pass_state,int handle_state) {
		String hql = "select count(*) from Event e where 1=1";        //查询语句
		if (account_id!=-1){
			//hql+="and e.accountByClsjId.id="+account_id;
			if(account_type==1)//事件登记者
			hql+=" and e.accountByDjsjId.id="+account_id;
			if(account_type==2)//事件分配者
				hql+=" and e.accountByFpsjId.id="+account_id;
			if(account_type==3)//事件审核者
				hql+=" and e.accountByShsjId.id="+account_id;
			/*if(account_type==4)  //事件处理者
				hql+="and e.accountByClsjId.id="+account_id;*/
				
		}
		if(product_name!=null)hql+=" and e.eventProduct like %'"+product_name+"'%";
		if(customer_name!=null)hql+=" and e.eventCustomer like %'"+customer_name+"'%";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";
		return dataAccessUtil.getAllRow(hql);
		}
	
	@SuppressWarnings("unchecked")
	/*public List<Event> getNeedPostEvent( int id) {
		//String hql = "from Event e where e.id >"+id+" and e.planTime1 < current_date() and e.completeState=0 and e.dispatchState=1 and e.passState='0' order by id asc"; 
		String hql = "from Event e where e.id >"+id+" and e.planTime1 < current_date() and e.completeState=0 and e.passState='0' order by id asc"; 
	    return dataAccessUtil.find(hql);
	}*/
	//第一个getNeedPostEvent
	public List<Event> getNeedPostEvent() {
		//String hql = "from Event e where e.id >"+id+" and e.planTime1 < current_date() and e.completeState=0 and e.dispatchState=1 and e.passState='0' order by id asc"; 
		String hql = "from Event e where e.planTime1 < current_date() and e.completeState=0 and e.passState='0' order by id asc"; 
	    return dataAccessUtil.find(hql);
	}



	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#getRowCount(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, int)
	 */
	
	//用于统计查询的getRowCount
	@Override
	public int getRowCount(int account_id, int account_type,
			String enginner_id, String startDate, String endDate,
			String event_type, int complete_state, int dispatch_state,
			String pass_state, int handle_state) {
		String hql = "from Event e where 1=1";        //查询语句
		if (account_id!=-1){
			if(account_type==1)//事件登记者
			hql+=" and e.accountByDjsjId.id="+account_id;
			if(account_type==2)//事件分配者
				hql+=" and e.accountByFpsjId.id="+account_id;
			if(account_type==3)//事件审核者
				hql+=" and e.accountByShsjId.id="+account_id;
			/*if(account_type==4)  //事件处理者
				hql+="and e.accountByClsjId.id="+account_id;*/
				
		}
		if(enginner_id!=null)hql+=" and e.accountByClsjId.id ="+enginner_id;
		if(startDate!=null)hql+=" and e.completeTime >= '"+startDate+"'";
		if(endDate!=null)hql+=" and e.completeTime <='"+endDate+"'";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";
		return dataAccessUtil.getAllRowCount(hql);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#queryForPage(int, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, int)
	 */
	//用于统计查询的queryForPage
	@Override    
	public List<Object> queryForPage(int offset, int length, int account_id,
			int account_type, String enginner_id, String startDate,
			String endDate, String event_type, int complete_state,
			int dispatch_state, String pass_state, int handle_state) {
		String hql = "from Event e where 1=1";        //查询语句
		if (account_id!=-1){
			hql+="and e.accountByClsjId.id="+account_id;
			if(account_type==1)//事件登记者			
			//hql+=" and (e.accountByDjsjId.id="+account_id+" or e.accountByClsjId.id="+account_id+")";
			hql+="and e.accountByDjsjId.id="+account_id;
			if(account_type==2)//事件分配者
				hql+=" and e.accountByFpsjId.id="+account_id;
			if(account_type==3)//事件审核者
				hql+=" and e.accountByShsjId.id="+account_id;
		}

		if(enginner_id!=null)hql+=" and e.accountByClsjId.id ="+enginner_id;
		if(startDate!=null)hql+=" and e.completeTime >= '"+startDate+"'";
		if(endDate!=null)hql+=" and e.completeTime <='"+endDate+"'";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";
	     return dataAccessUtil.queryForPage(hql,offset, length);
	}
   /*用于导出execl表的查询*/
	
	public List<Object> queryForPage(int account_id,int account_type,
			String enginner_id, String startDate,String endDate, String event_type,
			int complete_state, int dispatch_state, String pass_state,int handle_state){
		String hql = "from Event e where 1=1";        //查询语句
		if (account_id!=-1){
			hql+="and e.accountByClsjId.id="+account_id;
			if(account_type==1)//事件登记者			
			//hql+=" and (e.accountByDjsjId.id="+account_id+" or e.accountByClsjId.id="+account_id+")";
			hql+="and e.accountByDjsjId.id="+account_id;
			if(account_type==2)//事件分配者
				hql+=" and e.accountByFpsjId.id="+account_id;
			if(account_type==3)//事件审核者
				hql+=" and e.accountByShsjId.id="+account_id;
		}

		if(enginner_id!=null)hql+=" and e.accountByClsjId.id ="+enginner_id;
		if(startDate!=null)hql+=" and e.completeTime >= '"+startDate+"'";
		if(endDate!=null)hql+=" and e.completeTime <='"+endDate+"'";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";
	     return dataAccessUtil.queryForPage(hql);
	}
	
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#getNeedPostEvent()
	 */
	@SuppressWarnings("unchecked")
	@Override //自己增加的查询语句
	public List<Event> getNeedPostEvent(int id) {
		//String hql = "from Event e where e.id >"+id+" and  e.dispatchState=1 and e.completeState=0 and e.passState='0' order by id asc";
		//String hql = "from Event e where e.dispatchState=1 and e.completeState=0 and e.passState='0' order by id asc";
		//String hql = "from Event e where e.id >"+id+" and e.dispatchState=1 and e.completeState=0 and e.passState='0' and (e.emailfpState=0 or e.emailyqState=0) order by id asc";
		String hql = "from Event e where  e.dispatchState=1 and e.completeState=0 and e.passState='0' and (e.emailfpState=0 or e.emailyqState=0) order by id asc";      
		return dataAccessUtil.find(hql);
	}
	/*
	 * 新增显示list*/
	
	@SuppressWarnings("unchecked")
	
	public List<Event>getShowEvent(String name){
		String hql= "from Event e where e.completeState=0 and e.passState='0' and e.porder by id asc";
				return dataAccessUtil.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#queryForPage(int, int, int, int, int, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 */
	
	/**   
	 *以下为库房查询
	 * */
	@Override
	public List<Object> queryForPage(int offset, int length, int account_id,
			int nowstate, int account_type, String product_name,
			String customer_name, String event_type, int complete_state,
			int dispatch_state, String pass_state) {

		String hql = "from Event e where 1=1"; //查询语句
        if(nowstate==3)hql+=" and e.nowstate="+nowstate;
		/*if(product_name!=null)hql+=" and e.eventProduct like %'"+product_name+"'%";
		if(customer_name!=null)hql+=" and e.eventCustomer like %'"+customer_name+"'%";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";*/
	     return dataAccessUtil.queryForPage(hql,offset, length);
		
		
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#getRowCount(int, int, int, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, int)
	 */
	@Override
	public int getRowCount(int account_id, int account_type, int nowstate,
			String product_name, String customer_name, String event_type,
			int complete_state, int dispatch_state, String pass_state,
			int handle_state) {
		String hql = "from Event e where 1=1";        //查询语句
		if(nowstate==3)hql+=" and e.nowstate="+nowstate;
		/*if(product_name!=null)hql+=" and e.eventProduct like %'"+product_name+"'%";
		if(customer_name!=null)hql+=" and e.eventCustomer like %'"+customer_name+"'%";
		if(event_type!=null)hql+=" and e.eventType='"+event_type+"'";
		if(complete_state!=-1)hql+=" and e.completeState="+complete_state;
		if(dispatch_state!=-1)hql+=" and e.dispatchState="+dispatch_state;
		if(pass_state!=null)hql+=" and e.passState='"+pass_state+"'";*/
		return dataAccessUtil.getAllRowCount(hql);
	}


	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#findInventoryKeeper()
	 */
	@Override
	public List<Account> findInventoryKeeper() {
		String hql = "select new Account(email) from Account a where a.kfgl=1";
		return dataAccessUtil.getPersonMailAddress(hql);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#findAuditPerson()
	 */
	@Override
	public List<Account> findAuditPerson() {
		String hql = "select new Account(email) from Account a where a.kfgl!=1 and a.sjsh=1";
		return dataAccessUtil.getPersonMailAddress(hql);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#findCLSJPerson()
	 */
	@Override
	public List<Account> findCLSJPerson(int eventID) {
		String hql ="select distinct new Account(a.email) from Account a,Event e where a.id=e.accountByClsjId and e.id="+eventID;
		return dataAccessUtil.getPersonMailAddress(hql);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#findSHManWithKGMan(int, int)
	 */
	@Override
	public List<Account> findSHManWithKGMan(int KGID, int eventID) {
		String hql = "select new Account(a.email) from Account a,Event e where (e.id="+eventID+" and a.sjsh=1 and a.id!=e.accountByClsjId) or (a.id!=e.accountByClsjId and e.id="+eventID+" and a.kfgl=1 and a.kfgl!="+KGID+")";
		return dataAccessUtil.getPersonMailAddress(hql);
	}

	/**
	 * 
	 * 增加执行人查询
	 * 
	 * */
	@Override
	public int getRowCount(int account_id, int account_type, String event_type,
			int complete_state, int dispatch_state, String pass_state,
			int handle_state) {
		String hql = "from Event e where 1=1";        //查询语句
		if (account_id!=-1){
			hql+="and e.accountByClsjId.id="+account_id;
		 
			}
		
		if(complete_state!=-1)hql+=" and e.completeState!="+complete_state;
		return dataAccessUtil.getAllRowCount(hql);
	} 

     /***
      * 对执行者查询的事件
      */
	
	@Override
	public List<Object> queryForPage(int offset, int length, int account_id,
			int account_type, String event_type, int complete_state,
			int dispatch_state, String pass_state){
         
		String hql = "from Event e where 1=1"; //查询语句
		
		if (account_id!=-1){
			hql+="and e.accountByClsjId.id="+account_id;
		
		}
	  
      if(complete_state!=-1)
    	  hql+=" and e.completeState!="+complete_state;
   return dataAccessUtil.queryForPage(hql,offset, length);
	}
	
	/***
	 * 个人事件包括审核事件与执行事件
	 */
	
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#queryForPage(int, int, int, int, java.lang.String, int, int, java.lang.String)
	 */
	/*@Override
	public List<Object> queryForPage(int offset, int length, int account_id,
			int account_type, String event_type, int complete_state,
			int dispatch_state, String pass_state) {
		  
				String hql = "from Event e where 1=1"; //查询语句
				
				if (account_id!=-1){
				hql+="and (e.accountByShsjId.id="+account_id+" or e.accountByClsjId.id="+account_id+")" ;
				}
				
			  
		      if(complete_state!=-1)
		    	  hql+=" and e.completeState!="+complete_state;
		      
		     
		   return dataAccessUtil.queryForPage(hql,offset, length);
	}*/
	

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#addPeriodicEvent(com.xnjd.hynm.model.PeriodicEvent)
	 */
	@Override
	public int addPeriodicEvent(PeriodicEvent periodicEvent) {
		// TODO Auto-generated method stub
		try {
			return dataAccessUtil.add(periodicEvent);
		} catch (Exception e) {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#queryPeriocicEventForPage(java.lang.String, int, int)
	 */
	@Override
	public List<Object> queryPeriocicEventForPage(String hql, int offset,
			int length) {
		return dataAccessUtil.queryForPage(hql, offset, length);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#getPeriodicEventTotalPage()
	 */
	@Override
	public int getPeriodicEventTotalRow(String hql) {		
		return dataAccessUtil.getAllRow(hql);	
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#loadPeriodicEventById(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PeriodicEvent loadPeriodicEventById(int periodicEventId) {
		return (PeriodicEvent) dataAccessUtil.findById(PeriodicEvent.class,periodicEventId);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#deletePeriodicEventById(int)
	 */
	@Override
	public Boolean deletePeriodicEventById(int perriodicEventId) {
		dataAccessUtil.deleteById(PeriodicEvent.class, perriodicEventId);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#updatePeriodicEvent(com.xnjd.hynm.model.PeriodicEvent)
	 */
	@Override
	public Boolean updatePeriodicEvent(PeriodicEvent periodicEvent){
		// TODO Auto-generated method stub
		 dataAccessUtil.create(periodicEvent);
		 return true;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.EventManageDao#updateEventIsOpenState(com.xnjd.hynm.model.PeriodicEvent)
	 */
	@Override
	public int updateEventIsOpenState(PeriodicEvent periodicEvent) {
		String hql = "update PeriodicEvent p set p.eventIsOpen=:eventIsOpen where p.id=:id";
		Query query = getSession().createQuery(hql);
		query.setBoolean("eventIsOpen", periodicEvent.isEventIsOpen());
		query.setInteger("id", periodicEvent.getId());
		return query.executeUpdate();
	}

	


	

	
}
