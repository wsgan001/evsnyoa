package com.xnjd.hynm.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Event entity. @author MyEclipse Persistence Tools
 */

public class Event implements java.io.Serializable {

	// Fields

	private Integer id;
	private Account accountByFpsjId;
	private Account accountByShsjId;
	private Account accountByDjsjId;
	private Account accountByClsjId;
	private Date eventDate;
	private String eventType;
	private String eventInfo;
	private String eventCustomer;
	private String eventProduct;
	private String eventEngineer;
	private Date planTime1;
	private Date planTime2;
	private Date planTime3;
	private Boolean completeState;
	private Date completeTime;
	private String filePath;
	private Boolean fileState;
	private String fileName;
	private String visitName;
	private Date visitDate;
	private String visitInfo;
	private Double eventEffect;
	private Boolean dispatchState;
	private Boolean handleState;
	private String passState;
	private Boolean applyState;
	private Boolean clsjLate;
	private String clsjLatereason;
	private String clsjLateplan;
	private String clsjLatehand;
	private Boolean emailfpState;
	private Boolean emailyqState;
	private Integer nowstate;
	private Date  dgdate;

	private Integer periodicEventId;
	
	private String reviewAdvice;
	private Set journals = new HashSet(0);
	//private Set paigus = new HashSet(0);
	private Set evaluations = new HashSet(0);
	private Paigu paigu;
	private Set devices = new HashSet(0);

	// Constructors

	/** default constructor */
	public Event() {
	}

	/** minimal constructor */
	public Event(Account accountByDjsjId) {
		this.accountByDjsjId = accountByDjsjId;
	}

	
	/**
	 * @param eventDate
	 * @param eventType
	 * @param eventCustomer
	 * @param eventProduct
	 * @param eventEngineer
	 * @param planTime1
	 * @param visitName
	 */
	public Event(Integer id, String eventType,String eventCustomer,String eventProduct,String eventEngineer,String visitName, Date eventDate,Date planTime1) {
		super();
		this.id = id;
		this.eventDate = eventDate;
		this.eventType = eventType;
		this.eventCustomer = eventCustomer;
		this.eventProduct = eventProduct;
		this.eventEngineer = eventEngineer;
		this.planTime1 = planTime1;
		this.visitName = visitName;
	}

	




	/**
	 * full Constructor
	 */
	
	




	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id
	 * @param accountByFpsjId
	 * @param accountByShsjId
	 * @param accountByDjsjId
	 * @param accountByClsjId
	 * @param eventDate
	 * @param eventType
	 * @param eventInfo
	 * @param eventCustomer
	 * @param eventProduct
	 * @param eventEngineer
	 * @param planTime1
	 * @param planTime2
	 * @param planTime3
	 * @param completeState
	 * @param completeTime
	 * @param filePath
	 * @param fileState
	 * @param fileName
	 * @param visitName
	 * @param visitDate
	 * @param visitInfo
	 * @param eventEffect
	 * @param dispatchState
	 * @param handleState
	 * @param passState
	 * @param applyState
	 * @param clsjLate
	 * @param clsjLatereason
	 * @param clsjLateplan
	 * @param clsjLatehand
	 * @param emailfpState
	 * @param emailyqState
	 * @param nowstate
	 * @param dgdate
	 * @param periodicEventId
	 * @param reviewAdvice
	 * @param journals
	 * @param evaluations
	 * @param paigu
	 * @param devices
	 */
	public Event(Integer id, Account accountByFpsjId, Account accountByShsjId,
			Account accountByDjsjId, Account accountByClsjId, Date eventDate,
			String eventType, String eventInfo, String eventCustomer,
			String eventProduct, String eventEngineer, Date planTime1,
			Date planTime2, Date planTime3, Boolean completeState,
			Date completeTime, String filePath, Boolean fileState,
			String fileName, String visitName, Date visitDate,
			String visitInfo, Double eventEffect, Boolean dispatchState,
			Boolean handleState, String passState, Boolean applyState,
			Boolean clsjLate, String clsjLatereason, String clsjLateplan,
			String clsjLatehand, Boolean emailfpState, Boolean emailyqState,
			Integer nowstate, Date dgdate, Integer periodicEventId,
			String reviewAdvice, Set journals, Set evaluations, Paigu paigu,
			Set devices) {
		super();
		this.id = id;
		this.accountByFpsjId = accountByFpsjId;
		this.accountByShsjId = accountByShsjId;
		this.accountByDjsjId = accountByDjsjId;
		this.accountByClsjId = accountByClsjId;
		this.eventDate = eventDate;
		this.eventType = eventType;
		this.eventInfo = eventInfo;
		this.eventCustomer = eventCustomer;
		this.eventProduct = eventProduct;
		this.eventEngineer = eventEngineer;
		this.planTime1 = planTime1;
		this.planTime2 = planTime2;
		this.planTime3 = planTime3;
		this.completeState = completeState;
		this.completeTime = completeTime;
		this.filePath = filePath;
		this.fileState = fileState;
		this.fileName = fileName;
		this.visitName = visitName;
		this.visitDate = visitDate;
		this.visitInfo = visitInfo;
		this.eventEffect = eventEffect;
		this.dispatchState = dispatchState;
		this.handleState = handleState;
		this.passState = passState;
		this.applyState = applyState;
		this.clsjLate = clsjLate;
		this.clsjLatereason = clsjLatereason;
		this.clsjLateplan = clsjLateplan;
		this.clsjLatehand = clsjLatehand;
		this.emailfpState = emailfpState;
		this.emailyqState = emailyqState;
		this.nowstate = nowstate;
		this.dgdate = dgdate;
		this.periodicEventId = periodicEventId;
		this.reviewAdvice = reviewAdvice;
		this.journals = journals;
		this.evaluations = evaluations;
		this.paigu = paigu;
		this.devices = devices;
	}

