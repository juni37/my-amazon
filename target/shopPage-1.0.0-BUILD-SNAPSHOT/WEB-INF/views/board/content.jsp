<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- content.jsp -->
<html>
<head>
	<title>�۳��뺸��</title>
</head>
<body>	
<div align="center">
	<b>�۳��� ����</b>
	<table border="1" width="600">
		<tr>
			<th bgcolor="yellow" width="20%">�۹�ȣ</th>
			<td align="center" width="30%">${getBoard.num}</td>
			<th bgcolor="yellow" width="20%">��ȸ��</th>
			<td align="center" width="30%">${getBoard.readcount}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">�ۼ���</th>
			<td align="center" width="30%">${getBoard.writer}</td>
			<th bgcolor="yellow" width="20%">�ۼ���</th>
			<td align="center" width="30%">${getBoard.reg_date}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">������</th>
			<td width="80%" colspan="3">
				${getBoard.subject}
			</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">�۳���</th>
			<td width="80%" colspan="3">
				${getBoard.content}
			</td>
		</tr>
		<tr bgcolor="yellow">
			<td colspan="4" align="right">
				<input type="button" value="��۾���"
									onclick="window.location='write_board.do?num=${getBoard.num}&re_step=${getBoard.re_step}&re_level=${getBoard.re_level}'">
				<input type="button" value="�ۼ���"
									onclick="window.location='update_board.do?num=${getBoard.num}'">
				<input type="button" value="�ۻ���"
									onclick="window.location='delete_board.do?num=${getBoard.num}'">
				<input type="button" value="�۸��" 
									onclick="window.location='board.do'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>









