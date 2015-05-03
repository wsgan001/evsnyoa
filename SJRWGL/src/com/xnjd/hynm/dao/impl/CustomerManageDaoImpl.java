/**
 * 
 */
package com.xnjd.hynm.dao.impl;

import java.util.List;

import net.sf.json.JSONArray;

import com.xnjd.hynm.dao.CustomerManageDao;
import com.xnjd.hynm.dao.DataAccessUtil;

import com.xnjd.hynm.model.Customer;

/**
 * @author Administrator
 *
 */
public class CustomerManageDaoImpl implements CustomerManageDao{
	
	
	
	private DataAccessUtil dataAccessUtil;
	/**
	 * @param dataAccessUtil the dataAccessUtil to set
	 */
	public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
		this.dataAccessUtil = dataAccessUtil;
	}
	

	
	@Override
	public int getAllRowCount(String[] param) {
		String hql = "from Customer";        //查询语句
		return dataAccessUtil.getAllRowCount(hql);
		
	}
	
	@Override
	public List<Object> queryForPage(int offset, int length, String[] param) {
		String hql = "from Customer";        //查询语句
	       return dataAccessUtil.queryForPage(hql,offset, length);  
	}
	
	@Override
	public boolean addCustomer(Customer user) {
		try
		{
			dataAccessUtil.create(user);
		} 
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	
	@Override
	public boolean deleteCustomer(int id) {
		boolean status = false;
		try{
			dataAccessUtil.deleteById(Customer.class, id);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	
	@Override
	public boolean updateCustomer(Customer customer) {
		boolean status = false;
		try{
			dataAccessUtil.update(customer);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	
	@Override
	public Customer loadwCustomerById(int id) {
		// TODO Auto-generated method stub
		return (Customer) dataAccessUtil.findById(Customer.class,id);
	}

	
	@Override
	public Customer loadwCustomerByName(String name) {
		String queryString = "from Customer s where s.customer=?";
		@SuppressWarnings("unchecked")
		List<Customer> list = dataAccessUtil.find(queryString,name);
		if(list.isEmpty())return null;
		else return list.get(0);
	}

	
	@Override
	public List<Customer> loadCustomerList() {
		String queryString="from Customer";
		List<Customer>customerlist=dataAccessUtil.find(queryString);
		//System.out.println("userslist="+userslist);
		return customerlist;
	}
	
	public boolean isExist(String name) {
		String queryString = "from Customer s where s.customerName=?";
		@SuppressWarnings("unchecked")
		List<Object> list = dataAccessUtil.find(queryString,name);
		if(list.isEmpty())return false;
		else return true;
	}



	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.CustomerManageDao#loadCustomer()
	 */
	@Override
	public List<Customer> loadCustomer() {
		String queryString="from Customer";
		List<Customer>customerlist=dataAccessUtil.find(queryString);
		//System.out.println("userslist="+userslist);
		return customerlist;
	}
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.CustomerManageDao#findCustomerList(com.xnjd.hynm.model.Customer)
	 */
	@Override
	public List findCustomerList(Customer customerAddress) {
		String findCustomerHql = "select customerName from Customer s where s.province=? and s.city=? and s.county =?";
		/*Object[]  params= null;
		params[0] = customerAddress.getProvince();
		params[1] = customerAddress.getCity();
		params[2] = customerAddress.getCounty();*/
		List list = null;
		list = dataAccessUtil.customerList(findCustomerHql, new String[]{customerAddress.getProvince(),customerAddress.getCity(),customerAddress.getCounty()});
		return list;
		
		
	}




	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.CustomerManageDao#findDimCustomer(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findDimCustomer(String customerName) {
		String hql = "select new Customer(customerName) from Customer c where customerName like '%"+customerName+"%'";
		return dataAccessUtil.find(hql);
	}


    /* 
	 * 查找客户名单
	 */
	@Override
	public JSONArray getCustomer(String input) {
		/*String queryString = "from Customer s where s.customerName LIKE %?";
		@SuppressWarnings("unchecked")
		List<Customer> list = dataAccessUtil.find(queryString,input);*/
		String hql ="select customerName from Customer c where c.customerName LIKE ?"+"%"+input;
		List<?> list=null;
		list= dataAccessUtil.executeQueryHql(hql);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}



	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByCustomerName(String customerName) {
      String hql= "from Customer c where c.customerName like ?";
       return dataAccessUtil.find(hql, "%"+customerName+"%");
       
		/*String hql ="select customerName from Customer c where c.customerName LIKE ?"+"%"+customerName;
		List list=null;
		list= dataAccessUtil.executeQueryHql(hql);
		return list;*/
	} 


}
