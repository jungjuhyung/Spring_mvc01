package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 컨트롤러를 어노테이션 하지 않으면 반드시 Controller 인터페이스 상속받아서 구현해야한다.
public class Start1Controller implements Controller {
	
	// 실제 실행되서 처리하는 메서드
	// Controller를 상속 받은 형태로 하면 무조건 반환형이 ModelAndView이다
	// @Controller 일 경우는 자유롭게 사용 가능하다.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 일처리 저장하는 곳 : Model
		// 되돌아갈 JSP 지정하는 것 : View
		// ModelAndView 객체는 Model과 View를 같이 가지고 있다.
		
		// view 지정 방법1)
		// ModelAndView mv = new ModelAndView();
		// mv.setViewName("되돌아갈 jsp이름");
		
		// viwe 지정 방법2)
		// ModelAndView mv = new ModelAndView("되돌아갈 jsp이름");
		
		ModelAndView mv = new ModelAndView();
		// views 경로도 지정하지 않고 .jsp도 붙이지 않는다.
		mv.setViewName("result1");
		
		// 일처리 하는 코드
		// 일처리=비지니스 로직 => service => service 임플 => 맵퍼(여기선 DAO) => DB처리
		// 저장은 JSP처럼 필요한 정보를 ModelAndView를 이용해서 저장
		
		// 저장 방법1) ModelAndView 이용
		// mv.addObject("이름", 값);
		
		// 저장 방법2) request 이용
		// request.setAttribute("이름", 값);
		
		// 저장 방법3) Session 이용
		// request.getSession().setAttribute("이름", 값);
		
		
		mv.addObject("name", "홍길동");
		mv.addObject("age", 17);
		
		request.setAttribute("addr", "서울");
		
		request.getSession().setAttribute("phone", "010-4444-4444");
		
		return mv;
	}
}
