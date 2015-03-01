<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<html>
<head>
    <title>YouContact</title>
    <link href="files/css/style.css" rel="stylesheet">
</head>
<body>
<h1>Отправить email контактам.</h1>
<form>
    <label>Кому:</label>
    <input type="text" placeholder="кому"/>
    <label>Тема:</label>
    <input type="text" placeholder="Тема"/>
    <label>Выберите шаблон:</label>
    <select>
        <option>Не выбран</option>
        <option>Шаблон1</option>
        <option>Шаблон2</option>
    </select>
    <textarea placeholder="текст письма"></textarea>
    <button><a href="?command=SendEmailCommand">Отправить</a></button>
    <button><a href="?command=ContactsList">Отменить</a></button>
</form>
</body>
</html>
