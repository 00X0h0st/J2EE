package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.entities.Account;
import com.ocs.entities.Business;
import com.ocs.entities.Cost;
import com.ocs.interfaces.daos.IAccountDAO;
import com.ocs.interfaces.daos.IBusinessDAO;
import com.ocs.interfaces.daos.ICostDAO;
import com.ocs.interfaces.services.IBusinessService;
import com.ocs.services.ServiceException;
import com.ocs.vos.BusinessVO;

/**
 * ����ҵ���˺�ģ���action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class BusinessAction {
	private int id;	//ҵ���˺�id
	private boolean isStartSuccess;		//�Ƿ�ͨ�ɹ�
	private boolean isPauseSuccess;		//�Ƿ���ͣ�ɹ�
	private boolean isDeleteSuccess;	//�Ƿ�ɾ���ɹ�
	private boolean isIdCardNoExist;	//ĳ���֤�Ƿ����
	private boolean isBusinessRepeat;	//ҵ���˺��Ƿ��ظ�
	private boolean isAccountPauseOrDelete;		//ĳ�˻��˺��ǲ��Ǵ�����ͣ��ͨ״̬
	private String osUserName;	//�û���
	private String unixHost;	//����������ip
	private String idCardNo;	//��Ӧ��account�����֤��
	private String status;		//״̬
	private int page = 1;	//Ĭ�ϴ򿪵�һҳ
	private int pageSize;	//ҳ�������������
	private List<BusinessVO> businessVOs;	//һϵ��BusinessVO����
	private int totalPages;		//��ҳ��
	private Business business;		//Businessʵ������
	private BusinessVO bvo;		//BusinessVOʵ������
	private Account account;		//�˻��˺�
	private List<Cost> costs;	//����cost�ļ���
	
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IBusinessDAO businessDAO;
	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private ICostDAO costDAO;

	/**
	 * ����ҵ���˺�
	 * @return
	 */
	public String find() {
		try {
			businessVOs = businessService.findByCondition(osUserName, unixHost, idCardNo, status, page, pageSize);
			totalPages = businessService.findTotalPages(osUserName, unixHost, idCardNo, status, pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * ��ͨҵ���˺�
	 * @return
	 */
	public String start() {
		try {
			businessService.start(id);
			isStartSuccess = true;

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isStartSuccess = false;
		}
		return "isStartSuccess";
	}

	/**
	 * ��ͣҵ���˺�
	 * @return
	 */
	public String pause() {
		try {
			businessService.pause(id);
			isPauseSuccess = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isPauseSuccess = false;
		}
		
		return "isPauseSuccess";
	}
	
	/**
	 * ���ĳҵ���˺Ŷ�Ӧ���˻��˺��Ƿ�����ͣ��ɾ��״̬
	 * @return
	 */
	public String isAccountPauseOrDelete() {
		try {
			if(accountDAO.checkAccountPauseOrDelete(id)) {
				isAccountPauseOrDelete = true;
			} else {
				isAccountPauseOrDelete = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "checkAccountPauseOrDeleteSuccess";
	}
	
	/**
	 * ���һ��ҵ��
	 * @return
	 */
	public String add() {
		try {
			businessService.add(bvo);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "addSuccess";
	}
	
	/**
	 * ���ǰ�˴�����˻��˺��Ƿ��ظ�
	 * @return
	 */
	public String checkBusinessRepeat() {
		try {
			if(businessDAO.checkBusinessRepeat(unixHost, osUserName)) {
				isBusinessRepeat = true;
			} else {
				isBusinessRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "checkBusinessRepeatSuccess";
	}
	
	/**
	 * ������֤���Ƿ����
	 * @return
	 */
	public String checkIdCardNoExist() {
		try {
			if(accountDAO.findIdCardNo(idCardNo) == true) {
				isIdCardNoExist = true;
			} else {
				isIdCardNoExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "checkIdCardNoExistSuccess";
	}
	
	/**
	 * �������֤��Ѱ�������˺�
	 * @return
	 */
	public String findAccount() {
		try {
			account = businessService.findAccountByCardNo(idCardNo);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "findAccountSuccess";
	}
	
	/**
	 * ɾ��ĳ��ҵ���˺ţ���������ɾ����ֻ�ǰ�������Ϊ�ر�״̬
	 * @return
	 */
	public String delete() {
		try {
			businessService.delete(id);
			isDeleteSuccess = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isDeleteSuccess = false;
		}
		return "isDeleteSuccess";
	}
	
	/**
	 * ��ת������ҵ���˺�ҳ��
	 * @return
	 */
	public String toAddBusiness() {
		try {
			costs = costDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toAddBusinessSuccess";
	}
	
	/**
	 * ��ת���޸�ҵ���˺�ҳ��
	 * @return
	 */
	public String toUpdateBusiness() {
		try {
			business = businessDAO.findById(id);
			costs = costDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toUpdateBusinessSuccess";
	}
	
	/**
	 * �޸�ҵ���˺ţ�������������ҵ���˺ű����ǲ��뵽
	 * bak���У����½�ʱ�Զ�����
	 * @return
	 */
	public String update() {
		try {
			businessService.update(business);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "updateSuccess";
	}

	public String getOsUserName() {
		return osUserName;
	}
	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
	}
	public String getUnixHost() {
		return unixHost;
	}
	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public List<BusinessVO> getBusinessVOs() {
		return businessVOs;
	}

	public void setBusinessVOs(List<BusinessVO> businessVOs) {
		this.businessVOs = businessVOs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsStartSuccess() {
		return isStartSuccess;
	}

	public void setStartSuccess(boolean isStartSuccess) {
		this.isStartSuccess = isStartSuccess;
	}

	public boolean getIsPauseSuccess() {
		return isPauseSuccess;
	}

	public void setPauseSuccess(boolean isPauseSuccess) {
		this.isPauseSuccess = isPauseSuccess;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean getIsDeleteSuccess() {
		return isDeleteSuccess;
	}

	public void setDeleteSuccess(boolean isDeleteSuccess) {
		this.isDeleteSuccess = isDeleteSuccess;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public boolean getIsIdCardNoExist() {
		return isIdCardNoExist;
	}

	public void setIdCardNoExist(boolean isIdCardNoExist) {
		this.isIdCardNoExist = isIdCardNoExist;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BusinessVO getBvo() {
		return bvo;
	}

	public void setBvo(BusinessVO bvo) {
		this.bvo = bvo;
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public boolean getIsBusinessRepeat() {
		return isBusinessRepeat;
	}

	public void setBusinessRepeat(boolean isBusinessRepeat) {
		this.isBusinessRepeat = isBusinessRepeat;
	}

	public void setAccountPauseOrDelete(boolean isAccountPauseOrDelete) {
		this.isAccountPauseOrDelete = isAccountPauseOrDelete;
	}

	public boolean getIsAccountPauseOrDelete() {
		return isAccountPauseOrDelete;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public ICostDAO getCostDAO() {
		return costDAO;
	}

	public void setCostDAO(ICostDAO costDAO) {
		this.costDAO = costDAO;
	}
	
	
}
