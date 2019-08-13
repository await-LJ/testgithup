package com.znsd.ssm.entities;

import java.io.Serializable;

public class Cla implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public Cla() {
		super();
	}
	public Cla(Integer cid, Integer cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	private Integer cid;
	private Integer cname;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCname() {
		return cname;
	}
	public void setCname(Integer cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Cla [cid=" + cid + ", cname=" + cname + "]";
	}
	
}
