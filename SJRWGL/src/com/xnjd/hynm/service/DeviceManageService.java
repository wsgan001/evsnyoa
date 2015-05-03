/**
 * 
 */
package com.xnjd.hynm.service;

import java.util.List;

import com.xnjd.hynm.model.Device;
import com.xnjd.hynm.model.Evaluation;
import com.xnjd.hynm.util.PageBean;

/**
 * @author Administrator
 *
 */
public interface DeviceManageService {


	public boolean isExist(String name);
	/**
     * 分页查询
     * @param hql 查询的条件
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return
     */
    public PageBean queryForPage(int pageSize,int page,String[] param);
	/**
     * 添加客户
     * @param user
     * @return 
     */
    public boolean addDevice(Device device);
    
    /**
     * 根据id删除指定的用户纪录
     * @param id
     * @return
     */
    public boolean deleteDevice(int id);
    /**
     * 更新设备KG
     * @param user
     * @return
     */
    public boolean updateDevice(Device device);
    /**
     * 更新设备admin
     * @param user
     * @return
     */
    public boolean updateDeviceAdmin(Device device);
    /**
     * 根据指定id载入用户实体
     * @param id
     * @return
     */
    public Device loadwDeviceById(int id);
    /**
     * 根据指定name载入用户实体
     * @param name
     * @return
     */
    public Device loadwDeviceByName(String name);

	/**
	 * @param id 
	 * @return
	 */
	public List<Device> loadDeviceList();
	
	public List<Device> loadDevice();
	
	public Device loadDeviceByEventId(int eventid);
	/**
	 * @param eventID 
	 * @return
	 */
	public List<Device> loadDeviceList(int eventid);
	/**
	 * 
	 * @param device 设备内容
	 * @return 返回该设备id
	 */
	public int saveDeviceWithReturnId(Device device);

	
}
