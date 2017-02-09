<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet"/>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        $(function () {
            $("#tabs").tabs().addClass("ui-tabs-vertical ui-helper-clearfix");
            $("#tabs li").removeClass("ui-corner-top").addClass("ui-corner-left");
        });
    </script>

    <style>
        .ui-tabs-vertical {
            width: 55em;
        }

        .ui-tabs-vertical .ui-tabs-nav {
            padding: .2em .1em .2em .2em;
            float: left;
            width: 12em;
        }

        .ui-tabs-vertical .ui-tabs-nav li {
            clear: left;
            width: 100%;
            border-bottom-width: 1px !important;
            border-right-width: 0 !important;
            margin: 0 -1px .2em 0;
        }

        .ui-tabs-vertical .ui-tabs-nav li a {
            display: block;
        }

        .ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active {
            padding-bottom: 0;
            padding-right: .1em;
            border-right-width: 1px;
        }

        .ui-tabs-vertical .ui-tabs-panel {
            padding: 1em;
            float: right;
            width: 40em;
        }
    </style>

</head>
<body>

<h1>Hello <sec:authentication property="name"/></h1>

<table>
    <tr>
        <td>
            <form action="/findContactsByName" method="post">
                <input type="text" name="firstName" placeholder="find contacts by first name">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Search</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/findContactsBySurname" method="POST">
                <input type="text" name="surname" placeholder="find contacts by surname">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Search</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/findContactsByMobilePhoneNumber" method="post">
                <input type="tel" name="mobilePhoneNumber" placeholder="find contacts by mobile phone number">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Search</button>
            </form>
        </td>
    </tr>
</table>

<div id="tabs">
    <ul>
        <c:forEach items="${listOfContacts}" var="contact">
            <li>
                <a href="#tabs-${contact.getId()}">${contact.getSurname()} ${contact.getName()} ${contact.getPatronymic()}</a>
            </li>
        </c:forEach>
    </ul>
    <c:forEach items="${listOfContacts}" var="contact">
        <div id="tabs-${contact.getId()}">
            <table>
                <tr>
                    <th>First name</th>
                    <td>${contact.getName()}</td>
                </tr>
                <tr>
                    <th>Surname</th>
                    <td>${contact.getSurname()}</td>
                </tr>
                <tr>
                    <th>Patronymic</th>
                    <td>${contact.getPatronymic()}</td>
                </tr>
                <tr>
                    <th>Mobile phone</th>
                    <td>${contact.getMobilePhoneNumber()}</td>
                </tr>
                <tr>
                    <th>Home phone number</th>
                    <td>${contact.getHomePhoneNumber()}</td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td>${contact.getAddress()}</td>
                </tr>
                <tr>
                    <th>E-mail</th>
                    <td>${contact.getEmail()}</td>
                </tr>
            </table>
        </div>
    </c:forEach>

</div>

<button onclick="location.href='/createContact'">Create contact</button>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}"
      method="post">
    <input type="submit"
           value="Log out"/>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

</body>
</html>
