/**
 * 
 */
package com.xnjd.hynm.dao;

import java.util.List;

import com.xnjd.hynm.model.Evaluation;


/**
 * @author Administrator
 *
 */
public interface EvaluationManageDao {
	     
	
	
	/**
	 * @param evaluation
	 * @return
	 * 添加评价信息
	 */
	public boolean addEvaluation(Evaluation evaluation);
	
	

	/**
	 * @param evaluation
	 * @return
	 * 统计所有评价信息
	 */
	public List<Evaluation> avgEvaluation(int event_id);
	
	
	
	
	
	

}
