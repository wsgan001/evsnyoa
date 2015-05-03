package com.xnjd.hynm.model;

import java.util.Date;

/**
 * Journal entity. @author MyEclipse Persistence Tools
 */

public class Journal implements java.io.Serializable {

	// Fields

	private Integer id;
	private Event event;
	private String rzinfo;
	private String filePath;
	private Boolean fileState;
	private String fileName;
	private Date rzdate;

	// Constructors

	/** default constructor */
	public Journal() {
	}

	/** minimal constructor */
	public Journal(Event event) {
		this.event = event;
	}

	/** full constructor */
	public Journal(Event event, String rzinfo, Date rzdate) {
		this.event = event;
		this.rzinfo = rzinfo;
		this.rzdate = rzdate;
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

	public String getRzinfo() {
		return this.rzinfo;
	}

	public void setRzinfo(String rzinfo) {
		this.rzinfo = rzinfo;
	}
    
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
	public Boolean getFileState() {
		return fileState;
	}

	
	public void setFileState(Boolean fileState) {
		this.fileState = fileState;
	}

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getRzdate() {
		return this.rzdate;
	}

	public void setRzdate(Date rzdate) {
		this.rzdate = rzdate;
	}

}