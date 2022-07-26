<%@page import="java.net.URLEncoder"%>
<%@page import="com.hbbs.HBbsDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.util.MyPage"%>
<%@page import="com.hbbs.HBbsDAO"%>
<%@page import="com.util.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();

	Connection conn = DBConn.getConnection();
	HBbsDAO dao = new HBbsDAO(conn);
	
	MyPage myPage = new MyPage();
	
	// MyPage에서 넘어온 pageNum
	String pageNum = request.getParameter("pageNum");
	int currentPage = 1;
	
	// 처음 실행을 검사
	if(pageNum != null){
		currentPage = Integer.parseInt(pageNum);
	}
	
	// 검색 ---------------------------------------------------------------------
	
	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");
	
	if(searchValue != null){ // 검색을 했을 경우에
		
		// Get방식은 한글을 인코딩해서 보냄
		if(request.getMethod().equalsIgnoreCase("GET")){ // 대소문자를 구분하지 않고 get 방식으로 보내게 되면
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
	}else {
		
		searchKey = "BBSTITLE";
		searchValue = "";
		
	}
	
	// 검색 ---------------------------------------------------------------------

	
	// 전체 데이터 갯수 구하기
	int dataCount = dao.getDataCount(searchKey, searchValue);
	
	// 하나의 페이지에 보여줄 데이터 갯수(게시판에 게시물을 몇 개씩 보여줄 것인지)
	int numPerPage = 5;
	
	// 전체 페이지 갯수
	int totalPage = myPage.getPageCount(numPerPage, dataCount);
	
	// 삭제 시에 페이지 수가 줄었을 때 처리
	if(currentPage>totalPage){
		currentPage = totalPage;
	}
	
	// 데이터 베이스에서 가져올 rownum의 시작과 끝
	int start = (currentPage-1)*numPerPage+1;
	int end = currentPage*numPerPage;		
	
	List<HBbsDTO> list = dao.getHBbsList(start, end, searchKey, searchValue);

	
	//검색 - 검색기능을 사용할 경우 get방식의 주소에 추가로 적용.
	String param = "";
		//null이 아니면 검색을 한 것이다.
	if(!searchValue.equals("")) {
			
		//이때 주소를 만들어준다
		param = "?searchKey=" + searchKey;
		param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			
	}
	
	
	
	// 페이징 처리
	String mainUrl = "main.jsp" + param;
	String pageIndexList =
			myPage.pageIndexList(currentPage, totalPage, mainUrl);
	
	//글보기 주소
	String articleUrl = cp + "/HBbs/view.jsp";
	
	if(param.equals("")) { //검색을 안했을 때
		articleUrl += "?pageNum=" + currentPage;
	} else { //검색을 했을 때
		articleUrl += param + "&pageNum=" + currentPage;
	}
	
	DBConn.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS 게시판</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/main.css">

</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="section" align="center">
		
		<div id="container" align="center">
		
			<jsp:include page="nav-left.jsp"/>
			
			<div id="content" >
				<h3 id="container-header">게시판</h3>
				<form action="" method="post" name="mainForm">
					<%if (dataCount == 0) { %>
					<div><h1>작성된 글이 없습니다.</h1>
					</div>
					<%} else { %>
					<table style="width: 750px;">
						<tr>
							<th>글번호</th>
							<th>카테고리</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성/수정일</th>
							<th>조회수</th>
						</tr>
						<%for (HBbsDTO dto : list) {%>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<%} %>
					</table>
					<%} %>	
					<div style="margin-top: 30px;">
						<a href="created.jsp" class="button">글 작성</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>