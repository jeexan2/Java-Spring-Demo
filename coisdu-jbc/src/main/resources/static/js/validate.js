/**
 * Created by User on 22-Oct-18.
 */


//On Change Check
var check = null;
function checkUserName(name){
    //var check = true;
    var getName = name;
    return  $.ajax({type:'GET',
        url:'/security/userNamechecking/'+getName,
        async: false,
        succes: function (responseText) {
            response = responseText;
        }
    }).responseText;
}

function onChangeCheck(id,name,type,inputType,minVal,actionType) {
    if((type == 'text' || type == 'password') && name != "email" && name != "contactNumber"
        && inputType == "input") {
        if(name != "username" && type != "password") {
            $('#' + id).on("change", function () {
                var value = $('#' + id).val();
                if (value.length >= 1) {
                    $('.' + name).remove();
                }
            });
        }
        else if(name == "username"){
            $('#' + id).on("keyup", function () {
                var value = $('#' + id).val();
                //if(actionType != "Update")
                //checkUserName(value);
                var response = '';
                response = checkUserName(value);
                if (value.length >= 1 && response == 'false') {
                    $('.' + name).remove();

                }
                // console.log(response);
            });
        }
        else if(name == "password"){
            $('#'+id).on("keyup",function () {
                var value = $('#'+id).val();
                if(passwordCheck(value)){
                    $('.'+name).remove();
                }
            });
        }
        else if(name == "postCode"){
            $('#'+id).on("keyup",function () {
                var value = $('#'+id).val();
                if(validatePostCode(value) == true){
                    $('.'+name).remove();
                }
                else {
                    $('.'+name).remove();
                    labelWarning(name,id,inputType);
                }
            });
        }
    }
    else if(type == "text" && name == "email" && inputType == "input"){
        $('#'+id).on("keyup",function(){
            var value = $('#' + id).val();
            if(validateEmail(value) == true)
                $('.'+name).remove();
            else {
                $('.'+name).remove();
                labelWarning(name,id,inputType);
            }

        });
    }
    else if(type == "text" && name == "contactNumber" && inputType == "input"){
        $('#'+id).on("keyup",function(){
            //console.log("hit");
            var value = $('#' + id).val();
            if(validateContactNumber(value))
                $('.'+name).remove();
            else {
                $('.'+name).remove();
                labelWarning(name,id,inputType);
            }
        });
    }
    else if(inputType == "select"){
        $('#'+id).on("change",function(){
            if(id == "divisionId")
                dropDownLoad(dropDownLoadOfDistrict());
            else if(id == "districtId")
                dropDownLoadOfThana();
            var value = $('#' + id).val();
            if(value != -1)
                $('.'+name).remove();
        });
    }
    else if(type == "number"){
        $('#'+id).on("change",function(){
            var value = $('#'+id).val();
            if(value >= minVal)
                $('.'+name).remove();
        });
    }


}

//Validation Check
function validateForm(actionType){
    //alert(actionType);
    var flag = true;
    var elements = $("input");
    for(var i = 0; i < elements.length;i++){
        var name = elements[i].getAttribute('name');
        var type = elements[i].getAttribute('type');
        var id = elements[i].getAttribute('id');
        if($('#'+id).attr('data-required') == "false"){
           // console.log(name + " "+ "done");
            continue;
        }
        if(type == "text" && name != "email" && name != "contactNumber"){
            //return name.length
            var val = $('#'+id).val();
            flag = flag & val.length > 0;

           if(name == "username") {
             /**  if(actionType != "Update")
                   checkUserName(val); **/
               //else check = false;
                if(actionType == "Update") {
                    continue;
                }
                var response = '';
                response = checkUserName(val);
               flag = flag & response == 'false';
               check = null;

            }
        }
        else if(type == "text" && name == "email"){
            var val = $('#'+id).val();
            flag = flag & validateEmail(val);
        }
        else if(type == "text" && name == "contactNumebr"){
            var val = $('#'+id).val();
            flag = flag & validateContactNumber(val);
        }
        else if(name == "password"){
            var val = $('#'+id).val();
           flag = flag & passwordCheck(val);
        }
    }

    var dropdown = $("select");
    for(var i = 0; i < dropdown.length;i++){
        var id = dropdown[i].getAttribute('id');
        var val = $('#'+id).val();
        flag = flag & val != -1;
    }

    return flag;
}

