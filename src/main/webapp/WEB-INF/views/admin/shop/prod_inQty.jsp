<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_inQty.jsp -->
<html>
<head>
	<title>��ǰ�԰�</title>
</head>
<body>
	<div align="center">
	<h2>�� ǰ �� ��</h2>
	<form name="f" action="prod_inQty.do" method="post">
		${getProduct.pname} ��ǰ �԰� : <input type="text" name="inqty">��
		<input type="hidden" name="pnum" value="${getProduct.pnum}"/>
		<input type="hidden" name="pqty" value="${getProduct.pqty}"/>
		<input type="submit" value="�԰�">		
	</form>
	</div>
</body>
</html>