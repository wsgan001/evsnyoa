/**
 * 
 */
package com.xnjd.hynm.service.impl;

import java.util.List;

import com.xnjd.hynm.dao.UsersManageDao;
import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.service.UsersManageService;
import com.xnjd.hynm.util.PageBean;

/**
 * @author swjtu
 *
 */
public class UsersManageServiceImpl implements UsersManageService {
	private UsersManageDao usersManageDao;
	/**
	 * @param usersManageDao the usersManageDao to set
	 */
	public void setUsersManageDao(UsersManageDao usersManageDao) {
		this.usersManageDao = usersManageDao;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#queryForPage(int, int, java.lang.String[])
	 */
	@Override
	public PageBean queryForPage(int pageSize, int page, String[] param) {
		int allRow=usersManageDao.getAllRowCount(param);
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
        pageBean.setList(usersManageDao.queryForPage(offset, pageSize, param));
        pageBean.init();
        return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#validateUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateUser(String name, String pwd) {
		// TODO Auto-generated method stub
		return usersManageDao.validateUser(name, pwd);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#addUsers(com.xnjd.hynm.model.Users)
	 */
	@Override
	public boolean addUsers(Account user) {
		// TODO Auto-generated method stub
		return usersManageDao.addUsers(user);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#deleteUsers(int)
	 */
	@Override
	public boolean deleteUsers(int id) {
		// TODO Auto-generated method stub
		return usersManageDao.deleteUsers(id);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#updateUsers(com.xnjd.hynm.model.Users)
	 */
	@Override
	public boolean updateUsers(Account user) {
		// TODO Auto-generated method stub
		return usersManageDao.updateUsers(user);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#loadwUsersById(int)
	 */
	@Override
	public Account loadwUsersById(int id) {
		// TODO Auto-generated method stub
		return usersManageDao.loadwUsersById(id);
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#loadwUsersByName(java.lang.String)
	 */
	@Override
	public Account loadwUsersByName(String name) {
		// TODO Auto-generated method stub
		return usersManageDao.loadwUsersByName(name);
	}



	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#searchUsers(int)
	 */
	@Override
	public List<Account> loadUsersList() {
		// TODO Auto-generated method stub
		return usersManageDao.loadUsersList();
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.service.UsersManageService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return usersManageDao.isExist(name);
	}

	public List<Account> loadUser(){
		return usersManageDao.loadUser();
	}
}
