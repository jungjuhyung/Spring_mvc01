package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start4Controller {
	
	// 컨트롤러 메서드의 반환형은 String, ModelandView
	// 반환형이 String이면 ViewName에 들어갈 값을 return에 작성해주면 된다.
	@GetMapping("start4.do")
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		/*
		 ModelAndView는 클라이언트로 같이 넘어가지 않기 때문에 return에 넣어주지 않으면 안된다.
		 단 model 객체는 이미 생성되어 있기 때문에 저장한 값을 사용할 수 있다.
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("computer", "14세대 i7");
		 */
		request.setAttribute("computer", "14세대 i7");
		return "result4";
	}
}
