package com.xnjd.hynm.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Paigu entity. @author MyEclipse Persistence Tools
 */

public class Paigu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Event event;
	private String guzhangType;
	private String guzhangInfo;
	private String paiguInfo;
	private Boolean chucai;
	private Boolean evsnytongyi;
	private Boolean kehutongyi;
	private String ruanguzhang;
	private String yingguzhang;
	private Boolean baoxiantime;
	private Boolean xiaoshouchuli;
	private String paigufanganPath;
	private Boolean paigufanganState;
	private String paigufanganName;
	private String paiguqianziPath;
	private String paiguqianziName;
	private String huifangInfo;
	private Integer huifangstate;
	private Short endevent;
	private Integer nowstate;
	//private Set devices = new HashSet(0);

	// Constructors

	/** default constructor */
	public Paigu() {
	}

	/** minimal constructor */
	public Paigu(Event event) {
		this.event = event;
	}

	/** full constructor */
	public Paigu(Event event, String guzhangType, String guzhangInfo,
			String paiguInfo, Boolean chucai, Boolean evsnytongyi,
			Boolean kehutongyi, String ruanguzhang, String yingguzhang,
			Boolean baoxiantime, Boolean xiaoshouchuli, String paigufanganPath,
			Boolean paigufanganState, String paigufanganName,
			String paiguqianziPath, String paiguqianziName, String huifangInfo,
			Integer huifangstate, Short endevent, Integer nowstate) {
		this.event = event;
		this.guzhangType = guzhangType;
		this.guzhangInfo = guzhangInfo;
		this.paiguInfo = paiguInfo;
		this.chucai = chucai;
		this.evsnytongyi = evsnytongyi;
		this.kehutongyi = kehutongyi;
		this.ruanguzhang = ruanguzhang;
		this.yingguzhang = yingguzhang;
		this.baoxiantime = baoxiantime;
		this.xiaoshouchuli = xiaoshouchuli;
		this.paigufanganPath = paigufanganPath;
		this.paigufanganState = paigufanganState;
		this.paigufanganName = paigufanganName;
		this.paiguqianziPath = paiguqianziPath;
		this.paiguqianziName = paiguqianziName;
		this.huifangInfo = huifangInfo;
		this.huifangstate = huifangstate;
		this.endevent = endevent;
		this.nowstate = nowstate;
		
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

	public String getGuzhangType() {
		return this.guzhangType;
	}

	public void setGuzhangType(String guzhangType) {
		this.guzhangType = guzhangType;
	}

	public String getGuzhangInfo() {
		return this.guzhangInfo;
	}

	public void setGuzhangInfo(String guzhangInfo) {
		this.guzhangInfo = guzhangInfo;
	}

	public String getPaiguInfo() {
		return this.paiguInfo;
	}

	public void setPaiguInfo(String paiguInfo) {
		this.paiguInfo = paiguInfo;
	}

	public Boolean getChucai() {
		return this.chucai;
	}

	public void setChucai(Boolean chucai) {
		this.chucai = chucai;
	}

	public Boolean getEvsnytongyi() {
		return this.evsnytongyi;
	}

	public void setEvsnytongyi(Boolean evsnytongyi) {
		this.evsnytongyi = evsnytongyi;
	}

	public Boolean getKehutongyi() {
		return this.kehutongyi;
	}

	public void setKehutongyi(Boolean kehutongyi) {
		this.kehutongyi = kehutongyi;
	}

	public String getRuanguzhang() {
		return this.ruanguzhang;
	}

	public void setRuanguzhang(String ruanguzhang) {
		this.ruanguzhang = ruanguzhang;
	}

	public String getYingguzhang() {
		return this.yingguzhang;
	}

	public void setYingguzhang(String yingguzhang) {
		this.yingguzhang = yingguzhang;
	}

	public Boolean getBaoxiantime() {
		return this.baoxiantime;
	}

	public void setBaoxiantime(Boolean baoxiantime) {
		this.baoxiantime = baoxiantime;
	}

	public Boolean getXiaoshouchuli() {
		return this.xiaoshouchuli;
	}

	public void setXiaoshouchuli(Boolean xiaoshouchuli) {
		this.xiaoshouchuli = xiaoshouchuli;
	}

	public String getPaigufanganPath() {
		return this.paigufanganPath;
	}

	public void setPaigufanganPath(String paigufanganPath) {
		this.paigufanganPath = paigufanganPath;
	}

	public Boolean getPaigufanganState() {
		return this.paigufanganState;
	}

	public void setPaigufanganState(Boolean paigufanganState) {
		this.paigufanganState = paigufanganState;
	}

	public String getPaigufanganName() {
		return this.paigufanganName;
	}

	public void setPaigufanganName(String paigufanganName) {
		this.paigufanganName = paigufanganName;
	}

	public String getPaiguqianziPath() {
		return this.paiguqianziPath;
	}

	public void setPaiguqianziPath(String paiguqianziPath) {
		this.paiguqianziPath = paiguqianziPath;
	}

	public String getPaiguqianziName() {
		return this.paiguqianziName;
	}

	public void setPaiguqianziName(String paiguqianziName) {
		this.paiguqianziName = paiguqianziName;
	}

	public String getHuifangInfo() {
		return this.huifangInfo;
	}

	public void setHuifangInfo(String huifangInfo) {
		this.huifangInfo = huifangInfo;
	}

	public Integer getHuifangstate() {
		return this.huifangstate;
	}

	public void setHuifangstate(Integer huifangstate) {
		this.huifangstate = huifangstate;
	}

	public Short getEndevent() {
		return this.endevent;
	}

	public void setEndevent(Short endevent) {
		this.endevent = endevent;
	}

	public Integer getNowstate() {
		return this.nowstate;
	}

	public void setNowstate(Integer nowstate) {
		this.nowstate = nowstate;
	}

	

}