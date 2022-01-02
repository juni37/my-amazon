<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_outQty.jsp -->
<html>
<head>
	<title>상품출고</title>
</head>
<body>
	<div align="center">
	<h2>상 품 출 고</h2>
	<form name="f" action="prod_outQty.do" method="post">
		현재 상품 갯수 : ${getProduct.pqty}개<br>
		${getProduct.pname} 상품 출고 : <input type="text" name="outqty">개
		<input type="hidden" name="pnum" value="${getProduct.pnum}"/>
		<input type="hidden" name="pqty" value="${getProduct.pqty}"/>
		<input type="submit" value="출고">		
	</form>
	</div>
</body>
</html>