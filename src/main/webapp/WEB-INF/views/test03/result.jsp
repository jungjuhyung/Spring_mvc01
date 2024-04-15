<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과보기</h2>
	<h3>
 		<li>올린 사람 : ${name}</li>
		<li>파일 이름 : ${f_name}</li>
		<li>파일 타입 : ${file_type}</li>
		<li>파일 크기 : ${size}KB</li>
		<li>수정 날짜 : ${lastday}</li>
		<!-- 위치와 파일이름을 파라미터로 사용하지만 위치가 고정되어 있기때문에 파일 이름만  파라미터로 전송 -->
		<li>다운로드 : <a href="down.do?f_name=${f_name}"><img src="resources/images/${f_name}"></a></li>
		<li>다운로드2 : <a href="down2.do?f_name=${f_name}"><img src="resources/images/${f_name}"></a></li>
	</h3>
</body>
</html>