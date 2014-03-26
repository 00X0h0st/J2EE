package com.ocs.services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocs.daos.BusinessDAO;
import com.ocs.daos.DAOFactory;
import com.ocs.entities.Account;
import com.ocs.entities.Business;
import com.ocs.interfaces.services.IBusinessService;
import com.ocs.mappers.account.AccountMapper;
import com.ocs.mappers.business.BusinessVOMapper;
import com.ocs.vos.BusinessVO;

/**
 * ����ҵ���˺�ģ���ҵ���߼�
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class BusinessService extends JdbcDaoSupport implements IBusinessService {
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * ������������һϵ��BusinessVO����
	 * @param osUserName �û���
	 * @param unixHost ������ip
	 * @param idCardNo ���֤��
	 * @param status ״̬
	 * @param page ĳҳ
	 * @param pageSize ҳ����������
	 * @return һ��BusinessVO����
	 * @throws ServiceException
	 */
	public List<BusinessVO> findByCondition(String osUserName,
			String unixHost,
			String idCardNo, 
			String status, 
			int page, int pageSize)
					throws ServiceException {
		
		BusinessDAO businessDAO = DAOFactory.getBusinessDAO();
		
		//ƴ��ѯSQL
		List<Object> params = new ArrayList<Object>();
		String sql = "SELECT * FROM (" +
				"	SELECT b.*," +
				" 	a.real_name,a.idcard_no," + 
				"	c.name,c.descr,rownum r " +
				"	FROM business b " +
				"	INNER JOIN account a ON b.account_id=a.id " +
				"	INNER JOIN cost c ON b.cost_id=c.id " +
				"	WHERE 1=1 ";
		if(osUserName != null
				&& osUserName.length() > 0) {
			sql += " AND b.os_username like ? ";
			params.add("%" + osUserName + "%");
		}
		if(unixHost != null
				&& unixHost.length() > 0) {
			sql += " AND b.unix_host like ? ";
			params.add("%" + unixHost + "%");
		}
		if(idCardNo != null
				&& idCardNo.length() > 0) {
			sql += " AND a.idcardNo like ? ";
			params.add("%" + idCardNo + "%");
		}
		if(status != null
				&& status.length() > 0
				&& !status.equals("-1")) {
			sql += " AND b.status=? ";
			params.add(status);
		}
		sql += ") WHERE r<? AND r>? ";
		params.add(page * pageSize + 1);
		params.add((page - 1) * pageSize);

		Object[] paramsObjectArray = params.toArray();
		try {
			List<BusinessVO> businessVOs = this.getJdbcTemplate().query(sql, paramsObjectArray, new BusinessVOMapper());
			return businessVOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("��ѯҵ���˺�ʧ�ܣ�",e);
		}

	}
	
	/**
	 * ��ͣĳ�˻��˺�ʱ�������µ�ҵ���˺Ŷ���ͣ
	 * @param id
	 * @throws ServiceException
	 */
	public void pauseByAccount(int id)
			throws ServiceException {
		String sql = "UPDATE business SET status='0',pause_date=sysdate WHERE account_id=? AND status='1'";
		
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("��ͣҵ���˺�ʧ��", e);
		} 
	}
	
	/**
	 * ɾ��ĳ���˻��˺�ʱ�������µ�����ҵ���˺Ŷ�ɾ��
	 * @param id �˻��˺�id
	 */
	public void deleteByAccount(int id) 
			throws ServiceException {
		String sql = "UPDATE business SET status='2',close_date=sysdate WHERE account_id=?";
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("ɾ��ҵ���˺�ʧ��", e);
		}
	}
	
	/**
	 * �ҵ���ҳ��
	 * @param osUserName �û���
	 * @param unixHost ������ip
	 * @param idCardNo ���֤��
	 * @param status ״̬
	 * @param pageSize ҳ�������������
	 * @return
	 */
	public int findTotalPages(String osUserName,
			String unixHost,
			String idCardNo, 
			String status, int pageSize) 
				throws ServiceException {
		List<Object> params = new ArrayList<Object>();
		String sql = "SELECT count(id) FROM(" + 
			"SELECT b.id FROM " + 
			"business b INNER JOIN account a ON b.account_id=a.id " + 
			"INNER JOIN cost c ON b.cost_id=c.id WHERE 1=1 ";
		if(osUserName != null
				&& osUserName.length() > 0) {
			sql += " AND b.os_username like ? ";
			params.add("%" + osUserName + "%");
		}
		if(unixHost != null
				&& unixHost.length() > 0) {
			sql += " AND b.unix_host like ? ";
			params.add("%" + unixHost + "%");
		}
		if(idCardNo != null
				&& idCardNo.length() > 0) {
			sql += " AND a.idcardNo like ? ";
			params.add("%" + idCardNo + "%");
		}
		if(status != null
				&& status.length() > 0
				&& !status.equals("-1")) {
			sql += " AND b.status=? ";
			params.add(status);
		}
		
		sql += ")";
		
		Object[] paramsObjectArray = params.toArray();
		int rows;
		
		try {	
			rows = this.getJdbcTemplate().queryForInt(sql, paramsObjectArray);

			/* ��������������ѯ��ҳ�� */
			if(rows % pageSize == 0) {
				return rows / pageSize;
			} else {
				return rows / pageSize + 1;
			}			
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("ҵ���˺�ģ���ѯ��ҳ������", e);
		} 
	}
	
	/**
	 * ɾ��ĳ��ҵ���˺�
	 * @param id ҵ���˺�id
	 */
	public void delete(int id) 
			throws ServiceException {
		String sql = "UPDATE business SET status='2'," +
				"close_date=sysdate WHERE id=?";
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ɾ��ҵ���˺�ʧ�ܣ�", e);
		}
	}
	
	/**
	 * �޸�Business
	 * @param b
	 */
	public void update(Business b) 
			throws ServiceException {
		if(b == null) {
			return;
		}
		String sql = "INSERT INTO business_update_bak " +
				"VALUES(business_bak_seq.nextval,?,?)";
		Object[] params = {b.getId(), b.getCostId()};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("����ҵ���˺�ʧ�ܣ�",e);
		}
	}
	
	/**
	 * ��ͨĳҵ��
	 * @param id ҵ���˺�id
	 */
	public void start(int id) 
			throws ServiceException {
		String sql = "UPDATE business SET status='1',pause_date=null,create_date=sysdate " + 
				"WHERE id=?";
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("��ͨҵ���˺�ʧ��", e);
		} 
	}
	
	/**
	 * ��ͣĳҵ��
	 * @param id
	 */
	public void pause(int id) 
			throws ServiceException {
		String sql = "UPDATE business SET status='0',pause_date=sysdate WHERE id=?";
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("��ͣҵ��ʧ��", e);
		} 
	}
	
	/**
	 * ���һ��ҵ���˺�
	 * @param business 
	 * @throws ServiceException
	 */
	public void add(BusinessVO business) 
			throws ServiceException {
		if(business == null) {
			return;
		}
		
		//�����������ʽ����
		if(!business.getUnixHost().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}") ||
				!business.getOsUserName().matches("^\\w{1,8}$") || 
				!business.getLoginPassword().matches("^\\w{1,30}$")) {
			return;
		}
		
		String sql = "INSERT INTO business VALUES(business_seq.nextval,?,?,?,?,'1',sysdate," +
				"null,null,?) ";
		
		Object[] params = {business.getAccountId(), business.getUnixHost(),
				business.getOsUserName(), business.getLoginPassword(),
				business.getCostId()};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("���ҵ���˺�ʧ��", e);
		} 
	}
	
	/**
	 * �������֤�Ų����˻��˺�
	 * @param idCardNo ���֤��
	 * @return
	 */
	public Account findAccountByCardNo(String idCardNo) 
			throws ServiceException {
		
		String sql = "SELECT * FROM account WHERE idcard_no=?";
		Object[] params = {idCardNo};
		
		try {
			Account a = this.getJdbcTemplate().queryForObject(sql, params, new AccountMapper());		
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("�������֤�Ų�ѯ�˻��˺ų�������ҵ���˺�ҳ�湦�ܻ��������", e);
		}
	}
}