//Label Get and Warning Sign
function labelWarning(name,id,inputType,unique){
    var labelName = $('label[for="' + id + '"]').html();
    var errorString = "";
    if(name != "password") errorString = typeof unique != "undefined"
        && unique == true?"Duplicate Found.Enter new "+labelName +"." :'Please Enter the '+ labelName+ '.';
    else if(name == "password"){
        errorString = "Please Enter minimum 8 characters long password with one small letter and" +
            "one capital letter";
    }
    var errorTag = name;
    var errorAlert = '<span class='+errorTag +'>' +errorString + '</span>';
    $(inputType+'[name = '+name+']').after(errorAlert);
    $('.'+name).addClass('error');
}
//On change
function formValidation(actionType){
    //Input Elements
    var elements = $("input");
    //console.log(elements);
    for(var i = 0; i < elements.length;i++){
        var type = elements[i].getAttribute('type');
        var name = elements[i].getAttribute('name');
        var id = elements[i].getAttribute('id');

        if($('#'+id).attr('data-required') == "false"){
            continue;
        }
         if((type == 'text' || type == 'password') && name != 'email' && name != 'conatactNumber'){
            if(elements[i].value.length <= 1 && name != "username" && type!= "password"){

                labelWarning(name,id,"input");
                onChangeCheck(id,name,type,"input");
            }
            else if(name == "password"){
                if(passwordCheck($('#'+id).val()) == false) {
                    labelWarning(name, id, "input");
                    onChangeCheck(id, name, type, "input");
                }
            }

             else if(name == "username"){
                var val = $('#'+id).val();

               // checkUserName(val);
               // alert(check);
                var response = '';
                response = checkUserName(val);
               // alert(response);
                  if(actionType == 'Update') {
                      continue;
                  }
                  if(val.length < 1 || response == "true") {
                     if(response == "true") labelWarning(name, id, "input",true);
                     else labelWarning(name,id,"input");
                      onChangeCheck(id, name, type, "input");
                  }
            }

        }
        else if(type == 'text' && name == 'email'){
            if(!validateEmail($('#'+id).val())){
                labelWarning(name,id,"input");
                onChangeCheck(id,name,type,"input");
            }
        }
        else if(type == 'text' && name == 'contactNumber'){
            if(!validateContactNumber($('#'+id).val())){
                labelWarning(name,id,"input");
                onChangeCheck(id,name,type,"input");
            }
        }
        else if(type == "number"){
            if($('#'+id).val() == ""){
                labelWarning(name,id,"input");
                onChangeCheck(id,name,type,"input",1)
            }
        }
    }
    //Input Dropdowns
    var dropdown = $("select");
    for(var i = 0; i < dropdown.length;i++){
        var type = dropdown[i].getAttribute('type');
        var name = dropdown[i].getAttribute('name');
        var id = dropdown[i].getAttribute('id');
        //console.log(id);

        var val = $('#'+id).val();

        if(val <= 0){
            labelWarning(name,id,"select");
            onChangeCheck(id,name,type,"select");
        }
    }
    var textbox = $("textarea");
    for(var i = 0; i < textbox.length;i++){
        var type = textbox[i].getAttribute('type');
        var name = textbox[i].getAttribute('name');
        var id = textbox[i].getAttribute('id');

        var val = $('#'+id).val();
        if(val.length <= 0){
            labelWarning(name,id,inputType);
        }
    }
}
