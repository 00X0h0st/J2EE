package com.ocs.entities;

import java.sql.Date;

/**
 * ҵ���˺�ʵ����
 * @author Leslie Leung
 */
public class Business {
	private Integer id;		//id
	private Integer accountId;		//���Ӧ���˻��˺�id
	private String unixHost;	//����������ip
	private String osUserName;	//�û���
	private String loginPassword;	//����
	private String status;	//״̬
	private Date createDate;	//��������
	private Date pauseDate;		//��ͣ����
	private Date closeDate;		//�ر�����
	private Integer costId;		//��Ӧ���ʷ�id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getUnixHost() {
		return unixHost;
	}
	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}
	public String getOsUserName() {
		return osUserName;
	}
	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPauseDate() {
		return pauseDate;
	}
	public void setPauseDate(Date pauseDate) {
		this.pauseDate = pauseDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
}
