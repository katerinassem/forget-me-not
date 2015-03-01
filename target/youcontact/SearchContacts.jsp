<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<html>
<head>
    <title>YouContact</title>
    <link href="files/css/style.css" rel="stylesheet">
</head>
<body>
<h1>Поиск контактов.</h1>
<form>
    <label>ФИО:</label>
    <input type="text" placeholder="фамилия"/>
    <input type="text" placeholder="имя"/>
    <input type="text" placeholder="отчество"/>
    <fieldset>
        <legend>Дата рождения</legend>
        <label>От:</label>
        <input type="text"/>
        <label>До:</label>
        <input type="text"/>
    </fieldset>
    <fieldset>
        <legend>Пол</legend>
        <label class="sex" >женский</label>
        <input type="radio" class="sex" name="sex"/>
        <label class="sex" >мужской</label>
        <input type="radio" class="sex" name="sex"/>
    </fieldset>
    <label>Семейное положение:</label>
    <input type="text" placeholder="семейное положение"/>
    <label>Гражданство:</label>
    <input type="text" placeholder="гражданство"/>
    <fieldset>
        <legend>Адрес</legend>
        <label>	Страна:</label>
        <input type="text" placeholder="страна"/>
        <label>Город:</label>
        <input type="text" placeholder="город"/>
        <label>Улица:</label>
        <input type="text" placeholder="улица"/>
        <label>Дом:</label>
        <input type="text" placeholder="дом"/>
        <label>Квартира:</label>
        <input type="text" placeholder="квартира"/>
        <label>Индекс:</label>
        <input type="text" placeholder="индекс"/>
    </fieldset>
    <button><a href="?command=SearchContactsCommand">Поиск</a></button>
</form>
</body>
</html>
