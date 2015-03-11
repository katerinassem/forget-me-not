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
    <form id="main" method="post" action="?command=SaveContactCommand&option=saveall" enctype="multipart/form-data">

        <div id="attachment-pop-up">
            <h4>Создать присоединение.</h4>
            <div name="attachment">
                <input name="attachmentId" type="hidden"/>
                <input name="file" type="file"/>
                <label>Имя файла: </label>
                <input name="fileName" type="text"/>
                <input name="uploadDate" type="hidden"/>
                <label>Комментарий: </label>
                <input name="attachmentComment" type="text" placeholder="комментарий"/>
                <button form="main" type="button" onclick="setAttachment()" formaction="?command=CreateEditContactCommand&option=editmore">Сохранить</button>
                <button type="button" onclick="showPopUp('attachment-pop-up', 'none')">Отменить</button>
            </div>
        </div>

        <label>ФИО:</label>
        <input name="id" type="hidden" value="${sessionScope.contact.id}"/>
        <input name="firstName" type="text" placeholder="имя*" value="${sessionScope.contact.firstName}"/>
        <input name="secondName" type="text" placeholder="фамилия*"  value="${sessionScope.contact.secondName}"/>
        <input name="nameByFather" type="text" placeholder="отчество" value="${sessionScope.contact.nameByFather}"/>
        <label>Дата рождения:</label>
        <div id="date-tab">
            <input name="day" type="text" placeholder="дд" value="${sessionScope.contact.day}"/>
            <input name=month type="text" placeholder="мм" value="${sessionScope.contact.month}"/>
            <input name="year" type="text" placeholder="гг" value="${sessionScope.contact.year}"/>
        </div>
        <fieldset>
            <label class="sex">женский</label>
            <input name="checkedSex" class="sex" type="radio" name="sex" value="f" checked="${sessionScope.contact.ifFemale}"/>
        </fieldset>
        <fieldset>
            <label class="sex" >мужской</label>
            <input name="checkedSex" class="sex" type="radio" name="sex" value="m" checked="${sessionScope.contact.ifMale}"/>
        </fieldset>

        <label>Гражданство:</label>
        <input name="sitizenship" type="text" placeholder="гражданство"  value="${sessionScope.contact.sitizenship}"/>
        <label>Web-сайт:</label>
        <input name="webSite" type="text" placeholder="web-сайт"  value="${sessionScope.contact.webSite}"/>
        <label>email:</label>
        <input name="email" type="text" placeholder="email"  value="${sessionScope.contact.email}"/>
        <label>Компания:</label>
        <input name="company" type="text" placeholder="компания"  value="${sessionScope.contact.company}"/>
        <fieldset>
            <legend>Адрес</legend>
                <label>Страна:</label>
                <input name="addressId" type="hidden" value="${sessionScope.contact.address.id}"/>
                <input name="country" type="text" placeholder="страна" value="${sessionScope.contact.address.country}"/>
                <label>Город:</label>
                <input name="city" type="text" placeholder="город" value="${sessionScope.contact.address.city}"/>
                <label>Улица:</label>
                <input name="street" type="text" placeholder="улица" value="${sessionScope.contact.address.street}"/>
                <label>Дом:</label>
                <input name="building" type="text" placeholder="дом" value="${sessionScope.contact.address.building}"/>
                <label>Квартира:</label>
                <input name="apartment" type="text" placeholder="квартира" value="${sessionScope.contact.address.apartment}"/>
                <label>Индекс:</label>
                <input name="index" type="text" placeholder="индекс" value="${sessionScope.contact.address.index}"/>
        </fieldset>
        <form name="telephone" method="post" action="?command=DeleteTelephonesCommand&">
            <fieldset>
                <legend>Контактные телефоны</legend>
                <button type="submit">X удалить</button>
                <button type="button" onclick="showPopUp('telephone-pop-up', 'block')">＋ создать</button>
                <table id="telephone-table">
                    <tr>
                        <th></th>
                        <th>Номер</th>
                        <th>Тип</th>
                        <th>Комментарий</th>
                    </tr>
                    <c:if test="${sessionScope.contact.telephones != null}">
                        <c:forEach items="${sessionScope.contact.telephones}" var="telephone" varStatus="status">
                            <tr id="${telephone.countryCode}${telephone.operatorCode}${telephone.telephoneNumber}">
                                <td>
                                    <input id="telephone${telephone.id}" name="telephoneIds" type="hidden" value="${telephone.id}"/>
                                    <input name="countryCodes" type="hidden" value="${telephone.countryCode}"/>
                                    <input name="operatorCodes" type="hidden" value="${telephone.operatorCode}"/>
                                    <input name="telephoneNumbers" type="hidden" value="${telephone.telephoneNumber}"/>
                                    <input name="telephoneTypes" type="hidden" value="${telephone.type}"/>
                                    <input name="telephoneComments" type="hidden" value="${telephone.comment}"/>

                                    <input name="checkedTelephones" type="checkbox" value="${telephone.id}"/>
                                </td>
                                <td onclick="showPopUp('telephone-pop-up', 'block', '${telephone.countryCode}${telephone.operatorCode}${telephone.telephoneNumber}')">✐${telephone.countryCode}(${telephone.operatorCode})${telephone.telephoneNumber}</td>
                                <td>${telephone.type}</td>
                                <td>${telephone.comment}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </fieldset>
        </form>
        <form name="attachment" method="post" action="?command=DeleteAttachmentsCommand">
            <c:if test="${sessionScope.contact.id != null}">
                <fieldset>
                    <legend>Список присоединений</legend>
                    <button type="submit">X удалить</button>
                    <button type="button" onclick="showPopUp('attachment-pop-up', 'block')">＋ создать</button>
                    <table id="attachment-table">
                        <tr>
                            <th></th>
                            <th>Имя файла</th>
                            <th>Дата загрузки</th>
                            <th>Комментарий</th>
                        </tr>
                        <c:if test="${sessionScope.contact.getAttachments() != null}">
                            <c:forEach items="${sessionScope.contact.attachments}" var="attachment" varStatus="status">
                                <div id="${attachment.fileName}${attachment.formattedUploadDate}">
                                    <input id="attachment${attachment.id}" name="attachmentIds" type="hidden" value="${attachment.id}"/>
                                    <input name="fileNames" type="hidden" value="${attachment.fileName}"/>
                                    <input name="formattedUploadDates" type="hidden" value="${attachment.formattedUploadDate}"/>
                                    <input name="attachmentComments" type="hidden" value="${attachment.comment}"/>
                                    <tr>
                                        <td><input name="checkedAttachments" type="checkbox" value="${attachment.id}"/></td>
                                        <td><a href="files/${sessionScope.contact.id}/${attachment.uniqueName}">⇓</a><div onclick="showPopUp('attachment-pop-up', 'block', '${attachment.fileName}${attachment.formattedUploadDate}')">&nbsp✐${attachment.fileName}</div></td>
                                        <td>${attachment.formattedUploadDate}</td>
                                        <td>${attachment.comment}</td>
                                    </tr>
                                </div>
                            </c:forEach>
                        </c:if>
                    </table>
                </fieldset>
            </c:if>
        </form>
        <button form="main" onclick="validateMain()" type="button">Сохранить</button>
    </form>

    </form>
    <div id="telephone-pop-up">
        <h4>Создать или редактировать телефон.</h4>
        <form name="telephone"  method="post">
            <input name="telephoneId" type="hidden"/>
            <label>Код страны:</label>
            <input name="countryCode" type="text" placeholder="код страны"/>
            <label>Код оператора:</label>
            <input name="operatorCode" type="text" placeholder="код оператора"/>
            <label>Телефонный номер:</label>
            <input name="telephoneNumber" type="text" placeholder="телефонный номер"/>
            <fieldset>
                <legend>Тип телефона</legend>
                <label class="tel">Домашний</label>
                <input name="checkedType" class="tel" type="radio" value="h" name="telephone_type" checked="false"/>
                <label class="tel">Мобильный</label>
                <input name="checkedType" type="radio" class="tel" value="m" name="telephone_type" checked="true"/>
            </fieldset>
            <label>Комментарий:</label>
            <input name="telephoneComment" type="text" placeholder="комментарий"/>
            <button type="button" onclick="setTelephone()">Сохранить</button>
            <button type="button" onclick="showPopUp('telephone-pop-up', 'none')">Отменить</button>
        </form>
    </div>
</body>
</html>
