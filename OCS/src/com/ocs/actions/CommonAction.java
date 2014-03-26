package com.ocs.actions;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.interfaces.daos.IUserDAO;
import com.ocs.interfaces.services.ICommonService;
import com.ocs.services.ServiceException;
import com.ocs.utils.CommonUtil;
import com.ocs.vos.UserVO;

/**
 * ����һЩͨ�ù��ܵ�action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class CommonAction extends CommonUtil {
	
	private UserVO user;	//��ǰ�û�
	private String password;	//�û�����
	private String userRoles;	//�û����н�ɫ��ϳɵ��ַ���
	
	@Autowired
	private ICommonService commonService;
	@Autowired
	private IUserDAO userDAO;
	
	/**
	 * ��ת����ҳ
	 * @return
	 */
	public String toIndex() {
		return "toIndex";
	}
	
	/**
	 * ��ת��û��Ȩ��ҳ��
	 * @return
	 */
	public String toNoPermission() {
		return "toNoPermission";
	}
	
	/**
	 * �޸��û��ĸ�����Ϣ
	 * @return
	 */
	public String updateUserInfo() {
		try {
			commonService.updateUserInfo(user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "updateUserInfoSuccess";
	}
	
	/**
	 * �����û�����
	 * @return
	 */
	public String resetUserPassword() {
		String userCode = (String) session.get("user");
		try {
			commonService.updateUserPassword(password, userCode);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "resetUserPasswordSuccess";
	}
	
	/**
	 * ��ת���޸��û�����ҳ��
	 * @return
	 */
	public String toResetPassword() {
		String userCode = (String) session.get("user");
		try {
			user = userDAO.findByUserCode(userCode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toResetPasswordSuccess";
	}
	
	/**
	 * ��ת���޸��û�������Ϣҳ��
	 * @return
	 */
	public String toUpdateUserInfo() {
		String userCode = (String) session.get("user");
		try {
			user = userDAO.findByUserCode(userCode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toUpdateUserInfoSuccess";
	}
	
	/**
	 * �˳�ϵͳ�����ص���¼ҳ�棬��Ҫ���session
	 * @return
	 */
	public String exit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "exitSuccess";
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public ICommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
}
