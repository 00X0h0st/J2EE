package com.ocs.interfaces.daos;

import com.ocs.daos.DAOException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface ICommonDAO {
	/**
	 * ���������Ƿ�������ȷ
	 * @param password ������
	 * @return
	 * @throws DAOException
	 */
	boolean checkPassword(UserVO user) throws DAOException;
}
