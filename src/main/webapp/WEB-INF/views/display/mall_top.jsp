<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_top.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>¼îÇÎ¸ô</title>
	<script type="text/javascript">
		function nextPage(var1, var2){
			document.cgList.action = "mall_cgProdList.do";
			document.cgList.code.value = var1;
			document.cgList.cname.value = var2;
			document.cgList.submit();
		}
	</script>
</head>
<body>
	<form name="cgList" method="post">
		<input type="hidden" name="code">
		<input type="hidden" name="cname">
	</form>
	<div align="center">
		<table border="1" width="800" height="600">
			<tr height="10%">
				<td align="center" colspan="2">¼îÇÎ¸ô | Àå¹Ù±¸´Ï | MAIN</td>
			</tr>
			<tr>
				<td width="20%" align="center" valign="top">
					<font size="3">Ä«Å×°í¸®</font><br>
					<table border="1">
						<c:forEach var="cdto" items="${listCategory}">
							<tr>
								<td align="center">
									<a href="javascript:nextPage('${cdto.code}','${cdto.cname}')">
										${cdto.cname}[${cdto.code}]
									</a>
								</td>
							</tr>
						</c:forEach>	
					</table>
				</td>		
				<td>
								