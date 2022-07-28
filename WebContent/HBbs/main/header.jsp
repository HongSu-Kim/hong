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
			<a href="/hong/HBBS/main/main.do">HS 게시판</a>
		</h1>
		<div class="box" style="float: right;">
		<%if(userId == null) { %>
			<p>
				<a href="/hong/HBBS/user/login.do" class="button">로그인</a>
				<a href="/hong/HBBS/user/join.do" class="button">회원가입</a>
			</p>
		<%} else { %>
			<p>
				<a href="/hong/HBBS/userInfo/userInfo.do" class="button">회원정보</a>
				<a href="/hong/HBBS/user/logout_ok.do" class="button">로그아웃</a>
			</p>
		<%} %>
		</div>
	</div>
</div>