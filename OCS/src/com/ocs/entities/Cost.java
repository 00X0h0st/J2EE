package com.ocs.entities;

import java.sql.Date;

/**
 * �ʷ�ʵ����
 * @author Leslie Leung
 */
public class Cost {
	private Integer id;		//�ʷ�ID
	private String name;	//�ʷ�����
	private Integer basicDuration;	//����ʱ��
	private Double basicCost;		//��������
	private Double unitCost;	//��λ����
	private String status;		//״̬
	private String descr;		//����
	private Date createTime;	//����ʱ��
	private Date startTime;		//��ͨʱ��
	private String costType;	//�ʷ�����
	
	public Integer getId() {
		return id;
	}	
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public Integer getBasicDuration() {
		return basicDuration;
	}	
	public void setBasicDuration(Integer basicDuration) {
		this.basicDuration = basicDuration;
	}	
	public Double getBasicCost() {
		return basicCost;
	}	
	public void setBasicCost(Double basicCost) {
		this.basicCost = basicCost;
	}	
	public Double getUnitCost() {
		return unitCost;
	}	
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}	
	public String getStatus() {
		return status;
	}	
	public void setStatus(String status) {
		this.status = status;
	}	
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
}
