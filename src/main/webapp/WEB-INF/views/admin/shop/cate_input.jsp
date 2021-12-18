<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- cate_input.jsp -->
<html>
<head>
<title>카테고리등록</title>
</head>
<body>
<div align="center">
<form name="f" action="cate_input.do" method="post">
	<table border="1" width="400" height="150">
		<caption valign="top"><h2>카테고리등록</h2></caption>
		<tr>
			<th bgcolor="yellow">카테고리코드</th>
			<td><input type="text" name="code"></td>
		</tr>
		<tr>
			<th bgcolor="yellow">카테고리이름</th>
			<td><input type="text" name="cname"></td>
		</tr>
		<tr bgcolor="orange">
			<td colspan="2" align="center">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>	
</form>
</div>
</body>
</html>