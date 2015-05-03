/**
 * 
 */
package com.xnjd.hynm.dao;
import java.util.List;


import com.xnjd.hynm.model.Device;
import com.xnjd.hynm.model.Paigu;

/**
 * @author Administrator
 *
 */
public interface DeviceManageDao {
	
	
	


		/**获取记录总数
		 * @param param 条件
		 * @return
		 */
		public int getAllRowCount(String[] param);

		/**
	    * 分页查询
	    * @param hql 查询的条件
	    * @param offset 开始记录
	    * @param length 一次查询几条记录
	    * @return
	    */
	   public List<Object> queryForPage(int offset,int length,String[] param);
	   
	   public boolean addDevice(Device device);
	   
	   /**
	    * 根据id删除指定的用户纪录
	    * @param id
	    * @return
	    */
	   public boolean deleteDevice(int id);
	   /**
	    * 更新用户
	    * @param user
	    * @return
	    */
	   public boolean updateDevice(Device device);
	   
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
		 * @return
		 */
		public List<Device> loadDeviceList();
		
		
		public boolean isExist(String name);
		
		
		public List<Device> loadDevice();
		
		

		/**
		 * @param eventid
		 * @return
		 */
		public List<Device> loadDeviceList(int eventid);

		/**
		 * 
		 * @param device 设备内容
		 * @return 返回该设备id
		 */
		public int saveDeviceWithReturnId(Device device);
		/**
		 * 更新device
		 * @return 成功与否
		 */
		public int updateDeviceAdmin(Device device);

}
