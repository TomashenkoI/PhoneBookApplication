<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

    <div role="navigation">
        <div class="navbar navbar-inverse">
            <a href="#" class="navbar-brand">Bootsample</a>
        </div>
    </div>

    <c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}"
          method="post">
        <input type="submit"
               value="Log out" />
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>

    <h1>Hello
    <sec:authentication property="name"/>
    </h1>

    <a href="/registration">registration</a>
    <a href="/login">Sign in</a>
    <a href="/contacts">Contacts</a>

    <script src="static/js/bootstrap.min.js"></script>

</body>
</html>
