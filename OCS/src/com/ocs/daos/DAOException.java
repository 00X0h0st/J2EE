package com.ocs.daos;

/**
 * �Զ����쳣�����ڴ���DAO�������쳣
 * @author Leslie Leung
 */
public class DAOException extends Exception {
	/**
	 * ���췽�����쳣��Ϣ
	 * @param msg
	 * @param t
	 */
	public DAOException(String msg, Throwable t) {
		super(msg, t);
	}
}
