package com.ocs.daos;


import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.ocs.entities.Cost;
import com.ocs.interfaces.daos.ICostDAO;
import com.ocs.mappers.cost.CostMapper;


/**
 * DAO�ӿ�ʵ����CostDAO�������ʷ�
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class CostDAO extends JdbcDaoSupport implements ICostDAO {
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
		
	/**
	 * ͨ�����ֲ���ĳ��Cost
	 * @param id ��ǰ�ʷѵ�id
	 * @param name
	 * @return
	 */
	public boolean findByName(Integer id, String name)
			throws DAOException {
		if(name == null || name.length() == 0) {
			return false;
		}
				
		String sql = "SELECT id FROM cost WHERE name=? ";
		Object[] params = {name};
		List<Map<String, Object>> rows = null;
		try {
			rows = this.getJdbcTemplate().queryForList(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("�����Ʋ�ѯ�ʷ�����ʧ�ܣ�",e);
		}
				
		if(rows != null && !rows.isEmpty() && !id.equals(Integer.parseInt(rows.get(0).get("id").toString() ) ) ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ����id����cost
	 * @param id
	 * @return һ���ʷ�����
	 * @throws DAOException
	 */
	public Cost findById(int id) 
			throws DAOException {
		String sql = "SELECT * FROM cost WHERE id=? ";
		Object[] params = {id};
		
		try {
			Cost c = this.getJdbcTemplate().queryForObject(sql, params, new CostMapper());
			return c;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("����id��ѯcostʧ�ܣ��޸�costҳ���������ʾ�����", e);
		}
	}
	
	/**
	 * �ҵ����е��ʷ�
	 * @return
	 */
	public List<Cost> findAll() 
			throws DAOException {
		
		String sql = "SELECT * FROM cost";
		try {
			List<Cost> costs = this.getJdbcTemplate().query(sql, new CostMapper());
			return costs;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("���������ʷ����ݳ���", e);
		}
	}

}
