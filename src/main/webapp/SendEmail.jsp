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
    <script src="files/js/popup.js" ></script>
</head>
<body>
<h1>Отправить email контактам.</h1>
<form name="send" method="post" action="?command=SendEmailCommand&option=send">
    <label>Кому:</label>
    <label name="emails">${emails}</label>
    <input name="emails" type="hidden" value="${emails}"/>
    <label>Тема:</label>
    <input name="subject" type="text" placeholder="Тема"/>
    <label>Выберите шаблон:</label>
    <select>
        <option>Не выбран</option>
        <option>Шаблон1</option>
        <option>Шаблон2</option>
    </select>
    <textarea name="letter"  placeholder="текст письма"></textarea>
    <button type="button" onclick="validateSend()">Отправить</button>
    <button type="button"><a href="?command=ShowContactsCommand">Отменить</a></button>
</form>
</body>
</html>
