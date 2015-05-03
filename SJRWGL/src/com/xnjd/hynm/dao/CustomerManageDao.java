/**
 * 
 */
package com.xnjd.hynm.dao;

import java.util.List;


import com.xnjd.hynm.model.Customer;

import net.sf.json.JSONArray;

/**
 * @author Administrator
 *
 */
public interface CustomerManageDao {


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
   
   public boolean addCustomer(Customer user);
   
   /**
    * 根据id删除指定的用户纪录
    * @param id
    * @return
    */
   public boolean deleteCustomer(int id);
   /**
    * 更新用户
    * @param user
    * @return
    */
   public boolean updateCustomer(Customer customer);
   
   /**
    * 根据指定id载入用户实体
    * @param id
    * @return
    */
   public Customer loadwCustomerById(int id);
   /**
    * 根据指定name载入用户实体
    * @param name
    * @return
    */
   public Customer loadwCustomerByName(String name);

	/**
	 * @return
	 */
	public List<Customer> loadCustomerList();
	
	
	public boolean isExist(String name);
	
	
	public List<Customer> loadCustomer();
	/**
	 * 根据地址查找符合条件的客户
	 * @return  符合条件的用户列表
	 */
	public List findCustomerList(Customer customerAddress);

	
	/**
	 * 根据信息模糊查询数据
	 */
	public List<Customer> findDimCustomer(String name);
	

	
	
	/**
	 * 根据输入的客户名称利用ajax查询数据库
	 * @param input
	 * @return
	 */
	
	public JSONArray  getCustomer(String input);
	
	public List<Customer> findByCustomerName(String customerName);
	
	

}
