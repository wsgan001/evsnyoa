/**
 * 
 */
package com.xnjd.hynm.dao.impl;

import java.util.List;

import com.xnjd.hynm.dao.EvaluationManageDao;
import com.xnjd.hynm.model.Evaluation;
import com.xnjd.hynm.dao.DataAccessUtil;
/**
 * @author Administrator
 *
 */
public class EvaluationManageDaoImpl implements EvaluationManageDao {
	
	private DataAccessUtil dataAccessUtil;
	/**
	 * @param dataAccessUtil the dataAccessUtil to set
	 */
	public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
		this.dataAccessUtil = dataAccessUtil;
	}
	
	public boolean addEvaluation(Evaluation evaluation){
		try
		{
			dataAccessUtil.create(evaluation);
		} 
		catch (Exception e)
		{
			return false;
		}
		return true;
		
	}
		
/*@SuppressWarnings("unchecked")
public List<Evaluation> avgEvaluation(int event_id){
		
		String hql="select avg(e.lateTime) as lateTime,avg(e.finlInfo) as finlInfo,avg(e.fileInfo) as fileInfo,avg(e.eventNd) as eventNd form Evaluation as e where e.eventid=?";
		
		List<Evaluation> list = dataAccessUtil.find(hql,event_id);
		if(list.isEmpty())return null;
		else return list;
			
		
	}*/
	
public List<Evaluation> avgEvaluation(int event_id){
		
		String hql="select avg(late_time) as lateTime,avg(finl_info) as finlInfo,avg(file_info) as fileInfo,avg(event_nd) as eventNd from evaluation  where eventid="+event_id;
		
		return dataAccessUtil.queryChat(hql);
			
		
	}
	

}
