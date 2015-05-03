/**
 * 
 */
package com.xnjd.hynm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xnjd.hynm.model.Device;

import com.xnjd.hynm.model.Event;

import com.xnjd.hynm.service.DeviceManageService;
import com.xnjd.hynm.service.EventManageService;



/**
 * @author Administrator
 *
 */
public class DeviceManageAction extends ActionSupport implements ModelDriven<Device> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EventManageService  eventManageService;
	private DeviceManageService deviceManageService;

	/**
	 * @return the deviceManageService
	 */

	private List<Device> deviceList;

	public DeviceManageService getDeviceManageService() {
		return deviceManageService;
	}

	private int eventID;
	private String actionMsg;
	private Device device;
	

	private int deviceID;
   
    
	/**
	 * @return the deviceID
	 */
	public int getDeviceID() {
		return deviceID;
	}


	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
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
	 * @param deviceManageService the deviceManageService to set
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
	 * @param eventManageService the eventManageService to set
	 */
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}


	


	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}


	@Override
	public Device getModel() {
		device = new Device();
		return device;
	}
	

	public List<Device> getDeviceList() {
		return deviceList;
	}


	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}




public void saveDeviceWithReturnId() throws UnsupportedEncodingException{
		
		Event temppaigu = eventManageService.loadEventByID(eventID);
		device.setEvent(temppaigu);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			out = response.getWriter();
			//id为空说明是保存
			int  deviceID= deviceManageService.saveDeviceWithReturnId(device); 
				if(deviceID!=0){
					//增加成功
			    	out.print(deviceID);		
			    	out.flush();
					out.close();
			    }else{
			    	out.print(0);
			    	out.flush();
					out.close();
			    };
				 actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");	
				}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
		}
public void updateDeviceByDeviceId(){
	Event temppaigu = eventManageService.loadEventByID(eventID);
	device.setEvent(temppaigu);
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out = null;
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");
	try {
		out = response.getWriter();
		boolean update_device_state= deviceManageService.updateDevice(device); 
			if(update_device_state){
				//更新成功
		    	out.print("true");		
		    	out.flush();
				out.close();
		    }else{
		    	out.print("false");
		    	out.flush();
				out.close();
		    };
			 actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");	
			}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
		
	
	
}
	
public String deleteDevice() throws UnsupportedEncodingException{
	if(deviceManageService.deleteDevice(deviceID))actionMsg="删除设备信息成功！";
	else actionMsg="删除设备信息失败";
	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("eventID", eventID);
	System.out.println(eventID);
	return "deleteDevice";
}	
	
public String updateDevice() throws UnsupportedEncodingException{
	 Event tempEvent = new Event();
	 tempEvent.setId(eventID);
     device.setEvent(tempEvent);
	 if(deviceManageService.updateDevice(device))
		actionMsg="更新设备信息成功！";
	else actionMsg="更新设备信息失败！";
	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
	return "updateDevice";
 }	

public String updateDeviceAdmin() throws UnsupportedEncodingException{
	 Event tempEvent = new Event();
	 tempEvent.setId(eventID);
	 device.setEvent(tempEvent);
	 if(deviceManageService.updateDeviceAdmin(device))
		actionMsg="更新设备信息成功！";
	else actionMsg="更新设备信息失败！";
	actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
	return "updateDeviceAdmin";
}	



public String loadDeviceToView() {
	Device temp = deviceManageService.loadwDeviceById(deviceID);
    BeanUtils.copyProperties(temp,device);
    return "loadDeviceToView";
	
}

public String loadDeviceToAdEdit(){
	Device temp = deviceManageService.loadwDeviceById(deviceID);
    BeanUtils.copyProperties(temp,device);
    return "loadDeviceToAdEdit";
	
}

public String loadDeviceToCmEdit(){
	Device temp = deviceManageService.loadwDeviceById(deviceID);
    BeanUtils.copyProperties(temp,device);
    return "loadDeviceToCmEdit";
	
	
}

 //库房管理员查询
public String loadDeviceList()
{
	deviceList=deviceManageService.loadDeviceList(eventID);
    return "loadDeviceList";
    
	}
//普通用户查询
public String loadDeviceEditList(){
	HttpServletRequest request = ServletActionContext.getRequest();
	request.getAttribute("eventID");
	deviceList=deviceManageService.loadDeviceList(eventID);
	
    return "loadDeviceEditList";
	
	
}

public String loadDeviceToEdit(){
	Event temppaigu = eventManageService.loadEventByID(eventID);
	temppaigu.setNowstate(3);
	eventManageService.updateEvent(temppaigu);
	return "loadDeviceToEdit";
	
}
public String addDevice(){
	Event tempevent = eventManageService.loadEventByID(eventID);
	device.setEvent(tempevent);
	deviceManageService.saveDeviceWithReturnId(device);
	return "addDevice";
	
}

}
