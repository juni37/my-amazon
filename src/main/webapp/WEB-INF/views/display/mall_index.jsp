<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_index.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
	<hr color="green" width="300">
	<h2> H I T</h2>
	<hr color="green" width="300">
	<c:if test="${empty pspec.HIT}">
		<h3>HIT상품이 없습니다</h3>
	</c:if>
 	<c:if test="${not empty pspec.HIT}">
 		<table width="800" border="0" align="center">
 			<c:set var="co" value="1"/>
 			<tr>
				<c:forEach var="dto" items="${pspec.HIT}">
					<td align="center">
						<img src="resources/files/${dto.pimage}" width="80" height="60"><br>
						${dto.pname}<br>
						<font color="red">${dto.price}</font>원<br>
						<font color="blue">${dto.point}</font>point
					</td>
					<c:set var="co" value="${co+1}"/>
					<c:if test="${co>3}">
						</tr><tr>
						<c:set var="co" value="1"/>
					</c:if>	
				</c:forEach>
			</tr>	
		</table>
	</c:if>
<hr color="green" width="300">
	<h2>N E W</h2>
	<hr color="green" width="300">
	<c:if test="${empty pspec.NEW}">
		<h3>NEW상품이 없습니다</h3>
	</c:if>
 	<c:if test="${not empty pspec.NEW}">
 		<table width="800" border="0" align="center">
 			<c:set var="co" value="1"/>
 			<tr>
				<c:forEach var="dto" items="${pspec.NEW}">
					<td align="center">
						<img src="resources/files/${dto.pimage}" width="80" height="60"><br>
						${dto.pname}<br>
						<font color="red">${dto.price}</font>원<br>
						<font color="blue">${dto.point}</font>point
					</td>
					<c:set var="co" value="${co+1}"/>
					<c:if test="${co>3}">
						</tr><tr>
						<c:set var="co" value="1"/>
					</c:if>	
				</c:forEach>
			</tr>	
		</table>
	</c:if>
	<hr color="green" width="300">
	<h2>S A L E</h2>
	<hr color="green" width="300">
	<c:if test="${empty pspec.SALE}">
		<h3>SALE상품이 없습니다</h3>
	</c:if>
 	<c:if test="${not empty pspec.SALE}">
 		<table width="800" border="0" align="center">
 			<c:set var="co" value="1"/>
 			<tr>
				<c:forEach var="dto" items="${pspec.SALE}">
					<td align="center">
						<img src="resources/files/${dto.pimage}" width="80" height="60"><br>
						${dto.pname}<br>
						<font color="red">${dto.price}</font>원<br>
						<font color="blue">${dto.point}</font>point
					</td>
					<c:set var="co" value="${co+1}"/>
					<c:if test="${co>3}">
						</tr><tr>
						<c:set var="co" value="1"/>
					</c:if>	
				</c:forEach>
			</tr>	
		</table>
	</c:if>
</div>