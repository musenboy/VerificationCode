<%--
  Created by musenboy.
  Date: 2016/8/5
  Time: 18:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <script type="text/javascript">
        function reloadImage() {
            document.getElementById("identity").src = "<%=request.getContextPath()%>/image/IdentityServlet.do?ts=" + new Date().getTime()
        }
    </script>
</head>
<body>
    <div>
        <a href="javascript:void(0)">
            <img src="<%=request.getContextPath()%>/image/IdentityServlet.do" id="identity" onclick="reloadImage()">
        </a>
    </div>
</body>
</html>
