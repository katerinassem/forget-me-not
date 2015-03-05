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
<form>
    <button><a href="?command=CreateEditContactCommand&option=create">Создать новый контакт</a></button>
    <button><a href="?command=DeleteContactsCommand">Удалить выделенные контакты</a></button>
    <button><a href="SearchContacts.jsp">Поиск контактов</a></button>
    <button><a href="?command=SendEmailCommand">Отправить email выделенным контактам</a></button>
    <table>
        <tr>
            <td><input type="checkbox"/></td>
            <th>ФИО</th>
            <th>Дата рождения</th>
            <th>Адрес</th>
            <th>Компания</th>
        </tr>
        <c:forEach items="${sessionScope.contacts}" var="contact" varStatus="varStatus">
            <tr>
                <td><input type="checkbox"/></td>
                <td><a href="?command=CreateEditContactCommand&option=edit&id=${contact.getId()}">${contact.getFirstName()} ${contact.getSecondName()} ${contact.getNameByFather()}</a></td>
                <td>${contact.getDateOfBirth()}</td>
                <td>${contact.getAddress()}</td>
                <td>${contact.getCompany()}</td>
            </tr>
        </c:forEach>
        <ul id="page-tab">
        <c:forEach var="index" varStatus="status" begin="1" end="${sessionScope.pageCount}" step="1">
            <li class="page-button" id="${index}"><a href="?command=ShowContacts&page=${index}">${index}</a></li>
        </c:forEach>
        </ul>
    </table>
</form>
</body>
</html>
