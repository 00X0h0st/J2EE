package com.ocs.interceptors;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.ocs.utils.CommonUtil;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * ����Ƿ�ӵ�и�Ȩ��
 * @author Leslie Leung
 */
public class CheckHasPrivilegeInterceptor extends CommonUtil {

	@Override
	public String intercept(ActionInvocation ai) 
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ai.getInvocationContext().getSession();
		List<Integer> privilegeIds = (List<Integer>) session.get("userPrivileges");

		HttpServletRequest request = ServletActionContext.getRequest();
		//��ȡ�������ַ����ַ
		StringBuffer url = request.getRequestURL();
		//��ȡ���ַ������������ж�����urlΪhttp://localhost:8080/OCS/cost/cost_find,��ȡcost
		String moduleName = url.substring(url.lastIndexOf("/"), url.lastIndexOf("_"));
		//Ȩ��id
		int id = moduleNameToInt(moduleName);
		//�������common��
		if(id == 0) {
			return ai.invoke();
		}
		//�����û���Ȩ��id����������û�иôӵ�ַ����ȡ������Ȩ��id���еĻ������Է��ʣ�û�оͲ����Է���
		//��������
		for(Integer privilegeId: privilegeIds) {
			if(id == privilegeId) {
				return ai.invoke();
			}
		}
		
		return "noPermission";
	}

	/**
	 * ��ģ�������ַ���ת��Ϊ����
	 * @param moduleName ģ������
	 * @return
	 */
	public int moduleNameToInt(String moduleName) {
		if("/role".equals(moduleName)) {
			return 1;
		} else if("/user".equals(moduleName)) {
			return 2;
		} else if("/cost".equals(moduleName)) {
			return 3;
		} else if("/account".equals(moduleName)) {
			return 4;
		} else if("/business".equals(moduleName)) {
			return 5;
		} else {
			return 0;	//0��ʾcommon��
		}
	}
}
