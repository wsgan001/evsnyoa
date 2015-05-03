/**
 * 
 */
package com.xnjd.hynm.service;

import java.util.List;

import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.util.PageBean;

/**
 * @author swjtu
 *
 */
public interface UsersManageService {
	
	/**
	 * 检测用户是否存在
	 * @param name
	 * @return
	 */
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
	 * 验证用户名是否存在
	 * @param name 用户名
	 * @param pwd 用户密码
	 * @return
	 */
	public boolean validateUser(String name,String pwd);
	/**
     * 添加用户
     * @param user
     * @return 
     */
    public boolean addUsers(Account user);
    
    /**
     * 根据id删除指定的用户纪录
     * @param id
     * @return
     */
    public boolean deleteUsers(int id);
    /**
     * 更新用户
     * @param user
     * @return
     */
    public boolean updateUsers(Account user);
    
    /**
     * 根据指定id载入用户实体
     * @param id
     * @return
     */
    public Account loadwUsersById(int id);
    /**
     * 根据指定name载入用户实体
     * @param name
     * @return
     */
    public Account loadwUsersByName(String name);

	/**
	 * @param id 
	 * @return
	 */
	public List<Account> loadUsersList();

	/**
	 * @return
	 */
   public List<Account> loadUser();
}
