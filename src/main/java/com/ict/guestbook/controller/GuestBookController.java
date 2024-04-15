package com.ict.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook.service.GuestDAO;
import com.ict.guestbook.service.GuestVO;


@Controller
public class GuestBookController {
	@Autowired
	private GuestDAO guestdao;
	
	@GetMapping("list.do")
	public ModelAndView list() {
		ModelAndView mr = new ModelAndView("guestbook/list");
		List<GuestVO> list = guestdao.getList();
		mr.addObject("list", list);
		return mr;
	}
	@GetMapping("write.do")
	public ModelAndView write() {
		ModelAndView mr = new ModelAndView("guestbook/write");
		return mr;
	}
	@PostMapping("write_ok.do")
	public ModelAndView writeOK(GuestVO vo) {
		// redirect: 값을 경로로 지정하면 다시 클라이언트로 가서 새로운 request를 가지고 간다.
		// 이때 .do를 하면 서블릿으로 보낼 수 있다.
		ModelAndView mr = new ModelAndView("redirect:list.do");
		
		int result = guestdao.getInsert(vo);
		return mr;
	}
	@GetMapping("onelist.do")
	public ModelAndView onelist(String idx) {
		ModelAndView mr = new ModelAndView("guestbook/onelist");
		GuestVO gvo = guestdao.getOne(idx);
		mr.addObject("g2vo", gvo);
		return mr;
	}
	@PostMapping("update.do")
	public ModelAndView update(String idx) {
		ModelAndView mr = new ModelAndView("guestbook/update");
		GuestVO gvo = guestdao.getOne(idx);
		mr.addObject("g2vo", gvo);
		return mr;
	}
	@PostMapping("update_ok.do")
	public ModelAndView updateOk(GuestVO gvo) {
		ModelAndView mr = new ModelAndView("redirect:onelist.do?idx="+gvo.getIdx());
		int res = guestdao.getUpdate(gvo);
		GuestVO g2vo = guestdao.getOne(gvo.getIdx());
		mr.addObject("g2vo", g2vo);
		return mr;
	}
	@PostMapping("delete.do")
	public ModelAndView delete(String idx, String pwd) {
		ModelAndView mr = new ModelAndView("guestbook/delete");
		mr.addObject("idx", idx);
		mr.addObject("pwd", pwd);
		return mr;
	}
	@PostMapping("delete_ok.do")
	public ModelAndView deleteok(String idx) {
		ModelAndView mr = new ModelAndView("redirect:list.do");
		int res = guestdao.getDelete(idx);
		return mr;
	}
}
