/**
 * 
 */
package com.xnjd.hynm.dao;

import java.util.List;

import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.model.PeriodicEvent;
/**
 * @author STONE
 *
 */
public interface EventManageDao {
	
	/**
	 * @param account_id
	 * @param product_name
	 * @param customer_name
	 * @param event_type
	 * @param complete_state
	 * @param dispatch_state
	 * @param pass_state
	 * @return
	 * 获取记录总数
	 */
	public int getRowCount(int account_id,int account_type,
			String product_name, String customer_name, String event_type,
			int complete_state, int dispatch_state, String pass_state,int handle_state);
	public int getRowCount(int account_id,int account_type,
			String enginner_id, String startDate,String endDate, String event_type,
			int complete_state, int dispatch_state, String pass_state,int handle_state);
	
	public int getRowCount(int account_id,int account_type,int nowstate,
			String product_name, String customer_name, String event_type,
			int complete_state, int dispatch_state, String pass_state,int handle_state);
	//新增执行人对事件的处理
	public int getRowCount(int account_id,int account_type, String event_type,
			int complete_state, int dispatch_state, String pass_state,int handle_state);
	
	public List<Object> queryOtherForPage(int offset, int length, int account_id,int account_type,
			String product_name, String customer_name, String event_type,
			int complete_state, int dispatch_state, String pass_state);
	
	/**
	 * @param offset
	 * @param length
	 * @param account_id
	 * @param product_name
	 * @param customer_name
	 * @param event_type
	 * @param complete_state
	 * @param dispatch_state
	 * @param pass_state
	 * @return
	 *  * 分页查询
	 */

	public List<Object> queryForPage(int offset, int length, int account_id,int account_type,String product_name,String customer_name,String event_type,int complete_state,int dispatch_state,String pass_state );
	public List<Object> queryForPage(int offset, int length,int account_id,int account_type,
			String enginner_id, String startDate,String endDate, String event_type,
			int complete_state, int dispatch_state, String pass_state,int handle_state);
	public List<Object> queryForPage(int offset, int length, int account_id,int nowstate,int account_type,String product_name,String customer_name,String event_type,int complete_state,int dispatch_state,String pass_state );
	
	public List<Object> queryForPage(int offset, int length, int account_id,int account_type,String event_type,int complete_state,int dispatch_state,String pass_state );
	
	//增加用于导出execl表格内容的查询接口
	public List<Object> queryForPage(int account_id,int account_type,
			String enginner_id, String startDate,String endDate, String event_type,
			int complete_state, int dispatch_state, String pass_state,int handle_state);
	
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
	
	public boolean deleteEventByID(int id);
	
	public List<Event> getNeedPostEvent(int id);
	
	public List<Event> getNeedPostEvent();
	
	/**
     * 查找库房管理人员
     */
    public List<Account> findInventoryKeeper();
    /**
     * 查找审核人
     */
    public List<Account> findAuditPerson();
    
    /**
     * 查询处理事件人的邮箱信息
     */
    public List<Account> findCLSJPerson(int eventID);
    /**
     * 查询事件审核者 但不为邮件执行者+库管的邮箱信息
     */
    public List<Account> findSHManWithKGMan(int KGID,int eventID);
    /**
     * 插入周期性事件
     */
    public int addPeriodicEvent(PeriodicEvent periodicEvent);
    /**
     * 查询周期性事件并分页
     * @param hql
     * @param offset
     * @param length
     * @return
     */
    public List<Object> queryPeriocicEventForPage(String hql,int offset, int length);
    
    public int getPeriodicEventTotalRow(String hql); 
    /**
     * 根据Id查询周期性事件
     * @return PeriodicEvent
     */
    public PeriodicEvent loadPeriodicEventById(int periodicEventId);
    /**
     * 根据id删除周期性事件
     * @param perriodicEventId
     * @return
     */
    public Boolean deletePeriodicEventById(int perriodicEventId);
    /**
     * 更新周期性事件
     */
    public Boolean updatePeriodicEvent(PeriodicEvent periodicEvent);
    /**
     * 更新周期性事件状态
     */
    public int updateEventIsOpenState(PeriodicEvent periodicEvent);
}
