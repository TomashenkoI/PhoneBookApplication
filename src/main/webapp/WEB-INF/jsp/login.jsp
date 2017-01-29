<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link type="text/css" href="/static/css/bootstrap.min.css" rel="stylesheet"/>

</head>
<body>

    <div role="navigation">
        <div class="navbar navbar-inverse">
            <a href="#" class="navbar-brand">Bootsample</a>
        </div>
    </div>


    <h1>hello <sec:authentication property="name"/></h1>

    <c:if test="${param.error != null}">
        <p>
            Invalid username or password.
        </p>
    </c:if>

    <form action="/login" method="post">
        <table>
            <tr>
                <th><label for="username">Username</label></th>
                <td><input type="text" id="username" name="username"/></td>
            </tr>
            <tr>
                <th><label for="password">Password</label></th>
                <td><input type="password" name="password" id="password"></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Log in</button>
    </form>

    <a href="/registration">registration</a>

</body>
</html>
