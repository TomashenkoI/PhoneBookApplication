<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

    <form action="/" method="post">
        <table>
            <tr>
                <th>Login</th>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <th>Password</th>
                <th><input type="password" name="password"></th>
            </tr>
            <tr>
                <th>Full name</th>
                <th><input type="text" name="fullName"></th>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </tr>
        </table>
        <button>Submit</button>
    </form>

</body>
</html>
