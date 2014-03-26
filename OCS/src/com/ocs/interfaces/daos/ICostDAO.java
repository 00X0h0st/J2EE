package com.ocs.interfaces.daos;

import java.util.List;
import com.ocs.daos.DAOException;
import com.ocs.entities.Cost;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface ICostDAO {	
	/**
	 * ͨ�����ֲ���ĳ��Cost
	 * @param id ��ǰ�ʷѵ�id
	 * @param name
	 * @return
	 */
	boolean findByName(Integer id, String name) throws DAOException;
	
	/**
	 * ����id����cost
	 * @param id
	 * @return һ���ʷ�����
	 * @throws DAOException
	 */
	Cost findById(int id) throws DAOException;
	
	/**
	 * �ҵ����е��ʷ�
	 * @return
	 */
	List<Cost> findAll() throws DAOException;
}
