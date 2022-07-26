<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String userId = null;
	if (session.getAttribute("userId") != null) {
		userId = (String)session.getAttribute("userId");
	}

%>
<div id="header" align="center">
	<div id="container" align="left">
		<h1 style="padding-left: 20px; display: inline-block; margin-right: auto;">
			<a href="main.jsp">HS 게시판</a>
		</h1>
		<div class="box" style="float: right;">
		<%if(userId == null) { %>
			<p>
				<a href="login.jsp" class="button">로그인</a>
				<a href="join.jsp" class="button">회원가입</a>
			</p>
		<%} else { %>
			<p>
				<a href="userInfo.jsp" class="button">회원정보</a>
				<a href="logout_ok.jsp" class="button">로그아웃</a>
			</p>
		<%} %>
		</div>
	</div>
</div>