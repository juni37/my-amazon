<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- cate_list.jsp -->
<html>
<head>
	<title>카테고리목록</title>
	<script type="text/javascript"> 
	function checkDel(cnum){
		var isDel = window.confirm("정말로 삭제하시겠습니까?");
		if (isDel){
			location.href="cate_delete.do?cnum="+cnum;
		} 
	}
</script>
</head>
<body>
<div align="center">
	<table border="1" width="500">
		<caption valign="top"><h2>카테고리 목록</h2></caption>
		<tr bgcolor="yellow">
			<th>번호</th>
			<th>카테고리코드</th>
			<th>카테고리이름</th>
			<th>수정|삭제</th>
		</tr> 
		
		<c:if test="${empty listCategory}">
		<tr>
			<td colspan="6">등록된 카테고리가 없습니다.</td>
		</tr>		
	</c:if>		
	<c:forEach var="dto" items="${listCategory}">
		<tr>
			<td>${dto.cnum}</td>
			<td>${dto.code}</td>
			<td>${dto.cname}</td>
			<td>
				<a href="cate_edit.do?cnum=${dto.cnum}">수정</a> | 
				<a href="javascript:checkDel('${dto.cnum}')">삭제</a>
			</td>
		</tr>		
	</c:forEach>
	</table>
</div>
</body>
</html>
