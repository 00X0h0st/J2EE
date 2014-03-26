package com.ocs.interfaces.services;

import java.util.List;

import com.ocs.services.ServiceException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 */
public interface IUserService {
	/**
	 * �����û��б�
	 * @param privilegeId Ȩ��id
	 * @param roleName ��ɫ����
	 * @param page ҳ��
	 * @param pageSize ҳ�����������
	 * @return
	 * @throws ServiceException
	 */
	List<UserVO> findByCondition(Integer privilegeId, String roleName,
			int page, int pageSize)
					throws ServiceException;
	
	/**
	 * ������ҳ��
	 * @param privilegeId Ȩ��id
	 * @param roleName ��ɫ����
	 * @param pageSize ҳ�������������
	 * @return
	 * @throws ServiceException
	 */
	int findTotalPages(Integer privilegeId, String roleName, int pageSize)
			throws ServiceException;
	
	/**
	 * ���һ���û�
	 * @param user
	 */
	void add(UserVO user) throws ServiceException;
	
	/**
	 * ɾ��һ���û�
	 * @param userId
	 */
	void delete(int userId) throws ServiceException;
	
	/**
	 * �޸�ĳ���û�����Ϣ
	 * @param user ĳ���û�
	 */
	void update(UserVO user) throws ServiceException;
}
