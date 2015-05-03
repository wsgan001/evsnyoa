/**
 * 
 */
package com.xnjd.hynm.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;


import com.opensymphony.xwork2.ActionSupport;
import com.xnjd.hynm.model.Customer;
import com.xnjd.hynm.service.CustomerManageService;

/**
 * @author Administrator
 *
 */
public class CustomerFindAction extends  ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private CustomerManageService customerManageService;
	
	private String eventCustomer;
	
	private List<Customer> customerList;
	
	private List<String> content = new ArrayList<String>();
	
	public String  execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			  
                HttpServletRequest request = ServletActionContext.getRequest();
				request.setCharacterEncoding("UTF-8");
				
				String customername = request.getParameter("eventCustomer");
				customername=new String(customername.getBytes("ISO-8859-1"),"utf-8");
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
		return SUCCESS;
	}
	
	

	public CustomerManageService getCustomerManageService() {
		return customerManageService;
	}

	public void setCustomerManageService(CustomerManageService customerManageService) {
		this.customerManageService = customerManageService;
	}



	public String getEventCustomer() {
		return eventCustomer;
	}
    public void setEventCustomer(String eventCustomer) {
		this.eventCustomer = eventCustomer;
	}

   
	public List<Customer> getCustomerList() {
		return customerList;
	}
    public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}


	public List<String> getContent() {
		return content;
	}


	public void setContent(List<String> content) {
		this.content = content;
	}
    
    
}
