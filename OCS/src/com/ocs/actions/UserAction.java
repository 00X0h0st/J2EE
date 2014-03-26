package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.daos.DAOFactory;
import com.ocs.daos.RoleDAO;
import com.ocs.daos.UserDAO;
import com.ocs.interfaces.daos.IRoleDAO;
import com.ocs.interfaces.daos.IUserDAO;
import com.ocs.interfaces.services.IUserService;
import com.ocs.services.ServiceException;
import com.ocs.services.ServiceFactory;
import com.ocs.services.UserService;
import com.ocs.vos.RoleVO;
import com.ocs.vos.UserVO;

/**
 * �û�ģ��action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class UserAction {
	private int page = 1;	//Ĭ����ʾ��һҳ
	private int pageSize;	//ҳ�������������
	private List<UserVO> users;	//һϵ��User����
	private int privilegeId;	//Ȩ��id
	private String roleName;	//��ɫ����
	private int userId;		//�û�id
	private int totalPages;		//��ҳ��
	private List<RoleVO> roles;		//һЩ��RoleVO����
	private UserVO user;	//һ���û�
	private String userName;	//�û�������
	private boolean isUserNameRepeat;	//�û������Ƿ��ظ�
	private boolean isTelephoneRepeat;	//�û��ֻ������Ƿ��ظ�
	private boolean isUserCodeRepeat;	//����û��˺��Ƿ��ظ�
	private String telephone;	//�ֻ�����
	private String userCode;	//�û��˺�
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IRoleDAO roleDAO;
	
	/**
	 * �����û�����
	 * @return
	 */
	public String find() {
		try {
			users = userService.findByCondition(privilegeId, roleName, page, pageSize);
			totalPages = userService.findTotalPages(privilegeId, roleName, pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * ����û������Ƿ��ظ�
	 * @return
	 */
	public String checkNameRepeat() {
		try {
			if(userDAO.checkUserNameRepeat(userName)) {
				isUserNameRepeat = true;
			} else {
				isUserNameRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "checkNameRepeatSuccess";
	}
	
	/**
	 * ����ֻ������Ƿ��ظ�
	 * @return
	 */
	public String checkTelephoneRepeat() {
		try {
			if(userDAO.checkTelephoneRepeat(telephone)) {
				isTelephoneRepeat = true;
			} else {
				isTelephoneRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "checkTelephoneRepeatSuccess";
	}
	
	/**
	 * ɾ��һ���û�����
	 * @return
	 */
	public String delete() {
		try {
			userService.delete(userId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "deleteSuccess";
	}
	
	/**
	 * ��ת������ҳ��,��Ҫ�ҵ����н�ɫ�����֣��Ա㹩����ʱ��ѡ��
	 * @return
	 */
	public String toAddUser() {
		try {
			roles = roleDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "toAddUserSuccess";
	}
	
	/**
	 * ����һ���û�
	 * @return
	 */
	public String add() {
		try {
			userService.add(user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "addSuccess";
	}
	
	/**
	 * ��ת���޸�userҳ��
	 * @return
	 */
	public String toUpdateUser() {
		try {
			user = userDAO.findById(userId);
			roles = roleDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "toUpdateUserSuccess";
	}
	
	/**
	 * ����û��˺��Ƿ��ظ�
	 * @return
	 */
	public String checkUserCodeRepeat() {
		try {
			if(userDAO.checkUserCodeRepeat(userCode)) {
				isUserCodeRepeat = true;
			} else {
				isUserCodeRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "checkUserCodeRepeatSuccess";
	}
	
	/**
	 * �޸�ĳ���û���Ϣ
	 * @return
	 */
	public String update() {
		try {
			userService.update(user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "updateSuccess";
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
	
	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getIsUserNameRepeat() {
		return isUserNameRepeat;
	}

	public void setUserNameRepeat(boolean isUserNameRepeat) {
		this.isUserNameRepeat = isUserNameRepeat;
	}

	public boolean getIsTelephoneRepeat() {
		return isTelephoneRepeat;
	}

	public void setTelephoneRepeat(boolean isTelephoneRepeat) {
		this.isTelephoneRepeat = isTelephoneRepeat;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public boolean getIsUserCodeRepeat() {
		return isUserCodeRepeat;
	}

	public void setUserCodeRepeat(boolean isUserCodeRepeat) {
		this.isUserCodeRepeat = isUserCodeRepeat;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
