<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/join.css">

<script type="text/javascript">
	function join() {
		
		var f = document.joinForm;
		
		f.userBirth.value = f.userYear.value + f.userMonth.value +  f.userDay.value; 
	
		f.action = "join_ok.jsp";
		f.submit();

	}
</script>

</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="section" align="center">
		<div id="container" align="center">
		
			<jsp:include page="nav-left.jsp"/>
			
			<div id="content" >
				<h3 id="container-header">회원가입</h3>
				<form action="" method="post" name="joinForm">
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
					<div class="box row">
						<div class="box label">
							<label for="userName"><span>이름</span></label>
						</div>
						<div class="box input">
							<input type="text" name="userName"/>
						</div>
					</div>
					<div class="box row">
						<div class="box label">
							<label for="userYear"><span>생일</span></label>
						</div>
						<div class="box input">
							<input type="text" name="userYear" class="input-3"/>
							<input type="text" name="userMonth" class="input-3 center"/>
							<input type="text" name="userDay" class="input-3 right"/>
						</div>
					</div>
					<div class="box row">
						<div class="box label">
							<label for="userGender"><span>성별</span></label>
						</div>
						<div class="box input">
							<input type="text" name="userGender"/>
						</div>
					</div>
					<div class="box row">
						<div class="box label">
							<label for="userTel"><span>전화번호</span></label>
						</div>
						<div class="box input">
							<input type="text" name="userTel"/>
						</div>
					</div>
					<div class="box row">
						<div class="box label">
							<label for="userEmail"><span>이메일</span></label>
						</div>
						<div class="box input">
							<input type="text" name="userEmail"/>
						</div>
					</div>
					<input type="hidden" name="userBirth"/>
					<div style="margin-top: 30px;">
						<a href="javascript:join();" class="button">가입하기</a>
						<a href="javascript:history.back();" class="button">돌아가기</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>