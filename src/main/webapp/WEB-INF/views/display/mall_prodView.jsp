<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="mall_top.jsp"%>
<script type="text/javascript">
	function goCart(){
		document.f.action="mall_cartAdd.do"
		document.f.submit()
	}
</script>
<table width="99%" border="0" class="outline">
	<tr class="m1">
		<td colspan="2" align="center">
			<b>[${getProduct.pname}] 상품 정보</b>
		</td>
	</tr>
	<tr>
		<td align="center" class="m3">
			<img src="resources/files/${getProduct.pimage}" width="200" height="200">
		</td>
		<td class="m3">
			<form name="f" method="post">
				상품번호 : ${getProduct.pnum}<br>
				상품이름 : ${getProduct.pname}<br>
				상품가격 : <font color="red">${getProduct.price}</font>원<br>
				상품포인트 : <font color="red">[${getProduct.point}]</font>point<br>
				상품갯수 : <input type="text" name="qty" size="3" value="1">개<br><br>
				<input type="hidden" name="pnum" value="${getProduct.pnum}">
				<input type="hidden" name="select" value="${param.select}">
				<a href="javascript:goCart()">장바구니</a>
				<a href="javascript:goOrder()">즉시구매</a>
			</form>	
		</td>
	</tr>	
	<tr>
		<td colspan="2" align="left">
			<b>상품 상세 설명</b><br>
			${getProduct.pcontent}
		</td>
	</tr>		
</table>
<%@ include file="mall_bottom.jsp"%>