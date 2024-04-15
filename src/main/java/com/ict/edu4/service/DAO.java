package com.ict.edu4.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class DAO {
	private SqlSessionTemplate sqlSessionTemplate;
	
	// sqlSessionTemplate변수의 getter, setter
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public DAO() {
		System.out.println("테스트");
	}
	
	// 리스트
	public List<VO> getList() {
		try {
			List<VO> list = null;
			// sqlSessionTemplate는 기본적으로 auto commit이다.
			list = sqlSessionTemplate.selectList("members.list");
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
