<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<head>
    <title>YouContact</title>
    <link href="files/css/style.css" rel="stylesheet">
    <script src="files/js/popup.js" ></script>
</head>
<body>
<h1>Поиск контактов.</h1>
<form name="search" method="post" action="?command=SearchContactsCommand">
    <label>ФИО:</label>
    <input name="firstName" type="text" placeholder="имя"/>
    <input name="secondName" type="text" placeholder="фамилия"/>
    <input name="nameByFather" type="text" placeholder="отчество"/>
    <fieldset>
        <legend>Дата рождения</legend>
        <label>От:</label>
        <div class="date-tab">
            <input name="day" type="text" placeholder="дд"/>
            <input name=month type="text" placeholder="мм"/>
            <input name="year" type="text" placeholder="гг"/>
        </div>
        <label>До:</label>
        <div class="date-tab">
            <input name="day1" type="text" placeholder="дд"/>
            <input name="month1" type="text" placeholder="мм"/>
            <input name="year1" type="text" placeholder="гг"/>
        </div>
    </fieldset>
    <fieldset>
        <legend>Пол</legend>
        <label class="sex" >женский</label>
        <input name="checkedSex" type="radio" class="sex" name="sex"/>
        <label class="sex" >мужской</label>
        <input name="checkedSex" type="radio" class="sex" name="sex"/>
    </fieldset>
    <label>Семейное положение:</label>
    <input type="text" placeholder="семейное положение"/>
    <label>Гражданство:</label>
    <input name="sitizenship" type="text" placeholder="гражданство"/>
    <fieldset>
        <legend>Адрес</legend>
        <label>	Страна:</label>
        <input name="country" type="text" placeholder="страна"/>
        <label>Город:</label>
        <input name="city" type="text" placeholder="город"/>
        <label>Улица:</label>
        <input name="street" type="text" placeholder="улица"/>
        <label>Дом:</label>
        <input name="building" type="text" placeholder="дом"/>
        <label>Квартира:</label>
        <input name="apartment" type="text" placeholder="квартира"/>
        <label>Индекс:</label>
        <input name="index" type="text" placeholder="индекс"/>
    </fieldset>
    <button type="button" onclick="validateSearch()">Поиск</button>
</form>
</body>
</html>
