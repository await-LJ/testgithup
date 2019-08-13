package com.znsd.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znsd.ssm.Dao.StuDao;
import com.znsd.ssm.entities.Cla;
import com.znsd.ssm.entities.Stu;
import com.znsd.ssm.service.StuService;
@Service
public class StuServiceImpl implements StuService {
	@Autowired
	StuDao dao;
	
	public List<Stu> limit(int page, int count) {
		// TODO Auto-generated method stub
		return dao.limit(page, count);
	}

	public int stuadd(Stu stu) {
		// TODO Auto-generated method stub
		return dao.stuadd(stu);
	}

	public int stuupdate(Stu stu) {
		// TODO Auto-generated method stub
		return dao.stuUpdate(stu);
	}

	public int studelete(Integer sid) {
		// TODO Auto-generated method stub
		return dao.studelete(sid);
	}

	public int count() {
		// TODO Auto-generated method stub
		return dao.count();
	}

	public List<Cla> query() {
		// TODO Auto-generated method stub
		return dao.query();
	}

	public List<Stu> stuQuery() {
		// TODO Auto-generated method stub
		return dao.stuQuery();
	}

	public Cla queryId(Integer cname) {
		// TODO Auto-generated method stub
		return dao.queryId(cname);
	}

}
