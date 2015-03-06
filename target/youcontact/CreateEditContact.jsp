<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<head>
    <title>YouContact</title>
    <link href="files/css/style.css" rel="stylesheet">
    <script src="files/js/popup.js" ></script>
</head>
<body>
<h1>Создать или редактировать контакт.</h1>
<a href="?command=ChoosePhotoCommand"><img src="files/images/default_avatar.jpg"/></a>
<form action="?command=SaveContactCommand">
    <label>ФИО:</label>
    <input name="id" type="hidden" value="${sessionScope.contact.id}"/>
    <input name="firstName" type="text" placeholder="фамилия*" value="${sessionScope.contact.firstName}"/>
    <input name="secondName" type="text" placeholder="имя*"  value="${sessionScope.contact.secondName}"/>
    <input name="nameByFather" type="text" placeholder="отчество" value="${sessionScope.contact.nameByFather}"/>
    <label>Дата рождения:</label>
    <div id="date-tab">
        <input type="text" placeholder="дд" value="${sessionScope.contact.day}"/>
        <input type="text" placeholder="мм" value="${sessionScope.contact.month}"/>
        <input type="text" placeholder="гг" value="${sessionScope.contact.year}"/>
    </div>
    <fieldset>
        <legend>Пол</legend>
        <label class="sex">женский</label>
        <input class="sex" type="radio" name="sex" checked="${sessionScope.contact.ifFemale}"/>
        <label class="sex" >мужской</label>
        <input class="sex" type="radio" name="sex" checked="${sessionScope.contact.ifMale}"/>
    </fieldset>
    <label>Гражданство:</label>
    <input type="text" placeholder="гражданство"  value="${sessionScope.contact.sitizenship}"/>
    <label>Web-сайт:</label>
    <input type="text" placeholder="web-сайт"  value="${sessionScope.contact.webSite}"/>
    <label>email:</label>
    <input type="text" placeholder="email"  value="${sessionScope.contact.email}"/>
    <label>Компания:</label>
    <input type="text" placeholder="компания"  value="${sessionScope.contact.company}"/>
    <fieldset>
        <legend>Адрес</legend>
            <label>Страна:</label>
            <input type="text" placeholder="страна" value="${sessionScope.contact.address.country}"/>
            <label>Город:</label>
            <input type="text" placeholder="город" value="${sessionScope.contact.address.city}"/>
            <label>Улица:</label>
            <input type="text" placeholder="улица" value="${sessionScope.contact.address.street}"/>
            <label>Дом:</label>
            <input type="text" placeholder="дом" value="${sessionScope.contact.address.building}"/>
            <label>Квартира:</label>
            <input type="text" placeholder="квартира" value="${sessionScope.contact.address.apartment}"/>
            <label>Индекс:</label>
            <input type="text" placeholder="индекс" value="${sessionScope.contact.address.index}"/>
    </fieldset>
    <form method="post" action="?command=DeleteTelephonesCommand&">
        <fieldset>
            <legend>Контактные телефоны</legend>
            <button type="submit">X удалить</button>
            <button><a href=href="javascript:PopUpShow()">＋ создать</a></button>
            <table id="telephone-table">
                <tr>
                    <th></th>
                    <th>Номер</th>
                    <th>Тип</th>
                    <th>Комментарий</th>
                </tr>
                <c:if test="${sessionScope.contact.telephones != null}">
                    <c:forEach items="${sessionScope.contact.telephones}" var="telephone" varStatus="status">
                        <tr>
                            <td><input name="checkedTelephones" type="checkbox" value="${telephone.id}"/></td>
                            <td><a href="?command=CreateEditTelephoneCommand&option=edit&id=${telephone.id}">✐ ${telephone.countryCode}(${telephone.operatorCode})${telephone.telephoneNumber}</a></td>
                            <td>${telephone.telephoneType}</td>
                            <td>${telephone.comment}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </fieldset>
    </form>
    <form method="post" action="?command=DeleteAttachmentsCommand">
        <fieldset>
            <legend>Список присоединений</legend>
                <button type="submit">X удалить</button>
                <button onclick="show('block')">＋ создать</button>
                <table id="attachment-table">
                    <tr>
                        <th></th>
                        <th>Имя файла</th>
                        <th>Дата загрузки</th>
                        <th>Комментарий</th>
                    </tr>
                    <c:if test="${sessionScope.contact.getAttachments() != null}">
                        <c:forEach items="${sessionScope.contact.attachments}" var="attachment" varStatus="status">
                            <tr>
                                <td><input name="checkedAttachments" type="checkbox" value="${attachment.id}"/></td>
                                <td><a href="files/${sessionScope.contact.id}/${attachment.uniqueName}">⇓</a>&nbsp<a href="?command=CreateEditAttachmentCommand&option=edit&id=${attachment.id}">✐ ${attachment.fileName}</a></td>
                                <td>${attachment.formattedUploadDate}</td>
                                <td>${attachment.comment}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </fieldset>
        </form>
    </form>
    <button>Сохранить</button>
    <div id="window">Hello!!!</div>
    <div id="wrap">Hiiiii</div>
</body>
</html>
