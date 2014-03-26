package com.ocs.daos;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.entities.Business;
import com.ocs.interfaces.daos.IBusinessDAO;
import com.ocs.mappers.business.BusinessMapper;

/**
 * ҵ���˺�ģ���DAO
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class BusinessDAO extends JdbcDaoSupport implements IBusinessDAO {
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * ͨ��id����Business
	 * @param id
	 * @return
	 */
	public Business findById(int id) 
			throws DAOException {
		String sql = "SELECT * FROM business WHERE id=?";
		
		Object[] params = {id};
		
		try {
			Business b = this.getJdbcTemplate().queryForObject(sql, params, new BusinessMapper());
			return b;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("ͨ��id����Businessʧ�ܣ��޸�ҵ���˺�ҳ����ʾ���ݻ����", e);
		} 
	}
	
	/**
	 * ���ǰ�˴����business�Ƿ��ظ�
	 * @return
	 */
	public boolean checkBusinessRepeat(String unixHost, String osUserName) 
			throws DAOException {
			
		String sql = "SELECT id FROM business WHERE unix_host=? AND os_username=?";
		
		Object[] params = {unixHost, osUserName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("���ҵ���˺��Ƿ��ظ��������½�ҵ���˺�ʱ����������Ŀ���", e);
		} 
	}

}
