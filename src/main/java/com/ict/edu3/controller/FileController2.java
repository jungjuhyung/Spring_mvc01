package com.ict.edu3.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class FileController2 {	
	
	// 업로드 방법
	// pom.xml에서 파일 업로드와 다운로드에 관련된 라이브러리 등록
	// commons-fileupload, commons-io
	
	// servlet-context.xml에 파일 업로드용 클래스 등록
	// 업로드 시 파일 용량 제한 설정
	
	// @RequestParam("변수명") 으로 request의 특정 파라미터만 받을 수 있다.
	@PostMapping("f_up2.do")
	public ModelAndView fileUp2(
			@RequestParam("f_name") MultipartFile fname,
			@RequestParam("name") String name,
			HttpServletRequest request
			){
		try {
			ModelAndView mv = new ModelAndView("test03/result");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload2");
			
			// Spring 파일업로드의 단점은 같은 이름 처리르 하지 않습니다.
			// 저장 폴더에 같은 이름이 있으면 업로드 되지 않는다.
			String f_name = fname.getOriginalFilename();
			String file_type = fname.getContentType();
			long size = fname.getSize()/1024;
			
			// 실제 파일 업로드 작업
			// 올릴 파일을 바이트 배열로 만듬
			byte[] in = fname.getBytes();
			
			// 올릴 장소와 저장이름 지정
			// File out = new File(저장경로, 저장 이름)
			File out = new File(path, f_name);
			
			// 파일복사(업로드)
			FileCopyUtils.copy(in, out);
			
			mv.addObject("name", name);
			mv.addObject("f_name", f_name);
			mv.addObject("file_type", file_type);
			mv.addObject("size", size);
			
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 스프링 다운로드 방식
	// request로 넘어온 파라미터는 @RequestParam("f_name") 작성하지 않고 String f_name으로만 해도 된다.
	@GetMapping("down2.do")
	public void fileDown2(String f_name,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload2/"+f_name);
			String r_path = URLEncoder.encode(path, "utf-8");
			// 다운받을 브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + r_path);
			
			File file = new File(new String(path.getBytes(), "utf-8"));
			FileInputStream in = new FileInputStream(file);
			
			// response가 브라우저로 보내는 것이 된다.
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
