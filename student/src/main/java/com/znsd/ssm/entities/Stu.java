package com.znsd.ssm.entities;

import java.io.Serializable;

public class Stu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public Stu() {
		super();
	}
	public Stu( String sname, Integer sex, String hobby, Cla cla) {
		super();
		this.sname = sname;
		this.sex = sex;
		this.hobby = hobby;
		this.cla = cla;
	}
	private Integer sid;
	private String sname;
	private Integer sex;
	private String hobby;
	private Cla cla;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Cla getCla() {
		return cla;
	}
	public void setCla(Cla cla) {
		this.cla = cla;
	}
	@Override
	public String toString() {
		return "Stu [sid=" + sid + ", sname=" + sname + ", sex=" + sex + ", hobby=" + hobby + ", cla=" + cla + "]";
	}
	
}
