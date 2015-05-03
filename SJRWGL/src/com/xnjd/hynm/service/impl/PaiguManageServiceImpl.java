/**
 * 
 */
package com.xnjd.hynm.service.impl;

/**
 * @author Administrator
 *
 */

import com.xnjd.hynm.dao.PaiguManageDao;

import com.xnjd.hynm.model.Paigu;
import com.xnjd.hynm.service.PaiguManageService;
public class PaiguManageServiceImpl implements PaiguManageService {
	
	private PaiguManageDao paiguManageDao;

	
	/**
	 * @return the paiguManageDao
	 */
	public PaiguManageDao getPaiguManageDao() {
		return paiguManageDao;
	}

	/**
	 * @param paiguManageDao the paiguManageDao to set
	 */
	public void setPaiguManageDao(PaiguManageDao paiguManageDao) {
		this.paiguManageDao = paiguManageDao;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.PaiguManageService#addPaigu(com.xnjd.hynm.model.Paigu)
	 */
	@Override
	public boolean addPaigu(Paigu paigu) {
		// TODO Auto-generated method stub
		return paiguManageDao.addPaigu(paigu);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.PaiguManageService#deletePaigu(int)
	 */
	@Override
	public boolean deletePaigu(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.PaiguManageService#updatePaigu(com.xnjd.hynm.model.Paigu)
	 */
	@Override
	public boolean updatePaigu(Paigu paigu) {
		return paiguManageDao.updatePaigu(paigu);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.PaiguManageService#loadPaiguById(int)
	 */
	@Override
	public Paigu loadPaiguById(int id) {
		// TODO Auto-generated method stub
		return paiguManageDao.loadPaiguByID(id);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.PaiguManageService#loadPaiguByPaiguId(int)
	 */
	@Override
	public Paigu loadPaiguByPaiguId(int paiguid) {
		return paiguManageDao.loadPaiguByPaiguID(paiguid);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.PaiguManageService#loadPaiguByEventId(int)
	 */
	@Override
	public Paigu loadPaiguByEventId(int eventid) {
		// TODO Auto-generated method stub
		return paiguManageDao.loadPaiguByEventID(eventid);
	}

}
