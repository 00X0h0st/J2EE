package com.ocs.utils;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * �������һЩͨ�ù��ܶ�����һ�����������࣬�̳�������Ҫʵ�����е�ĳЩ������
 * ����ע��session��request����
 * @author Leslie Leung
 */
public class CommonUtil extends JdbcDaoSupport implements SessionAware, Interceptor {
	protected Map<String, Object> session;
	
	/**
	 * ע�����ӳ�
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

}
