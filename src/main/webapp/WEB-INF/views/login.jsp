<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<%@ include file="/WEB-INF/include/common_css.jsp" %>
		<style type="text/css">
		.login-form {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			box-sizing: border-box;
			margin: 100px 0px;
			border: 1px solid #dbdbdb;
			padding: 20px;
			width: 300px;
		}
		.login-input {
			box-sizing: border-box;
			margin-bottom: 10px;
			outline: none;
			border: 1px solid #dbdbdb;
			padding: 5px 20px;
			width: 100%;
			height: 40px;
			cursor: pointer;
		}
		
		.submit-button {
			box-sizing: border-box;
			border: 1px solid #dbdbdb;
			padding: 5px 20px;
			width: 100%;
			height: 40px;
			cursor: pointer;
			background: #ffffff;
		}
		
		.submit-button:hover {
			background: #fafafa;
		}
		
		.submit-button:active {
			background: #eeeeee;
		}
		
	</style>
</head>
<body>
	<div class="container">
		<!-- 타 페이지에서 공통으로 사용하는 영역을 파일로 불러옴 -->
		<%@ include file="/WEB-INF/include/header.jsp" %>
		<div class="main-container">
			<form class="login-form" action="/ssa/api/login" method="POST">
				<h1>Login</h1>
				<input class="login-input" name="username" placeholder="usename">
				<input class="login-input" type="password" name="password" placeholder="password">
				<button class="submit-button">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>