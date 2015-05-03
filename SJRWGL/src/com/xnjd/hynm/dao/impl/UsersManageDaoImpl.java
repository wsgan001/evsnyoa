/**
 * 
 */
package com.xnjd.hynm.dao.impl;

import java.util.List;

import com.xnjd.hynm.dao.DataAccessUtil;
import com.xnjd.hynm.dao.UsersManageDao;
import com.xnjd.hynm.model.Account;

/**
 * @author swjtu
 *
 */
public class UsersManageDaoImpl implements UsersManageDao {
	private DataAccessUtil dataAccessUtil;
	/**
	 * @param dataAccessUtil the dataAccessUtil to set
	 */
	public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
		this.dataAccessUtil = dataAccessUtil;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#queryForPage(int, int, java.lang.String[])
	 */
	@Override
	public List<Object> queryForPage(int offset, int length, String[] param) {
		String hql = "from Account";        //查询语句
	       return dataAccessUtil.queryForPage(hql,offset, length);        //"一页"的记录
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#validateUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateUser(String name, String pwd) {
		// TODO Auto-generated method stub
		String queryString = "from Account s where s.account=? and s.pwd=?";
		@SuppressWarnings("unchecked")
		List<Object> list = dataAccessUtil.find(queryString,new String[]{name,pwd});
		if(list.isEmpty())return false;
		else return true;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#addUsers(com.xnjd.hynm.model.Users)
	 */
	@Override
	public boolean addUsers(Account user) {
		// TODO Auto-generated method stub
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

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#deleteUsers(int)
	 */
	@Override
	public boolean deleteUsers(int id) {
		// TODO Auto-generated method stub
		boolean status = false;
		try{
			dataAccessUtil.deleteById(Account.class, id);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#updateUsers(com.xnjd.hynm.model.Users)
	 */
	@Override
	public boolean updateUsers(Account user) {
		// TODO Auto-generated method stub
		boolean status = false;
		try{
			dataAccessUtil.update(user);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#loadwUsersById(int)
	 */
	@Override
	public Account loadwUsersById(int id) {
		// TODO Auto-generated method stub
		System.out.println("==========================11111111111111111111111111111111");
		return (Account) dataAccessUtil.findById(Account.class,id);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#loadwUsersByName(java.lang.String)
	 */
	@Override
	public Account loadwUsersByName(String name) {
		String queryString = "from Account s where s.account=?";
		@SuppressWarnings("unchecked")
		List<Account> list = dataAccessUtil.find(queryString,name);
		if(list.isEmpty())return null;
		else return list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#searchUsers()
	 * 查询用户列表
	 */
	@Override
	public List<Account> loadUsersList() {
		// TODO Auto-generated method stub
		//System.out.println("userslist123");
		String queryString="from Account";
		List<Account>userslist=dataAccessUtil.find(queryString);
		//System.out.println("userslist="+userslist);
		return userslist;
	}

	
	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String name) {
		String queryString = "from Account s where s.account=?";
		@SuppressWarnings("unchecked")
		List<Object> list = dataAccessUtil.find(queryString,name);
		if(list.isEmpty())return false;
		else return true;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.UsersManageDao#getAllRowCount(java.lang.String[])
	 */
	@Override
	public int getAllRowCount(String[] param) {
		// TODO Auto-generated method stub
		String hql = "from Account";        //查询语句
		return dataAccessUtil.getAllRowCount(hql);
	}
	
	public List<Account> loadUser(){
		String queryString="from Account";
		List<Account>userslist=dataAccessUtil.find(queryString);
		//System.out.println("userslist="+userslist);
		return userslist;
	}
}
