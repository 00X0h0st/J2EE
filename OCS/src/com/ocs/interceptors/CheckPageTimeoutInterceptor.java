package com.ocs.interceptors;

import java.util.Map;

import com.ocs.utils.CommonUtil;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * �����ҳ�Ƿ�ʱ��������
 * @author Leslie Leung
 */
public class CheckPageTimeoutInterceptor extends CommonUtil {
	@Override
	public String intercept(ActionInvocation ai) 
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ai.getInvocationContext().getSession();
		String userCode = (String) session.get("user");
		
		if(userCode == null) {//���userCodeΪnull��Ҳ����˵��ҳ��ʱ��
			return "pageTimeout";
		} else {//���userCode��Ϊnull��֤����ҳû��ʱ�����Լ�������ҳ�Ͻ��н�������
			return ai.invoke();
		}
	}
}
