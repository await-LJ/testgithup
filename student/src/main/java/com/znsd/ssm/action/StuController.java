package com.znsd.ssm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znsd.ssm.entities.Cla;
import com.znsd.ssm.entities.Stu;
import com.znsd.ssm.service.StuService;

@Controller
public class StuController {
	@Autowired
	StuService service;
	
	@RequestMapping(value = "/index")
	public String query() {
		
		return "homepage";
		
	}
	@ResponseBody
	@RequestMapping(value = "/query")
	public Map<String, Object> query(@RequestParam(required = false, defaultValue = "1") Integer page,@RequestParam(required = false, defaultValue = "10") Integer rows){//页数
		Integer count=rows*(page-1);
		Map<String, Object> map = new HashMap<String, Object>();
		int a=service.count();
		List<Map<String ,Object>> l=new ArrayList<Map<String, Object>>();
		List<Stu> list = service.limit(count, rows);
		for (Stu sub : list) {
			Map<String ,Object> m=new HashMap<String, Object>();
			m.put("sid", sub.getSid());
			m.put("sname", sub.getSname());
			m.put("sex", sub.getSex()!=0?"男":"女");
			m.put("hobby", sub.getHobby());
			m.put("cname", sub.getCla().getCname());
			l.add(m);
		}
		map.put("total", a);
		map.put("rows", l);
		return map;
	}
	@ResponseBody
	@RequestMapping(value ="/combobox")
	public List<Map<String ,Object>> combobox() {
		List<Map<String ,Object>> l=new ArrayList<Map<String, Object>>();
		List<Cla> list = service.query();
		for (Cla class1 : list) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", class1.getCid());
			map1.put("text", class1.getCname());
			l.add(map1);
		}
		
		return l;
	}
	

	@RequestMapping(value = "/stuAdd", method = RequestMethod.POST)
	@ResponseBody
	public Boolean stuaddpost(@RequestParam("cid") Integer cid,@RequestParam("cname") Integer cname,Stu stu, Map<String, Object> map) {
		System.out.println(cid);
		Cla cla= new Cla();
		cla.setCid(cid);
		cla.setCname(cname);
	
		stu.setCla(cla);
		service.stuadd(stu);
		return true;
		

	}
	@RequestMapping(value = "/stuUpdate")
	@ResponseBody
	public Boolean stuUpdatepost(@RequestParam("cname") Integer cname,Stu stu,Map<String,Object> map) {
		System.out.println(cname);
		Cla cla= service.queryId(cname);
		stu.setCla(cla);
		service.stuupdate(stu);
		return true;
		
	}
	@RequestMapping(value = "/stuDelect")
	@ResponseBody
	public Boolean stuDelect(@RequestParam("sid") Integer sid) {
			System.out.println(sid);
			service.studelete(sid);
			return true;
		
	}
	
	
}
