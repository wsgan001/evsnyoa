/**
 * 
 */
package com.xnjd.hynm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author my
 *
 */
public class PeriodicEvent implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String eventType;
	private Date eventStartTime;
	private Date eventEndTime;
	/**
	 * 代表几月.几周，几天
	 */
	private Integer timeInterval;
	/**
	 * 1,代表天,2,代表周3,代表月
	 */
	private Integer intervalType;
	private boolean eventEndMailIsSended;
	private Date createEventDate;
	private boolean eventIsEnd;
	private String eventProduct;
	private String eventCustomer;
	private String eventInfo;
	private String eventEngineer;
	private String visitName;
	//事件是否开始
	private boolean eventIsOpen;
	private Integer accountByFpsjId;
	private Integer accountByShsjId;
	private Integer accountByDjsjId;
	private Integer accountByClsjId;


	/**
	 * @param id
	 * @param eventType
	 * @param eventStartTime
	 * @param eventEndTime
	 * @param timeInterval
	 * @param intervalType
	 * @param eventEndMailIsSended
	 * @param createEventDate
	 * @param eventIsEnd
	 * @param eventProduct
	 * @param eventCustomer
	 * @param eventInfo
	 * @param eventEngineer
	 * @param visitName
	 * @param eventIsOpen
	 * @param accountByFpsjId
	 * @param accountByShsjId
	 * @param accountByDjsjId
	 * @param accountByClsjId
	 */
	public PeriodicEvent(Integer id, String eventType, Date eventStartTime,
			Date eventEndTime, Integer timeInterval, Integer intervalType,
			boolean eventEndMailIsSended, Date createEventDate,
			boolean eventIsEnd, String eventProduct, String eventCustomer,
			String eventInfo, String eventEngineer, String visitName,
			boolean eventIsOpen, Integer accountByFpsjId,
			Integer accountByShsjId, Integer accountByDjsjId,
			Integer accountByClsjId) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.timeInterval = timeInterval;
		this.intervalType = intervalType;
		this.eventEndMailIsSended = eventEndMailIsSended;
		this.createEventDate = createEventDate;
		this.eventIsEnd = eventIsEnd;
		this.eventProduct = eventProduct;
		this.eventCustomer = eventCustomer;
		this.eventInfo = eventInfo;
		this.eventEngineer = eventEngineer;
		this.visitName = visitName;
		this.eventIsOpen = eventIsOpen;
		this.accountByFpsjId = accountByFpsjId;
		this.accountByShsjId = accountByShsjId;
		this.accountByDjsjId = accountByDjsjId;
		this.accountByClsjId = accountByClsjId;
	}



	/**
	 * @return the intervalType
	 */
	public Integer getIntervalType() {
		return intervalType;
	}
	/**
	 * @param intervalType the intervalType to set
	 */
	public void setIntervalType(Integer intervalType) {
		this.intervalType = intervalType;
	}
	/**
	 * @param visitName the visitName to set
	 */
	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}
	/**
	 * @return the eventEngineer
	 */
	public String getEventEngineer() {
		return eventEngineer;
	}
	/**
	 * @param eventEngineer the eventEngineer to set
	 */
	public void setEventEngineer(String eventEngineer) {
		this.eventEngineer = eventEngineer;
	}
	/**
	 * @return the visitName
	 */
	public String getVisitName() {
		return visitName;
	}

	
	
	
	
	/**
	 * @return the eventIsOpen
	 */
	public boolean isEventIsOpen() {
		return eventIsOpen;
	}


	/**
	 * @param eventIsOpen the eventIsOpen to set
	 */
	public void setEventIsOpen(boolean eventIsOpen) {
		this.eventIsOpen = eventIsOpen;
	}


	/**
	 * @return the accountByFpsjId
	 */
	
	public Integer getAccountByFpsjId() {
		return accountByFpsjId;
	}
	/**
	 * @param id
	 * @param createEventDate
	 */
	public PeriodicEvent(Integer id, Date createEventDate) {
		super();
		this.id = id;
		this.createEventDate = createEventDate;
	}
	/**
	 * @param accountByFpsjId the accountByFpsjId to set
	 */
	public void setAccountByFpsjId(Integer accountByFpsjId) {
		this.accountByFpsjId = accountByFpsjId;
	}
	/**
	 * @return the accountByShsjId
	 */
	public Integer getAccountByShsjId() {
		return accountByShsjId;
	}
	/**
	 * @param accountByShsjId the accountByShsjId to set
	 */
	public void setAccountByShsjId(Integer accountByShsjId) {
		this.accountByShsjId = accountByShsjId;
	}
	/**
	 * @return the accountByDjsjId
	 */
	public Integer getAccountByDjsjId() {
		return accountByDjsjId;
	}
	/**
	 * @param accountByDjsjId the accountByDjsjId to set
	 */
	public void setAccountByDjsjId(Integer accountByDjsjId) {
		this.accountByDjsjId = accountByDjsjId;
	}
	/**
	 * @return the accountByClsjId
	 */
	public Integer getAccountByClsjId() {
		return accountByClsjId;
	}
	/**
	 * @param accountByClsjId the accountByClsjId to set
	 */
	public void setAccountByClsjId(Integer accountByClsjId) {
		this.accountByClsjId = accountByClsjId;
	}

	
	
	
