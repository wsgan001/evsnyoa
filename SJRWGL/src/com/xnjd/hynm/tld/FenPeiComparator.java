package com.xnjd.hynm.tld;

import java.util.Comparator;

import com.xnjd.hynm.model.Event;
/**
 * 对事件排序规则进行设定
 * 当前的规则是三个状态 满足条件多的排在前面
 * @author my
 *
 */

public class FenPeiComparator implements Comparator<Event> {

	public int compare(Event e1, Event e2) {
		int x = 0,y = 0;
		
		if(e1.getDispatchState())x++;
		if(e1.getApplyState())x++;
		if(e1.getCompleteState())x++;
		if(e2.getDispatchState())y++;
		if(e2.getApplyState())y++;
		if(e2.getCompleteState())y++;
		return y-x;
		
	}
}
