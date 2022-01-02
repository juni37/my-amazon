<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_inQty.jsp -->
<html>
<head>
	<title>상품입고</title>
</head>
<body>
	<div align="center">
	<h2>상 품 입 고</h2>
	<form name="f" action="prod_inQty.do" method="post">
		${getProduct.pname} 상품 입고 : <input type="text" name="inqty">개
		<input type="hidden" name="pnum" value="${getProduct.pnum}"/>
		<input type="hidden" name="pqty" value="${getProduct.pqty}"/>
		<input type="submit" value="입고">		
	</form>
	</div>
</body>
</html>