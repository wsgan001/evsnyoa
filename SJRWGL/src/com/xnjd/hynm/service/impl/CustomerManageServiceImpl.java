/**
 * 
 */
package com.xnjd.hynm.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import com.xnjd.hynm.dao.CustomerManageDao;
import com.xnjd.hynm.model.Customer;
import com.xnjd.hynm.service.CustomerManageService;
import com.xnjd.hynm.util.PageBean;

/**
 * @author Administrator
 *
 */
public class CustomerManageServiceImpl implements CustomerManageService {

	private CustomerManageDao customerManageDao;
	 /**
	 * @return the customerManageDao
	 */
	public CustomerManageDao getCustomerManageDao() {
		return customerManageDao;
	}

	/**
	 * @param customerManageDao the customerManageDao to set
	 */
	public void setCustomerManageDao(CustomerManageDao customerManageDao) {
		this.customerManageDao = customerManageDao;
	}
	
	@Override
	public PageBean queryForPage(int pageSize, int page, String[] param) {
		int allRow=customerManageDao.getAllRowCount(param);
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        if(page>totalPage)page--;
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int currentPage = PageBean.countCurrentPage(page);
		 //把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(customerManageDao.queryForPage(offset, pageSize, param));
        pageBean.init();
        return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.CustomerManageService#findCustomerList(java.lang.String)
	 */
	@Override
	public String findCustomerList(Customer costomerAddress) {
		List list =  customerManageDao.findCustomerList(costomerAddress);
		if(list.size()>0){
			StringBuffer sb = new StringBuffer();
			for(int i =0;i<list.size();i++){
				 sb.append(",");
				 sb.append(list.get(i));
				
			}
			String  cutomerString = (sb.substring(1, sb.length()-1)).toString();//去除最后一个,号
			System.out.println(cutomerString+"!!!!!");
			return cutomerString;
		}else return null;
		 
	}
	
	@Override
	public boolean addCustomer(Customer customer) {
		return customerManageDao.addCustomer(customer);
	}

	
	@Override
	public boolean deleteCustomer(int id) {
		return customerManageDao.deleteCustomer(id);
	}

	
	@Override
	public boolean updateCustomer(Customer customer) {
		
		return customerManageDao.updateCustomer(customer);
	}

	
	@Override
	public Customer loadwCustomerById(int id) {
		return customerManageDao.loadwCustomerById(id);
	}

	
	@Override
	public Customer loadwCustomerByName(String name) {
		return customerManageDao.loadwCustomerByName(name);
	}

	
	@Override
	public List<Customer> loadCustomerList() {
		
		return customerManageDao.loadCustomerList();
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.CustomerManageService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String name) {
		return customerManageDao.isExist(name);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.CustomerManageService#loadCustomer()
	 */
	@Override
	public List<Customer> loadCustomer() {
		return customerManageDao.loadCustomer();
	}


	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.CustomerManageService#findDimCustomerList()
	 */
	@Override
	public List<Customer> findDimCustomerList(String customerName) {
		return customerManageDao.findDimCustomer(customerName);
	}

	/* 增加查询的service方法*/
	@Override
	public JSONArray getCustomer(String input) {
		// TODO Auto-generated method stub
		return customerManageDao.getCustomer(input);
	}


	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.CustomerManageService#findByCustomerName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByCustomerName(String customerName) {
		// TODO Auto-generated method stub
		return customerManageDao.findByCustomerName(customerName);
	}

	
	


}
