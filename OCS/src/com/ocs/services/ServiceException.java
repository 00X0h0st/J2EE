package com.ocs.services;

/**
 * �Զ����쳣��������ҵ���������쳣
 * @author Leslie Leung
 */
public class ServiceException extends Exception {
	/**
	 * ���췽�������ø��๹�췽��
	 * @param msg �쳣��ʾ��Ϣ
	 * @param t 
	 */
	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
