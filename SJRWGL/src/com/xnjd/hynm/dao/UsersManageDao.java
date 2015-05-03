/**
 * 
 */
package com.xnjd.hynm.dao;

import java.util.List;

import com.xnjd.hynm.model.Account;

/**
 * @author swjtu
 *
 */
public interface UsersManageDao {
	
	/**
	 * 检测用户是否存在
	 * @param name
	 * @return
	 */
	public boolean isExist(String name);
	
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
	 * @return
	 */
	public List<Account> loadUsersList();
	
	//查找用户的集合
	
	public List<Account> loadUser();


}
