<%@page import="com.huserInfo.HUserInfoDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DBConn"%>
<%@page import="com.huser.HUserDAO"%>
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

	int result = dao.insertHuser(dto);

	if (result == 0) {
		script.print("<script>");
		script.print("alert('가입실패!');");
		script.print("history.back();");
		script.print("</script>");
	} else {
		new HUserInfoDAO(conn).insertData(dto.getUserId());
		script.print("<script>");
		script.print("alert('가입성공!');");
		script.print("location='main.jsp';");
		script.print("</script>");
	}

	script.print("<script>");
	script.print("alert('잘못된 접근!');");
	script.print("location='main.jsp';");
	script.print("</script>");
%>