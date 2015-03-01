<%--
  Created by IntelliJ IDEA.
  User: Катерина
  Date: 20.02.2015
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>YouContact</title>
    <link href="files/css/style.css" rel="stylesheet">
</head>
<body>
<h1>Создать или редактировать контакт.</h1>
<a href="?command=ChoosePhotoCommand"><img src="files/images/default_avatar.jpg"/></a>
<form>
    <label>ФИО:</label>
    <input type="text" placeholder="фамилия*" value="${sessionScope.firstName}"/>
    <input type="text" placeholder="имя*"/>
    <input type="text" placeholder="отчество"/>
    <label>Дата рождения:</label>
    <input type="text" placeholder="дд.мм.гг"/>
    <fieldset>
        <legend>Пол</legend>
        <label class="sex" >женский</label>
        <input class="sex" type="radio" name="sex"/>
        <label class="sex" >мужской</label>
        <input class="sex" type="radio" name="sex"/>
    </fieldset>
    <label>Гражданство:</label>
    <input type="text" placeholder="гражданство"/>
    <label>Web-сайт:</label>
    <input type="text" placeholder="web-сайт"/>
    <label>email:</label>
    <input type="text" placeholder="email"/>
    <label>Компания:</label>
    <input type="text" placeholder="компания"/>
    <fieldset>
        <legend>Адрес</legend>
        <label>Страна:</label>
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
    <form>
        <fieldset>
            <legend>Контактные телефоны</legend>
            <button><a href="?command=DeleteTelephonesCommand">Удалить выделенные телефоны</a></button>
            <button><a href="javascript:window.open('?command=CreateEditTelephoneCommand', 'telephones', 'width=600,height=400,toolbar=no,menubar=no,location=no')">Редактировать/создать новый телефон</a></button>
            <table>
                <tr>
                    <th><input type="checkbox"/></th>
                    <th>Номер</th>
                    <th>Тип</th>
                    <th>Комментарий</th>
                </tr>
                <tr>
                    <td><input type="checkbox"/></td>
                    <td>80293844605</td>
                    <td>Мобильный</td>
                    <td>комментарий</td>
                </tr>
                <tr>
                    <td><input type="checkbox"/></td>
                    <td>80173131059</td>
                    <td>Домашний</td>
                    <td>комментарий</td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
                    <legend>Список присоединений</legend>
                <form>
                    <button><a href="?command=DeleteAttachmentsCommand">Удалить выделенные присоединения</a></button>
                    <button><a href="javascript:window.open('?command=CreateEditAttachmentCommand', 'attachments', 'width=600,height=400,toolbar=no,menubar=no,location=no')">Создать присоединение</a></button>
                    <table>
                        <tr>
                            <th><input type="checkbox"/></th>
                            <th>Имя файла</th>
                            <th>Дата загрузки</th>
                            <th>Комментарий</th>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>Имя файла</td>
                            <td>Дата загрузки</td>
                            <td>комментарий</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>Имя файла</td>
                            <td>Дата загрузки</td>
                            <td>комментарий</td>
                        </tr>
                    </table>
                </fieldset>
        </form>
    </form>
    <button>Сохранить</button>
</form>
</body>
</html>
