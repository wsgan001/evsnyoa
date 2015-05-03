package com.xnjd.hynm.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String account;
	private String pwd;
	private Boolean sjdj;
	private Boolean sjfp;
	private Boolean sjcl;
	private Boolean sjsh;
	private Boolean sjpj;
	private Boolean qxfp;
	private Boolean khgl;
	private Boolean kfgl;
	private String post;
	private String name;
	private String telephone;
	private String email;
	private Set eventsForFpsjId = new HashSet(0);
	private Set eventsForDjsjId = new HashSet(0);
	private Set eventsForShsjId = new HashSet(0);
	private Set eventsForClsjId = new HashSet(0);

	// Constructors

	/**
	 * @param email
	 */
	public Account(String email) {
		super();
		this.email = email;
	}

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String account, String pwd) {
		this.account = account;
		this.pwd = pwd;
	}

	/** full constructor */
	public Account(String account, String pwd, Boolean sjdj, Boolean sjfp,
			Boolean sjcl, Boolean sjsh, Boolean sjpj, Boolean qxfp,
			Boolean khgl, Boolean kfgl,String post, String name, String telephone,
			String email, Set eventsForFpsjId, Set eventsForDjsjId,
			Set eventsForShsjId, Set eventsForClsjId) {
		this.account = account;
		this.pwd = pwd;
		this.sjdj = sjdj;
		this.sjfp = sjfp;
		this.sjcl = sjcl;
		this.sjsh = sjsh;
		this.sjpj = sjpj;
		this.qxfp = qxfp;
		this.khgl = khgl;
		this.kfgl = kfgl;
		this.post = post;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.eventsForFpsjId = eventsForFpsjId;
		this.eventsForDjsjId = eventsForDjsjId;
		this.eventsForShsjId = eventsForShsjId;
		this.eventsForClsjId = eventsForClsjId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean getSjdj() {
		return this.sjdj;
	}

	public void setSjdj(Boolean sjdj) {
		this.sjdj = sjdj;
	}

	public Boolean getSjfp() {
		return this.sjfp;
	}

	public void setSjfp(Boolean sjfp) {
		this.sjfp = sjfp;
	}

	public Boolean getSjcl() {
		return this.sjcl;
	}

	public void setSjcl(Boolean sjcl) {
		this.sjcl = sjcl;
	}

	public Boolean getSjsh() {
		return this.sjsh;
	}

	public void setSjsh(Boolean sjsh) {
		this.sjsh = sjsh;
	}

	public Boolean getSjpj() {
		return this.sjpj;
	}

	public void setSjpj(Boolean sjpj) {
		this.sjpj = sjpj;
	}

	public Boolean getQxfp() {
		return this.qxfp;
	}

	public void setQxfp(Boolean qxfp) {
		this.qxfp = qxfp;
	}

	public Boolean getKhgl() {
		return this.khgl;
	}

	public void setKhgl(Boolean khgl) {
		this.khgl = khgl;
	}
     
	
	public Boolean getKfgl() {
		return kfgl;
	}

	
	public void setKfgl(Boolean kfgl) {
		this.kfgl = kfgl;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set getEventsForFpsjId() {
		return this.eventsForFpsjId;
	}

	public void setEventsForFpsjId(Set eventsForFpsjId) {
		this.eventsForFpsjId = eventsForFpsjId;
	}

	public Set getEventsForDjsjId() {
		return this.eventsForDjsjId;
	}

	public void setEventsForDjsjId(Set eventsForDjsjId) {
		this.eventsForDjsjId = eventsForDjsjId;
	}

	public Set getEventsForShsjId() {
		return this.eventsForShsjId;
	}

	public void setEventsForShsjId(Set eventsForShsjId) {
		this.eventsForShsjId = eventsForShsjId;
	}

	public Set getEventsForClsjId() {
		return this.eventsForClsjId;
	}

	public void setEventsForClsjId(Set eventsForClsjId) {
		this.eventsForClsjId = eventsForClsjId;
	}

}