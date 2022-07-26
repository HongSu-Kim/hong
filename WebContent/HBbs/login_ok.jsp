<%@page import="com.huserInfo.HUserInfoDAO"%>
<%@page import="com.huser.HUserDTO"%>
<%@page import="com.util.DBConn"%>
<%@page import="com.huser.HUserDAO"%>
<%@page import="java.sql.Connection"%>
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
	Connection conn = DBConn.getConnection();
	HUserDAO dao = new HUserDAO(conn);
	
	String password = dto.getUserPassword();
	dto = dao.getHUser(dto);
	

	if (dto == null) {
		script.print("<script>");
		script.print("alert('존재하지 않는 아이디!');");
		script.print("history.back();");
		script.print("</script>");
	} else {
		if (!password.equals(dto.getUserPassword())) {
			script.print("<script>");
			script.print("alert('비밀번호 틀림!');");
			script.print("history.back();");
			script.print("</script>");
		} else {
			session.setAttribute("userId", dto.getUserId());
			new HUserInfoDAO(conn).updateLastDate(dto.getUserId());
			script.print("<script>");
			script.print("alert('로그인성공!');");
			script.print("location='main.jsp';");
			script.print("</script>");
		}
	}

	script.print("<script>");
	script.print("alert('잘못된 접근!');");
	script.print("location='main.jsp';");
	script.print("</script>");
%>