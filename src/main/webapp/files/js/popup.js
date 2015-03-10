/**
 * Created by Катерина on 07.03.2015.
 */


function validateMain(){
    var form = document.forms["main"];
    var valid = true;
    var firstName = form.firstName.value;
    if(firstName.length === 0 || firstName.length > 29){
        form.firstName.style.borderColor='red';
        valid = false;
    }
    else{
        form.firstName.style.borderColor='green';
    }
    var secondName = form.secondName.value;
    if(secondName.length === 0 || secondName > 29){
        form.secondName.style.borderColor='red';
        valid = false;
    }
    else{
        form.secondName.style.borderColor='green';
    }
    nameByFather = form.nameByFather.value;
    if(nameByFather.length > 29){
        form.nameByFather.style.borderColor='red';
    }
    else{
        form.nameByFather.style.borderColor='green';
    }
    day = form.day.value;
    if(day < 1 || day > 31){
        form.day.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.day.style.borderColor = 'green';
    }
    month = form.month.value;
    if(month < 1 || month > 12){
        form.month.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.month.style.borderColor = 'green';
    }
    year = form.year.value;
    if(year < 1970 || year >2070){
        form.year.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.year.style.borderColor = 'green';
    }
    sitizenship = form.sitizenship.value;
    if(sitizenship.length == 0 || sitizenship.length > 39){
        form.sitizenship.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.sitizenship.style.borderColor = 'green';
    }
    webSite = form.webSite.value;
    if(webSite.length > 99){
        form.webSite.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.webSite.style.borderColor = 'green';
    }
    email = form.email.value;
    if(email.length > 99){
        form.email.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.email.style.borderColor = 'green';
    }
    company = form.company.value;
    if(company.length > 49){
        form.company.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.company.style.borderColor = 'green';
    }
    country = form.country.value;
    if(country.length == 0 || country.length > 39){
        form.country.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.country.style.borderColor = 'green';
    }
    city = form.city.value;
    if(city.length == 0 || city.length > 100){
        form.city.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.city.style.borderColor = 'green';
    }
    street = form.street.value;
    if(street > 99){
        form.street.style.borderColor = 'red';
        valid = false;
    }
    else{
        form.street.style.borderColor = 'green';
    }
    building = form.building.value;
    apartment = form.building.value;
    index = form.building.value;
    if(valid){
        form.submit();
    }
}


function showPopUp(id, option, value){

    if(id == 'attachment-pop-up' && value != undefined){
        var form = document.forms['attachment'];
        form.getElementsByName('attachmentIds')[0].value = value;
    }
    else if(id == 'telephone-pop-up' && value != undefined){
        var form = document.forms['telephone'];
        form.getElementsByName('telephoneIds')[0].value = value;
    }
    document.getElementById(id).style.display = option;
    return false;
}

function setAttachment(){
    var form = document.forms["attachment"];
    var date = "";
    if(form.formattedUploadDate.value != ""){
        date = form.formattedUploadDate.value;
    }
    else{
        date = Date().toDateString();
    }
    var s = '<input name="attachmentIds" type="hidden" value="' + form.attachmentId.value + '"/>' +
        '<input name="fileNames" type="hidden" value="' + form.fileName.value + '"/>' +
        '<input name="formattedUploadDates" type="hidden" value="' + date + '"/>' +
        '<input name="attachmentComments" type="hidden" value="' + form.attachmentComment.value + '"/>' +
        '<tr><td><input name="checkedAttachments" type="checkbox" value="' + form.attachmentId.value + '"/></td>'
        + '<td><a href="#" onclick="showPopUp(\'attachment-pop-up\', \'block\', \' ' + form.attachmentId.value +' \')">✐ fileName</a></td>'
        + '<td>' + date + '</td>'
        + '<td>' + form.attachmentComment.value + '</td></tr>';
    document.getElementById('attachment-table').innerHTML += s;
    form.reset();
    document.getElementById('attachment-pop-up').style.display = 'none';
}

function setTelephone(){
    var form = document.forms["telephone"];
    var s = '<input name="telephoneIds" type="hidden" value="' + form.telephoneId.value +'"/>' +
        '<input name="countryCodes" type="hidden" value="' + form.countryCode.value + '"/>' +
        '<input name="operatorCodes" type="hidden" value="' + form.operatorCode.value + '"/>' +
        '<input name="telephoneNumbers" type="hidden" value="' + form.telephoneNumber.value + '"/>' +
        '<input name="telephoneTypes" type="hidden" value="' + form.checkedType.value + '"/>' +
        '<input name="telephoneComments" type="hidden" value="' + form.telephoneComment.value + '"/>' +
        '<tr><td><input name="checkedTelephones" type="checkbox" value="' + form.telephoneId.value + '"/></td>' +
        '<td><a href="#" onclick="showPopUp(\'telephone-pop-up\', \'block\', \' ' + form.telephoneId.value + ' \')">✐ ' + form.countryCode.value + '(' + form.operatorCode.value + ')' + form.telephoneNumber.value + '</a></td>' +
        '<td>' + form.checkedType.value + '</td>' +
        '<td>' + form.telephoneComment.value + '</td>' +
        '</tr>';
    document.getElementById('telephone-table').innerHTML += s;
    form.reset();
    document.getElementById('telephone-pop-up').style.display = 'none';
}
