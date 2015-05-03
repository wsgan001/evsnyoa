/**
 * 
 */
package com.xnjd.hynm.service;

import java.util.List;

import net.sf.json.JSONArray;


import com.xnjd.hynm.model.Customer;
import com.xnjd.hynm.util.PageBean;

/**
 * @author Administrator
 *
 */
public interface CustomerManageService {
	
	
	
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
    public boolean addCustomer(Customer customer);
    
    /**
     * 根据id删除指定的用户纪录
     * @param id
     * @return
     */
    public boolean deleteCustomer(int id);
    /**
     * 更新客户
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
	 * @param id 
	 * @return
	 */
	public List<Customer> loadCustomerList();
	
	public List<Customer> loadCustomer();
	/**
	 * 根据指定地址查找符合条件的用户
	 * @param address  省市区
	 * @return
	 */
	public String findCustomerList(Customer costomerAddress);

   /**
    * 根据客户名称查询
    */
	public List<Customer> findDimCustomerList(String customerName);

	
	public JSONArray getCustomer(String input);
	
	public List<Customer> findByCustomerName(String customerName);
	
	

}
