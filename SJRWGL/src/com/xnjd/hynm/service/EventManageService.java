/**
 * 
 */
package com.xnjd.hynm.service;

import java.util.List;


import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.model.PeriodicEvent;
import com.xnjd.hynm.util.PageBean;

/**
 * @author STONE
 *
 */
public interface EventManageService {
	
	public List<Event>getNeedPostEvent( int id);
   
	public List<Event>getNeedPostEvent();
	/**
	 * @param page
	 * @param pageSize
	 * @return
	 * 加载全部事件
	 */
	public PageBean LoadAllEvent(int page, int pageSize);
	
	/**
	 * @param page
	 * @param pageSize
	 * @param accountID
	 * @return
	 * 加载某一类用户的全部事件
	 */
	public PageBean LoadAllEvent(int page, int pageSize,int accountID);
	
	public PageBean LoadAllEvent(int page, int pageSize, int accountID,int nowstate); //库房查询
	
	/**
	 * @param page
	 * @param pageSize
	 * @return
	 * 加载全部未分配的事件
	 */
	public PageBean LoadAllUndispatchedEvent(int page, int pageSize);
	/**
	 * @param page
	 * @param pageSize
	 * @param accountID
	 * @return
	 * 加载某一用户的未分配事件
	 */
	public PageBean LoadUndispatchedEvent(int page, int pageSize,int accountID);
	/**
	 * @param page
	 * @param pageSize
	 * @return
	 * 加载全部已分配的事件
	 */
	public PageBean LoadAllDispatchedEvent(int page, int pageSize);
	
	/**
	 * @param page
	 * @param pageSize
	 * @param accountID
	 * @return
	 * 加载某一用户的已分配事件
	 */
	public PageBean LoadDispatchedEvent(int page, int pageSize,int accountID);
	/**
	 * @param page
	 * @param pageSize
	 * @return
	 * 加载所有未完成事件
	 */
	public PageBean LoadAllUncompletedEvent(int page, int pageSize);
	/**
	 * @param page
	 * @param pageSize
	 * @param accountID
	 * @return
	 * 加载某一用户 的未完成事件
	 */
	public PageBean LoadUncompletedEvent(int page, int pageSize,int accountID);
	
    /* 加载全部用于导出的完成事件*/
	public List<?> LoadAllCompletedExport(String enginner_id, String startDate, String endDate);
	
	public List<?>  LoadCompletedExport(int accountID,String enginner_id, String startDate, String endDate);
	
	
	
	/**
	 * @param page
	 * @param pageSize
	 * @return
	 * 加载全部已完成事件
	 */
	public PageBean LoadAllCompletedEvent(int page, int pageSize,String enginner_id, String startDate, String endDate);
	/**
	 * @param page
	 * @param pageSize
	 * @param accountID
	 * @return
	 * 加载某一用户的已完成事件
	 */
	public PageBean LoadCompletedEvent(int page, int pageSize,int accountID,String enginner_id, String startDate, String endDate);
	
	
	/**
	 * @param page
	 * @param pageSize
	 * @return
	 * 加载全部未审核的事件
	 */
	public PageBean LoadAllUnpassedEvent(int page, int pageSize);
	/**
	 * @param page
	 * @param pageSize
	 * @param accountID
	 * @return
	 * 加载某一用户的未审核事件
	 */
	public PageBean LoadUnpassedEvent(int page, int pageSize,int accountID);
	/**
	 * @param page
	 * @param pageSize
	 * @param state 审核结果状态
	 * @return
	 * 加载全部已经审核的事件
	 */
	public PageBean LoadAllPassedEvent(int page, int pageSize,int state);
	/**
	 * @param page
	 * @param pageSize
	 * @param accountID
	 * @param state 审核结果状态
	 * @return
	 * 加载某一用户的已经审核的事件
	 */
	public PageBean LoadPassedEvent(int page, int pageSize,int accountID,int state);
	/**
	 * @param event
	 * @return 更新事件信息
	 */
	public boolean updateEvent(Event event);

	/**
	 * @param event
	 * @return
	 * 添加事件信息
	 */
	public boolean addEvent(Event event);

	/**
	 * @param event
	 * @return
	 * 删除指定事件
	 */
	public boolean deleteEvent(Event event);

	/**
	 * @param id
	 * @return
	 * 根据id加载指定的事件实体
	 */
	public Event loadEventByID(int id);

	/**
	 * @param eventID
	 * @return
	 */
	public boolean deleteEventByID(int id);
	/**
     * 查找库房管理人员
     */
    public String findInventoryKeeper();
    /**
     * 查找审核人
     */
    public String findAuditPerson();
    /**
     * 
     * @param id 事件id
     * @return 返回对应事件名称
     */
    public String loadEventById(int id);
    
    /**
     * 查询处理事件人的邮箱信息
     */
    public String findCLSJPerson(int eventID);
    /**
     * 查询事件审核者 但不为邮件执行者+库管的邮箱信息
     */
    public String findSHManWithKGMan(int KGID,int eventID);
    /**
     * 增加周期性事件
     * @param periodicEvent 周期性事件属性对象
     * @return
     */
    public boolean addPeriodicEvent(PeriodicEvent periodicEvent);
    /**
     * 加载所有周期性事件
     * @param page
     * @param pageSize
     * @return
     */
    public PageBean loadAllPeriodicEvent(int page, int pageSize);
    /**
     * 查询周期性事件关联小事件
     * @return
     */
    public PageBean loadPeriodicEventRelaEvent(int periodicEventId,int page,int pageSize);
    /**
     * 根据Id查询周期性事件
     * @return PeriodicEvent
     */
    public PeriodicEvent loadPeriodicEventById(int periodicEventId);
    /**
     * 根据id删除周期性事件
     * @param periodicEventId
     * @return
     */
    public boolean deletePeriodicEventById(int periodicEventId);
    /**
     * 根据id更新事件
     */
    public boolean updatePeriodicEvent(PeriodicEvent periodicEvent);
    /**
     * 更新周期性事件状态
     */
    public boolean updateEventIsOpenState(PeriodicEvent periodicEvent);

	/**
	 * @param page
	 * @param pAGE_SIZE
	 * @return
	 */
	public PageBean LoadOtherEvent(int page, int pAGE_SIZE);
    
}
