<%--
  Created by IntelliJ IDEA.
  User: koszkuld
  Date: 24/04/2022
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <form:form method="post" action="person" modelAttribute="person">
        Login: <form:input type="text" path="login"/> </br>
        Email: <form:input type="email" path="email"/> </br>
        Password: <form:input type="password" path="password"/> </br>
        <input type="submit">
    </form:form>

</body>
</html>
