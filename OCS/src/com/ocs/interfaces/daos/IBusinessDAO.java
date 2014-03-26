package com.ocs.interfaces.daos;

import com.ocs.daos.DAOException;
import com.ocs.entities.Business;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IBusinessDAO {
	/**
	 * ͨ��id����Business
	 * @param id
	 * @return
	 */
	Business findById(int id) throws DAOException;
	
	/**
	 * ���ǰ�˴����business�Ƿ��ظ�
	 * @return
	 */
	boolean checkBusinessRepeat(String unixHost, String osUserName) throws DAOException;
}
