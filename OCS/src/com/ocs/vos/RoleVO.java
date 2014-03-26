package com.ocs.vos;

import java.util.List;
import com.ocs.entities.Role;

/**
 * ��ɫ����ģ���VO
 * @author Leslie Leung
 */
public class RoleVO extends Role {
	private List<Integer> privilegeIds;	//Ȩ��id����
	private String privilegeNames;		//Ȩ������ƴ�������ַ���
	
	public List<Integer> getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(List<Integer> privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String getPrivilegeNames() {
		return privilegeNames;
	}
	public void setPrivilegeNames(String privilegeNames) {
		this.privilegeNames = privilegeNames;
	}	
}
