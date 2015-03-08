/**
 * Created by Катерина on 07.03.2015.
 */

    //Функция показа
function showPopUp(id, option, elemId){

    if(id == 'attachment-pop-up' && elemId != undefined){
        var form = document.forms['attachment'];
        form.getElementsByName('attachmentId')[0].value = elemId;
    }
    else if(id == 'telephone-pop-up' && elemId != undefined){
        var form = document.forms['telephone'];
        form.getElementsByName('telephoneId')[0].value = elemId;
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
        + '<td><a href="showPopUp(\'attachment-pop-up\', \'block\', \' ' + form.attachmentId.value +' \')">✐ fileName</a></td>'
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
        '<td><a href="showPopUp(\'telephone-pop-up\', \'block\', \' ' + form.telephoneId.value + ' \')">✐ ' + form.countryCode.value + '(' + form.operatorCode.value + ')' + form.telephoneNumber.value + '</a></td>' +
        '<td>' + form.checkedType.value + '</td>' +
        '<td>' + form.telephoneComment.value + '</td>' +
        '</tr>';
    document.getElementById('telephone-table').innerHTML += s;
    form.reset();
    document.getElementById('telephone-pop-up').style.display = 'none';
}