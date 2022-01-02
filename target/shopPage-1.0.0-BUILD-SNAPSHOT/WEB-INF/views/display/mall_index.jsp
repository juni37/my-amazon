<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_index.jsp -->
<%@ include file="mall_top.jsp"%>
<div align="center">
	<hr color="green" width="300">
	<h2> H I T</h2>
	<hr color="green" width="300">
	<c:if test="${empty viewProd.HIT}">
		<h3>HIT상품이 없습니다</h3>
	</c:if>
 	<c:if test="${not empty viewProd.HIT}">
 		<table width="80%" border="0" align="center">
 			<c:set var="co" value="1"/>
 			<tr>
				<c:forEach var="dto" items="${viewProd.HIT}">
					<td align="center">
						<a href="mall_view.do?select=HIT&pnum=${dto.pnum}">
							<img src="resources/files/${dto.pimage}" width="80" height="60">
						</a><br>
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
	<c:if test="${empty viewProd.NEW}">
		<h3>NEW상품이 없습니다</h3>
	</c:if>
 	<c:if test="${not empty viewProd.NEW}">
 		<table width="80%" border="0" align="center">
 			<c:set var="co" value="1"/>
 			<tr>
				<c:forEach var="dto" items="${viewProd.NEW}">
					<td align="center">
						<a href="mall_view.do?select=NEW&pnum=${dto.pnum}">
							<img src="resources/files/${dto.pimage}" width="80" height="60">
						</a><br>
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
	<c:if test="${empty viewProd.SALE}">
		<h3>SALE상품이 없습니다</h3>
	</c:if>
 	<c:if test="${not empty viewProd.SALE}">
 		<table width="80%" border="0" align="center">
 			<c:set var="co" value="1"/>
 			<tr>
				<c:forEach var="dto" items="${viewProd.SALE}">
					<td align="center">
						<a href="mall_view.do?select=SALE&pnum=${dto.pnum}">
							<img src="resources/files/${dto.pimage}" width="80" height="60">
						</a><br>
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
<%@ include file="mall_bottom.jsp"%>
