package com.ocs.interfaces.services;

import java.util.List;
import com.ocs.entities.Account;
import com.ocs.services.ServiceException;

/**
 * 
 * @author Leslie Leung
 */
public interface IAccountService {
	/**
	 * ������������account
	 * @param idCardNo ���֤
	 * @param realName ����
	 * @param loginName ��¼��
	 * @param status ״̬
	 * @page ĳҳ
	 * @pageSize ĳҳ������������
	 * @return ���ҳ�����account�ļ���
	 * @throws ServiceException
	 */
	List<Account> findByCondition(String idCardNo, String realName,
			String loginName, String status, int page, int pageSize) 
					throws ServiceException;
	
	/**
	 * ��ͣĳ�˻��˺�
	 * @param id ���˻��˺�id
	 * @throws ServiceException
	 */
	void pause(int id) throws ServiceException;
	
	/**
	 * ɾ��ĳ���˻��˺�
	 * @param id ���˻��˺�id
	 * @throws ServiceException
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * ������ҳ��
	 * @param idCardNo ���֤
	 * @param realName ����
	 * @param loginName ��¼��
	 * @param status ״̬
	 * @param pageSize ҳ�������������
	 * @return ��ҳ��
	 * @throws ServiceException
	 */
	int findTotalPages(String idCardNo, String realName,
			String loginName, String status, int pageSize) 
			throws ServiceException;
	
	/**
	 * ��ͨ�����˺�
	 * @param id �����˺�id
	 * @throws ServiceException
	 */
	void start(int id) throws ServiceException;
	
	/**
	 * ���һ��account
	 * @param account
	 */
	void add(Account account) throws ServiceException;
	
	/**
	 * �޸�account
	 * @param account
	 */
	void update(Account account) throws ServiceException;
	
	/**
	 * �ҵ�һ���˻��˺ŵ���ϸ��Ϣ
	 * @param id
	 * @return
	 */
	Account detail(int id) throws ServiceException;
}
