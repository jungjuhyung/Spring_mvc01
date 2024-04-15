package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start3Controller {
	/*
	 * 요청이 오면 실행되야한다는 것을 표시해주는 어노테이션(구버젼)
	 * @RequestMapping(value = "start3.do", method = RequestMethod.GET)
	 * @RequestMapping(value = "start3.do", method = RequestMethod.POST)
	 * get, post상관없이 할 때 작성
	 * @RequestMapping("start3.do")
	 * 
	 * 요청이 오면 실행되야한다는 것을 표시해주는 어노테이션(신버젼)
	 * @GetMapping("start3.do")
	 * @PatchMapping("start3.do")
	 */
	
	@GetMapping("start3.do")
	public ModelAndView exec(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("result3");
		mv.addObject("city", "서울");
		return mv;
	}
}
