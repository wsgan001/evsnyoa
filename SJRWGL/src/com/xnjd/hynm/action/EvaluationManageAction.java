/**
 * 
 */
package com.xnjd.hynm.action;


import java.util.List;


import org.jfree.chart.JFreeChart;

import com.xnjd.hynm.util.JfreeChartDraw;
import com.opensymphony.xwork2.ActionSupport;
import com.xnjd.hynm.model.Evaluation;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.service.EvaluationManageService;
import com.xnjd.hynm.service.EventManageService;
/**
 * @author Administrator
 *
 */
public class EvaluationManageAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;


	/**
	 * @return the chart
	 */
	public JFreeChart getChart() {
		return chart;
	}


	/**
	 * @param chart the chart to set
	 */
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}


	/**
	 * 
	 */
	
	private String actionMsg;
	

    
	private String wtjj;
	private String sfyq;
	private String jsnd;
	private String wdzl;
	
	private int fraction;
	private int eventID;
	private int EvaluationID;
	
	private EventManageService eventManageService;
	
	private EvaluationManageService evaluationManageService;
	/**


	/*private Evaluation evaluation= new Evaluation();
	*//**
	 * @return the evaluation
	 *//*
	public Evaluation getEvaluation() {
		return evaluation;
	}


	*//**
	 * @param evaluation the evaluation to set
	 *//*
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}*/


	/**
	 * @param evaluationManageService the evaluationManageService to set
	 */
	public void setEvaluationManageService(
			EvaluationManageService evaluationManageService) {
		this.evaluationManageService = evaluationManageService;
	}


	/**
	 * @param eventManageService the eventManageService to set
	 */
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	
	
	
	/**
	 * @return the actionMsg
	 */
	public String getActionMsg() {
		return actionMsg;
	}
	/**
	 * @param actionMsg the actionMsg to set
	 */
	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}
	

	
	





	


	/**
	 * @return the wtjj
	 */
	public String getWtjj() {
		return wtjj;
	}




	/**
	 * @param wtjj the wtjj to set
	 */
	public void setWtjj(String wtjj) {
		this.wtjj = wtjj;
	}




	/**
	 * @return the sfyq
	 */
	public String getSfyq() {
		return sfyq;
	}




	/**
	 * @param sfyq the sfyq to set
	 */
	public void setSfyq(String sfyq) {
		this.sfyq = sfyq;
	}




	/**
	 * @return the jsnd
	 */
	public String getJsnd() {
		return jsnd;
	}




	/**
	 * @param jsnd the jsnd to set
	 */
	public void setJsnd(String jsnd) {
		this.jsnd = jsnd;
	}




	/**
	 * @return the wdzl
	 */
	public String getWdzl() {
		return wdzl;
	}




	/**
	 * @param wdzl the wdzl to set
	 */
	public void setWdzl(String wdzl) {
		this.wdzl = wdzl;
	}




	/**
	 * @return the fraction
	 */
	public int getFraction() {
		return fraction;
	}
	/**
	 * @param fraction the fraction to set
	 */
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	/**
	 * @return the eventID
	 */
	public int getEventID() {
		return eventID;
	}
	/**
	 * @param eventID the eventID to set
	 */
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
	
	/**
	 * @return the evaluationID
	 */
	public int getEvaluationID() {
		return EvaluationID;
	}


	/**
	 * @param evaluationID the evaluationID to set
	 */
	public void setEvaluationID(int evaluationID) {
		EvaluationID = evaluationID;
	}

	
    public String addEvaluation() {
        
        int wt = 0,yq=0,zl=0,nd=0;//设置分数初始值
      //设置事件问题解决值
       //System.out.println(wtjj);
        Evaluation evaluation= new Evaluation();
		if("1".equals(wtjj)){
			
			evaluation.setFinlInfo(10);
			 wt=10;
		   }
			else if("2".equals(wtjj)){
				evaluation.setFinlInfo(7);
				wt=7;
				}
			else if("3".equals(wtjj)){
				evaluation.setFinlInfo(5);
				wt=5;
			}
		 //设置是否延期值
	
	     if("1".equals(sfyq)){
	    	evaluation.setLateTime(10);
	    	yq=10;
	       }
	       else if("2".equals(sfyq)){
	    	evaluation.setLateTime(5);
	    	yq=5;
	       }
	     //设置事件难度值
	  
	      if("1".equals(jsnd)){
	    	  evaluation.setEventNd(10);
	    	  nd=10;
	      }
	      else if("2".equals(jsnd)){
	    	  evaluation.setEventNd(7);
	    	  nd=7;
	      }
	      else if("3".equals(jsnd)){
	    	  evaluation.setEventNd(5);
	    	  nd=5;
	      }
	      if("1".equals(wdzl)){
	    	 evaluation.setFileInfo(10);
	    	 zl=10;
	      }
	      else if("2".equals(wdzl)){
	    	  evaluation.setFileInfo(7);
	    	  zl=7;
	      }
	      else if("3".equals(wdzl)){
	    	  evaluation.setFileInfo(5);
	    	  zl=5;
	      }
     
	    int sum=wt+yq+zl+nd;
	    evaluation.setFraction(sum);
		Event tempevent = eventManageService.loadEventByID(eventID);
		evaluation.setEvent(tempevent);
		//Account account=(Account)ActionContext.getContext().getSession().get("admin");
        //evaluation.setPeploeid(account);		
	    if(evaluationManageService.addEvaluation(evaluation))actionMsg="评价添加成功！";
				else actionMsg="评价添加失败！";
		 //actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
			return "addEvaluation";	
	}
	
    
    public String avgEvaluation(){
    	
		List<Evaluation> cList=evaluationManageService.avgEvaluation(eventID);
		String clsj = "绩效值";
		this.setChart(JfreeChartDraw.createBarChart(clsj, cList));
	    return "SUCCESS";
		
       }
    
    public Event loadPaiguByEventId(int eventID) {
		// TODO Auto-generated method stub
		return eventManageService.loadEventByID(eventID);
	}

}
