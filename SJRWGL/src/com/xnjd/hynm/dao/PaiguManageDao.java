/**
 * 
 */
package com.xnjd.hynm.dao;

/**
 * @author Administrator
 *
 */
//import java.util.List;



import com.xnjd.hynm.model.Paigu;
public interface PaiguManageDao {
	public boolean updatePaigu(Paigu paigu);
	

	/**
	 * @param journal
	 * @return
	 * 添加日志信息
	 */
	public  boolean addPaigu(Paigu paigu);

	/**
	 * @param journal
	 * @return
	 * 删除指定日志
	 */
	public boolean deleteJournal(Paigu journal);

	/**
	 * @param id
	 * @return
	 * 根据id加载指定的日志实体
	 */
	public Paigu loadPaiguByID(int id);
	
	public boolean deletePaiguByID(int id);
	
	public Paigu loadPaiguByPaiguID(int paiguid);

	//public Paigu loadPaiguByEvent(Event event);
	public Paigu  loadPaiguByEventID(int eventid);

	
}
