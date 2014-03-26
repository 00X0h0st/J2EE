package com.ocs.interfaces.services;

import java.util.List;

import com.ocs.entities.Account;
import com.ocs.entities.Business;
import com.ocs.services.ServiceException;
import com.ocs.vos.BusinessVO;

/**
 * 
 * @author Leslie Leung
 */
public interface IBusinessService {
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
	List<BusinessVO> findByCondition(String osUserName, String unixHost, String idCardNo, 
			String status, 
			int page, int pageSize)
					throws ServiceException;
	
	/**
	 * ��ͣĳ�˻��˺�ʱ�������µ�ҵ���˺Ŷ���ͣ
	 * @param id
	 * @throws ServiceException
	 */
	void pauseByAccount(int id) throws ServiceException;
	
	/**
	 * ɾ��ĳ���˻��˺�ʱ�������µ�����ҵ���˺Ŷ�ɾ��
	 * @param id �˻��˺�id
	 */
	void deleteByAccount(int id) throws ServiceException;
	
	/**
	 * �ҵ���ҳ��
	 * @param osUserName �û���
	 * @param unixHost ������ip
	 * @param idCardNo ���֤��
	 * @param status ״̬
	 * @param pageSize ҳ�������������
	 * @return
	 */
	int findTotalPages(String osUserName, String unixHost, String idCardNo, 
			String status, int pageSize) 
				throws ServiceException;
	
	/**
	 * ɾ��ĳ��ҵ���˺�
	 * @param id ҵ���˺�id
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * �޸�Business
	 * @param b
	 */
	void update(Business b) throws ServiceException;
	
	/**
	 * ��ͨĳҵ��
	 * @param id ҵ���˺�id
	 */
	void start(int id) throws ServiceException;
	
	/**
	 * ��ͣĳҵ��
	 * @param id
	 */
	void pause(int id) throws ServiceException;
	
	/**
	 * ���һ��ҵ���˺�
	 * @param business 
	 * @throws ServiceException
	 */
	void add(BusinessVO business) throws ServiceException;
	
	/**
	 * �������֤�Ų����˻��˺�
	 * @param idCardNo ���֤��
	 * @return
	 */
	Account findAccountByCardNo(String idCardNo) throws ServiceException;
}
