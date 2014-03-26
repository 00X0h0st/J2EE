package com.ocs.vos;

import java.util.List;
import com.ocs.entities.User;

/**
 * User��VO
 * @author Leslie Leung
 */
public class UserVO extends User {
	private String roleNames;	//��ɫ���ַ�������
	private List<Integer> roleIds;	//��ɫid����
	
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	
}
