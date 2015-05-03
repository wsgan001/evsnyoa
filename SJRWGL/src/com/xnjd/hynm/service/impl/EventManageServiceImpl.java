/**
 * 
 */
package com.xnjd.hynm.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.xnjd.hynm.dao.EventManageDao;
import com.xnjd.hynm.dao.UsersManageDao;
import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.model.PeriodicEvent;
import com.xnjd.hynm.service.EventManageService;
import com.xnjd.hynm.util.PageBean;

/**
 * @author STONE
 * 
 */
public class EventManageServiceImpl implements EventManageService {
	private EventManageDao eventManageDao;
	

	
	/**
	 * @return the eventManageDao
	 */
	public EventManageDao getEventManageDao() {
		return eventManageDao;
	}






	/**
	 * @param eventManageService
	 *            the eventManageService to set
	 */
	public void setEventManageDao(EventManageDao eventManageDao) {
		this.eventManageDao = eventManageDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#queryForPage(int, int,
	 * java.lang.String[])
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xnjd.hynm.service.EventManageService#updateEvent(com.xnjd.hynm.model
	 * .Event)
	 */
	@Override
	public boolean updateEvent(Event event) {
		// TODO Auto-generated method stub
		return eventManageDao.updateEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xnjd.hynm.service.EventManageService#addEvent(com.xnjd.hynm.model
	 * .Event)
	 */
	@Override
	public boolean addEvent(Event event) {
		// TODO Auto-generated method stub
		return eventManageDao.addEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xnjd.hynm.service.EventManageService#deleteEvent(com.xnjd.hynm.model
	 * .Event)
	 */
	@Override
	public boolean deleteEvent(Event event) {
		// TODO Auto-generated method stub
		// return eventManageDao.deleteEvent(event);
		return eventManageDao.deleteEvent(event);
	}

	public boolean deleteEventByID(int id) {

		return eventManageDao.deleteEventByID(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#loadEventByID(int)
	 */
	@Override
	public Event loadEventByID(int id) {
		// TODO Auto-generated method stub
		return eventManageDao.loadEventByID(id);
	}
	@Override
	public String loadEventById(int id) {
		// TODO Auto-generated method stub
		Event event = eventManageDao.loadEventByID(id);
		if(event!=null)return event.getEventInfo();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadAllEvent(int, int)
	 */
	@Override
	public PageBean LoadAllEvent(int page, int pageSize) {
		int allRow = eventManageDao.getRowCount(-1, 0, null, null, null, -1,
				-1, "0", -1);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryForPage(offset, pageSize, -1, 0,
				null, null, null, -1, -1, "0"));
		pageBean.init();
		return pageBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadAllEvent(int, int, int)
	 */
	@Override
	public PageBean LoadAllEvent(int page, int pageSize, int accountID) {
		//int allRow=eventManageDao.getRowCount(accountID,1, null, null, null, 0, -1, "0",-1);
		int allRow=eventManageDao.getRowCount(accountID, 1, null, 1, 1, "0",-1);
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        if(page>totalPage)page--;
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int currentPage = PageBean.countCurrentPage(page);
		 //把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        //pageBean.setList(eventManageDao.queryForPage(offset, pageSize,accountID,1, null, null, null, 0,0, "0"));
        pageBean.setList(eventManageDao.queryForPage(offset, pageSize, accountID, 1, null, 1,0, "0"));
        pageBean.init();
        return pageBean;
	}

	// 添加库房查询显示
	@Override
	public PageBean LoadAllEvent(int page, int pageSize, int accountID,
			int nowstate) {
		int allRow = eventManageDao.getRowCount(accountID, 1, 3, null, null,
				null, 0, -1, "0", -1);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryForPage(offset, pageSize,
				accountID, nowstate, 1, null, null, null, 0, 0, "0"));
		pageBean.init();
		return pageBean;
	}

	@Override
	public PageBean LoadAllUndispatchedEvent(int page, int pageSize) {
		int allRow = eventManageDao.getRowCount(-1, -1, null, null, null, 0, 0,
				null, 0);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryForPage(offset, pageSize, -1, -1,
				null, null, null, 0, 0, null));
		pageBean.init();
		return pageBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadUndispatchedEvent(int,
	 * int, int)
	 */
	@Override
	public PageBean LoadUndispatchedEvent(int page, int pageSize, int accountID) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadAllDispatchedEvent(int,
	 * int)
	 */
	@Override
	public PageBean LoadAllDispatchedEvent(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadDispatchedEvent(int,
	 * int, int)
	 */
	@Override
	public PageBean LoadDispatchedEvent(int page, int pageSize, int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xnjd.hynm.service.EventManageService#LoadAllUncompletedEvent(int,
	 * int)
	 */
	@Override
	public PageBean LoadAllUncompletedEvent(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadUncompletedEvent(int,
	 * int, int)
	 */
	@Override
	public PageBean LoadUncompletedEvent(int page, int pageSize, int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadAllCompletedEvent(int,
	 * int)
	 */
	@Override
	public PageBean LoadAllCompletedEvent(int page, int pageSize,
			String enginner_id, String startDate, String endDate) {
		int allRow = eventManageDao.getRowCount(-1, 0, enginner_id, startDate,
				endDate, null, 1, 1, "1", 1);
		//System.out.print(allRow);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryForPage(offset, pageSize, -1, 0,
				enginner_id, startDate, endDate, null, 1, 1, "1", 1));
		pageBean.init();
		return pageBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadCompletedEvent(int,
	 * int, int)
	 */
	@Override
	public PageBean LoadCompletedEvent(int page, int pageSize, int accountID,
			String enginner_id, String startDate, String endDate) {
		int allRow = eventManageDao.getRowCount(accountID, 3, enginner_id,
				startDate, endDate, null, 1, 1, "1", 1);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryForPage(offset, pageSize,
				accountID, -1, enginner_id, startDate, endDate, null, 1, 1,
				"1", 1));
		pageBean.init();
		return pageBean;
	}

	/**
	 * 增加用于导出execl表格内容的查询输出
	 * **/
	public List<?> LoadAllCompletedExport(String enginner_id, String startDate,
			String endDate) {

		List<Object> iter = eventManageDao.queryForPage(-1, 0, enginner_id,
				startDate, endDate, null, 1, 1, "1", 1);
		return iter;
	}

	public List<?> LoadCompletedExport(int accountID, String enginner_id,
			String startDate, String endDate) {
		List<Object> iter = eventManageDao.queryForPage(accountID, -1,
				enginner_id, startDate, endDate, null, 1, 1, "1", 1);
		return iter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xnjd.hynm.service.EventManageService#LoadAllUnpassedEvent(int,
	 * int)
	 */
	@Override
	public PageBean LoadAllUnpassedEvent(int page, int pageSize) {
		int allRow = eventManageDao.getRowCount(-1, 0, null, null, null, 0, 1,
				"0", 0);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryForPage(offset, pageSize, -1, 0,
				null, null, null, 0, 1, "0"));
		pageBean.init();
		return pageBean;
	}

	
	@Override
	public PageBean LoadUnpassedEvent(int page, int pageSize, int accountID) {
		int allRow = eventManageDao.getRowCount(accountID, 2, null, null, null,
				0, 1, "0", 0);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryForPage(offset, pageSize,
				accountID, 2, null, null, null, 0, 1, "0"));
		pageBean.init();
		return pageBean;
	}

	
	@Override
	public PageBean LoadAllPassedEvent(int page, int pageSize, int state) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public PageBean LoadPassedEvent(int page, int pageSize, int accountID,
			int state) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> getNeedPostEvent(int id) {
		// TODO Auto-generated method stub
		return eventManageDao.getNeedPostEvent(id);
	}

	
	@Override
	public List<Event> getNeedPostEvent() {
		// TODO Auto-generated method stub
		return eventManageDao.getNeedPostEvent();
	}

	
	@Override
	public String findInventoryKeeper() {
		String inventoryKeeper = "";
		List<Account> KFList = eventManageDao.findInventoryKeeper();
		if (KFList.size() != 0) {
			for (Account KFAccount : KFList) {
				String emailAddress = KFAccount.getEmail().trim();
				if(emailAddress!=null&&!"".equals(emailAddress))
				inventoryKeeper += (emailAddress + ",");
			}
			if(inventoryKeeper!=null&&!"".equals(inventoryKeeper))
			inventoryKeeper = inventoryKeeper.substring(0,inventoryKeeper.length() - 1).trim();
			return inventoryKeeper;
		} else {
			return null;
		}

	}

	
	@Override
	public String findAuditPerson() {
		String auditPerson = "";
		List<Account> APList = eventManageDao.findAuditPerson();
		if(APList.size() !=0){
			for(Account APAccount : APList){
				String emailAddress = APAccount.getEmail().trim();
				if(emailAddress!=null&&!"".equals(emailAddress))
				auditPerson += (emailAddress+",");
			}
			if(auditPerson!=null&&!"".equals(auditPerson))
			auditPerson = auditPerson.substring(0,auditPerson.length()-1).trim();
			return auditPerson;
		}else{
			return null;
			}
		
	}

	
	@Override
	public String findCLSJPerson(int eventID) {
		// TODO Auto-generated method stub
		String sb = "";
		String emailAddress = "";
		List<Account> accountList = eventManageDao.findCLSJPerson(eventID);
		if(accountList.size()>0){
			for(Account account:accountList){
				if(account.getEmail()!=null&!("").equals(account.getEmail()))
				sb += (account.getEmail()+",");
			}
			sb = sb.toString();
			if(sb!=null&&!"".equals(sb)){
				emailAddress = sb.substring(0,sb.length()-1).trim();
			}
			return emailAddress;
		}
		return null;
	}

	
	@Override
	public String findSHManWithKGMan(int KGID, int eventID) {
		// TODO Auto-generated method stub
		String sb ="";
		String emailAddress ="";
		List<Account> accountList = eventManageDao.findSHManWithKGMan(KGID, eventID);
		if(accountList.size()>0){
			for(Account account:accountList){
				if(account.getEmail()!=null&!("").equals(account.getEmail()))
				sb+=(account.getEmail()+",");
			}
			if(sb!=null&&!"".equals(sb)){
				emailAddress = sb.substring(0,sb.length()-1).trim();
			}
			return emailAddress;
		}
		return null;
				
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#addPeriodicEvent(com.xnjd.hynm.model.PeriodicEvent)
	 */
	@Override
	public boolean addPeriodicEvent(PeriodicEvent periodicEvent) {
       if((eventManageDao.addPeriodicEvent(periodicEvent))!=0)return true;	
		return false;
	}






	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#loadAllPeriodicEvent(int, int)
	 */
	@Override
	public PageBean loadAllPeriodicEvent(int page, int pageSize) {
		String Hql = "select count(*) from PeriodicEvent p";
		int allRow = eventManageDao.getPeriodicEventTotalRow(Hql);
		
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		String hql = "from PeriodicEvent p where p.eventIsEnd!=true order by id desc";//查询未结束的周期性事件
		pageBean.setList(eventManageDao.queryPeriocicEventForPage(hql,offset, pageSize));
		pageBean.init();
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#loadPeriodicEventRelaEvent(int)
	 */
	@Override
	public PageBean loadPeriodicEventRelaEvent(int periodicEventId,int page,int pageSize) {
		String Hql = "select count(*) from Event e where e.periodicEventId="+periodicEventId;
		int allRow = eventManageDao.getPeriodicEventTotalRow(Hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		String hql = "from Event e where e.periodicEventId="+periodicEventId;//查询未结束的周期性事件
		pageBean.setList(eventManageDao.queryPeriocicEventForPage(hql,offset, pageSize));
		pageBean.init();
		return pageBean;
	}






	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#loadPeriodicEventById(int)
	 */
	@Override
	public PeriodicEvent loadPeriodicEventById(int periodicEventId) {
		PeriodicEvent periodicEvent = eventManageDao.loadPeriodicEventById(periodicEventId);
		if(periodicEvent!=null){
				return periodicEvent;
		}
		return null;
	}






	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#deletePeriodicEventById(int)
	 */
	@Override
	public boolean deletePeriodicEventById(int periodicEventId) {
		eventManageDao.deletePeriodicEventById(periodicEventId);
		return true;
	}






	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#updatePeriodicEvent(com.xnjd.hynm.model.PeriodicEvent)
	 */
	@Override
	public boolean updatePeriodicEvent(PeriodicEvent periodicEvent) {
		return eventManageDao.updatePeriodicEvent(periodicEvent);
	}






	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#updateEventIsOpenState(com.xnjd.hynm.model.PeriodicEvent)
	 */
	@Override
	public boolean updateEventIsOpenState(PeriodicEvent periodicEvent) {
		if(eventManageDao.updateEventIsOpenState(periodicEvent)!=0)return true;
		return false;
	}






	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.EventManageService#LoadOtherEvent(int, int)
	 */
	@Override
	public PageBean LoadOtherEvent(int page, int pageSize) {
		int allRow = eventManageDao.getRowCount(-1, 0, null, null, null, -1,
				-1, "0", -1);
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		if (page > totalPage)
			page--;
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int currentPage = PageBean.countCurrentPage(page);
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(eventManageDao.queryOtherForPage(offset, pageSize, -1, 0,
				null, null, null, -1, -1, "0"));
		pageBean.init();
		return pageBean;
	}
	

}
