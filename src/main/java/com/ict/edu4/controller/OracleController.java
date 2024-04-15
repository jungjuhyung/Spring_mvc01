package com.ict.edu4.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu4.service.DAO;
import com.ict.edu4.service.VO;

@Controller
public class OracleController {
	// @Inject : java에서 지원하는 어노테이션으로 Spring에서 지원하는 @Autowired와 기능이 같다.
	@Autowired
	private DAO dao;
	
	@GetMapping("oracle_list.do")
	public ModelAndView oracleList() {
		ModelAndView mr = new ModelAndView("test04/list");
		List<VO> list = dao.getList();
		mr.addObject("list", list);
		return mr;
	}
	
}
