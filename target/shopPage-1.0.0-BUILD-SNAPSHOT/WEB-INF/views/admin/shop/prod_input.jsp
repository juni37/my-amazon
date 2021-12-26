<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_input.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>상품입력</title>
</head>
<body>
<div align="center">
<form name="f" action="prod_input.do" method="post" enctype="multipart/form-data">
	<table border="0" width="600">
		<caption>상품등록카테고리</caption>
		<tr>
			<th class="m2">카테고리</th>
			<td align="left">
				<select name="category_fk">
				<c:forEach var="dto" items="${listCategory}">
					<option value="${dto.code}">${dto.cname}[${dto.code}]</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품명</th>
			<td align="left"><input type="text" name="pname"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품코드</th>
			<td align="left"><input type="text" name="pcode"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">제조회사</th>
			<td align="left"><input type="text" name="pcompany"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품이미지</th>
			<td align="left"><input type="file" name="pimage"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품가격</th>
			<td align="left"><input type="text" name="price"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품스팩</th>
			<td align="left">
				<select name="pspec">
					<option value="NORMAL" selected>::NORMAL::</option>
					<option value="HIT">HIT</option>
					<option value="NEW">NEW</option>
					<option value="SALE">SALE</option>
				</select>
			</td>	
		</tr>
		<tr>
			<th bgcolor="yellow">SALE(%)</th>
			<td align="left"><input type="text" name="sale" value="0"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품소개</th>
			<td align="left">
				<textarea name="pcontent" rows="5" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<th bgcolor="yellow">상품포인트</th>
			<td align="left"><input type="text" name="point"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="yellow">
				<input type="submit" value="상품등록">
				<input type="reset" value="취소">
			</td>
		</tr>			
	</table>
</form>
</div>
</body>
</html>









