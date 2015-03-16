<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <label>${emails}</label>
    <input name="emails" type="hidden" value="${emails}"/>
    <input name="checkedIds" type="hidden" value="${checkedIds}"/>
    <label>Тема:</label>
    <input name="subject" type="text" placeholder="Тема"/>
    <label>Выберите шаблон:</label>
    <input type="hidden" name="checkedTemplate"/>
    <select id="templates" onchange="changeTemplate()" name="template">
        <option >не выбрано</option>
        <c:forEach items="${templateNames}" var="template" varStatus="status">
            <option>${template}</option>
        </c:forEach>
    </select>
    <textarea name="letter"  placeholder="текст письма"></textarea>
    <button type="button" onclick="validateSend()">Отправить</button>
    <button type="button"><a href="?command=ShowContactsCommand">Отменить</a></button>
</form>
<c:forEach items="${templateContents}" var="content" varStatus="status">
    <p class="available-template" id="${status.index}Content" >${content}</p>
</c:forEach>
</body>
</html>
