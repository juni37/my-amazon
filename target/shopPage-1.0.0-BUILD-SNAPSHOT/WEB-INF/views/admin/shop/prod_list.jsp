<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"> 
	function checkDel(pnum, pimage){
		var isDel = window.confirm("������ �����Ͻðڽ��ϱ�?");
		if (isDel){
			location.href="prod_delete.do?pnum="+pnum + "&pimage="+pimage;
		} 
	}
</script>
<div align="center">
	<h2>��ǰ���</h2>
	<form name="f" action="prod_find.do" method="post">
		<select name="search">
			<option value="cate">ī�װ�����</option>
			<option value="prod">��ǰ��</option>
			<option value="all">��ü��ǰ</option>
		</select>
		<input type="text" name="searchString">
		<input type="submit" value="�˻�">		
	</form>
<table border="1" width="800">
	<tr bgcolor="yellow">
		<th>��ȣ</th>
		<th>��ǰ��</th>
		<th>��ǰ�ڵ�</th>
		<th>�̹���</th>
		<th>����</th>
		<th>����</th>
		<th>����|����</th>
	</tr>
<c:if test="${empty listProduct}">
	<tr>
		<td colspan="7">��ϵ� ��ǰ�� �����ϴ�.</td>
	</tr>		
</c:if>	
<c:forEach var="dto" items="${listProduct}">
	<tr>
		<td>${dto.pnum}</td>
		<td>${dto.pname}</td>
		<td>${dto.pcode}</td>
		<td>
			<a href="prod_view.do?pnum=${dto.pnum}">
				<img src="resources/files/${dto.pimage}" border="0" width="40" height="40">
			</a>
		</td>
		<td>${dto.price}</td>
		<td>${dto.pqty}</td>
		<td>
			<a href="prod_update.do?pnum=${dto.pnum}">����</a> |
			<a href="javascript:checkDel('${dto.pnum}','${dto.pimage}')">����</a>
		</td>
	</tr>
</c:forEach>
</table>
</div>