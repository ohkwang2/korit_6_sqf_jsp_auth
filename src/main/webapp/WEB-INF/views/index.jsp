<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>홈페이지</title>
	<!-- 타 페이지에서 공통으로 사용하는 CSS를 파일로 불러옴 -->
	<%@ include file="/WEB-INF/include/common_css.jsp" %>
</head>
<body>
	<div class="container">
		<!-- 타 페이지에서 공통으로 사용하는 영역을 파일로 불러옴 -->
		<%@ include file="/WEB-INF/include/header.jsp" %>
		<div class="main-container">
			<h1>Servlet Session Authetication</h1>
		</div>
	</div>
</body>
</html>