<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>상품보기</title>
</head>
<body>
<div align="center">
<form name="f" action="prod_list.do" method="post">
	<table border="1" width="600">
	<caption>상품상세보기</caption>
		<tr>
			<th width="15%" bgcolor="yellow">상품번호</th>
			<td width="35%" align="center">${getProduct.pnum}</td>
			<th width="15%" bgcolor="yellow">상품코드</th>
			<td width="35%" align="center">${getProduct.pcode}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">상품명</th>
			<td width="35%" align="center">${getProduct.pname}</td>
			<th width="15%" bgcolor="yellow">제조회사</th>
			<td width="35%" align="center">${getProduct.pcompany}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">상품이미지</th>
			<td width="35%" align="center" colspan="3">
				<img src="resources/files/${getProduct.pimage}" border="0" width="80" height="80">
			</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">상품수량</th>
			<td width="35%" align="center">${getProduct.pqty}</td>
			<th width="15%" bgcolor="yellow">상품가격</th>
			<td width="35%" align="center">${getProduct.price}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">상품스펙</th>
			<td width="35%" align="center">${getProduct.pspec}</td>
			<th width="15%" bgcolor="yellow">상품포인트</th>
			<td width="35%" align="center">${getProduct.point}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">상품소개</th>
			<td width="35%" align="center" colspan="3">
				<textarea name="pcontents" rows="5" cols="50" readOnly>${getProduct.pcontent}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4" bgcolor="yellow" align="center">
				<input type="submit" value="돌아가기">
			</td>
		</tr>
	</table>
</form>	
</div>
</body>
</html>










