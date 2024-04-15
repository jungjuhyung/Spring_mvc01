package com.ict.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu.service.Test01Service;

@Controller
public class Test01Controller {
	
	// 사용할 service는 private 변수로 저장해주고 @Autowired로 생성된 객체를 연결해줘야한다.
	// 변수 이름과 참조하는 클래스의 어노테이션 id가 같을 때 : @Autowired를 사용
	// 이름이 다르면 @Qualifier("참조하려는 클래스의 어노테이션 id")를 추가한다.
	@Autowired
	private Test01Service test01Service;
	@GetMapping("hello.do")
	public ModelAndView HelloCommand() {
		ModelAndView mv = new ModelAndView("test01/result");
		String msg = test01Service.getGreeting();
		mv.addObject("msg", msg);
		return mv;
	}
}
