<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS 게시판</title>

<link rel="stylesheet" href="<%=cp %>/HBbs/css/style.css">
<link rel="stylesheet" href="<%=cp %>/HBbs/css/main.css">

</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="section" align="center">
		
		<div id="container" align="center">
		
			<jsp:include page="nav-left.jsp"/>
			
			<div id="content" >
				<h3 id="container-header">게시판</h3>
				<form action="" method="post" name="mainForm">
					<table style="width: 750px;" border="1px;">
						<tr>
							<th>글번호</th>
							<th>카테고리</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성/수정일</th>
							<th>조회수</th>
						</tr>
						<c:if test="${dataCount != 0 }">
							<c:forEach var="dto" items="${list }">
								<tr>
									<td>${dto.bbsId }</td>
									<td>${dto.bbsCategory }</td>
									<td>${dto.userId }</td>
									<td>
										<a href="/HBBS/bbs/view.do">${dto.bbsTitle }</a>
									</td>
									<c:if test="${dto.updatedDate == null}">
										<td>${dto.createdDate }</td>
									</c:if>
									<c:if test="${dto.updatedDate != null}">
										<td>${dto.updatedDate }</td>
									</c:if>
									<td>${dto.hitCount }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<c:if test="${dataCount == 0 }">
						<div>
							<h1>작성된 글이 없습니다.</h1>
						</div>
					</c:if>
					<div style="margin-top: 30px;">
						<c:if test="${dataCount != 0 }">
							${pageIndexList }
						</c:if>
						<a href="<%=cp %>/HBBS/bbs/created.do" class="button" style="float: right; margin-right: 30px;">글 작성</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>