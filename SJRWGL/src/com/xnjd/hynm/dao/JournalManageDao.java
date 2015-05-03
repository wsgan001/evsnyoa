/**
 * 
 */
package com.xnjd.hynm.dao;


import java.util.List;

import com.xnjd.hynm.model.Journal;

/**
 * @author Administrator
 *
 */
public interface JournalManageDao {
	public int getRowCount(int event_id,String event_customer, String event_product,String event_type, String event_engineer,String rzinfo) ;
	public int getRowCount(String eventid) ;
	public List<Object> queryForPage(int offset,int length,String eventid);
	/**
	 * @param journal
	 * @return 更新日志信息
	 */
	public boolean updateJournal(Journal journal);

	/**
	 * @param journal
	 * @return
	 * 添加日志信息
	 */
	public boolean addJournal(Journal journal);

	/**
	 * @param journal
	 * @return
	 * 删除指定日志
	 */
	public boolean deleteJournal(Journal journal);

	/**
	 * @param id
	 * @return
	 * 根据id加载指定的日志实体
	 */
	public Journal loadJournalByID(int id);
	
	public boolean deleteJournalByID(int id);



	/**
	 * @param journal
	 * @return
	 */
	

}
