/**
 * 
 */
package com.xnjd.hynm.service;


import com.xnjd.hynm.model.Paigu;

/**
 * @author Administrator
 *
 */
public interface PaiguManageService {
	
	/**
     * 添加排故记录
     * @param user
     * @return 
     */
	  public boolean addPaigu(Paigu paigu);
	    
	    /**
	     * 根据id删除指定的排故纪录
	     * @param id
	     * @return
	     */
	    public boolean deletePaigu(int id);
	    /**
	     * 更新排故记录
	     * @param user
	     * @return
	     */
	    public boolean updatePaigu(Paigu paigu);
	    
	    /**
	     * 根据指定id载入排故实体
	     * @param id
	     * @return
	     */
	    public Paigu loadPaiguById(int id);

		/**
		 * @param eventID
		 * @return
		 */
		public Paigu loadPaiguByPaiguId(int paiguid);
		
		
		public Paigu loadPaiguByEventId(int eventid);
	    

}
