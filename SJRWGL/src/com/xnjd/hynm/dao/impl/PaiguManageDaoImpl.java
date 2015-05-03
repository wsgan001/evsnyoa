/**
 * 
 */
package com.xnjd.hynm.dao.impl;

/**
 * @author Administrator
 *
 */
//import java.util.List;

import com.xnjd.hynm.dao.DataAccessUtil;

import com.xnjd.hynm.dao.PaiguManageDao;

import com.xnjd.hynm.model.Paigu;

public class PaiguManageDaoImpl implements PaiguManageDao{
	
	private DataAccessUtil dataAccessUtil;
	
	

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#updatePaigu(com.xnjd.hynm.model.Paigu)
	 */
	@Override
	public boolean updatePaigu(Paigu paigu) {
		boolean status = false;
		try{
			dataAccessUtil.update(paigu);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#addPaigu(com.xnjd.hynm.model.Paigu)
	 */
	@Override
	public boolean addPaigu(Paigu paigu) {
		try
		{
			dataAccessUtil.create(paigu);
		} 
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#deleteJournal(com.xnjd.hynm.model.Paigu)
	 */
	@Override
	public boolean deleteJournal(Paigu journal) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#loadPaiguByID(int)
	 */
	@Override
	public Paigu loadPaiguByID(int id) {
		return (Paigu) dataAccessUtil.findById(Paigu.class,id);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#deletePaiguByID(int)
	 */
	@Override
	public boolean deletePaiguByID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the dataAccessUtil
	 */
	public DataAccessUtil getDataAccessUtil() {
		return dataAccessUtil;
	}

	/**
	 * @param dataAccessUtil the dataAccessUtil to set
	 */
	public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
		this.dataAccessUtil = dataAccessUtil;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#loadPaiguByPaiguID(int)
	 */
	@Override
	public Paigu loadPaiguByPaiguID(int paiguid) {
		return (Paigu) dataAccessUtil.findByEventId(Paigu.class,paiguid);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#loadPaiguByEventID(int)
	 */
	@Override
	public Paigu loadPaiguByEventID(int eventid) {
		String hql = "from Paigu where event.id="+eventid; 
		 return (Paigu)dataAccessUtil.executeHql(hql);
	}

	
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.PaiguManageDao#loadPaiguByPaiguID(int)
	 */
	
	
	/*@Override
	public Paigu loadPaiguByPaiguID(int paiguid) {
		String hql = "from Paigu where event.id="+paiguid;        //查询语句
	       return (Paigu)dataAccessUtil.findHql(hql);
	}*/
	
   
}
