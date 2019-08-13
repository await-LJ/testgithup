package com.znsd.ssm.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znsd.ssm.entities.Cla;
import com.znsd.ssm.entities.Stu;

public interface StuDao {
	public List<Stu> limit(@Param("page")int page,@Param("limit")int limit);
	public int stuadd(Stu stu);
	public int stuUpdate(Stu stu);
	public int studelete(Integer sid);
	public int count();
	public List<Cla> query();
	public List<Stu> stuQuery();
	public Cla queryId(@Param("cname") Integer cname);
}
