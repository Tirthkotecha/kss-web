<%--
    Document   : Logout.jsp
    Created on : 16 Nov, 2018, 9:09:35 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <h1>Redirecting to Login page</h1>
    </body>
    <%session.invalidate();%>
    <script type="text/javascript">
        OpenHome();
        function OpenHome() {
            localStorage.clear();
            window.location = "index.jsp";
        }
    </script>
</html>
