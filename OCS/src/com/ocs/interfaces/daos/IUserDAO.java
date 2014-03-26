package com.ocs.interfaces.daos;

import java.sql.ResultSet;
import java.util.List;

import com.ocs.daos.DAOException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IUserDAO {
	/**
	 * ͨ��id����UserVO
	 * @param id
	 * @return
	 */
	UserVO findById(int id) throws DAOException;
	
	/**
	 * ͨ���û��˺��������û�
	 * @param userCode �û���
	 * @return
	 */
	UserVO findByUserCode(String userCode) throws DAOException;
	
	/**
	 * ����û��˺��Ƿ��ظ�
	 * @param userCode
	 * @return
	 * @throws DAOException
	 */
	boolean checkUserCodeRepeat(String userCode) throws DAOException;
	
	/**
	 * ����û��������Ƿ��ظ�
	 * @param userName
	 * @return
	 */
	boolean checkUserNameRepeat(String userName) throws DAOException;
	
	/**
	 * ����ֻ������Ƿ��ظ�
	 * @param telephone
	 * @return
	 */
	boolean checkTelephoneRepeat(String telephone) throws DAOException;
	
	/**
	 * �����û��˺Ų��ҵ�ǰ�û����е�Ȩ��id����
	 * @param userCode
	 */
	public List<Integer> findUserPrivilegeIds(String userCode) throws DAOException;
}
