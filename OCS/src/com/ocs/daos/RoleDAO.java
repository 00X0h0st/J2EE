package com.ocs.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.interfaces.daos.IRoleDAO;
import com.ocs.mappers.Role.RoleVOMapper;
import com.ocs.vos.RoleVO;

/**
 * ��ɫ�����DAO
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class RoleDAO extends JdbcDaoSupport implements IRoleDAO {
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * ͨ��id����RoleVO
	 * @param id
	 * @return
	 */
	public RoleVO findById(int id) 
			throws DAOException {
		
		try {
			// ��ѯ��ɫ
			String sql = "SELECT * FROM role_info WHERE id=?";
			Object[] params = {id};
			RoleVO role = this.getJdbcTemplate().queryForObject(sql, params, new RoleVOMapper());
			
			// ��ѯȨ��
			String sql2 = "SELECT privilege_id FROM role_privilege WHERE role_id=?";
			Object[] params2 = {id};
			
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);
			List<Integer> privilegeIdList = new ArrayList<Integer>();
			
			if(list != null && !list.isEmpty()) {
				for(Map<String, Object> privilegeIds: list) {
					int privilegeId = Integer.parseInt(privilegeIds.get("privilege_id").toString());
					privilegeIdList.add(privilegeId);
				}
			}
				
			role.setPrivilegeIds(privilegeIdList);

			return role;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("����id����role�����޸�roleҳ�����ʾ���������", e);
		}
	}

	/**
	 * �������н�ɫ
	 * @return
	 * @throws DAOException
	 */
	public List<RoleVO> findAll() 
			throws DAOException {
		String sql = "SELECT * FROM role_info";
		try {
			List<RoleVO> roleVOs = this.getJdbcTemplate().query(sql, new RoleVOMapper());
			return roleVOs;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("�������н�ɫ���ֳ��������û�ҳ�����ʾ�ϻ��д���", e);
		} 
	}
	
	/**
	 * ������ݿ����Ƿ���������ɫ����
	 * @param roleName
	 * @return
	 */
	public boolean checkRoleName(String roleName) 
			throws DAOException {
		String sql = "SELECT id FROM role_info WHERE name=?";
		Object[] params = {roleName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("�������н�ɫ���ֳ��������û�ҳ�����ʾ�ϻ��д���", e);
		} 
	}	
}