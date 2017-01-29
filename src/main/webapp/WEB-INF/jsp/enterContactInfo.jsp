<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet"/>

    <script type="text/javascript" src="static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="static/js/bootstrap.js"></script>
    <script type="text/javascript" src="static/js/bootstrap-formhelpers-phone.js"></script>

</head>
<body>

<h1>Hello <sec:authentication property="name"/></h1>
<c:if test="${doesItAlreadyExist == false}">
<form action="/addNewContact" method="post">
    <table>
        <tr>
            <th><label for="name">First name</label></th>
            <td><input type="text" id="name" name="firstName" required></td>
        </tr>
        <tr>
            <th><label for="surname">Surname</label></th>
            <td><input type="text" id="surname" name="surname" required></td>
        </tr>
        <tr>
            <th><label for="patronymic">Patronymic</label></th>
            <td><input type="text" id="patronymic" name="patronymic" required></td>
        </tr>
        <tr>
            <th><label for="mobilePhoneNumber">Mobile phone number</label></th>
            <td><input type="text" class="input-medium bfh-phone" data-format="+38 (0dd) ddd-dd-dd"
                       data-country="" id="mobilePhoneNumber" name="mobilePhoneNumber" required></td>
        </tr>
        <tr>
            <th><label for="homePhoneNumber">Home phone number</label></th>
            <td><input type="text" class="input-medium bfh-phone" data-format="+38 (0dd) ddd-dd-dd"
                       id="homePhoneNumber" name="homePhoneNumber"></td>
        </tr>
        <tr>
            <th><label for="address">Address</label></th>
            <td><input type="text" id="address" name="address"></td>
        </tr>
        <tr>
            <th><label for="email">E-mail</label></th>
            <td><input type="email" id="email" name="email"></td>
        </tr>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </table>
    <button onclick="putInfo()">Submit</button>
</form>
</c:if>
<c:if test="${doesItAlreadyExist == true}">
    <form action="/updateContact_id=${contact.getId()}" method="post">
        <table>
            <tr>
                <th><label for="name1">First name</label></th>
                <td><input type="text" id="name1" name="firstName" value="${contact.getName()}" required></td>
            </tr>
            <tr>
                <th><label for="surname1">Surname</label></th>
                <td><input type="text" id="surname1" name="surname" value="${contact.getSurname()}" required></td>
            </tr>
            <tr>
                <th><label for="patronymic1">Patronymic</label></th>
                <td><input type="text" id="patronymic1" name="patronymic" value="${contact.getPatronymic()}" required></td>
            </tr>
            <tr>
                <th><label for="mobilePhoneNumber1">Mobile phone number</label></th>
                <td><input type="text" class="input-medium bfh-phone" data-format="+38(0dd)ddddddd"
                           id="mobilePhoneNumber1" name="mobilePhoneNumber" value="${contact.getMobilePhoneNumber()}" required></td>
            </tr>
            <tr>
                <th><label for="homePhoneNumber1">Home phone number</label></th>
                <td><input type="text" class="input-medium bfh-phone" data-format="+38(0dd)ddddddd"
                           id="homePhoneNumber1" name="homePhoneNumber" value="${contact.getHomePhoneNumber()}"></td>
            </tr>
            <tr>
                <th><label for="address1">Address</label></th>
                <td><input type="text" id="address1" name="address" value="${contact.getAddress()}"></td>
            </tr>
            <tr>
                <th><label for="email1">E-mail</label></th>
                <td><input type="email" id="email1" name="email" value="${contact.getEmail()}"></td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </table>
        <button>Submit</button>
    </form>
</c:if>
</body>
</html>
