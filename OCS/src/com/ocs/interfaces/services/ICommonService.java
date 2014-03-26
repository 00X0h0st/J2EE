package com.ocs.interfaces.services;
import com.ocs.services.ServiceException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 */
public interface ICommonService {
	/**
	 * �����û�������Ϣ
	 * @param user
	 * @throws ServiceException
	 */
	void updateUserInfo(UserVO user) throws ServiceException;
	
	/**
	 * �޸��û�����
	 * @throws ServiceException
	 */
	void updateUserPassword(String password, String userCode) throws ServiceException;
}
