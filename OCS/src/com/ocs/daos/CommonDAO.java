package com.ocs.daos;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.interfaces.daos.ICommonDAO;
import com.ocs.vos.UserVO;

/**
 * ͨ��dao
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class CommonDAO extends JdbcDaoSupport implements ICommonDAO {
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * ���������Ƿ�������ȷ
	 * @param password ������
	 * @return
	 * @throws DAOException
	 */
	public boolean checkPassword(UserVO user) 
			throws DAOException {
		if(user == null) {
			return false;
		}
			
		String sql = "SELECT password FROM user_info WHERE id=? ";
		Object[] params = {user.getId()};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			String password = null;
			if(list != null && !list.isEmpty()) {
				password = list.get(0).get("password").toString();
			}
			
			if(user.getPassword() == password) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("�޸��û�����ʱ�������빦�ܳ���", e);
		} 		
	}
}
