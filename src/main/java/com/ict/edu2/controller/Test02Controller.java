package com.ict.edu2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu.service.Test01Service;
import com.ict.edu2.service.Test02Service;
import com.ict.edu2.vo.VO;

@Controller
public class Test02Controller {
	@Autowired
	private Test02Service test02Service;
	
	/*
	@PostMapping("calc.do")
	public ModelAndView calc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("test02/result");
		String s1 = request.getParameter("s1");
		String s2 = request.getParameter("s2");
		String op = request.getParameter("op");
		String cPage = request.getParameter("cPage");
		
		String[] hobby = request.getParameterValues("hobby");
		
		int su1 = Integer.parseInt(s1);
		int su2 = Integer.parseInt(s2);
		
		int result = test02Service.getCalc(su1, su2, op);
		mv.addObject("result", result);
		mv.addObject("s1", s1);
		mv.addObject("s2", s2);
		mv.addObject("op", op);
		mv.addObject("cPage", cPage);
		mv.addObject("hobby", hobby);
		return mv;
	}
	*/
	/*
	// 파라미터값을 vo에 담기 위해 VO를 메서드 인자로 설정
	// vo 안에 있는 변수는 파라미터 이름과 같아야 한다.
	// 이렇게 하면 자동으로 VO 안에 파라미터가 저장되서 온다.
	@PostMapping("calc.do")
	public ModelAndView calc(VO vo) {
		ModelAndView mv = new ModelAndView("test02/result");
		int su1 = Integer.parseInt(vo.getS1());
		int su2 = Integer.parseInt(vo.getS2());
		
		int result = test02Service.getCalc(su1, su2, vo.getOp());
		
		vo.setResult(String.valueOf(result));
		mv.addObject("vo", vo);

		return mv;
	}
	*/
	
	@PostMapping("calc.do")
	public ModelAndView calc(@ModelAttribute("vo2")VO vo) {
		ModelAndView mv = new ModelAndView("test02/result");
		int su1 = Integer.parseInt(vo.getS1());
		int su2 = Integer.parseInt(vo.getS2());
		
		int result = test02Service.getCalc(su1, su2, vo.getOp());
		
		vo.setResult(String.valueOf(result));
		
		// @ModelAttribute("vo")VO vo => 이게 Model에 자동으로 저장해준다.
		// mv.addObject("vo2", vo);
		
		return mv;
	}
	
}
