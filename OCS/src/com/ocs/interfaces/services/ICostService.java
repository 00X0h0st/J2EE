package com.ocs.interfaces.services;
import java.util.List;

import com.ocs.entities.Cost;
import com.ocs.services.ServiceException;

/**
 * 
 * @author Leslie Leung
 */
public interface ICostService {
	/**
	 * ����ĳcost����ϸ��Ϣ
	 * @param id
	 * @return
	 */
	Cost detail(int id) throws ServiceException;
	
	/**
	 * ����ĳ�ʷ�
	 * @param id ���ʷ�id
	 */
	void start(int id) throws ServiceException;
	
	/**
	 * �޸�һ���ʷ�����
	 * @param cost ���ʷ�����
	 * @throws ServiceException
	 */
	void update(Cost cost) throws ServiceException;
	
	/**
	 * ɾ���ʷ�ģ���е�һ������
	 * @param id Ŀ�����ݵ�id
	 * @throws ServiceException
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * �����ݿ��в���һ���ʷ�����
	 * @param cost
	 * @throws ServiceException
	 */
	void add(Cost cost) throws ServiceException;
	
	/**
	 * ���շ�ҳ�ҵ�һ���ʷ�����
	 * @param page ҳ��
	 * @param pageSize ҳ�������������
	 * @return
	 * @throws ServiceException
	 */
	List<Cost> find(int page, int pageSize) throws ServiceException;
	
	/**
	 * �ҵ���ҳ��
	 * @param pageSize ҳ�������������
	 * @return
	 * @throws ServiceException
	 */
	int findTotalPages(int pageSize) throws ServiceException;
}
