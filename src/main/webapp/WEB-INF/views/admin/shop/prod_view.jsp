<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>��ǰ����</title>
</head>
<body>
<div align="center">
<form name="f" action="prod_list.do" method="post">
	<table border="1" width="600">
	<caption>��ǰ�󼼺���</caption>
		<tr>
			<th width="15%" bgcolor="yellow">��ǰ��ȣ</th>
			<td width="35%" align="center">${getProduct.pnum}</td>
			<th width="15%" bgcolor="yellow">��ǰ�ڵ�</th>
			<td width="35%" align="center">${getProduct.pcode}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">��ǰ��</th>
			<td width="35%" align="center">${getProduct.pname}</td>
			<th width="15%" bgcolor="yellow">����ȸ��</th>
			<td width="35%" align="center">${getProduct.pcompany}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">��ǰ�̹���</th>
			<td width="35%" align="center" colspan="3">
				<img src="resources/files/${getProduct.pimage}" border="0" width="80" height="80">
			</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">��ǰ����</th>
			<td width="35%" align="center">${getProduct.pqty}</td>
			<th width="15%" bgcolor="yellow">��ǰ����</th>
			<td width="35%" align="center">${getProduct.price}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">��ǰ����</th>
			<td width="35%" align="center">${getProduct.pspec}</td>
			<th width="15%" bgcolor="yellow">��ǰ����Ʈ</th>
			<td width="35%" align="center">${getProduct.point}</td>
		</tr>
		<tr>
			<th width="15%" bgcolor="yellow">��ǰ�Ұ�</th>
			<td width="35%" align="center" colspan="3">
				<textarea name="pcontents" rows="5" cols="50" readOnly>${getProduct.pcontent}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4" bgcolor="yellow" align="center">
				<input type="submit" value="���ư���">
			</td>
		</tr>
	</table>
</form>	
</div>
</body>
</html>










