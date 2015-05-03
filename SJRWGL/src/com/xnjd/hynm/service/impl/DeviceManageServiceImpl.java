/**
 * 
 */
package com.xnjd.hynm.service.impl;

import java.util.List;

import com.xnjd.hynm.dao.DeviceManageDao;
import com.xnjd.hynm.model.Device;
import com.xnjd.hynm.service.DeviceManageService;
import com.xnjd.hynm.util.PageBean;

/**
 * @author Administrator
 *
 */
public class DeviceManageServiceImpl  implements DeviceManageService{

	private DeviceManageDao deviceManageDao;
	
	
	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public PageBean queryForPage(int pageSize, int page, String[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean addDevice(Device device) {
		return deviceManageDao.addDevice(device);
	}

	
	@Override
	public boolean deleteDevice(int id) {
		return deviceManageDao.deleteDevice(id);
	}

	
	@Override
	public boolean updateDevice(Device device) {
		deviceManageDao.updateDevice(device);
		return true;
	}

	
	@Override
	public Device loadwDeviceById(int id) {
		return deviceManageDao.loadwDeviceById(id);
	}

	
	@Override
	public Device loadwDeviceByName(String name) {
		
		return deviceManageDao.loadwDeviceByName(name);
	}

	
	@Override
	public List<Device> loadDeviceList() {
		
		return deviceManageDao.loadDeviceList();
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.DeviceManageService#loadDevice()
	 */
	@Override
	public List<Device> loadDevice() {
		
		return deviceManageDao.loadDevice();
	}

	/**
	 * @return the deviceManageDao
	 */
	public DeviceManageDao getDeviceManageDao() {
		return deviceManageDao;
	}

	/**
	 * @param deviceManageDao the deviceManageDao to set
	 */
	public void setDeviceManageDao(DeviceManageDao deviceManageDao) {
		this.deviceManageDao = deviceManageDao;
	}

	@Override
	public Device loadDeviceByEventId(int eventid) {
		
		return deviceManageDao.loadwDeviceById(eventid);
	}


	@Override
	public List<Device> loadDeviceList(int eventid){

		List<Device> iter=deviceManageDao.loadDeviceList(eventid);
		return iter;
	}


	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.DeviceManageService#saveDeviceWithReturnId(com.xnjd.hynm.model.Device)
	 */
	@Override
	public int saveDeviceWithReturnId(Device device) {
		return deviceManageDao.saveDeviceWithReturnId(device);
		
	}


	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.DeviceManageService#updateDeviceAdmin(com.xnjd.hynm.model.Device)
	 */
	@Override
	public boolean updateDeviceAdmin(Device device) {
		deviceManageDao.updateDeviceAdmin(device);
		if(deviceManageDao.updateDeviceAdmin(device)==1){
			return true;
		}
	        return false;
	}
}
