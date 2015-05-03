package com.xnjd.hynm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Customer;
import com.xnjd.hynm.model.Evaluation;
import com.xnjd.hynm.model.PeriodicEvent;


/**
 * 继承HibernateDaoSupport，封装操作数据库的公共类
 * 
 * @author Eric.Feng, 03/15/2012
 */
public class DataAccessUtil extends HibernateDaoSupport {
	
	
	// logger
	private static Logger logger = Logger.getLogger(DataAccessUtil.class);
	
	
	/**
     * 分页查询
     * @param hql 查询的条件
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return
     */
    public List<Object> queryForPage(final String hql,final int offset,final int length){
    	@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object> list = getHibernateTemplate().executeFind(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException,SQLException{
                Query query = session.createQuery(hql);
                query.setFirstResult(offset);
                query.setMaxResults(length);
                List<Object> list = query.list();
                return list;
            }
        });
        return list;
    }
  //乱增加的内容
	public List<Object> queryForPage(final String hql){
    	@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object> list = getHibernateTemplate().executeFind(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException,SQLException{
                Query query = session.createQuery(hql);
                List<Object> list = query.list();
                return list;
            }
        });
        return list;
    }
	
	
    /** *//**
     * 查询所有记录数
     * @return 总记录数
     */
	public int getAllRowCount(String hql){
		return this.getHibernateTemplate().find(hql).size();
	}
	
	/**
	 * 使用count语句查条数
	 * @param hql
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int getAllRow(final String hql){
    	int allRow = getHibernateTemplate().execute(new HibernateCallback() {
    		/* (non-Javadoc)
    		 * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
    		 */
    		@Override
    		public Object doInHibernate(Session session)
    				throws HibernateException, SQLException {
    			Query query = session.createQuery(hql);
    			return Integer.parseInt(String.valueOf(query.uniqueResult()));	
    		}
		});
		return allRow;
       
    }

	
	/** 
	 * 向数据库添加一条对应于一个业务对象实例的记录 
	 * 
	 * @param entity 业务对象实例 
	 */
	public void create(Object entity) throws DaoException {
		//System.out.println("开始执行create()方法。");
		try {
			//System.out.println("开始执行save()方法。");
			//getHibernateTemplate().save(entity);
			getHibernateTemplate().merge(entity);
			
			//System.out.println("save()方法已执行。");
		} catch (DataAccessException e) {
			//System.out.println("执行save()报错了。");
			logger.error("向数据库插入 " + entity.getClass().getName() + " 的记录失败", e);
			throw new DaoException("保存 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
	}
	/** 
	 * 向数据库添加一条对应于一个业务对象实例的记录 
	 * 
	 * @param entity 业务对象实例 aaaaa
	 */
	public int add(PeriodicEvent entity) throws DaoException {
		//System.out.println("开始执行create()方法。");
		try {
			//System.out.println("开始执行save()方法。");
			//getHibernateTemplate().save(entity);
			getHibernateTemplate().save(entity);
			return  entity.getId();
			//System.out.println("save()方法已执行。");
		} catch (DataAccessException e) {
			//System.out.println("执行save()报错了。");
			logger.error("向数据库插入 " + entity.getClass().getName() + " 的记录失败", e);
			throw new DaoException("保存 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
			
		}
		
	}
	public void saveDeviceWithReturnId(Object entity) throws DaoException {
		try {
			getHibernateTemplate().save(entity);
			
		} catch (DataAccessException e) {
			logger.error("向数据库插入 " + entity.getClass().getName() + " 的记录失败", e);
			throw new DaoException("保存 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
	}
	/** 
	 * 向数据库更新一条对应于一个业务对象实例的记录 
	 * 
	 * @param entity 业务对象实例 
	 */
	public void update(Object entity) throws DaoException {
		try {
			//getHibernateTemplate().update(entity);
			getHibernateTemplate().merge(entity);
		} catch (DataAccessException e) {
			logger.error("向数据库更新 " + entity.getClass().getName() + " 的记录失败", e);
			throw new DaoException("更新 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
	}
	
	public void updatetwo(Object entity) throws DaoException {
		try {
			getHibernateTemplate().update(entity);
			//getHibernateTemplate().merge(entity);
		} catch (DataAccessException e) {
			logger.error("向数据库更新 " + entity.getClass().getName() + " 的记录失败", e);
			throw new DaoException("更新 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
	}
	/** 
	 * 从数据库删除一条对应于一个业务对象的记录 
	 * 
	 * @param id 主键
	 */
	@SuppressWarnings("unchecked")
	public void deleteById(Class clazz,Integer id) throws DaoException {
		try {
			getHibernateTemplate().delete(getHibernateTemplate().get(clazz, id));
		} catch (DataAccessException e) {
			logger.error("从数据库删除 " + clazz.getName() + " 的记录失败", e);
			throw new DaoException("从数据库删除 " + clazz.getName()
					+ " 实例失败", e);
		}
	}
	
	/** 
	 * 从数据库删除一条对应于一个业务对象的记录 
	 * 
	 * @param entity 业务对象实例 
	 */
	public void delete(Object entity) throws DaoException {
		try {
			getHibernateTemplate().delete(entity);
		} catch (DataAccessException e) {
			logger.error("从数据库删除 " + entity.getClass().getName() + " 的记录失败", e);
			throw new DaoException("从数据库删除 " + entity.getClass().getName()
					+ " 实例失败", e);
		}
	}

	/**
	 * 根据主键查找对象的详细信息
	 * @param clazz 对象对应的类
	 * @param id 主键
	 * @return
	 * @throws DaoException
	 */
	
	public Object findById(Class clazz,Integer id) throws DaoException {
		try {
			return getHibernateTemplate().get(clazz, id);
		} catch (DataAccessException e) {
			logger.error("加载 " + clazz.getName() + " 实例失败",e);
			throw new DaoException("加载 " + clazz.getName() + " 的实例失败", e);
		}
	}
	
	/** 
	 * 从数据库加载指定类型的业务对象的所有记录。 
	 * 
	 * @param clazz 业务对象的Class 
	 * @return 返回数据库中对应该业务对象的所有记录的集合 
	 */
	public List loadAll(Class clazz) throws DaoException {
		try {
			return getHibernateTemplate().loadAll(clazz);
		} catch (DataAccessException e) {
			logger.error("加载所有 " + clazz.getName() + " 实例时失败",e);
			throw new DaoException("加载所有 " + clazz.getName() + " 实例时失败", e);
		}
	}

	/** 
	 * 根据查询语句查询数据库并返回查询结果所包含的业务对象集合。 
	 * 
	 * @param queryString 指定查询语句 
	 * @return 返回查询结果包含的业务对象集合 
	 */
	public List find(String queryString) throws DaoException {
		try {
			return getHibernateTemplate().find(queryString);
		} catch (DataAccessException e) {
			logger.error("执行查询 " + queryString + " 失败", e);
			throw new DaoException("执行查询 " + queryString + " 失败", e);
		}
	}
	
	public Object findHql(String queryString) throws DaoException {
		try {
			return (Object) getHibernateTemplate().find(queryString);
		} catch (DataAccessException e) {
			logger.error("执行查询 " + queryString + " 失败", e);
			throw new DaoException("执行查询 " + queryString + " 失败", e);
		}
	}
	//添加的查找事件1
	public Object findByEventId(Class clazz,Integer eventid) throws DaoException {
		try {
			return getHibernateTemplate().get(clazz, eventid);
		} catch (DataAccessException e) {
			logger.error("加载 " + clazz.getName() + " 实例失败",e);
			throw new DaoException("加载 " + clazz.getName() + " 的实例失败", e);
		}
	}
	
    //添加的查找事件2
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object executeHql(final String hql) throws DaoException {
		Object o=getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) 
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				Object o = query.uniqueResult();
				
				return o;
			}
		});		
		return o;
	}
	
	//添加查找事件3
	
	public List<Object> queryHql(final String hql){
    	@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object> list = getHibernateTemplate().executeFind(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException,SQLException{
                Query query = session.createQuery(hql);
                query.setMaxResults(1);
                List<Object> list = query.list();
                list=(List<Object>) list.get(0);
                return list;
            }
        });
        return list;
    }
	
	
	
	
  
	/** 
	 * 根据带一个参数的查询语句查询数据库并返回查询结果所包含的业务对象集合。 
	 * 
	 * @param queryString 指定查询语句 
	 * @param param 指定所带参数 
	 * @return 返回查询结果包含的业务对象集合 
	 */
	public List find(String queryString, Object param) throws DaoException {
		try {
			return getHibernateTemplate().find(queryString, param);
		} catch (DataAccessException e) {
			logger.error("执行参数为 " + param + " 的查询 " + queryString+ " 失败", e);
			throw new DaoException("执行参数为 " + param + " 的查询 " + queryString
					+ " 失败", e);
		}
	}

	/** 
	 * 如果参数多余一个，根据多个参数的查询语句查询数据库并返回查询结果所包含的业务对象集合。 
	 * 
	 * @param queryString 指定查询语句 
	 * @param params 指定参数数组 
	 * @return 返回查询结果包含的业务对象集合 
	 */
	public List find(String queryString, Object[] params) throws DaoException {
		try {
			return getHibernateTemplate().find(queryString, params);
		} catch (DataAccessException e) {
			StringBuffer paramString = new StringBuffer("");
			for (int i = 0; i < params.length; i++) {
				paramString.append(params[i]);
				paramString.append(" ");
			}
			logger.error("执行参数为 " + paramString + "的查询 "
					+ queryString + " 失败", e);
			throw new DaoException("执行参数为 " + paramString + "的查询 "
					+ queryString + " 失败", e);
		}
	}

	//用于增加柱状体图的查询语句
	@SuppressWarnings("unchecked")
	public List<Object> executeSql(final String sql) throws DaoException {
		List<Object> list=getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				List<Object> list = sqlQuery.list();
				return list;
			}
		});
		return list;
	}
	//评价的处理
	public List<Evaluation> queryChat(final String hql){
    	@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Evaluation> list = getHibernateTemplate().executeFind(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException,SQLException{
                Query query = session.createSQLQuery(hql);
                List<Object> list = query.list();
                return list;
            }
        });
        return list;
    }
	
	
	//根据多条件查找符合条件的内容
		public List<Customer> customerList(String queryString,Object[] params){
			try {
				return getHibernateTemplate().find(queryString, params);
			} catch (DataAccessException e) {
				StringBuffer paramString = new StringBuffer("");
				for (int i = 0; i < params.length; i++) {
					paramString.append(params[i]);
					paramString.append(" ");
				}
				logger.error("执行参数为 " + paramString + "的查询 "
						+ queryString + " 失败", e);
				throw new DaoException("执行参数为 " + paramString + "的查询 "
						+ queryString + " 失败", e);
			}
		}
     //查找库房管理人员的邮箱地址
		public List<Account> getPersonMailAddress(final String hql){
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Account> KFGLList = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					List<String> KFGLList = query.list();
					return KFGLList;
				}
			});	
			return KFGLList;
		}
		
		/**
		 * 增加执行Hql语句返回list方法
		 * @param hql
		 * @return
		 */
		public List<Object> executeQueryHql(final String hql){
	    	@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Object> list = getHibernateTemplate().executeFind(new HibernateCallback(){
	            public Object doInHibernate(Session session) throws HibernateException,SQLException{
	                Query query = session.createSQLQuery(hql);
	                List<Object> list = query.list();
	                return list;
	            }
	        });
	        return list;
	    }
}
