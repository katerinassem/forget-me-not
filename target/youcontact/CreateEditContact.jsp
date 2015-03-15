<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>YouContact</title>
    <link href="files/css/style.css" rel="stylesheet">
    <script src="files/js/popup.js" ></script>
</head>
<body>
    <h1>Создать или редактировать контакт.</h1>
    <h4>${sessionScope.infoMessage}</h4>
    <div onclick="showPopUp('photo-pop-up', 'block')"><img src="files/images/default_avatar.jpg"/></div>
    <form id="main" method="post" action="" enctype="multipart/form-data">
        <input type="hidden" name="command" value="CreateEditContactCommand"/>
        <input type="hidden" name="option" value="editmore"/>
        <div id="attachment-pop-up">
            <h4>Создать присоединение.</h4>
            <div name="attachment">
                <input name="attachmentId" type="hidden"/>
                <input name="file" type="file"/>
                <input name="fileName" type="hidden"/>
                <label id="fileNameLabel"></label>
                <input name="uploadDate" type="hidden"/>
                <label>Комментарий: </label>
                <input name="attachmentComment" type="text" placeholder="комментарий"/>
                <button form="main" type="button" onclick="setAttachment()">Сохранить</button>
                <button type="button" onclick="showPopUp('attachment-pop-up', 'none')">Отменить</button>
            </div>
        </div>

        <label>ФИО:</label>
        <input name="id" type="hidden" value="${sessionScope.contact.id}"/>
        <input name="firstName" type="text" placeholder="имя*" value="${sessionScope.contact.firstName}"/>
        <input name="secondName" type="text" placeholder="фамилия*" value="${sessionScope.contact.secondName}"/>
        <input name="nameByFather" type="text" placeholder="отчество" value="${sessionScope.contact.nameByFather}"/>
        <label>Дата рождения:</label>
        <div id="date-tab">
            <input name="day" type="text" placeholder="дд" value="${sessionScope.contact.day}"/>
            <input name=month type="text" placeholder="мм" value="${sessionScope.contact.month}"/>
            <input name="year" type="text" placeholder="гг" value="${sessionScope.contact.year}"/>
        </div>
        <fieldset>
            <label class="sex">женский</label>
            <input name="checkedSex" class="sex" type="radio" name="sex" value="f" ${sessionScope.contact.ifFemale}/>
        </fieldset>
        <fieldset>
            <label class="sex" >мужской</label>
            <input name="checkedSex" class="sex" type="radio" name="sex" value="m" ${sessionScope.contact.ifMale}/>
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
                <input name="addressId" type="hidden" value="${sessionScope.contact.addressId}"/>
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
            <fieldset>
                <legend>Контактные телефоны</legend>
                <button type="button" onclick="deleteTelephones()">X удалить</button>
                <button type="button" onclick="showPopUp('telephone-pop-up', 'block')">＋ создать</button>
                <table id="telephone-table">
                    <tr>
                        <th class="checkbox"></th>
                        <th>Номер</th>
                        <th>Тип</th>
                        <th>Комментарий</th>
                    </tr>
                    <c:if test="${sessionScope.contact.telephones != null}">
                        <c:forEach items="${sessionScope.contact.telephones}" var="telephone" varStatus="status">
                            <tr id="${status.index}">
                                <td>
                                    <input type="hidden" id="{telephone.id}" value="${status.index}"/>
                                    <input id="telephone${telephone.id}" name="telephoneIds" type="hidden" value="${telephone.id}"/>
                                    <input name="countryCodes" type="hidden" value="${telephone.countryCode}"/>
                                    <input name="operatorCodes" type="hidden" value="${telephone.operatorCode}"/>
                                    <input name="telephoneNumbers" type="hidden" value="${telephone.telephoneNumber}"/>
                                    <input name="telephoneTypes" type="hidden" value="${telephone.type}"/>
                                    <input name="telephoneComments" type="hidden" value="${telephone.comment}"/>

                                    <input class="checkbox" name="checkedTelephones" type="checkbox" value="${telephone.id}"/>
                                </td>
                                <td onclick="showPopUp('telephone-pop-up', 'block', '${status.index}')">✐${telephone.countryCode}(${telephone.operatorCode})${telephone.telephoneNumber}</td>
                                <td>${telephone.typeString}</td>
                                <td>${telephone.comment}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </fieldset>
                <fieldset>
                    <legend>Список присоединений</legend>
                    <c:if test="${sessionScope.contact.id != null}">
                        <button type="button" onclick="deleteAttachments()">X удалить</button>
                        <button type="button" onclick="showPopUp('attachment-pop-up', 'block')">＋ создать</button>
                    </c:if>
                    <table id="attachment-table">
                        <tr>
                            <th class="checkbox"></th>
                            <th>Имя файла</th>
                            <th>Дата загрузки</th>
                            <th>Комментарий</th>
                        </tr>
                        <c:if test="${sessionScope.contact.id == null}">
                            <h5>Файлы можно загружать для существующих контактов.</h5>
                        </c:if>
                        <c:if test="${sessionScope.contact.id != null}">
                            <c:if test="${sessionScope.contact.attachments != null}">
                                <c:forEach items="${sessionScope.contact.attachments}" var="attachment" varStatus="status">
                                    <tr id="${attachment.formattedUploadDate}">
                                        <td>
                                            <input type="hidden" id="{attachment.id}" value="${attachment.formattedUploadDate}"/>
                                            <input name="attachmentIds" type="hidden" value="${attachment.id}"/>
                                            <input name="fileNames" type="hidden" value="${attachment.fileName}"/>
                                            <input name="formattedUploadDates" type="hidden" value="${attachment.formattedUploadDate}"/>
                                            <input name="attachmentComments" type="hidden" value="${attachment.comment}"/>

                                            <input class="checkbox" name="checkedAttachments" type="checkbox" value="${attachment.id}"/>
                                        </td>
                                        <td><a href="/upload/${sessionScope.contact.id}/${attachment.id}">⇓</a><div onclick="showPopUp('attachment-pop-up', 'block', '${attachment.formattedUploadDate}')">&nbsp✐${attachment.fileName}</div></td>
                                        <td>${attachment.formattedUploadDate}</td>
                                        <td>${attachment.comment}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:if>
                    </table>
                </fieldset>
        <button form="main" onclick="validateMain()" type="button">Сохранить</button>
        <div id="telephone-pop-up">
            <h4>Создать или редактировать телефон.</h4>
            <input name="myId" type="hidden"/>
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
                <input name="checkedType" class="tel" type="radio" value="h" name="telephone_type"/>
                <label class="tel">Мобильный</label>
                <input name="checkedType" type="radio" class="tel" value="m" name="telephone_type" checked/>
            </fieldset>
            <label>Комментарий:</label>
            <input name="telephoneComment" type="text" placeholder="комментарий"/>
            <button type="button" onclick="setTelephone()">Сохранить</button>
            <button type="button" onclick="showPopUp('telephone-pop-up', 'none')">Отменить</button>
        </div>

        <div id="photo-pop-up">
            <h4>Редактировать аватар.</h4>
            <label>Изображение:</label>
            <input name="avatarFile" type="file"/>
            <button type="button" onclick="setTelephone()">Выбрать</button>
            <button type="button" onclick="showPopUp('photo-pop-up', 'none')">Отменить</button>
        </div>
    </form>

</body>
</html>