	/**
	 * @return the periodicEventId
	 */
	public Integer getPeriodicEventId() {
		return periodicEventId;
	}

	/**
	 * @param periodicEventId the periodicEventId to set
	 */
	public void setPeriodicEventId(Integer periodicEventId) {
		this.periodicEventId = periodicEventId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccountByFpsjId() {
		return this.accountByFpsjId;
	}

	public void setAccountByFpsjId(Account accountByFpsjId) {
		this.accountByFpsjId = accountByFpsjId;
	}

	public Account getAccountByShsjId() {
		return this.accountByShsjId;
	}

	public void setAccountByShsjId(Account accountByShsjId) {
		this.accountByShsjId = accountByShsjId;
	}

	public Account getAccountByDjsjId() {
		return this.accountByDjsjId;
	}

	public void setAccountByDjsjId(Account accountByDjsjId) {
		this.accountByDjsjId = accountByDjsjId;
	}

	public Account getAccountByClsjId() {
		return this.accountByClsjId;
	}

	public void setAccountByClsjId(Account accountByClsjId) {
		this.accountByClsjId = accountByClsjId;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventInfo() {
		return this.eventInfo;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}

	public String getEventCustomer() {
		return this.eventCustomer;
	}

	public void setEventCustomer(String eventCustomer) {
		this.eventCustomer = eventCustomer;
	}

	public String getEventProduct() {
		return this.eventProduct;
	}

	public void setEventProduct(String eventProduct) {
		this.eventProduct = eventProduct;
	}

	public String getEventEngineer() {
		return this.eventEngineer;
	}

	public void setEventEngineer(String eventEngineer) {
		this.eventEngineer = eventEngineer;
	}

	public Date getPlanTime1() {
		return this.planTime1;
	}

	public void setPlanTime1(Date planTime1) {
		this.planTime1 = planTime1;
	}

	public Date getPlanTime2() {
		return this.planTime2;
	}

	public void setPlanTime2(Date planTime2) {
		this.planTime2 = planTime2;
	}

	
	/**
	 * @return the planTime3
	 */
	public Date getPlanTime3() {
		return planTime3;
	}

	/**
	 * @param planTime3 the planTime3 to set
	 */
	public void setPlanTime3(Date planTime3) {
		this.planTime3 = planTime3;
	}

	public Boolean getCompleteState() {
		return this.completeState;
	}

	public void setCompleteState(Boolean completeState) {
		this.completeState = completeState;
	}

	public Date getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Boolean getFileState() {
		return this.fileState;
	}

	public void setFileState(Boolean fileState) {
		this.fileState = fileState;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVisitName() {
		return this.visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

	public Date getVisitDate() {
		return this.visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitInfo() {
		return this.visitInfo;
	}

	public void setVisitInfo(String visitInfo) {
		this.visitInfo = visitInfo;
	}

	public Double getEventEffect() {
		return this.eventEffect;
	}

	public void setEventEffect(Double eventEffect) {
		this.eventEffect = eventEffect;
	}

	public Boolean getDispatchState() {
		return this.dispatchState;
	}

	public void setDispatchState(Boolean dispatchState) {
		this.dispatchState = dispatchState;
	}

	public Boolean getHandleState() {
		return this.handleState;
	}

	public void setHandleState(Boolean handleState) {
		this.handleState = handleState;
	}

	public String getPassState() {
		return this.passState;
	}

	public void setPassState(String passState) {
		this.passState = passState;
	}

	public Boolean getApplyState() {
		return this.applyState;
	}

	public void setApplyState(Boolean applyState) {
		this.applyState = applyState;
	}

	public Boolean getClsjLate() {
		return this.clsjLate;
	}

	public void setClsjLate(Boolean clsjLate) {
		this.clsjLate = clsjLate;
	}
	

	/**
	 * @return the clsjLatereason
	 */
	public String getClsjLatereason() {
		return clsjLatereason;
	}

	/**
	 * @param clsjLatereason the clsjLatereason to set
	 */
	public void setClsjLatereason(String clsjLatereason) {
		this.clsjLatereason = clsjLatereason;
	}

	/**
	 * @return the clsjLateplan
	 */
	public String getClsjLateplan() {
		return clsjLateplan;
	}

	/**
	 * @param clsjLateplan the clsjLateplan to set
	 */
	public void setClsjLateplan(String clsjLateplan) {
		this.clsjLateplan = clsjLateplan;
	}
       
	
	/**
	 * @return the clsjLatehand
	 */
	public String getClsjLatehand() {
		return clsjLatehand;
	}

	/**
	 * @param clsjLatehand the clsjLatehand to set
	 */
	public void setClsjLatehand(String clsjLatehand) {
		this.clsjLatehand = clsjLatehand;
	}

	public Boolean getEmailfpState() {
		return this.emailfpState;
	}

	public void setEmailfpState(Boolean emailfpState) {
		this.emailfpState = emailfpState;
	}

	public Boolean getEmailyqState() {
		return this.emailyqState;
	}

	public void setEmailyqState(Boolean emailyqState) {
		this.emailyqState = emailyqState;
	}

	public Integer getNowstate() {
		return this.nowstate;
	}

	public void setNowstate(Integer nowstate) {
		this.nowstate = nowstate;
	}

	public Set getJournals() {
		return this.journals;
	}

	public void setJournals(Set journals) {
		this.journals = journals;
	}

	/*public Set getPaigus() {
		return this.paigus;
	}

	public void setPaigus(Set paigus) {
		this.paigus = paigus;
	}*/

	public Set getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(Set evaluations) {
		this.evaluations = evaluations;
	}

	/**
	 * @return the paigu
	 */
	public Paigu getPaigu() {
		return paigu;
	}

	/**
	 * @param paigu the paigu to set
	 */
	public void setPaigu(Paigu paigu) {
		this.paigu = paigu;
	}

	/**
	 * @return the dgdate
	 */
	public Date getDgdate() {
		return dgdate;
	}

	/**
	 * @param dgdate the dgdate to set
	 */
	public void setDgdate(Date dgdate) {
		this.dgdate = dgdate;
	}
   
	
	public String getReviewAdvice() {
		return reviewAdvice;
	}

	
	public void setReviewAdvice(String reviewAdvice) {
		this.reviewAdvice = reviewAdvice;
	}

	public Set getDevices() {
		return this.devices;
	}

	public void setDevices(Set devices) {
		this.devices = devices;
	}
	
}