<%--
  Created by IntelliJ IDEA.
  User: 耿
  Date: 2022/11/9
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<jsp:forward page="/emps"></jsp:forward>

<html>
<head>
    <title>Title</title>
    <%
            pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <%--引入jquery--%>
    <script src="${APP_PATH}/static/js/jquery-1.12.4.min.js" ></script>
    <%--引入样式--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
    <button class="btn btn-success">按钮</button>
</body>
</html>
