package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.entities.Cost;
import com.ocs.interfaces.daos.ICostDAO;
import com.ocs.interfaces.services.ICostService;
import com.ocs.services.ServiceException;

/**
 * �����ʷѵ�Action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class CostAction {
	private int id;
	private boolean isStartSuccess;	//�Ƿ�ͨ�ɹ�
	private String name;
	private int page = 1;	//Ĭ�ϴ򿪵�һҳ
	private int pageSize;	//ҳ����
	private int totalPages;		//��ҳ��
	private Cost cost;	//һ���ʷ�����
	private List<Cost> costs;	//��ѯ������һ������
	private boolean isCostRepeat;	//����cost�Ƿ��ظ�����Ϣ
	
	@Autowired
	private ICostService costService;
	@Autowired
	private ICostDAO costDAO;
	
	/**
	 * ��ת���ʷ�����ҳ��
	 * @return
	 */
	public String toCostDetail() {
		try {
			cost = costService.detail(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findDetailSuccess";
	}
	
	/**
	 * ����ĳ�ʷ�
	 * @return
	 */
	public String start() {
		try {
			costService.start(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "startSuccess";
	}
	
	/**
	 * ��������ת�������ʷ�ҳ��
	 * @return
	 */
	public String toAddCost() {
		return "toAddCost";
	}
	
	/**
	 * �޸�ĳcost
	 * @return
	 */
	public String updateCost() {
		try {
			costService.update(cost);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "updateCostSuccess";
	}
	
	/**
	 * ��ת���޸�costҳ�棬�������Ҫ�ѵ�ǰ�����ʷѵ�����������ʾ�ڶ�Ӧ�ı�����
	 * @return
	 */
	public String toUpdateCost() {
		try {
			cost = costDAO.findById(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "toUpdateCostSuccess";
	}
	
	/**
	 * ���÷�ҳ��ѯ
	 * @return
	 */
	public String find() {
		try {
			costs = costService.find(page, pageSize);
			totalPages = costService.findTotalPages(pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * ����ʱ���Cost�Ƿ��ظ�
	 * @return
	 */
	public String checkCostRepeat() {
		try {
			if(costDAO.findByName(id, name) == true) {//����������ƴ���
				isCostRepeat = true;
			} else {//�������Ʋ�����
				isCostRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "costChecked";
	}
	
	/**
	 * ִ��ɾ���ʷ����ݲ���
	 * @return
	 */
	public String delete() {
		try {
			costService.delete(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "deleteSuccess";
	}
	
	/**
	 * ��һ���ʷ�������ӵ����ݱ���
	 * @return
	 */
	public String add() {
		try {
			costService.add(cost);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "addSuccess";
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

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public boolean getIsStartSuccess() {
		return isStartSuccess;
	}

	public void setStartSuccess(boolean isStartSuccess) {
		this.isStartSuccess = isStartSuccess;
	}

	public boolean getIsCostRepeat() {
		return isCostRepeat;
	}

	public void setCostRepeat(boolean isCostRepeat) {
		this.isCostRepeat = isCostRepeat;
	}

	public ICostService getCostService() {
		return costService;
	}

	public void setCostService(ICostService costService) {
		this.costService = costService;
	}

	public ICostDAO getCostDAO() {
		return costDAO;
	}

	public void setCostDAO(ICostDAO costDAO) {
		this.costDAO = costDAO;
	}

	
}
