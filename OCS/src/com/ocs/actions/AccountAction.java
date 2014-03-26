package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.entities.Account;
import com.ocs.interfaces.daos.IAccountDAO;
import com.ocs.interfaces.services.IAccountService;
import com.ocs.interfaces.services.IBusinessService;
import com.ocs.services.ServiceException;

/**
 * ���������˺ŵ�action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class AccountAction {
	private boolean isDeleteSuccess;	//�Ƿ�ɾ���ɹ�
	private boolean isOldPasswordInputRight;	//�������Ƿ�������ȷ
	private boolean isRecommenderExist;	//�Ƽ����Ƿ����
	private boolean isRealNameExist;	//��ʵ�����Ƿ����
	private boolean isLoginNameExist;	//��¼���Ƿ����
	private boolean isIdCardNoExist;	//���֤���Ƿ����
	private boolean isTelephoneExist;	//���绰�����Ƿ����
	private int id;	//id
	private String telephone;	//�ֻ�����
	private String idCardNo;	//���֤
	private String realName;	//����
	private String loginName;	//��¼��
	private String status;		//״̬
	private int page = 1;	//Ĭ�ϴ򿪵�һҳ
	private int pageSize;	//һ��ҳ������������
	private int totalPages;		//��ҳ��
	private Account account;	//һ���˻��˺�����
	private List<Account> accounts;		//�������һϵ��account
	
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IAccountDAO accountDAO;
	
	/**
	 * ������������account
	 * @return
	 */
	public String find() {
		try {
			accounts = accountService.findByCondition(idCardNo, realName, loginName, status, page, pageSize);
			totalPages = accountService.findTotalPages(idCardNo, realName, loginName, status, pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * ������ݿ����Ƿ���ڸ�����
	 * @return
	 */
	public String checkRealName() {
		try {
			if(accountDAO.findRealName(realName)) {
				isRealNameExist = true;
			} else {
				isRealNameExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findRealNameSuccess";
	}
	
	/**
	 * ������֤���Ƿ����
	 * @return
	 */
	public String checkIdCardNo() {
		try {
			if(accountDAO.findIdCardNo(idCardNo)) {
				isIdCardNoExist = true;
			} else {
				isIdCardNoExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findIdCardNoSuccess";
	}
	
	/**
	 * ����¼���Ƿ����
	 * @return
	 */
	public String checkLoginName() {
		try {
			if(accountDAO.findLoginName(loginName)) {
				isLoginNameExist = true;
			} else {
				isLoginNameExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findLoginNameSuccess";
	}
	
	/**
	 * ����ֻ������Ƿ����
	 * @return
	 */
	public String checkTelephone() {
		try {
			if(accountDAO.findTelephone(telephone)) {
				isTelephoneExist = true;
			} else {
				isTelephoneExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findTelephoneSuccess";
	}
	
	/**
	 * ����Ƽ��������Ƿ����
	 * @return
	 */
	public String checkRecommenderExist() {
		try {
			if(accountDAO.findIdCardNo(idCardNo) == true) {
				isRecommenderExist = true;
			} else {
				isRecommenderExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "isRecommenderExist";
	}
	
	/**
	 * ��ͨ�˺�
	 * @return
	 */
	public String start() {
		try {
			accountService.start(id);
		} catch(ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "startSuccess";
	}
		
	/**
	 * ��ͣĳ�˻��˺ţ�ͬʱ��ͣ���µ�����ҵ���˺�
	 * @return
	 */
	public String pause() {
		try {
			accountService.pause(id);
			businessService.pauseByAccount(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "pauseSuccess";
	}
	
	/**
	 * ɾ��ĳ���˻��˺ţ�ͬʱɾ�����µ�����ҵ���˺�
	 * @return
	 */
	public String delete() {
		try {
			accountService.delete(id);
			businessService.deleteByAccount(id);
			isDeleteSuccess = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isDeleteSuccess = false;
		}
		
		return "isDeleteSuccess";
		
	}
	
	/**
	 * ��ת������account����
	 * @return
	 */
	public String toAddAccount() {
		return "toAddAccount";
	}
	
	/**
	 * ��ת���޸�account����
	 * @return
	 */
	public String toUpdateAccount() {
		try {
			account = accountDAO.findById(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toUpdateAccountSuccess";
	}
	
	/**
	 * �鿴һ���˻��˺ŵ���ϸ��Ϣ
	 * @return
	 */
	public String detail() {
		try {
			account = accountService.detail(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "findDetailSuccess";
	}
	
	/**
	 * ����һ��account
	 * @return
	 */
	public String add() {
		try {
			accountService.add(account);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "addSuccess";
	}
	
	/**
	 * ͨ�����֤�����Ƽ���id
	 * @return
	 */
	public String findRecommenderId() {
		try {
			id = accountDAO.findIdByIdCardNo(idCardNo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findRecommenderIdSuccess";
	}
	
	/**
	 * �޸�һ��account����
	 * @return
	 */
	public String update() {
		try {
			accountService.update(account);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "updateSuccess";
	}
 	
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
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

//	public boolean getIsStartSuccess() {
//		return isStartSuccess;
//	}
//
//	public void setStartSuccess(boolean isStartSuccess) {
//		this.isStartSuccess = isStartSuccess;
//	}

	public boolean getIsRecommenderExist() {
		return isRecommenderExist;
	}

	public void setRecommenderExist(boolean isRecommenderExist) {
		this.isRecommenderExist = isRecommenderExist;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean getIsDeleteSuccess() {
		return isDeleteSuccess;
	}

	public void setDeleteSuccess(boolean isDeleteSuccess) {
		this.isDeleteSuccess = isDeleteSuccess;
	}

	public boolean getIsOldPasswordInputRight() {
		return isOldPasswordInputRight;
	}

	public void setOldPasswordInputRight(boolean isOldPasswordInputRight) {
		this.isOldPasswordInputRight = isOldPasswordInputRight;
	}

	public boolean getIsRealNameExist() {
		return isRealNameExist;
	}

	public void setRealNameExist(boolean isRealNameExist) {
		this.isRealNameExist = isRealNameExist;
	}

	public boolean getIsLoginNameExist() {
		return isLoginNameExist;
	}

	public void setLoginNameExist(boolean isLoginNameExist) {
		this.isLoginNameExist = isLoginNameExist;
	}

	public boolean getIsIdCardNoExist() {
		return isIdCardNoExist;
	}

	public void setIdCardNoExist(boolean isIdCardNoExist) {
		this.isIdCardNoExist = isIdCardNoExist;
	}

	public boolean getIsTelephoneExist() {
		return isTelephoneExist;
	}

	public void setTelephoneExist(boolean isTelephoneExist) {
		this.isTelephoneExist = isTelephoneExist;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	
	
}
