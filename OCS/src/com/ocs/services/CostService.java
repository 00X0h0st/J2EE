package com.ocs.services;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocs.entities.Cost;
import com.ocs.interfaces.services.ICostService;
import com.ocs.mappers.cost.CostMapper;

/**
 * �ʷѹ����ҵ���߼�
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class CostService extends JdbcDaoSupport implements ICostService {
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * ����ĳcost����ϸ��Ϣ
	 * @param id
	 * @return
	 */
	public Cost detail(int id) 
			throws ServiceException {
		String sql = "SELECT * FROM cost WHERE id=?";
		Object[] params = {id};
		Cost c = null;
		try {
			c = this.getJdbcTemplate().queryForObject(sql, params, new CostMapper());
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("�鿴�ʷ�����ʧ��", e);
		}
		return c;
	}
	
	/**
	 * ����ĳ�ʷ�
	 * @param id ���ʷ�id
	 */
	public void start(int id) 
			throws ServiceException {
		String sql = "UPDATE cost SET status='1',starttime=sysdate WHERE id=?";
		Object[] params = {id};
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("��ͨĳ�ʷ�ʧ��", e);
		}		
	}

	/**
	 * �޸�һ���ʷ�����
	 * @param cost ���ʷ�����
	 * @throws ServiceException
	 */
	public void update(Cost cost) 
			throws ServiceException {
		if(cost == null) {
			return;
		}
		
		//�����������ʽ����
		if(!cost.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9_.]{1,50}$")) {
			return;
		}
		
		String sql = "UPDATE cost SET name=?," +
				"base_duration=?,base_cost=?," +
				"unit_cost=?,descr=?,cost_type=? " +
				"WHERE id=?";
		
		Object[] params = {cost.getName(), cost.getBasicDuration(),
				cost.getBasicCost(), cost.getUnitCost(), cost.getDescr(),
				cost.getCostType(), cost.getId()};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("�޸�cost����ʧ��", e);
		}		
	}
	
	/**
	 * ɾ���ʷ�ģ���е�һ������
	 * @param id Ŀ�����ݵ�id
	 * @throws ServiceException
	 */
	public void delete(int id)
			throws ServiceException {
		String sql = "DELETE FROM cost WHERE id=?";
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("ɾ���ʷ�����ʧ��", e);
		}
	}
	
	/**
	 * �����ݿ��в���һ���ʷ�����
	 * @param cost
	 * @throws ServiceException
	 */
	public void add(Cost cost) 
			throws ServiceException {
		
		if(cost == null) {
			return;
		}
		
		//�����������ʽ����
		if(!cost.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9_.]{1,50}$")) {
			return;
		}
		
		String sql = "INSERT INTO cost " +
				"VALUES(cost_seq.nextval,?,?,?,?,'0',?,sysdate,null,?)";
		
		Object[] params = {cost.getName(), cost.getBasicDuration(),
				cost.getBasicCost(), cost.getUnitCost(),
				cost.getDescr(), cost.getCostType()};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("�����ʷ�����ʧ�ܣ�",e);
		}		
	}
	
	/**
	 * ���շ�ҳ�ҵ�һ���ʷ�����
	 * @param page ҳ��
	 * @param pageSize ҳ�������������
	 * @return
	 * @throws ServiceException
	 */
	public List<Cost> find(int page, int pageSize) 
			throws ServiceException {
		
		String sql = "SELECT * FROM (" +
				" SELECT c.*,rownum r FROM cost c WHERE rownum<?" +
				") WHERE r>? ";
		
		Object[] params = {page*pageSize+1, (page-1)*pageSize};
		
		try {
			List<Cost> costs = this.getJdbcTemplate().query(sql, params, new CostMapper());
			return costs;
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("Costģ���ҳ��ѯ����", e);
		}
	}
	
	/**
	 * �ҵ���ҳ��
	 * @param pageSize ҳ�������������
	 * @return
	 * @throws ServiceException
	 */
	public int findTotalPages(int pageSize) 
			throws ServiceException {
		
		String sql = "SELECT count(id) FROM cost";
		int rows;
		try {
			rows = this.getJdbcTemplate().queryForInt(sql);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("Costģ���ѯ��ҳ������", e);
		}
				
		/* ��������������ѯ��ҳ�� */
		if(rows % pageSize == 0) {
			return rows / pageSize;
		} else {
			return rows / pageSize + 1;
		}
	}
}
