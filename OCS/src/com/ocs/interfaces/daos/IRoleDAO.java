package com.ocs.interfaces.daos;

import java.util.List;
import com.ocs.daos.DAOException;
import com.ocs.vos.RoleVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IRoleDAO {
	/**
	 * ͨ��id����RoleVO
	 * @param id
	 * @return
	 */
	RoleVO findById(int id) throws DAOException;
	
	/**
	 * �������н�ɫ
	 * @return
	 * @throws DAOException
	 */
	List<RoleVO> findAll() throws DAOException;
	
	/**
	 * ������ݿ����Ƿ���������ɫ����
	 * @param roleName
	 * @return
	 */
	boolean checkRoleName(String roleName) throws DAOException;
}
