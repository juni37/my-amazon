<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" %>
<!-- list.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>게시판</title>
</head>
<body>
<div align="center">
	<b>글목록</b>
	<table border="0" width="800">
		<tr bgcolor="yellow">
			<td align="right"><a href="write_board.do">글쓰기</a></td>
		</tr>
	</table>
	<table border="1" width="800">
		<tr bgcolor="green">
			<th>번호</th>
			<th width="30%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
			<th>IP</th>
		</tr>

	<c:if test="${empty listBoard}">

		<tr>
			<td colspan="6">등록된 게시글이 없습니다.</td>
		</tr>		
	</c:if>		
	<c:forEach var="dto" items="${listBoard}">
		<tr>
			<td>${dto.num}</td>
			<td>
				<c:if test="${dto.re_level>0}">
					<img src="resources/img/level.gif" width="${dto.re_level*10}">
					<img src="resources/img/re.gif">
				</c:if>
				<a href="content_board.do?num=${dto.num}">
					${dto.subject}
				</a>
			</td>
			<td>${dto.writer}</td>
			<td>${dto.reg_date}</td>
			<td>${dto.readcount}</td>
			<td>${dto.ip}</td>
		</tr>		
	</c:forEach>
	</table>
	<c:if test="${getCount>0}">
		<c:if test="${startPage > pageBlock}">
			[<a href="board.do?pageNum=${startPage-pageBlock}">이전</a>]
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			[<a href="board.do?pageNum=${i}">${i}</a>]
		</c:forEach>
		<c:if test="${endPage < pageCount}">
			[<a href="board.do?pageNum=${startPage+pageBlock}">다음</a>]
		</c:if>
	</c:if>
</div>
</body>
</html>
