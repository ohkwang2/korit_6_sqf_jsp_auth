<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Mypage</title>
	<%@ include file="/WEB-INF/include/common_css.jsp" %>
		<style type="text/css">
		.mypage-form {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			box-sizing: border-box;
			border: 1px solid #dbdbdb;
			padding: 20px;
			width: 300px;
		}
		
		.mypage-form:nth-of-type(1) {
			margin-bottom: 20px;
		}
		
		.mypage-form:nth-of-type(2) {
			margin-bottom: 50px;
		}
		
		.mypage-input {
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
		<%@ include file="/WEB-INF/include/header.jsp" %>
		<div class="main-container">
			<form class="mypage-form" action="/ssa/api/mypage/edit" method="POST">
				<h1>Mypage</h1>
				<!-- disabled는 form에서 subbmit 했을 때 파라미터 값으로 안 날라감. but, readonly는 날라감 -->
				<input class="mypage-input" name="username" placeholder="usename" value="<%=user.getUsername() %>" disabled>
				<input class="mypage-input" name="name" placeholder="name" value="<%=user.getName() %>">
				<input class="mypage-input" name="email" placeholder="email" value="<%=user.getEmail() %>">
				<button class="submit-button">변경하기</button>
			</form>
			<form class="mypage-form" action="/ssa/api/mypage/password/edit" method="POST">
				<h1>Edit Password</h1>
				<input class="mypage-input" type="password" name="password" placeholder="password">
				<input class="mypage-input" type="password" name="checkPassword" placeholder="checkPassword">
				<button class="submit-button">변경하기</button>
			</form>
		</div>
	</div>
</body>
</html>