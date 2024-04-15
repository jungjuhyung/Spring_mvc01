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
public class FileController {
	
	// cos 라이브러리 사용
	// 현재 MultipartRequest, MultipartFile를 둘다 사용하고 있어서 충돌되고 있다.
	// MultipartFile을 serlvlet-context.xml에서 비활성화해야한다.
	@PostMapping("f_up.do")
	public ModelAndView fileUp(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mv = new ModelAndView("test03/result");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartRequest mr = 
					new MultipartRequest(request, path, 500*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			String name = mr.getParameter("name");
			String f_name = mr.getFilesystemName("f_name");
			String file_type = mr.getContentType("f_name");
			
			File file = mr.getFile("f_name");
			long size = file.length()/1024; // KB로 변환
			SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd hh:mm:ss E");
			String lastday = sdf.format(file.lastModified()); // 최종 수정 날짜
			
			mv.addObject("name", name);
			mv.addObject("f_name", f_name);
			mv.addObject("file_type", file_type);
			mv.addObject("size", size);
			mv.addObject("lastday", lastday);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// 다운로드
	@GetMapping("down.do")
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			// 한글 처리
			String r_path = URLEncoder.encode(path, "utf-8");
			
			// 브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + r_path);
			
			File file = new File(new String(path.getBytes(), "utf-8"));
			int b;
			
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			
			while ((b=bis.read()) != -1) {
				bos.write(b);
				bos.flush();
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
                bos.close();
                bis.close();
                fis.close();
			} catch (Exception e2) {
			}
		}
	}
	
}
