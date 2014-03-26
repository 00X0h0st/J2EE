package com.ocs.entities;

import java.sql.Date;

/**
 * ����Աʵ����
 * @author Leslie Leung
 */
public class User {
	private Integer id;		//id
	private String userCode;	//����
	private String password;	//����
	private String name;	//����
	private String telephone;	//�绰
	private String email;	//�����ʼ�
	private Date enrollDate;	//�Ǽ�ʱ��
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
