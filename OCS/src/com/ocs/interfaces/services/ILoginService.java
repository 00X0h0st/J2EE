package com.ocs.interfaces.services;

import java.io.InputStream;
import java.util.Map;

import com.ocs.services.ServiceException;

/**
 * 
 * @author Leslie Leung
 */
public interface ILoginService {
	/**
	 * ����¼�Ƿ���ȷ
	 * @param userCode �û���
	 * @param password ����
	 * @param checkCode ��֤��
	 * @param session
	 * @return 0���û������������֤�����1����¼�ɹ�
	 * @throws ServiceException
	 */
	int check(String userCode, String password, String checkCode, Map<String, Object> session) 
			throws ServiceException;
	
	/**
	 * ������֤��ͼƬ
	 * @param session 
	 * @return ����֤��ͼƬת��Ϊ����ʽ�������
	 * @throws ServiceException
	 */
	InputStream generateCheckCode(Map<String, Object> session) throws ServiceException;
}
