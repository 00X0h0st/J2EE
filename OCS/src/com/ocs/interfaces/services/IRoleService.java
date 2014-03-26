package com.ocs.interfaces.services;

import java.util.List;

import com.ocs.services.ServiceException;
import com.ocs.vos.RoleVO;

/**
 * 
 * @author Leslie Leung
 */
public interface IRoleService {
	/**
	 * ���ݷ�ҳ�ҵ�ĳҳ����������
	 * @param page ĳҳ
	 * @param pageSize ҳ�������������
	 * @return
	 * @throws ServiceException
	 */
	List<RoleVO> find(int page, int pageSize) throws ServiceException;
	
	/**
	 * �ҵ���ҳ��
	 * @param pageSize ҳ�������������
	 * @return
	 */
	int findTotalPages(int pageSize) throws ServiceException;
	
	/**
	 * ����role
	 * @param role
	 * @throws ServiceException
	 */
	void add(RoleVO role) throws ServiceException;
	
	/**
	 * ɾ��һ����ɫ
	 * @param id
	 * @throws ServiceException
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * �޸�role
	 * @param r
	 * @throws ServiceException
	 */
	void update(RoleVO role) throws ServiceException;
}
