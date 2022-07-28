<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="/hong/HBbs/css/style.css">
<link rel="stylesheet" href="/hong/HBbs/css/join.css">

<title>로그인</title>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="section" align="center">
	
		<div id="container" align="center">
		
			<jsp:include page="../main/nav-left.jsp"/>
			
			<div id="content" >
				<h3 id="container-header">로그인</h3>
				<form action="login_ok.do" method="post" name="loginForm">
					<div class="box row">
						<div class="box label">
							<label for="userId"><span>아이디</span></label>
						</div>
						<div class="box input">
							<input type="text" name="userId"/>
						</div>
					</div>
					<div class="box row">
						<div class="box label">
							<label for="userPassword"><span>비밀번호</span></label>
						</div>
						<div class="box input">
							<input type="text" name="userPassword"/>
						</div>
					</div>
					<div style="margin-top: 30px;">
						<a href="javascript:document.loginForm.submit();" class="button">로그인</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>