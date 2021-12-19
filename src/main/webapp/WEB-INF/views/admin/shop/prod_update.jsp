<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_update.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>상품수정</title>
</head>
<body>
<div align="center">
<form name="f" action="prod_update.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pnum" value="${getProduct.pnum}"/>
	<table border="0" width="600">
		<caption>상품수정카테고리</caption>
		<tr>
			<th bgcolor="yellow">상품코드</th>
			<td align="left">${getProduct.pcode}</td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품명</th>
			<td align="left"><input type="text" name="pname" value="${getProduct.pname}"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">제조회사</th>
			<td align="left"><input type="text" name="pcompany" value="${getProduct.pcompany}"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품이미지</th>
			<td align="left">
				<img src="resources/files/${getProduct.pimage}" width="60" height="60">
				<input type="file" name="pimage">
				<input type="hidden" name="pimage2" value="${getProduct.pimage}"/>
			</td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품가격</th>
			<td align="left"><input type="text" name="price" value="${getProduct.price}"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품스팩</th>
			<td align="left">
				<select name="pspec">
					<c:forTokens var="spec" items="NORMAL,HIT,NEW,SALE" delims=",">
						<c:if test="${spec==getProduct.pspec}">
							<option value="${spec}" selected>${spec}</option>
						</c:if>
						<c:if test="${spec!=getProduct.pspec}">
							<option value="${spec}">${spec}</option>
						</c:if>
					</c:forTokens>
				</select>
			</td>	
		</tr>
		<tr>
			<th bgcolor="yellow">SALE(%)</th>
			<td align="left"><input type="text" name="sale" value="${getProduct.sale}"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품소개</th>
			<td align="left">
				<textarea name="pcontent" rows="5" cols="50">${getProduct.pcontent}</textarea>
			</td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품포인트</th>
			<td align="left"><input type="text" name="point" value="${getProduct.point}"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="yellow">
				<input type="submit" value="상품수정">
				<input type="reset" value="취소">
			</td>
		</tr>			
	</table>
</form>
</div>
</body>
</html>