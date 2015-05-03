/**
 * 
 */
package com.xnjd.hynm.service;




import com.xnjd.hynm.model.Journal;
import com.xnjd.hynm.util.PageBean;

/**
 * @author Administrator
 *
 */
public interface JournalManageService {
	
	/**
     * 分页查询
     * @param hql 查询的条件
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return
     */
	public PageBean queryForPage(int offset,int length,String eventid);
    
	
	/**
     * 添加日志
     * @param user
     * @return 
     */
    public boolean addJournal(Journal journal);
    
    /**
     * 根据id删除指定的日志纪录
     * @param id
     * @return
     */
    public boolean deleteJournal(int id);
    /**
     * 更新日志
     * @param user
     * @return
     */
    public boolean updateJournal(Journal journal);
    
    /**
     * 根据指定id载入日志实体
     * @param id
     * @return
     */
    public Journal loadJournalById(int id);
    
	


}
