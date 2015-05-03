/**
 * 
 */
package com.xnjd.hynm.action;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Device;
import com.xnjd.hynm.service.DeviceManageService;
import com.xnjd.hynm.service.EventManageService;
import com.xnjd.hynm.util.AjaxResponseViewInfo;
import com.xnjd.hynm.util.SendMailUtil;

/**
 * @author my 主要用作于发送邮件
 * 
 */
public class SendMailAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EventManageService eventManageService;
	DeviceManageService deviceManageService;
	AjaxResponseViewInfo arvi = new AjaxResponseViewInfo();
	/**
	 * 邮箱收件人
	 */
	private String recipients;
	/**
	 * 邮箱抄送人
	 */
	private String copyToMan;
	/**
	 * 邮箱主题
	 */
	private String mailTheme;
	/**
	 * 邮箱内容
	 */
	private String mailContent;
	/**
	 * @return the recipients
	 */
	/**
	 * 库房管理人员邮箱内容
	 */
	private String inventoryKeeper;
	/**
	 * 审核人员的邮箱内容
	 */
	private String auditPerson;
	/**
	 * 事件ID
	 */
	private int eventID;
	/**
	 * 邮箱地址
	 */
	private String addressor;

	/**
	 * 邮箱密码
	 */
	private String addressorPassword;

	/**
	 * 查找库房管理人员
	 */
	public void findInventoryKeeper() {
		inventoryKeeper = eventManageService.findInventoryKeeper();
		arvi.reponseViewInfo(inventoryKeeper);
	}

	/**
	 * 查找审核人
	 */
	public void findAuditPerson() {
		auditPerson = eventManageService.findAuditPerson();
		arvi.reponseViewInfo(auditPerson);
	}

	/**
	 * @return the auditPerson
	 */
	public String getAuditPerson() {
		return auditPerson;
	}

	/**
	 * @param auditPerson the auditPerson to set
	 */
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	/**
	 * 通过id查找对应事件
	 */
	public void findEventNameById() {
		String eventName = eventManageService.loadEventById(eventID);
		arvi.reponseViewInfo(eventName);

	}

	/**
	 * 通过id查找符合条件的事件信息
	 */
	public void findEventDeviceInfoByEventId() {
		List<Device> deviceList = deviceManageService.loadDeviceList(eventID);
		StringBuffer sb = new StringBuffer();
		if (deviceList.size() > 0) {
			for (Device device : deviceList) {
				sb.append("设备名称:" + device.getDeviceName() + " ");
				sb.append("设备型号:" + device.getDeviceModel() + " ");
				sb.append("故障现象:" + device.getGuzhangInfo() + " ");
				sb.append("公司保修号:" + device.getProtectNumber() + " ");
				sb.append("工厂保修号:" + device.getSupplierPnumber() + " ");
				sb.append("检测人:" + device.getTestHuman() + " ");
				Date recordDate = device.getRecordDate();
				if (recordDate != null && ("").equals(recordDate)) {
					String recordDateString = recordDate.toString().trim();
					if (recordDateString != null
							& ("").equals(recordDateString)) {
						sb.append("登记日期:" + recordDateString.split(" ")[0]
								+ "." + "\n");
					}
				} else {
					    sb.append("登记日期：null"+"\n");
				}
				sb.append("\n").toString().trim();
			}
		}
		arvi.reponseViewInfo(sb.toString());
	}

	/**
	 * 获取发件人
	 */
	public void findAddressor() {
		Account account = (Account) ActionContext.getContext().getSession()
				.get("admin");
		if (account != null) {
			arvi.reponseViewInfo(account.getEmail());
		} else {
			// 返回空并关闭流
			arvi.reponseViewInfo(null);
		}
	}

	/**
	 * 获取执行者地址（库管->执行者）
	 */
	public void findCLSJEmailAddress() {
		String CLSJEmail = eventManageService.findCLSJPerson(eventID);
		arvi.reponseViewInfo(CLSJEmail);
	}

	/**
	 * 获取抄送人地址（库管->执行者）
	 */
	public void findSHManWithKGManEmailAddress() {
		Account account = (Account) ActionContext.getContext().getSession()
				.get("admin");
		if (account != null) {
			String SHKGEmail = eventManageService.findSHManWithKGMan(
					account.getId(), eventID);
			arvi.reponseViewInfo(SHKGEmail);
		} else {
			arvi.reponseViewInfo(null);
		}
	}

	/**
	 * 获取邮件内容 （库管->执行者）
	 */
	public void findKGEmailContent() {
		List<Device> deviceList = deviceManageService.loadDeviceList(eventID);
		StringBuffer sb = new StringBuffer();
		if (deviceList.size() > 0) {
			for (Device device : deviceList) {
				sb.append("设备名称:" + device.getDeviceName() + " ");
				Date PlanReturndate = device.getPlanReturndate();
				if (PlanReturndate != null && ("").equals(PlanReturndate)) {
					String planReturndateString = PlanReturndate.toString().trim();
					sb.append("预计返修时间:" + planReturndateString.split(" ")[0]+ "." + " ");
				} else {
					sb.append("预计返修时间:" + "null" + " ");
				}
				Date repairDate = device.getRepairDate();
				if (repairDate != null && ("").equals(repairDate)) {
					String repairDateString = repairDate.toString().trim();
					sb.append("是否保修期:" + repairDateString.split(" ")[0] + "."+ "\n");
				} else {
					sb.append("是否保修期:" + "null" + "\n");
				}
				sb.append("\n").toString().trim();
			}
			arvi.reponseViewInfo(sb);
		}else{
			arvi.reponseViewInfo(null);
		}
		
	}

	

	/**
	 * 获取邮件内容并发送邮件
	 */
	public void sendMail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// 1.收件人,抄送人,邮件主题,邮件内容,发件人邮箱 发件人密码
		SendMailUtil sendMailUtil = new SendMailUtil();
		sendMailUtil.setSMTPHost("smtp.evsny.com");
		sendMailUtil.setUser(addressor);
		sendMailUtil.setPassword(addressorPassword);
		sendMailUtil.setFrom(addressor);
		/* sendMailUtil.setTo(recipients); */
		// 当是单个收件人的时候
		if (recipients != null && !("").equals(recipients)) {
			String[] recipientsArr = recipients.toString().trim().split(",");
			if (recipientsArr.length == 1) {
				sendMailUtil.setTo(recipients);
			} else if (recipientsArr.length > 1) {
				// 当是多个收件人的时候
				for (int i = 0; i < recipientsArr.length; i++) {
					recipientsArr[i] = '"' + recipientsArr[i] + '"';
				}
				sendMailUtil.setRecipientsArr(recipientsArr);
			}
		}
		copyToMan = copyToMan.trim();
		if (copyToMan != null && !("").equals(copyToMan)) {
			/* String copyToMan1 = (('"'+copyToMan+'"')); */
			String[] copyToManArr = copyToMan.split(",");
			String[] mailManArr = new String[1];
			mailManArr[0] = "";
			int copyToManLength = copyToManArr.length;
			for (int i = 0; i < copyToManLength; i++) {
				copyToManArr[i] = ('"' + copyToManArr[i] + '"');
			}
			sendMailUtil.setCC(copyToManArr);
		}
		sendMailUtil.setSubject('"' + mailTheme + '"');
		sendMailUtil.setContent('"' + mailContent + '"');
		try {
			sendMailUtil.sendEventMail();
		} catch (AddressException e) {
			e.printStackTrace();
			// 邮箱帐号或密码错误
			arvi.reponseViewInfo("addressError");
		} catch (MessagingException e) {
			e.printStackTrace();
			// 发件人或抄送人错误
			arvi.reponseViewInfo("messageError");
		}
		// 发送成功
		arvi.reponseViewInfo("sendMessageSuccess");
	}

	/**
	 * @return the eventID
	 */
	public int getEventID() {
		return eventID;
	}

	/**
	 * @param eventID
	 *            the eventID to set
	 */
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	/**
	 * @return the inventoryKeeper
	 */
	public String getInventoryKeeper() {
		return inventoryKeeper;
	}

	/**
	 * @param inventoryKeeper
	 *            the inventoryKeeper to set
	 */
	public void setInventoryKeeper(String inventoryKeeper) {
		this.inventoryKeeper = inventoryKeeper;
	}



	public String getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients
	 *            the recipients to set
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return the copyToMan
	 */
	public String getCopyToMan() {
		return copyToMan;
	}

	/**
	 * @param copyToMan
	 *            the copyToMan to set
	 */
	public void setCopyToMan(String copyToMan) {
		this.copyToMan = copyToMan;
	}

	/**
	 * @return the mailTheme
	 */
	public String getMailTheme() {
		return mailTheme;
	}

	/**
	 * @param mailTheme
	 *            the mailTheme to set
	 */
	public void setMailTheme(String mailTheme) {
		this.mailTheme = mailTheme;
	}

	/**
	 * @return the mailContent
	 */
	public String getMailContent() {
		return mailContent;
	}

	/**
	 * @param mailContent
	 *            the mailContent to set
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	/**
	 * @return the deviceManageService
	 */
	public DeviceManageService getDeviceManageService() {
		return deviceManageService;
	}

	/**
	 * @param deviceManageService
	 *            the deviceManageService to set
	 */
	public void setDeviceManageService(DeviceManageService deviceManageService) {
		this.deviceManageService = deviceManageService;
	}

	/**
	 * @return the eventManageService
	 */
	public EventManageService getEventManageService() {
		return eventManageService;
	}

	/**
	 * @param eventManageService
	 *            the eventManageService to set
	 */
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}

	/**
	 * @return the addressor
	 */
	public String getAddressor() {
		return addressor;
	}

	/**
	 * @param addressor
	 *            the addressor to set
	 */
	public void setAddressor(String addressor) {
		this.addressor = addressor;
	}

	/**
	 * @return the addressorPassword
	 */
	public String getAddressorPassword() {
		return addressorPassword;
	}

	/**
	 * @param addressorPassword
	 *            the addressorPassword to set
	 */
	public void setAddressorPassword(String addressorPassword) {
		this.addressorPassword = addressorPassword;
	}

	

}
