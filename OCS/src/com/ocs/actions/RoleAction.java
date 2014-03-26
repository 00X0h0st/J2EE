package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.entities.Privilege;
import com.ocs.interfaces.daos.IRoleDAO;
import com.ocs.interfaces.services.IRoleService;
import com.ocs.services.ServiceException;
import com.ocs.utils.PrivilegeReader;
import com.ocs.vos.RoleVO;

/**
 * ��ɫ����action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class RoleAction {
	private int page = 1;	//Ĭ�ϴ򿪵�һҳ
	private int pageSize;	//ҳ��������������
	private int totalPages;	//��ҳ��
	private List<RoleVO> roleVOs;	//һ���ɫ����
	private int id;
	private RoleVO rvo;
	private static List<Privilege> privileges = PrivilegeReader.getPrivileges();		//����Ȩ�޼���
	private boolean isRoleNameRepeat;	//��ɫ�����Ƿ��ظ�
	private String roleName;	//��ɫ����
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRoleDAO roleDAO;
	
	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	public String find() {
		try {
			roleVOs = roleService.find(page, pageSize);
			totalPages = roleService.findTotalPages(pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "findSuccess";
	}
	
	/**
	 * ��ת���޸�Role����
	 * @return
	 */
	public String toUpdateRole() {
		try {
			rvo = roleDAO.findById(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toUpdateRoleSuccess";
	}
	
	/**
	 * ����ɫ�����Ƿ��ظ�
	 * @return
	 */
	public String checkRoleNameRepeat() {
		try {
			if(roleDAO.checkRoleName(roleName)) {
				isRoleNameRepeat = true;
			} else {
				isRoleNameRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "checkRoleNameRepeatSuccess";
	}
	
	/**
	 * ��ת������roleҳ��
	 * @return
	 */
	public String toAddRole() {
		return "toAddRole";
	}
	
	/**
	 * �޸�role
	 * @return
	 */
	public String update() {
		try {
			roleService.update(rvo);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "updateSuccess";
	}
	
	/**
	 * ����role
	 * @return
	 */
	public String add() {
		try {
			roleService.add(rvo);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "addSuccess";
	}
	
	/**
	 * ɾ��һ����ɫ
	 * @return
	 */
	public String delete() {
		try {
			roleService.delete(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "deleteSuccess";
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<RoleVO> getRoleVOs() {
		return roleVOs;
	}

	public void setRoleVOs(List<RoleVO> roleVOs) {
		this.roleVOs = roleVOs;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RoleVO getRvo() {
		return rvo;
	}

	public void setRvo(RoleVO rvo) {
		this.rvo = rvo;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public boolean getIsRoleNameRepeat() {
		return isRoleNameRepeat;
	}

	public void setRoleNameRepeat(boolean isRoleNameRepeat) {
		this.isRoleNameRepeat = isRoleNameRepeat;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
