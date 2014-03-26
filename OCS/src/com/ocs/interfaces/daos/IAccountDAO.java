package com.ocs.interfaces.daos;

import com.ocs.daos.DAOException;
import com.ocs.entities.Account;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IAccountDAO {
	/**
	 * ����id��ѯaccount
	 * @param id
	 * @return account
	 */
	Account findById(int id) throws DAOException;
	
	/**
	 * �������֤���Ƿ����
	 * @param idCardNo ���֤��
	 * @return 
	 */
	boolean findIdCardNo(String idCardNo) throws DAOException;
	
	/**
	 * �������֤�Ų���id
	 * @param idCardNo
	 * @return
	 */
	Integer findIdByIdCardNo(String idCardNo) throws DAOException;
	
	/**
	 * ����ҵ���˺Ŷ�Ӧ���˻��˺��Ƿ���ͣ����ɾ��
	 * @param id
	 */
	boolean checkAccountPauseOrDelete(int id) throws DAOException;
	
	/**
	 * ���������Ƿ����
	 * @param realName ����
	 * @return 
	 */
	boolean findRealName(String realName) throws DAOException;
	
	/**
	 * ���ҵ�¼���Ƿ����
	 * @param loginName ��¼��
	 * @return 
	 */
	boolean findLoginName(String loginName) throws DAOException;
	
	/**
	 * ���ҵ绰�Ƿ����
	 * @param loginName ��¼��
	 * @return 
	 */
	boolean findTelephone(String telephone) throws DAOException;
}
