/**
 * 
 */
package com.xnjd.hynm.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.service.UsersManageService;
import com.xnjd.hynm.util.PageBean;

/**
 * @author swjtu
 *
 */
/**
 * @author STONE
 *
 */
public class UsersManageAction extends ActionSupport implements ModelDriven<Account> {
	
	private List<Account> userlist; //为实现下拉列表框而添加

	private static final long serialVersionUID = 1L;
	private UsersManageService usersManageService;
	private Account user=new Account();//用于封装系统用户属性模型
    private String code; 
    private String name;
    private String telephone;
    private String email;
    private String post;
    private int userId;
    private int page;
    private PageBean pageBean;
    private boolean flag;
    private String actionMsg;	//Action间传递的消息参数
    private int eventId;
    
    
    /**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	 
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Account getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	public String isExist(){
		if(usersManageService.isExist(user.getAccount()))setFlag(true);
		else setFlag(false);
		return "isExist";
	}
	
	public String logout(){
		ActionContext.getContext().getSession().remove("admin");
		ActionContext.getContext().getSession().remove("startTime");
		return "logout";
	}
	public String login(){
		
		if(ActionContext.getContext().getSession().get("SESSION_SECURITY_CODE").equals(code)){
			if(usersManageService.validateUser(user.getAccount(), user.getPwd())){
				ActionContext.getContext().getSession().put("admin",usersManageService.loadwUsersByName(user.getAccount()));
				ActionContext.getContext().getSession().put("startTime", System.currentTimeMillis());	
				return "toIndex";
			}
			else{
				addActionError("账号或密码错误，请核对后再登陆！");
				return "login";
			}
		}
		{
			addActionError("验证码错误！");
			return "login";
		}
		
	}
	/* *下边新增加一个专门用于客户端判定的登陆处理方法*/
	
public String tologinindex(){
	
		if(usersManageService.validateUser(user.getAccount(), user.getPwd())){
			ActionContext.getContext().getSession().put("admin",usersManageService.loadwUsersByName(user.getAccount()));
			ActionContext.getContext().getSession().put("startTime", System.currentTimeMillis());
			return "toIndexjsp";
		}
		else{
			addActionError("账号或密码错误，请核对后再登陆！");
			return "login";
		}
	
	}
//增加的登录代码
public String toeventindex(){
	if(usersManageService.validateUser(user.getAccount(), user.getPwd())){
		ActionContext.getContext().getSession().put("admin",usersManageService.loadwUsersByName(user.getAccount()));
		ActionContext.getContext().getSession().put("startTime", System.currentTimeMillis());	
		return "toeventindex";
	}
	else{
		addActionError("账号或密码错误，请核对后再登陆！");
		return "login";
	}
	
}

public String loadEventView(){
	if(usersManageService.validateUser(user.getAccount(), user.getPwd())){
		ActionContext.getContext().getSession().put("admin",usersManageService.loadwUsersByName(user.getAccount()));
		ActionContext.getContext().getSession().put("startTime", System.currentTimeMillis());	
		return "toeventindex";
	}
	else{
		addActionError("账号或密码错误，请核对后再登陆！");
		return "login";
	}
	
}


	/* * 查询用户列表*/
	 
	public String loadUsersList() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		setPageBean(usersManageService.queryForPage(10,page==0?1:page, new String[]{""}));
		return "loadUsersList";
	}
	
	/**
	 * 增加用户
	 * @throws UnsupportedEncodingException */
	 
	public String addUsers() throws UnsupportedEncodingException{
		if(!usersManageService.isExist(user.getAccount())){
			if(usersManageService.addUsers(user))actionMsg="用户添加成功！";
			else actionMsg="用户添加失败！";
		}else{
			actionMsg="用户已经存在！";
		}
		actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		return "addUsers";	
	}

	
	/**
	 * 根据id删除指定用户
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String deleteUserById() throws UnsupportedEncodingException{
		if(usersManageService.deleteUsers(userId))actionMsg="删除用户成功！";
		else actionMsg="删除用户失败";
		actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
		return "deleteUserById";
	}
	
	/**
	 * 更新指定用户信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String updateUser() throws UnsupportedEncodingException{
		if(usersManageService.updateUsers(user))
			actionMsg="更新用户信息成功！";
		else actionMsg="更新用户信息失败！";
		actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
	
		if(((Account)ActionContext.getContext().getSession().get("admin")).getQxfp())
		return "updateUser";
		else{
			userId=user.getId();
			return "toloadUser";

		}
			
	}
	
	/**
	 * 根据id加载指定用户
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String loadUserById() throws UnsupportedEncodingException{
		if(actionMsg!=null){
			actionMsg=java.net.URLDecoder.decode(actionMsg,"utf-8");
			addActionMessage(actionMsg);
		}
		Account tempUser = usersManageService.loadwUsersById(userId);
		if(tempUser!=null){
			try {
				BeanUtils.copyProperties(user,tempUser);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			actionMsg="加载用户信息失败";
			try {
				actionMsg=java.net.URLEncoder.encode(actionMsg,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "loadUserError";
		}
		
		return "loadUserById";
		}
	
	/**
	 * @param usersManageService the usersManageService to set
	 */
	public void setUsersManageService(UsersManageService usersManageService) {
		this.usersManageService = usersManageService;
	}
	
	
	

	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
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
      /********显示下拉列表框内容的设置*****************/
	 public List<Account> getUserlist() {
		  return userlist;
		 }

		 public void setUserlist(List<Account> userlist) {
		  this.userlist = userlist;
		 }

		
		 
		 
	/*public String loadUser(){
		List<Account> userlist = usersManageService.loadUser();
		//userlist=service.findAll();
		this.setUserlist(userlist);
		
		//ServletActionContext.getRequest().setAttribute("userlist", userlist);
		return null;
	}*/
	
}
