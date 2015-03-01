<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
            <th>Контакт</th>
        </tr>
        <tr>
            <td><input type="checkbox"/></td>
            <td><a href="?command=CreateEditContactCommand&option=edit">${sessionScope.firstName}</a></td>
            <td>Дата рождения</td>
            <td>Адрес</td>
            <td>Компания</td>
            <td>Контакт</td>
        </tr>
        <tr>
            <td><input type="checkbox"/></td>
            <td><a href="?command=CreateEditContactCommand&option=edit">ФИО</a></td>
            <td>Дата рождения</td>
            <td>Адрес</td>
            <td>Компания</td>
            <td>Контакт</td>
        </tr>
        <tr>
            <td><input type="checkbox"/></td>
            <td><a href="?command=CreateEditContactCommand&option=edit">ФИО</a></td>
            <td>Дата рождения</td>
            <td>Адрес</td>
            <td>Компания</td>
            <td>Контакт</td>
        </tr>
    </table>
</form>
</body>
</html>
