package com.hbbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huserInfo.HUserInfoDAO;
import com.util.DBConn;
import com.util.MyPage;

public class HBbsServlet extends HttpServlet {

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
		HBbsDAO dao = new HBbsDAO(conn);

		MyPage myPage = new MyPage();

		String url;
		
		if (uri.indexOf("created.do") != -1) {
			
			if (req.getSession().getAttribute("userId") == null){
				
				script.print("<script>");
				script.print("alert('로그인 필요!');");
				script.print("</script>");
				
				url = cp + "/HBBS/user/login.do";
				resp.sendRedirect(url);
				return;
			}

			url = "/HBbs/bbs/created.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("created_ok.do") != -1) {
			
			HBbsDTO dto = new HBbsDTO();			

			dto.setBbsCategory(req.getParameter("bbsCategory"));
			dto.setUserId(req.getParameter("userId"));
			dto.setBbsTitle(req.getParameter("bbsTitle"));
			dto.setBbsContent(req.getParameter("bbsContent"));
			
			int result = dao.insertHBbs(dto);
			
			if (result == 0) {

				script.print("<script>");
				script.print("alert('작성실패!');");
				script.print("history.back();");
				script.print("</script>");
				
			} else {
				
				new HUserInfoDAO(conn).updateCreatedCount(dto.getUserId());
				url = cp + "/HBBS/main/main.do";
				resp.sendRedirect(url);
				
			}
			
		} else if (uri.indexOf("updated.do") != -1) {

			url = "/HBbs/bbs/updated.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("updated_ok.do") != -1) {
			
			HBbsDTO dto = new HBbsDTO();			

			dto.setBbsCategory(req.getParameter("bbsCategory"));
			dto.setBbsTitle(req.getParameter("bbsTitle"));
			dto.setBbsContent(req.getParameter("bbsContent"));
			dto.setBbsId(Integer.parseInt(req.getParameter("bbsId")));
			
			int result = dao.updateHBbs(dto);
			
			if (result == 0) {

				script.print("<script>");
				script.print("alert('수정실패!');");
				script.print("history.back();");
				script.print("</script>");
				
			} else {
				
				url = cp + "/HBBS/main/main.do";
				resp.sendRedirect(url);
				
			}
			
		} else if (uri.indexOf("view.do") != -1) {

			url = "/HBbs/bbs/view.jsp";
			forward(req, resp, url);
			
		}
		
	}

}
