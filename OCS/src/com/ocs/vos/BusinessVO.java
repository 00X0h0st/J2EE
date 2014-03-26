package com.ocs.vos;

import com.ocs.entities.Business;

/**
 * ҵ���˺�ģ���VO������ֱ��ʹ��ʵ����
 * ����װ�����⵼��ʵ�����д������
 * @author Leslie Leung
 */
public class BusinessVO extends Business {
	private String idCardNo;	//��Ӧ��account���֤��
	private String realName;	//��Ӧ��account����
	private String costName;	//��Ӧ��cost������
	private String costDescr;	//��Ӧ��cost��������Ϣ
	
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getCostDescr() {
		return costDescr;
	}
	public void setCostDescr(String costDescr) {
		this.costDescr = costDescr;
	}
}
