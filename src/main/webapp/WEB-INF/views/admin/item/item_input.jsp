<%--
  Created by IntelliJ IDEA.
  User: Yujun Kim
  Date: 2021-12-18
  Time: ���� 3:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!-- item_input.jsp -->
<html>
<head>
    <title>��ǰ ���</title>
</head>
<body>
<div align="center">
    <form name="f" action="cate_input.do" method="post">
        <table border="1" width="400" height="150">
            <caption valign="top"><h2>ī�װ������</h2></caption>
            <tr>
                <th bgcolor="yellow">ī�װ����ڵ�</th>
                <td><input type="text" name="code"></td>
            </tr>
            <tr>
                <th bgcolor="yellow">ī�װ����̸�</th>
                <td><input type="text" name="cname"></td>
            </tr>
            <tr bgcolor="orange">
                <td colspan="2" align="center">
                    <input type="submit" value="���">
                    <input type="reset" value="���">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>