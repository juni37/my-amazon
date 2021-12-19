<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- testFileUpload.jsp -->
<html>
<head>	
	<title>파일업로드</title>
</head>
<body>
	<form name="f" action="fileUpload.do" method="post" enctype="multipart/form-data">
		<table border="1" width="400">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>파일명</th>
				<td><input type="file" name="filename"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="파일업로드"></td>
			</tr>
		</table>
	</form>
</body>
</html>