<%@page import="com.huserInfo.HUserInfoDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DBConn"%>
<%@page import="com.hbbs.HBbsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<jsp:useBean id="dto" class="com.hbbs.HBbsDTO"/>
<jsp:setProperty property="*" name="dto"/>
<%
	PrintWriter script = response.getWriter();
	Connection conn = DBConn.getConnection();
	HBbsDAO dao = new HBbsDAO(conn);
	
	dto.setBbsId(dao.getMaxNum() + 1);

	script.print("<script>");
	script.print("alert("+ dto.getBbsId() + " : " + dto.getBbsCategory() + ");");
	script.print("alert("+ dto.getBbsCategory() + ");");
	script.print("alert("+ dto.getUserId() + ");");
	script.print("alert("+ dto.getBbsTitle() + ");");
	script.print("alert("+ dto.getBbsContent() + ");");
	script.print("</script>");
	
	int result = dao.insertHBbs(dto);
	
	if (result == 0) {
		script.print("<script>");
		script.print("alert('작성실패!');");
		script.print("history.back();");
		script.print("</script>");
	} else {
		new HUserInfoDAO(conn).updateCreatedCount(dto.getUserId());
		script.print("<script>");
		script.print("alert('작성성공!');");
		script.print("location='main.jsp'");
		script.print("</script>");
	}
	
	




%>