<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<jsp:useBean id="dto" class="com.huser.HUserDTO" />
<jsp:setProperty property="*" name="dto" />
<%
	PrintWriter script = response.getWriter();
	String userId = (String)session.getAttribute("userId");

	if (userId == null) {
		script.print("<script>");
		script.print("alert('로그인 정보 없음!');");
		script.print("history.back();");
		script.print("</script>");
	} else {
		session.setAttribute("userId", null);
		script.print("<script>");
		script.print("alert('로그아웃!');");
		script.print("location='main.jsp';");
		script.print("</script>");
	}
%>