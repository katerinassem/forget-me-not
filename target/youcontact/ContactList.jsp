<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="transfer.Contact" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<head>
    <title>YouContact</title>
    <link href="files/css/style.css" rel="stylesheet">
</head>
<body>
<h1>Список контактов.</h1>
<h4>${sessionScope.infoMessage}</h4>
<form method="post" action="?command=DeleteContactsCommand">
    <button  formaction="?command=CreateEditContactCommand&option=create">＋ создать</button>
    <button type="submit">X удалить</button>
    <button formaction="?command=SearchContactsCommand&option=get">? поиск</button>
    <button formaction="?command=SendEmailCommand&option=get">@ email</button>
    <table>
        <tr>
            <th></th>
            <th>ФИО</th>
            <th>Дата рождения</th>
            <th>Адрес</th>
            <th>Компания</th>
        </tr>
        <c:forEach items="${sessionScope.contacts}" var="contact" varStatus="varStatus">
            <tr>
                <td><input class="checkbox" name="checkbox" value="${contact.id}" type="checkbox"/></td>
                <td><a href="?command=CreateEditContactCommand&option=edit&id=${contact.id}">✐ ${contact.secondName} ${contact.firstName} ${contact.nameByFather}</a></td>
                <td>${contact.formattedDateOfBirth}</td>
                <td>${contact.address}</td>
                <td>${contact.company}</td>
            </tr>
        </c:forEach>
    </table>
    <ul id="page-tab">
        <c:forEach var="index" varStatus="status" begin="1" end="${sessionScope.pageCount}" step="1">
            <li class="page-button" id="${index}"><a href="?command=ShowContacts&page=${index}">${index}</a></li>
        </c:forEach>
    </ul>
</form>
</body>
</html>
