<%--
  Created by IntelliJ IDEA.
  User: Yujun Kim
  Date: 2021-12-18
  Time: 오후 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>파일 업로드</title>
</head>
<body>
    <form name = "f" action = "fileUpload.do" method = "post">
        <table border = "1" width = "400">
            <tr>
                <th>이름</th>
                <td><input type = "text" name = "name"></td>
            </tr>
            <tr>
                <th>파일</th>
                <td><input type = "file" name = "file"></td>
            </tr>
            <tr>
                <th>파일명</th>
                <td><input type = "text" name = "fileName"></td>
            </tr>
            <tr>
                <td align = "center" colspan="2"><input type = "submit" value = "파일 업로드"></td>
            </tr>
        </table>
    </form>
</body>
</html>
