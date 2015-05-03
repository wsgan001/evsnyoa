/**
 * 
 */
package com.xnjd.hynm.dao.impl;

import java.util.List;

import com.xnjd.hynm.dao.DataAccessUtil;

import com.xnjd.hynm.dao.JournalManageDao;


import com.xnjd.hynm.model.Customer;
import com.xnjd.hynm.model.Journal;

/**
 * @author Administrator
 *
 */
public class JournalManageDaoImpl implements JournalManageDao {
	private DataAccessUtil dataAccessUtil;
	/**
	 * @param dataAccessUtil the dataAccessUtil to set
	 */
	public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
		this.dataAccessUtil = dataAccessUtil;
	}
	
	@Override
	public int getRowCount(int event_id, String event_customer,
			String event_product, String event_type, String event_engineer,
			String rzinfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	@Override
	public Journal loadJournalByID(int id) {
		// TODO Auto-generated method stub
		return (Journal) dataAccessUtil.findById(Journal.class,id);
	}
	
	@Override
	public boolean deleteJournalByID(int id) {
		boolean status = false;
		try{
			dataAccessUtil.deleteById(Journal.class, id);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}
	
	@Override
	public boolean updateJournal(Journal journal) {
		
		try
		{
			dataAccessUtil.update(journal);
		} 
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.JournalManageDao#addJournal(com.xnjd.hynm.model.Journal)
	 */
	@Override
	public boolean addJournal(Journal journal) {
		// TODO Auto-generated method stub
		try
		{
			dataAccessUtil.create(journal);
		} 
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.JournalManageDao#deleteJournal(com.xnjd.hynm.model.Journal)
	 */
	@Override
	public boolean deleteJournal(Journal journal) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.JournalManageDao#queryForPage(int, int, java.lang.String)
	 */
	@Override
	public List<Object> queryForPage(int offset, int length, String eventid) {
		String hql = "from Journal where event.id="+eventid;        //查询语句
	       return dataAccessUtil.queryForPage(hql,offset, length);  
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.JournalManageDao#getRowCount(int)
	 */
	@Override
	public int getRowCount(String event_id) {
		String hql = "from Journal where event.id="+event_id;        //查询语句
		return dataAccessUtil.getAllRowCount(hql);
	}


}
