<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 작성</title>

<link rel="stylesheet" href="<%=cp %>/HBbs/css/style.css">
<link rel="stylesheet" href="<%=cp %>/HBbs/css/created.css">

</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="section" align="center">
		<div id="container" align="center">
		
			<jsp:include page="../main/nav-left.jsp"/>
			
			<div id="content" >
				<h3 id="container-header">글 작성</h3>
				<form action="created_ok.do" method="post" name="createdForm">
					<div class="box row" style="padding: 0px; width: 770px; height: 70px;'">
						<div class="box row row-2" style="border: none;">
							<div class="box label">
								<label for="userId"><span>작성자</span></label>
							</div>
							<div class="box input input-2">
								<input type="text" name="userId" value="<%=session.getAttribute("userId")%>" readonly="readonly"/>
							</div>
						</div>
						<div class="box row row-2" style="border: none;">
							<div class="box label">
								<label for="bbsCategory"><span>카테고리</span></label>
							</div>
							<div class="box input input-2">
								<select name="bbsCategory" style="width: 200px; border: none;">
									<option value="">선택</option>
									<option value="자유게시판">자유게시판</option>
									<option value="정보게시판">정보게시판</option>
									<option value="이미지게시판">이미지게시판</option>
								</select>
							</div>
						</div>
					</div>
					<div class="box row">
						<div class="box label">
							<label for="bbsTitle"><span>제목</span></label>
						</div>
						<div class="box input">
							<input type="text" name="bbsTitle"/>
						</div>
					</div>
					<div class="box row" style="height: 170px;">
						<div class="box label">
							<label for="bbsContent"><span>내용</span></label>
						</div>
						<div class="box input">
							<textarea rows="10" cols="83" name="bbsContent" style="border: none; outline: none; resize: none;"></textarea>
						</div>
					</div>
					<div style="margin-top: 30px;">
						<a href="javascript:document.createdForm.submit();" class="button">작성완료</a>
						<a href="javascript:history.back();" class="button">돌아가기</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>