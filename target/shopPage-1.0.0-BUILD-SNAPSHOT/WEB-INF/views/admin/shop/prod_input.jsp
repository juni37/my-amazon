<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_input.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>��ǰ�Է�</title>
</head>
<body>
<div align="center">
<form name="f" action="prod_input.do" method="post" enctype="multipart/form-data">
	<table border="0" width="600">
		<caption>��ǰ���ī�װ�</caption>
		<tr>
			<th class="m2">ī�װ�</th>
			<td align="left">
				<select name="category_fk">
				<c:forEach var="dto" items="${listCategory}">
					<option value="${dto.code}">${dto.cname}[${dto.code}]</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th bgcolor="yellow">��ǰ��</th>
			<td align="left"><input type="text" name="pname"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">��ǰ�ڵ�</th>
			<td align="left"><input type="text" name="pcode"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">����ȸ��</th>
			<td align="left"><input type="text" name="pcompany"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">��ǰ�̹���</th>
			<td align="left"><input type="file" name="pimage"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">��ǰ����</th>
			<td align="left"><input type="text" name="price"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">��ǰ����</th>
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
			<th bgcolor="yellow">��ǰ�Ұ�</th>
			<td align="left">
				<textarea name="pcontent" rows="5" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<th bgcolor="yellow">��ǰ����Ʈ</th>
			<td align="left"><input type="text" name="point"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="yellow">
				<input type="submit" value="��ǰ���">
				<input type="reset" value="���">
			</td>
		</tr>			
	</table>
</form>
</div>
</body>
</html>









