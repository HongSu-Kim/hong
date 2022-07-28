package com.main;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbbs.HBbsDAO;
import com.hbbs.HBbsDTO;
import com.util.DBConn;
import com.util.MyPage;

public class MainServlet extends HttpServlet {

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

		Connection conn = DBConn.getConnection();
		HBbsDAO dao = new HBbsDAO(conn);

		MyPage myPage = new MyPage();

		String url;

		if (uri.indexOf("main.do") != -1) {
			
			// page 
			String pageNum = req.getParameter("pageNum");
			int currentPage = 1;

			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			}

			// search
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "BBSTITLE";
				searchValue = "";
			}

			// list - bbsId
			int dataCount = dao.getDataCount(searchKey, searchValue);
			int numPerPage = 5;
			int totalPage = myPage.getPageCount(numPerPage, dataCount);

			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			List<HBbsDTO> list = dao.getHBbsList(start, end, searchKey, searchValue);

			// searchUrl
			String searchUrl = "";
			if (!searchValue.equals("")) {
				searchUrl = "searchKey=" + searchKey;
				searchUrl += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			// mainUrl
			String mainUrl = cp + "/HBbs/main/main.do";

			if (!searchUrl.equals("")) {
				mainUrl += "?" + searchUrl;
			}

			String pageIndexList = myPage.pageIndexList(currentPage, totalPage, mainUrl);
			
			req.setAttribute("list", list);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);

			url = "/HBbs/main/main.jsp";
			forward(req, resp, url);
			
		}
		
		DBConn.close();

	}

}
