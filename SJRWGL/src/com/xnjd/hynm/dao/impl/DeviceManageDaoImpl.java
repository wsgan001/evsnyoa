/**
 * 
 */
package com.xnjd.hynm.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xnjd.hynm.dao.DataAccessUtil;
import com.xnjd.hynm.dao.DeviceManageDao;
import com.xnjd.hynm.model.Device;

/**
 * @author Administrator
 *
 */
public class DeviceManageDaoImpl extends HibernateDaoSupport implements DeviceManageDao{

	
	private DataAccessUtil dataAccessUtil;
	public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
		this.dataAccessUtil = dataAccessUtil;
	}
	
	@Override
	public int getAllRowCount(String[] param) {
		
		String hql = "from Device";        //查询语句
		return dataAccessUtil.getAllRowCount(hql);
		
	}

	
	@Override
	public List<Object> queryForPage(int offset, int length, String[] param) {
		String hql = "from Device";        //查询语句
	       return dataAccessUtil.queryForPage(hql,offset, length); 
	}

	
	@Override
	public boolean addDevice(Device device) {
		try
		{
			dataAccessUtil.saveDeviceWithReturnId(device);
			
		} 
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	
	@Override
	public boolean deleteDevice(int id) {
		boolean status = false;
		try{
			dataAccessUtil.deleteById(Device.class, id);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	
	@Override
	public boolean updateDevice(Device device) {
		boolean status = false;
		try{
			dataAccessUtil.update(device);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	
	@Override
	public Device loadwDeviceById(int id) {
		// TODO Auto-generated method stub
		return (Device) dataAccessUtil.findById(Device.class,id);
	}

	
	@Override
	public Device loadwDeviceByName(String name) {
		String queryString = "from Device s where s.device=?";
		@SuppressWarnings("unchecked")
		List<Device> list = dataAccessUtil.find(queryString,name);
		if(list.isEmpty())return null;
		else return list.get(0);
	}

	
	@Override
	public List<Device> loadDeviceList() {
		String queryString="from Device";
		List<Device>devicelist=dataAccessUtil.find(queryString);
		//System.out.println("userslist="+userslist);
		return devicelist;
	}

	
	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public List<Device> loadDevice() {
		String queryString="from Device";
		List<Device>devicelist=dataAccessUtil.find(queryString);
		//System.out.println("userslist="+userslist);
		return devicelist;
	}

	

	
	@Override
	public List<Device> loadDeviceList(int eventid) {
		String queryString="from Device where event.id="+eventid;
		List<Device>devicelist=dataAccessUtil.find(queryString);
		//System.out.println("userslist="+userslist);
		return devicelist;
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.DeviceManageDao#saveDeviceWithReturnId(com.xnjd.hynm.model.Device)
	 */
	@Override
	public int saveDeviceWithReturnId(Device device) {
		dataAccessUtil.saveDeviceWithReturnId(device);
		return device.getId();
	}

	/* (non-Javadoc)
	 * @see com.xnjd.hynm.dao.DeviceManageDao#updateDeviceAdmin()
	 */
	@Override
	public int updateDeviceAdmin(Device device) {
		String hql ="update Device d set d.deviceName=:deviceName , d.deviceModel=:deviceModel , d.buyDate=:buyDate ," +
				"d.isGuarantee=:isGuarantee , d.supplier=:supplier , d.planReturndate=:planReturndate ," +
				"d.actualReturndate=:actualReturndate , d.retestHuman=:retestHuman , d.remarks=:remarks , d.visitinfo=:visitinfo where d.id=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("deviceName", device.getDeviceName(), Hibernate.STRING);
		query.setParameter("deviceModel", device.getDeviceModel(), Hibernate.STRING);
		query.setParameter("buyDate", device.getBuyDate(), Hibernate.DATE);
		query.setParameter("isGuarantee", device.getIsGuarantee(), Hibernate.BOOLEAN);
		query.setParameter("supplier", device.getSupplier(), Hibernate.STRING);
		query.setParameter("planReturndate", device.getPlanReturndate(), Hibernate.DATE);
		query.setParameter("actualReturndate", device.getActualReturndate(), Hibernate.DATE);
		query.setParameter("retestHuman", device.getRetestHuman(), Hibernate.STRING);
		query.setParameter("remarks", device.getRemarks(), Hibernate.STRING);
		query.setParameter("visitinfo", device.getVisitinfo(), Hibernate.STRING);
		query.setParameter("id", device.getId(), Hibernate.INTEGER);
		return query.executeUpdate();
	}
}
