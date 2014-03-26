package com.ocs.actions;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.interfaces.services.ILoginService;
import com.ocs.services.ServiceException;
import com.ocs.utils.CommonUtil;

/**
 * �����¼��action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class LoginAction extends CommonUtil {
	private String userCode;		//��¼��
	private String password;	//����
	private String checkCode;	//��֤��
	private InputStream checkCodeImgStream;		//��֤��ͼƬ��
	private int loginStatus = 1;	//��¼״̬��1��ʾ��¼�ɹ���0��ʾ��¼ʧ�ܣ�Ĭ����ת����¼ҳ��ʱΪ1
	
	@Autowired
	private ILoginService loginService;
	
	/**
	 * ��������ת�ص�¼����
	 * @return
	 */
	public String toLogin() {
		return "toLogin";
	}
	
	/**
	 * ���ڿ��Ƶ�¼ҵ���߼�
	 * @return
	 */
	public String login() {
		try {
			loginStatus = loginService.check(userCode, password, checkCode, session);
			if(loginStatus == 0) {
				return "loginFail";
			} else {
				return "loginSuccess";
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * �÷������ڿ���������֤��ͼƬ
	 * @return
	 */
	public String createCheckCodeImg() {
		try {
			checkCodeImgStream = loginService.generateCheckCode(session);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "createCheckCodeImgSuccess";
	}


	public InputStream getCheckCodeImgStream() {
		return checkCodeImgStream;
	}


	public void setCheckCodeImgStream(InputStream checkCodeImgStream) {
		this.checkCodeImgStream = checkCodeImgStream;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
}
