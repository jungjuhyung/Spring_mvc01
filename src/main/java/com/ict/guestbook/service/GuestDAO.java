package com.ict.guestbook.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class GuestDAO {
	private SqlSessionTemplate sqlSessionTemplate;
	
	public GuestDAO() {
		System.out.println("오나요");
	}
	// sqlSessionTemplate변수의 getter, setter
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 메서드들
	public List<GuestVO> getList() {
		// DB에서 오류가 많이 나기 때문에 try~catch로 확인
		System.out.println("테스트1");
		try {
			System.out.println("테스트2");
			List<GuestVO> list = null;
			list = sqlSessionTemplate.selectList("guestbook.list");
			return list;			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public GuestVO getOne(String idx) {
		GuestVO one = null;
		one = sqlSessionTemplate.selectOne("guestbook.selectone", idx);
		return one;
	}
	
	public int getInsert(GuestVO gvo) {
		
		try {
			int result = 0;
			result = sqlSessionTemplate.insert("guestbook.insert", gvo);
			return result;
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return 0;
	}
	public int getDelete(String idx) {
		try {
			int result = 0;
			result = sqlSessionTemplate.delete("guestbook.delete", idx);
			return result;
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return 0;
	}
	public int getUpdate(GuestVO gvo) {
		int result = 0;
		System.out.println(gvo.getIdx());
		result = sqlSessionTemplate.update("guestbook.update", gvo);
		return result;
	}
}
