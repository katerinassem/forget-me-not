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
    var s = '<tr><td><input name="checkedAttachments" type="checkbox" value="' + form.attachmentId.value + '"/></td>'
        + '<td><a href="showPopUp(\'attachment-pop-up\', \'block\', \' ' + form.attachmentId.value +' \')">✐ fileName</a></td>'
        + '<td>' + Date() + '</td>'
        + '<td>' + form.attachmentComment.value + '</td></tr>';
    document.getElementById('attachment-table').innerHTML += s;
    form.reset();
    document.getElementById('attachment-pop-up').style.display = 'none';
}


function setTelephone(){
    var form = document.forms["telephone"];
    alert("here");
    alert("form.telephoneId.value");
    alert("form.countryCode.value");
    console.log("form.operatorCode.value");
    console.log("form.telephoneNumber.value");
    var s = '<tr><td><input name="checkedTelephones" type="checkbox" value="' + form.telephoneId.value + '"/></td>' +
        '<td><a href="showPopUp(\'telephone-pop-up\', \'block\', \' ' + form.telephoneId.value + ' \')">✐ ' + form.countryCode.value + '(' + form.operatorCode.value + ')' + form.telephoneNumber.value + '</a></td>' +
        '<td>' + form.checkedType.value + '</td>' +
        '<td>' + form.telephoneComment.value + '</td>' +
        '</tr>';
    alert("here1");
    document.getElementById('telephone-table').innerHTML += s;
    alert("here2");
    form.reset();
    alert("here3");
    document.getElementById('telephone-pop-up').style.display = 'none';
}