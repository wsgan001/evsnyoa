package com.xnjd.hynm.model;

/**
 * Evaluation entity. @author MyEclipse Persistence Tools
 */

public class Evaluation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Event event;
	private Integer lateTime;
	private Integer finlInfo;
	private Integer fileInfo;
	private Integer eventNd;
	private Integer fraction;

	// Constructors

	/** default constructor */
	public Evaluation() {
	}

	/** full constructor */
	public Evaluation(Event event, Integer lateTime, Integer finlInfo,
			Integer fileInfo, Integer eventNd, Integer fraction) {
		this.event = event;
		this.lateTime = lateTime;
		this.finlInfo = finlInfo;
		this.fileInfo = fileInfo;
		this.eventNd = eventNd;
		this.fraction = fraction;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getLateTime() {
		return this.lateTime;
	}

	public void setLateTime(Integer lateTime) {
		this.lateTime = lateTime;
	}

	public Integer getFinlInfo() {
		return this.finlInfo;
	}

	public void setFinlInfo(Integer finlInfo) {
		this.finlInfo = finlInfo;
	}

	public Integer getFileInfo() {
		return this.fileInfo;
	}

	public void setFileInfo(Integer fileInfo) {
		this.fileInfo = fileInfo;
	}

	public Integer getEventNd() {
		return this.eventNd;
	}

	public void setEventNd(Integer eventNd) {
		this.eventNd = eventNd;
	}

	public Integer getFraction() {
		return this.fraction;
	}

	public void setFraction(Integer fraction) {
		this.fraction = fraction;
	}

}