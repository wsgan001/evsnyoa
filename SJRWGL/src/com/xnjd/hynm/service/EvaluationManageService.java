/**
 * 
 */
package com.xnjd.hynm.service;

import java.util.List;

import com.xnjd.hynm.model.Evaluation;

/**
 * @author Administrator
 *
 */
public interface EvaluationManageService {
	
	
	public boolean addEvaluation(Evaluation evaluation);
    
	public List<Evaluation> avgEvaluation(int event_id);
}