/*	private Set events = new HashSet(0);*/
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 
	 */
	public PeriodicEvent() {
		super();
	}

	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	
	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	/**
	 * @return the timeInterval
	 */
	public Integer getTimeInterval() {
		return timeInterval;
	}
	/**
	 * @param timeInterval the timeInterval to set
	 */
	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PeriodicEvent [id=" + id + ", eventType=" + eventType
				+ ", eventStartTime=" + eventStartTime + ", eventEndTime="
				+ eventEndTime + ", timeInterval=" + timeInterval
				+ ", intervalType=" + intervalType + ", eventEndMailIsSended="
				+ eventEndMailIsSended + ", createEventDate=" + createEventDate
				+ ", eventIsEnd=" + eventIsEnd + ", eventProduct="
				+ eventProduct + ", eventCustomer=" + eventCustomer
				+ ", eventInfo=" + eventInfo + ", eventEngineer="
				+ eventEngineer + ", visitName=" + visitName + ", eventIsOpen="
				+ eventIsOpen + ", accountByFpsjId=" + accountByFpsjId
				+ ", accountByShsjId=" + accountByShsjId + ", accountByDjsjId="
				+ accountByDjsjId + ", accountByClsjId=" + accountByClsjId
				+ "]";
	}
	/**
	 * @return the eventStartTime
	 */
	public Date getEventStartTime() {
		return eventStartTime;
	}
	/**
	 * @param eventStartTime the eventStartTime to set
	 */
	public void setEventStartTime(Date eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	/**
	 * @return the eventEndTime
	 */
	public Date getEventEndTime() {
		return eventEndTime;
	}
	/**
	 * @param eventEndTime the eventEndTime to set
	 */
	public void setEventEndTime(Date eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	/**
	 * @return the eventEndMailIsSended
	 */
	public boolean isEventEndMailIsSended() {
		return eventEndMailIsSended;
	}
	/**
	 * @param eventEndMailIsSended the eventEndMailIsSended to set
	 */
	public void setEventEndMailIsSended(boolean eventEndMailIsSended) {
		this.eventEndMailIsSended = eventEndMailIsSended;
	}
	/**
	 * @return the createEventDate
	 */
	public Date getCreateEventDate() {
		return createEventDate;
	}
	/**
	 * @param createEventDate the createEventDate to set
	 */
	public void setCreateEventDate(Date createEventDate) {
		this.createEventDate = createEventDate;
	}
	/**
	 * @return the eventIsEnd
	 */
	public boolean isEventIsEnd() {
		return eventIsEnd;
	}

	/**
	 * @return the eventProduct
	 */
	public String getEventProduct() {
		return eventProduct;
	}
	/**
	 * @param eventProduct the eventProduct to set
	 */
	public void setEventProduct(String eventProduct) {
		this.eventProduct = eventProduct;
	}
	/**
	 * @return the eventCustomer
	 */
	public String getEventCustomer() {
		return eventCustomer;
	}
	/**
	 * @param eventCustomer the eventCustomer to set
	 */
	public void setEventCustomer(String eventCustomer) {
		this.eventCustomer = eventCustomer;
	}
	/**
	 * @return the eventInfo
	 */
	public String getEventInfo() {
		return eventInfo;
	}
	/**
	 * @param eventInfo the eventInfo to set
	 */
	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}
	/**
	 * @param eventIsEnd the eventIsEnd to set
	 */
	public void setEventIsEnd(boolean eventIsEnd) {
		this.eventIsEnd = eventIsEnd;
	}
	

}
