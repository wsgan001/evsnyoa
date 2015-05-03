/**
 * 
 */
package com.xnjd.hynm.service.impl;

import com.xnjd.hynm.dao.JournalManageDao;

import com.xnjd.hynm.model.Journal;
import com.xnjd.hynm.service.JournalManageService;
import com.xnjd.hynm.util.PageBean;

/**
 * @author Administrator
 *
 */
public  class JournalManageServiceImpl implements JournalManageService {

	private  JournalManageDao journalManageDao;
	/**
	 * @return the journalManageDao
	 */
	public JournalManageDao getJournalManageDao() {
		return journalManageDao;
	}
	/**
	 * @param journalManageDao the journalManageDao to set
	 */
	public void setJournalManageDao(JournalManageDao journalManageDao) {
		this.journalManageDao = journalManageDao;
	}
	
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.JournalManageService#queryForPage(int, int, java.lang.String[])
	 */
	@Override
	public PageBean queryForPage(int pageSize, int page,String eventid) {
		int allRow=journalManageDao.getRowCount(eventid);
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
        pageBean.setList(journalManageDao.queryForPage(offset, pageSize, eventid));
        pageBean.init();
        return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.JournalManageService#addJournal(com.xnjd.hynm.model.Journal)
	 */
	@Override
	public boolean addJournal(Journal journal) {
		// TODO Auto-generated method stub
		return journalManageDao.addJournal(journal);
	}

	
	@Override
	public boolean deleteJournal(int id) {
		return journalManageDao.deleteJournalByID(id);
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.JournalManageService#updateJournal(com.xnjd.hynm.model.Journal)
	 */
	@Override
	public boolean updateJournal(Journal journal) {
		
		return journalManageDao.updateJournal(journal);
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.JournalManageService#loadwJournalById(int)
	 */
	@Override
	public Journal loadJournalById(int id) {
		// TODO Auto-generated method stub
		return journalManageDao.loadJournalByID(id);
	}

}
