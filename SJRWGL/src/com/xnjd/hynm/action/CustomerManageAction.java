/**
 * 
 */
package com.xnjd.hynm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xnjd.hynm.model.Customer;
import com.xnjd.hynm.service.CustomerManageService;
import com.xnjd.hynm.util.AjaxResponseViewInfo;
import com.xnjd.hynm.util.PageBean;



/**
 * @author Administrator
 * 
 */
public class CustomerManageAction extends ActionSupport implements
		ModelDriven<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Customer> customerlist; // 为实现下拉列表框而添加
	private CustomerManageService customerManageService;
	private String actionMsg;
	private int page;
	private PageBean pageBean;
	private Customer customer = new Customer();
	private int customerId;
	private String eventCustomer;
	private List<Customer> customerList;
	private List<String> content = new ArrayList<String>();
	/* 查询客户列表 */

	public String loadCustomerList() throws UnsupportedEncodingException {

		if (actionMsg != null) {
			actionMsg = java.net.URLDecoder.decode(actionMsg, "utf-8");
			addActionMessage(actionMsg);
		}
		setPageBean(customerManageService.queryForPage(10,
				page == 0 ? 1 : page, new String[] { "" }));
		// System.out.print("test........");
		return "loadCustomerList";
	}

	/**
	 * 增加客户
	 * 
	 * @throws UnsupportedEncodingException
	 */

	public String addCustomer() throws UnsupportedEncodingException {
		if (!customerManageService.isExist(customer.getCustomerName())) {
			if (customerManageService.addCustomer(customer))
				actionMsg = "用户添加成功！";
			else
				actionMsg = "用户添加失败！";
		} else {
			actionMsg = "用户已经存在！";
		}
		actionMsg = java.net.URLEncoder.encode(actionMsg, "utf-8");
		return null;
	}

	/* 删除客户 */
	public String deleteCustomerById() throws UnsupportedEncodingException {
		if (customerManageService.deleteCustomer(customerId))
			actionMsg = "删除用户成功！";
		else
			actionMsg = "删除用户失败";
		actionMsg = java.net.URLEncoder.encode(actionMsg, "utf-8");
		
		return "deleteCustomerById";
	}

	/* 更新客户 */
	public String updateCustomer() throws UnsupportedEncodingException {
		if (customerManageService.updateCustomer(customer))
			actionMsg = "更新用户信息成功！";
		else
			actionMsg = "更新用户信息失败！";
		actionMsg = java.net.URLEncoder.encode(actionMsg, "utf-8");
		
		return "toloadCustomer";

	}

	/* 根据id加载客户信息 */
	public String loadCustomerById() throws UnsupportedEncodingException {
		
		if (actionMsg != null) {
			
			actionMsg = java.net.URLDecoder.decode(actionMsg, "utf-8");
			addActionMessage(actionMsg);
		}
		Customer tempUser = customerManageService.loadwCustomerById(customerId);
		if (tempUser != null) {
			try {
				BeanUtils.copyProperties(customer, tempUser);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			/*actionMsg = "加载客户信息失败";
			System.out.println(actionMsg);*/
			try {
				actionMsg = java.net.URLEncoder.encode(actionMsg, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "loadCustomerError";
		}

		return "loadCustomerById";
	}
	
/* 通过客户名称值查询内容*/
	//测试没通过可以去掉此部分内容
	
public void getCustomerHql() {
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	String customername = request.getParameter("eventCustomer");
	try {
		customername=new String(customername.getBytes("ISO-8859-1"),"utf-8");
	} catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
	JSONArray jsonArray = customerManageService.getCustomer(customername);
	String result = jsonArray.toString();
	response.setContentType("text/json;charset=UTF-8");
	try 
	{
		response.getWriter().write(result);
	} 
	catch (IOException e) 
	{            
		e.printStackTrace();        
	}
	
	
}
	
    public void getCustomerList(){
    	
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			  
                HttpServletRequest request = ServletActionContext.getRequest();
				request.setCharacterEncoding("UTF-8");
				
				String customername = request.getParameter("eventCustomer");
				customerList= customerManageService.findByCustomerName(customername);
				for(Customer customer : customerList){
					content.add(customer.getCustomerName());
				}
				JSONArray json = JSONArray.fromObject(content);
				out = response.getWriter();
				out.print(json);
				out.flush();
				out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 根据地址获取相应的用户
	 */
	public void findCustomerList() {
		//System.out.println(customer.getProvince());
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			out = response.getWriter();
			// 不为空进行查询
			if (customer.getProvince() != null
					&& !customer.getProvince().equals("")
					&& customer.getCity() != null
					&& !customer.getCity().equals("")
					&& customer.getCounty() != null
					&& !customer.getCounty().equals("")) {

				HttpServletRequest request = ServletActionContext.getRequest();
				request.setCharacterEncoding("UTF-8");

				String cutomerString = customerManageService
						.findCustomerList(customer);
				if (!("").equals(cutomerString) && cutomerString != null) {
					out.print(cutomerString);
					out.flush();
					out.close();

				} else {

					// 没有值就返回空字符串
					out.print("1");
					out.flush();
					out.close();
				}

			} else {
				// 否则直接设置成没有用户
				out.print("1");
				out.flush();
				out.close();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//模糊查询用户列表
	public void findDimCustomer(){
		List<Customer> customers = customerManageService.findByCustomerName(customer.getCustomerName());
		AjaxResponseViewInfo arvi = new AjaxResponseViewInfo();
		if(customers!=null&&!customers.isEmpty()){
			//System.out.println("1");
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for(Customer customer :customers){
				sb.append("{title:\""+customer.getCustomerName().trim()+"\"},");
			}
			sb = sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			arvi.reponseViewInfo(sb.toString());
			
		}else{
			arvi.reponseViewInfo(null);
		}
		
	}
	/**
	 * @return the customerManageService
	 */
	public CustomerManageService getCustomerManageService() {
		return customerManageService;
	}

	/**
	 * @param customerManageService
	 *            the customerManageService to set
	 */
	public void setCustomerManageService(
			CustomerManageService customerManageService) {
		this.customerManageService = customerManageService;
	}

	/**
	 * @return the actionMsg
	 */
	public String getActionMsg() {
		return actionMsg;
	}

	/**
	 * @param actionMsg
	 *            the actionMsg to set
	 */
	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean
	 *            the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * @return the customerlist
	 */
	public List<Customer> getCustomerlist() {
		return customerlist;
	}

	/**
	 * @param customerlist
	 *            the customerlist to set
	 */
	public void setCustomerlist(List<Customer> customerlist) {
		this.customerlist = customerlist;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	
	public String getEventCustomer() {
		return eventCustomer;
	}

	
	public void setEventCustomer(String eventCustomer) {
		this.eventCustomer = eventCustomer;
	}
  
	
}
