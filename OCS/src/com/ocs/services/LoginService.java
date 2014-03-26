package com.ocs.services;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocs.daos.DAOException;
import com.ocs.interfaces.daos.IUserDAO;
import com.ocs.interfaces.services.ILoginService;
import com.ocs.utils.CheckCodeUtil;

/**
 * ��¼ģ��ʵ��
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class LoginService extends JdbcDaoSupport implements ILoginService {
	
	@Autowired
	private IUserDAO userDAO;
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * ����¼�Ƿ���ȷ
	 * @param userCode �û���
	 * @param password ����
	 * @param checkCode ��֤��
	 * @param session
	 * @return 0���û������������֤�����1����¼�ɹ�
	 * @throws ServiceException
	 */
	public int check(String userCode, String password, String checkCode, Map<String, Object> session) 
			throws ServiceException {
		String imageCode = (String)session.get("checkCode");
		
		//������֤�������Ƿ���ȷ
		if(checkCode == null || imageCode == null || !checkCode.equalsIgnoreCase(imageCode)) {
			return 0;	
		}

		if(userCode == null || userCode.length() == 0) {
			return 0;
		}

		Object[] params = {userCode};
		
		try {
			String sql = "SELECT password FROM user_info WHERE user_code=?";
			
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				if(password.equals(list.get(0).get("password").toString() ) ) {
					//��userCode�Ž�session��������ҳ��ʱ����
					session.put("user", userCode);
					
					//�Ѹ��û���ӵ�е�Ȩ�޷Ž�session������Ȩ�޿���
					List<Integer> privilegeIds = null;
					try {
						privilegeIds = userDAO.findUserPrivilegeIds(userCode);
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.put("userPrivileges", privilegeIds);
					
					return 1;
				}
			}
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("�����¼�Ƿ���ȷʧ�ܣ�",e);
		} 

	}

	/**
	 * ������֤��ͼƬ
	 * @param session 
	 * @return ����֤��ͼƬת��Ϊ����ʽ�������
	 * @throws ServiceException
	 */
	public InputStream generateCheckCode(Map<String, Object> session) 
			throws ServiceException {
		//������֤��ͼƬ
		Map<String, BufferedImage> map = CheckCodeUtil.createImage();
		//ͨ�������õ�Ψһ���ɵ���֤��
		String imageCode = map.keySet().iterator().next();
		//����֤�뱣����session�У����ڵ�¼ʱʹ��
		session.put("checkCode", imageCode);
		//������֤������֤��ͼƬ
		BufferedImage checkCodeImg = map.get(imageCode);
		//��ͼƬת��Ϊ�����������
		try {
			InputStream checkCodeStream = CheckCodeUtil.getInputStream(checkCodeImg);
			return checkCodeStream;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("������֤��ͼƬ����", e);
		}

	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
