package com.ocs.daos;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.entities.Account;
import com.ocs.interfaces.daos.IAccountDAO;
import com.ocs.mappers.account.AccountMapper;

/**
 * ����account��DAO
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class AccountDAO extends JdbcDaoSupport implements IAccountDAO {
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * ����id��ѯaccount
	 * @param id
	 * @return account
	 */
	public Account findById(int id) 
			throws DAOException {
		//�������˺�����
		String sql = "SELECT * FROM account WHERE id=?";
		Object[] params = {id};
		try {
			Account a = this.getJdbcTemplate().queryForObject(sql, params, new AccountMapper());
			//��ѯ�Ƽ������֤
			if(a.getRecommenderId() != null) {
				String sql2 = "SELECT idcard_no FROM account " +
						"WHERE id=?";
				Object[] params2 = {a.getRecommenderId()};
				
				List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);
				
				if(list != null && !list.isEmpty()) {
					String idCardNo = list.get(0).get("idcard_no").toString();
					a.setRecommenderIdCardNo(idCardNo);
				}
			}
			return a;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("����ID��ѯ�����˺�ʧ��,�˻��˺�ģ����ת���޸�ҳ��ʱ��ʾ�����",e);
		}
	}

	/**
	 * �������֤���Ƿ����
	 * @param idCardNo ���֤��
	 * @return 
	 */
	public boolean findIdCardNo(String idCardNo)
			throws DAOException {
		if(idCardNo == null || idCardNo.length() == 0) {
			return false;
		}
		String sql = "SELECT idcard_no FROM account " +
				"WHERE idcard_no=?";
		
		Object[] params = {idCardNo};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("��ѯ���֤�Ƿ����ʧ�ܣ�",e);
		}
	}
	
	/**
	 * �������֤�Ų���id
	 * @param idCardNo
	 * @return
	 */
	public Integer findIdByIdCardNo(String idCardNo) 
			throws DAOException {
		if(idCardNo == null || idCardNo.length() == 0) {
			return null;
		}
		String sql = "SELECT id FROM account " +
				"WHERE idcard_no=?";
		
		Object[] params = {idCardNo};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return Integer.parseInt(list.get(0).get("id").toString());
			} else {
				return null;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("�������֤�Ų�ѯ�Ƽ���idʧ�ܣ�",e);
		}
	}
	
	/**
	 * ����ҵ���˺Ŷ�Ӧ���˻��˺��Ƿ���ͣ����ɾ��
	 * @param id
	 */
	public boolean checkAccountPauseOrDelete(int id)
			throws DAOException {
			
		String sql = "SELECT status FROM account WHERE id=(" + 
				"SELECT account_id FROM business WHERE id=?)";
		
		Object[] params = {id};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			//����˻��˺Ŵ�����ͣ��ɾ��״̬
			if("0".equals(list.get(0).get("status").toString()) || "2".equals(list.get(0).get("status").toString()) ) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("����id����˻��˺�״̬ʧ�ܣ�ҵ���˺ŵĿ�ͨ���ܻ��ܵ�Ӱ��", e);
		}
	}
	
	/**
	 * ���������Ƿ����
	 * @param realName ����
	 * @return 
	 */
	public boolean findRealName(String realName)
			throws DAOException {
		if(realName == null || realName.length() == 0) {
			return false;
		}
		String sql = "SELECT id FROM account " +
				"WHERE real_name=?";
		
		Object[] params = {realName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("�˻��˺Ų�ѯ��ʵ�����Ƿ����ʧ�ܣ�",e);
		}
	}
	
	/**
	 * ���ҵ�¼���Ƿ����
	 * @param loginName ��¼��
	 * @return 
	 */
	public boolean findLoginName(String loginName)
			throws DAOException {
		if(loginName == null || loginName.length() == 0) {
			return false;
		}
		String sql = "SELECT id FROM account " +
				"WHERE login_name=?";
		
		Object[] params = {loginName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("�˻��˺Ų�ѯ��¼���Ƿ����ʧ�ܣ�",e);
		}
	}
	
	/**
	 * ���ҵ绰�Ƿ����
	 * @param loginName ��¼��
	 * @return 
	 */
	public boolean findTelephone(String telephone)
			throws DAOException {
		if(telephone == null || telephone.length() == 0) {
			return false;
		}
		String sql = "SELECT id FROM account " +
				"WHERE telephone=?";
		
		Object[] params = {telephone};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("�˻��˺Ų�ѯ�绰�����Ƿ����ʧ�ܣ�",e);
		}
	}
}
