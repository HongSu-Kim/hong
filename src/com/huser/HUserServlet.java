package com.huser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huserInfo.HUserInfoDAO;
import com.util.DBConn;
import com.util.MyPage;

public class HUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		PrintWriter script = resp.getWriter();

		Connection conn = DBConn.getConnection();
		HUserDAO dao = new HUserDAO(conn);

		MyPage myPage = new MyPage();

		String url;
		
		if (uri.indexOf("join.do") != -1) {

			url = "/HBbs/user/join.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("join_ok.do") != -1) {
			
			HUserDTO dto = new HUserDTO();

			dto.setUserId(req.getParameter("userId"));
			dto.setUserPassword(req.getParameter("userPassword"));
			dto.setUserName(req.getParameter("userName"));
			dto.setUserBirth(req.getParameter("userBirth"));
			dto.setUserGender(req.getParameter("userGender"));
			dto.setUserTel(req.getParameter("userTel"));
			dto.setUserEmail(req.getParameter("userEmail"));
			
			int result = dao.insertHuser(dto);

			if (result == 0) {
				
				script.print("<script>");
				script.print("alert('가입실패!');");
				script.print("history.back();");
				script.print("</script>");
				
			} else {
				
				new HUserInfoDAO(conn).insertData(dto.getUserId());

				url = cp + "/HBBS/main/main.do";
				resp.sendRedirect(url);
				
			}
			
		} else if (uri.indexOf("login.do") != -1) {
			
			url = "/HBbs/user/login.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("login_ok.do") != -1) {

			String password = req.getParameter("userPassword");
			
			HUserDTO dto = new HUserDTO();
			dto = dao.getHUser(req.getParameter("userId"));
			
			if (dto == null) {
				
				script.print("<script>");
				script.print("alert('존재하지 않는 아이디!');");
				script.print("history.back();");
				script.print("</script>");
				
			}
			
			if (!password.equals(dto.getUserPassword())) {
				
				script.print("<script>");
				script.print("alert('비밀번호 틀림!');");
				script.print("history.back();");
				script.print("</script>");
				
			} else {
				
				req.getSession().setAttribute("userId", dto.getUserId());
				new HUserInfoDAO(conn).updateLastDate(dto.getUserId());

				url = cp + "/HBBS/main/main.do";
				resp.sendRedirect(url);
				
			}
			
		} else if (uri.indexOf("logout_ok.do") != -1) {

			req.getSession().setAttribute("userId", null);

			url = cp + "/HBBS/main/main.do";
			resp.sendRedirect(url);
			
		}
		
		DBConn.close();

	}
}
