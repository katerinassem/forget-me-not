/**
 * Created by Катерина on 07.03.2015.
 */


function validateMain(){
    var form = document.forms["main"];
    var valid = true;
    var regDate = /^(((0[1-9]|[12]\d|3[01])\.(0[13578]|1[02])\.((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\.(0[13456789]|1[012])\.((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\.02\.((19|[2-9]\d)\d{2}))|(29\.02\.((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/;
    var regEmail = /.+@.+\..+/i;
    var day = form.day.value;
    var month = form.month.value;
    var year = form.year.value;
    var firstName = form.firstName.value;
    var secondName = form.secondName.value;
    var nameByFather = form.nameByFather.value;
    var date = day.toString() + '.' + month.toString() + '.' + year.toString();
    var sitizenship = form.sitizenship.value;
    var webSite = form.webSite.value;
    var email = form.email.value;
    var company = form.company.value;
    var country = form.country.value;
    var city = form.city.value;
    var street = form.street.value;
    var building = form.building.value;
    var apartment = form.apartment.value;
    var index = form.index.value;

    if(firstName.length === 0 || firstName.length > 30){
        form.firstName.style.borderColor='red';
        form.firstName.placeholder = '*заполните! не более 30 символов';
        valid = false;
    }
    else{
        form.firstName.style.borderColor='green';
        form.firstName.placeholder = '*имя';
    }
    if(secondName.length === 0 || secondName > 30){
        form.secondName.style.borderColor='red';
        form.secondName.placeholder = '*заполните! не более 30 символов';
        valid = false;
    }
    else{
        form.secondName.style.borderColor='green';
        form.secondName.placeholder = '*фамилия';
    }
    if(nameByFather.length > 30){
        form.nameByFather.style.borderColor='red';
        form.nameByFather.placeholder = 'не более 30 символов';
        valid = false;
    }
    else{
        form.nameByFather.style.borderColor='green';
        form.nameByFather.placeholder = 'отчество';
    }

    console.log(isInteger((day)));
    console.log(isInteger((month)));
    console.log(isInteger((year)));
    console.log(date);
    console.log(regDate.test(date));
    if((day !== "" && !isInteger(day)) || (month !== "" && !isInteger(month)) || (year !== "" && !isInteger(year)) || !regDate.test(date)) {
        form.day.style.borderColor = 'red';
        form.month.style.borderColor = 'red';
        form.year.style.borderColor = 'red';
        valid = false;
    }
    else {
        form.day.style.borderColor = 'green';
        form.month.style.borderColor = 'green';
        form.year.style.borderColor = 'green';
    }

    if(sitizenship.length > 40){
        form.sitizenship.style.borderColor = 'red';
        form.sitizenship.placeholder = 'не более 40 символов';
        valid = false;
    }
    else{
        form.sitizenship.style.borderColor = 'green';
        form.sitizenship.placeholder = 'гражданство';
    }

    if(webSite.length > 100){
        form.webSite.style.borderColor = 'red';
        form.webSite.placeholder = 'слишком длинный адрес';
        valid = false;
    }
    else{
        form.webSite.style.borderColor = 'green';
        form.webSite.placeholder = 'сайт';
    }
    if(email.length > 100 || !regEmail.test(email)){
        form.email.style.borderColor = 'red';
        form.email.placeholder = 'неверный или слишком большой email'
        valid = false;
    }
    else{
        form.email.style.borderColor = 'green';
        form.email.placeholder = 'email'
    }
    if(company.length > 50){
        form.company.style.borderColor = 'red';
        form.company.placeholder = 'до 50 символов';
        valid = false;
    }
    else{
        form.company.style.borderColor = 'green';
        form.company.placeholder = 'компания';
    }
    if(country.length === 0 || country.length > 40){
        form.country.style.borderColor = 'red';
        form.country.placeholder = 'заполните! до 40 символов';
        valid = false;
    }
    else{
        form.country.style.borderColor = 'green';
        form.country.placeholder = 'страна';
    }
    if(city.length == 0 || city.length > 100){
        form.city.style.borderColor = 'red';
        form.city.placeholder = 'заполните! до 100 символов';
        valid = false;
    }
    else{
        form.city.style.borderColor = 'green';
        form.city.placeholder = 'город';
    }
    if(street.length > 100){
        form.street.style.borderColor = 'red';
        form.street.placeholder = 'до 100 символов';
        valid = false;
    }
    else{
        form.street.style.borderColor = 'green';
        form.street.placeholder = 'улица';
    }
    if((building !== "" && !isInteger(building)) || building === 0 || building > 10000 ){
        form.building.style.borderColor = 'red';
        form.building.placeholder = 'неверный номер';
        valid = false;
    }
    else{
        form.building.style.borderColor = 'green';
        form.building.placeholder = 'дом';
    }
    if((apartment !== "" &&  !isInteger(apartment)) || apartment === 0 || apartment > 10000){
        form.apartment.style.borderColor = 'red';
        form.apartment.placeholder = 'неверная квартира';
        valid = false;
    }
    else{
        form.apartment.style.borderColor = 'green';
        form.apartment.placeholder = 'квартира';
    }
    if(index !== "" && !isInteger(index)){
        form.index.style.borderColor = 'red';
        form.index.placeholder = 'неверный индекс';
        valid = false;
    }
    else{
        form.index.style.borderColor = 'green';
        form.index.placeholder = 'индекс';
    }
    if(valid){
        form.submit();
    }
}

function isInteger(n) {
    return !isNaN(parseInt(n)) && isFinite(n);
}

function getNextElement(elem) {
    var current = elem.nextSibling;
    if(current) {
        current = current.nextSibling;
    }
    // current == null (нет следующего узла-элемента)
    // current.nodeType == 1 (нашли)
    return current;
}


function showPopUp(id, option, value){
    var form
    if(id == 'attachment-pop-up' && value != undefined){
        form = document.forms['main'];
        form.attachmentId.value = value;
        var trOfAttachment = document.getElementById('attachment' + value);
        trOfAttachment = getNextElement(trOfAttachment);
        form.fileName.value = trOfAttachment.value;
        trOfAttachment = getNextElement(trOfAttachment);
        form.uploadDate.value = trOfAttachment.value;
        trOfAttachment = getNextElement(trOfAttachment);
        form.attachmentComment.value = trOfAttachment.value;
        form.file.style.display = 'none';
    }
    else if(id == 'attachment-pop-up') {
        form = document.forms['main'];
        form.file.style.display = 'block';
        form.uploadDate.value = Date.now();
    }
    else if(id == 'telephone-pop-up' && value != undefined){
        form = document.forms['telephone'];
        form.telephoneId.value = value;
        var trOfTelephone = document.getElementById('telephone' + value);
        trOfTelephone = getNextElement(trOfTelephone);
        form.countryCode.value = trOfTelephone.value;
        trOfTelephone = getNextElement(trOfTelephone);
        form.operatorCode.value = trOfTelephone.value;
        trOfTelephone = getNextElement(trOfTelephone);
        form.telephoneNumber.value = trOfTelephone.value;
        trOfTelephone = getNextElement(trOfTelephone);
        form.checkedType.value = trOfTelephone.value;
        trOfTelephone = getNextElement(trOfTelephone);
        form.telephoneComment.value = trOfTelephone.value;
    }
    document.getElementById(id).style.display = option;
    return false;
}

function validateAttachment(form){
    var fileName = form.fileName.value;
    var attachmentComment = form.attachmentComment.value;
    var valid = true;
    if(fileName.length === 0 || fileName.length > 100){
        form.fileName.style.borderColor = 'red';
        form.fileName.placeholder = 'заполните! до 100 символов';
        valid = false;
    }
    else{
        form.fileName.style.borderColor = 'green';
        form.fileName.placeholder = 'имя файла';
    }
    if(attachmentComment.length > 200){
        form.attachmentComment.style.borderColor = 'red';
        form.attachmentComment.placeholder = 'до 200 символов';
        valid = false;
    }
    else{
        form.attachmentComment.style.borderColor = 'green';
        form.attachmentComment.placeholder = 'комментарий';
    }
    return valid;
}

function setAttachment(){
    var form = document.forms["attachment"];
    if(validateAttachment(form)) {
        var date = "";
        if (form.formattedUploadDate.value != "") {
            date = form.formattedUploadDate.value;
        }
        else {
            date = Date().toDateString();
        }
        var s = '<input name="attachmentIds" type="hidden" value="' + form.attachmentId.value + '"/>' +
            '<input name="fileNames" type="hidden" value="' + form.fileName.value + '"/>' +
            '<input name="formattedUploadDates" type="hidden" value="' + date + '"/>' +
            '<input name="attachmentComments" type="hidden" value="' + form.attachmentComment.value + '"/>' +
            '<tr><td><input name="checkedAttachments" type="checkbox" value="' + form.attachmentId.value + '"/></td>'
            + '<td onclick="showPopUp(\'attachment-pop-up\', \'block\', \' ' + form.attachmentId.value + ' \')">✐ fileName</td>'
            + '<td>' + date + '</td>'
            + '<td>' + form.attachmentComment.value + '</td></tr>';
        var attachmentString = form.fileName.value + date;
        var elemToEdit = document.getElementById(attachmentString);
        if (elemToEdit != null) {
            elemToEdit.innerHTML = s;
        }
        else {
            document.getElementById('attachment-table').innerHTML += '<div id="' + attachmentString + '">' + s + '</div>';
        }
        form.reset();
        document.getElementById('attachment-pop-up').style.display = 'none';
    }
}

function validateTelephone(form){
    var countryCode = form.countryCode.value;
    var operatorCode = form.operatorCode.value;
    var telephoneNumber = form.telephoneNumber.value;
    var telephoneComment = form.telephoneComment.value;
    var valid = true;
    if(countryCode === "" || !isInteger(countryCode) || countryCode > 1000){
        form.countryCode.style.borderColor = 'red';
        form.countryCode.placeholder = 'заполните! введите верный код';
        valid = false;
    }
    else{
        form.countryCode.style.borderColor = 'green';
        form.countryCode.placeholder = 'код страны';
    }
    if(operatorCode === "" || !isInteger(countryCode) || operatorCode > 1000){
        form.operatorCode.style.borderColor = 'red';
        form.operatorCode.placeholder = 'заполните! введите верный код';
        valid = false;
    }
    else{
        form.operatorCode.style.borderColor = 'green';
        form.operatorCode.placeholder = 'код оператора';
    }
    if(telephoneNumber === "" || !isInteger(telephoneNumber)){
        form.telephoneNumber.style.borderColor = 'red';
        form.telephoneNumber.placeholder = 'заполните! введите верный код';
        valid = false;
    }
    else{
        form.telephoneNumber.style.borderColor = 'green';
        form.telephoneNumber.placeholder = 'номер';
    }
    if(telephoneComment.length > 200){
        form.telephoneComment.style.borderColor = 'red';
        form.telephoneComment.placeholder = 'до 200 символов';
        valid = false;
    }
    else{
        form.telephoneComment.style.borderColor = 'green';
        form.telephoneComment.placeholder = 'комментарий';
    }
    return valid;
}

function setTelephone(){
    var form = document.forms["telephone"];
    if(validateTelephone(form)) {
        var s = '<input name="telephoneIds" type="hidden" value="' + form.telephoneId.value + '"/>' +
            '<input name="countryCodes" type="hidden" value="' + form.countryCode.value + '"/>' +
            '<input name="operatorCodes" type="hidden" value="' + form.operatorCode.value + '"/>' +
            '<input name="telephoneNumbers" type="hidden" value="' + form.telephoneNumber.value + '"/>' +
            '<input name="telephoneTypes" type="hidden" value="' + form.checkedType.value + '"/>' +
            '<input name="telephoneComments" type="hidden" value="' + form.telephoneComment.value + '"/>' +
            '<tr><td><input name="checkedTelephones" type="checkbox" value="' + form.telephoneId.value + '"/></td>' +
            '<td onclick="showPopUp(\'telephone-pop-up\', \'block\', \' ' + form.telephoneId.value + ' \')">✐ ' + form.countryCode.value + '(' + form.operatorCode.value + ')' + form.telephoneNumber.value + '</td>' +
            '<td>' + form.checkedType.value + '</td>' +
            '<td>' + form.telephoneComment.value + '</td>' +
            '</tr>';
        var telephoneString = form.countryCode.value.toString() + form.operatorCode.value.toString() + form.telephoneNumber.value.toString();
        var elemToEdit = document.getElementById(telephoneString);
        if (elemToEdit != null) {
            elemToEdit.innerHTML = s;
        }
        else {
            document.getElementById('telephone-table').innerHTML += '<div id="' + telephoneString + '">' + s + '</div>';
        }
        form.reset();
        document.getElementById('telephone-pop-up').style.display = 'none';
    }
}
