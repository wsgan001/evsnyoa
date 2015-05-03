package com.xnjd.hynm.model;

import java.util.Date;

/**
 * Device entity. @author MyEclipse Persistence Tools
 */

public class Device implements java.io.Serializable {

	// Fields

	private Integer id;
	private Event event;
	private String telephone;
	private String deviceName;
	private String deviceModel;
	private String guzhangInfo;
	private String protectNumber;
	private String supplierPnumber;
	private String testHuman;
	private Date repairDate;
	private String result;
	private String supplier;
	private Date buyDate;
	private Boolean isGuarantee;
	private Date planReturndate;
	private Date actualReturndate;
	private String retestHuman;
	private Date redeviceDate;
	private String remarks;
	private Date recordDate;
	private String visitinfo;

	// Constructors

	/** default constructor */
	public Device() {
	}

	/** full constructor */
	public Device(Event event, String telephone, String deviceName,
			String deviceModel, String guzhangInfo, String protectNumber,
			String supplierPnumber, String testHuman, Date repairDate,
			String result, String supplier, Date buyDate, Boolean isGuarantee,
			Date planReturndate, Date actualReturndate, String retestHuman,
			Date redeviceDate, String remarks, Date recordDate, String visitinfo) {
		this.event = event;
		this.telephone = telephone;
		this.deviceName = deviceName;
		this.deviceModel = deviceModel;
		this.guzhangInfo = guzhangInfo;
		this.protectNumber = protectNumber;
		this.supplierPnumber = supplierPnumber;
		this.testHuman = testHuman;
		this.repairDate = repairDate;
		this.result = result;
		this.supplier = supplier;
		this.buyDate = buyDate;
		this.isGuarantee = isGuarantee;
		this.planReturndate = planReturndate;
		this.actualReturndate = actualReturndate;
		this.retestHuman = retestHuman;
		this.redeviceDate = redeviceDate;
		this.remarks = remarks;
		this.recordDate = recordDate;
		this.visitinfo = visitinfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
   
	 
	

	
	public Event getEvent() {
		return event;
	}

	
	public void setEvent(Event event) {
		this.event = event;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getGuzhangInfo() {
		return this.guzhangInfo;
	}

	public void setGuzhangInfo(String guzhangInfo) {
		this.guzhangInfo = guzhangInfo;
	}

	public String getProtectNumber() {
		return this.protectNumber;
	}

	public void setProtectNumber(String protectNumber) {
		this.protectNumber = protectNumber;
	}

	public String getSupplierPnumber() {
		return this.supplierPnumber;
	}

	public void setSupplierPnumber(String supplierPnumber) {
		this.supplierPnumber = supplierPnumber;
	}

	public String getTestHuman() {
		return this.testHuman;
	}

	public void setTestHuman(String testHuman) {
		this.testHuman = testHuman;
	}

	public Date getRepairDate() {
		return this.repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Boolean getIsGuarantee() {
		return this.isGuarantee;
	}

	public void setIsGuarantee(Boolean isGuarantee) {
		this.isGuarantee = isGuarantee;
	}

	public Date getPlanReturndate() {
		return this.planReturndate;
	}

	public void setPlanReturndate(Date planReturndate) {
		this.planReturndate = planReturndate;
	}

	public Date getActualReturndate() {
		return this.actualReturndate;
	}

	public void setActualReturndate(Date actualReturndate) {
		this.actualReturndate = actualReturndate;
	}

	public String getRetestHuman() {
		return this.retestHuman;
	}

	public void setRetestHuman(String retestHuman) {
		this.retestHuman = retestHuman;
	}

	public Date getRedeviceDate() {
		return this.redeviceDate;
	}

	public void setRedeviceDate(Date redeviceDate) {
		this.redeviceDate = redeviceDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getVisitinfo() {
		return this.visitinfo;
	}

	public void setVisitinfo(String visitinfo) {
		this.visitinfo = visitinfo;
	}

	

}