<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- testFileUpload.jsp -->
<html>
<head>	
	<title>���Ͼ��ε�</title>
</head>
<body>
	<form name="f" action="fileUpload.do" method="post" enctype="multipart/form-data">
		<table border="1" width="400">
			<tr>
				<th>�̸�</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>���ϸ�</th>
				<td><input type="file" name="filename"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="���Ͼ��ε�"></td>
			</tr>
		</table>
	</form>
</body>
</html>