/**
 * Created by User on 23-Oct-18.
 */
$(document).ready(function () {
    $('#datatable').DataTable();
    $('#isActive1').prop("checked",true);
    $('#username').prop("readonly",false);
    $('#isLocked').on("change",function(){
        //alert("Is Lock changed");
        var value = $("input[name='isLocked']:checked").val();
       // alert(value);
        var lockedDiv = $('#lockedReasonHidden');
        if(value == 1){
            lockedDiv.css({"display":"block"});
        }
        else if(value == 0){
            lockedDiv.css({"display":"none"});
        }
    });
    $('#isActive').on("change",function(){
        var value = $("input[name='isActive']:checked").val();
       var activeDiv = $('#inActiveReasonHidden');
       if(value == 0){
           activeDiv.css({"display":"block"});
       }
       else if(value == 1){
           activeDiv.css({"display":"none"});
       }
    });
});

function submitForm(){
    var forms = [];
    $("form").each(function(){
        forms.push(this.name);
    });
    var buttons = [];
    $("form:button").each(function(){
        buttons.push(this);
    });
    //console.log(buttons);
  //  console.log("Buttons "+buttons);

    //console.log(forms);
    for(var i = 0;i < forms.length;i++){
        //alert(forms[i]);
        $('form[name ='+forms[i]+']').on("submit",function(e){
            e.preventDefault();
            $('.error').remove();
            var curSubmit = this.find(":submit");
          //  console.log(curSubmit);
            var buttons = $("button");
            for(var i = 0; i < buttons.length;i++){
                var name = buttons[i].getAttribute('name');
                //alert(name);
            }

        });
    }

}
/**var formNames = [];
$("form").each(function(){
   formNames.push(this.name);
});
formNames.each(function () {
   var btns = [];
   var frm = $("form[name="+this+"]");
   btns = frm.find(":button");
    $('#'+btns[0].id).html().on("submit",function(e){
        e.preventDefault();
      alert("fafsa");
   });
});**/

$('#addUserForm').on("submit",function (e) {
    e.preventDefault();
    $('.error').remove();
    formValidation($('#add').html());
    //Form Get

    //Form Get

    //alert($('#'+btns[0].id).html()+ " "+$('#add').html());
    var action = $('#add').html();
    var check = validateForm($('#add').html());
    var textStr = "Are you want to "+action+ " the User?";
    if(check) {
        $.confirm({
            title: textStr,
            buttons: {
                addUser: {
                    btnClass: "btn-blue",
                    text: "Yes",
                    action: function () {
                        $('#addUserForm').unbind("submit").submit();
                    }
                },
                cancel: function () {
                    //formRefresh();
                }
            }
        });
    }
});
//Toggle Password
$(".toggle-password").click(function() {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $('#password');
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});

//Radio Button

function formRefresh(){
    $('.error').remove();
    var inputElements = $("input");
    var selectElements = $("select");
    var radioFlag = false;
    $('#username').prop("readonly",false);
    for(var i = 0; i < inputElements.length;i++){
        var type = inputElements[i].getAttribute('type');
        var name = inputElements[i].getAttribute('name');
        var id = inputElements[i].getAttribute('id');
        //console.log("id: "+id+" name: "+name+" type:"+type);

        if(type == "text" || type == "password"){
            $('#'+id).val('');
        }
        else if(type=="hidden" && name == "id"){
            $('#'+id).val('-1');
        }
        else if(type == "radio"){
            $('input[name='+name+']').prop('checked', true);
        }

    }
    $('#isActive').prop('checked',true);
    $('#inActiveReasonHidden').css({"display":"block"});
    $('#inactiveReason').val('');
    $('#lockedReasonHidden').css({"display":"none"});
    $('#lockedReason').val('');
    for(var i = 0; i < selectElements.length;i++){
        var id = selectElements[i].getAttribute("id");
        var onLoad = $('#'+id).data('id');
        // alert(onLoad);
        if(onLoad == "onLoad") {
            $('#'+id).empty();
        }
        else $('#'+id).val('-1');
    }

    $('#add').html('Add');

}

//On change parts
$('#officeCategory').on("change",function () {
    dropDownLoadOffice();
});

function dropDownLoadOffice(id){
    $.getJSON('/security/getOfficeList',{
        officeCategory: Number($('#officeCategory').val()),
        ajax: true
    },function(data){
        var htmlAdd = '';
        var len = data.length;
        for(var i = 0; i < len; i++){
            htmlAdd += '<option value=' + data[i].secOfficeId + '>'+data[i].name+'</option>';

        }
        $('#officeId').empty();
        $('#officeId').append(htmlAdd);
        if(typeof id != "undefined"){
            $("#officeId").val(id);
            //console.log(id);
           // alert(id);
        }
    });

}

function formEdit(identifier){
    formRefresh();
    var getId = $(identifier).data('id');
    $.get('/security/getUser/'+getId,function(responseText){
        //console.log(responseText);

         $('#username').val(responseText.username);
         $('#username').prop("readonly",true);
         $('#id').val(responseText.userId);
         $('#password').val(responseText.password);
         $('#officeCategory').val(responseText.officeCategory);
          dropDownLoadOffice(responseText.officeId);
        //$('#officeId').val(responseText.officeId);
         $('#roleId').val(responseText.roleId);
         console.log(responseText.isActive);
         console.log(responseText.isLocked);
         if(responseText.isActive == 0){
             $('#lockedReasonHidden').css({"display":"block"});
             $('#lockedReason').val(responseText.lockedReason);
         }
         $('input:radio[name = isActive]').val([responseText.isActive]);
         if(responseText.isLocked == 1){
             $('#inActiveReasonHidden').css({"display":"block"});
             $('#inactiveReason').val(responseText.inactiveReason);

         }
         $('input:radio[name = isLocked]').val([responseText.isLocked]);

         $("#add").html('Update');
    });

}

function passwordCheck(pass){
    var regex = /^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\d]{8,}$/ ;
    return regex.test(pass);
}
function formDelete(identifier){

    var getId =  $(identifier).data('id').split(' ')[0];
    var rowId = $(identifier).data('id').split(' ')[1];
   // alert("Id:"+getId +" row:"+rowId);
    $.confirm({
        title: 'Delete User?',
        content: 'Are you sure you want to delete the User?',
        // autoClose: 'cancelAction|8000',
        buttons: {
            deleteOffice: {
                btnClass: "btn-danger",
                text: 'Delete User',
                action: function () {
                    $.ajax({
                        type:'POST',
                        url:'/security/deleteUser/' + getId,
                        success:function(){
                            //formRefresh();
                            var siblings = $(identifier).closest('tr');
                            $(identifier).closest('tr').css('background','tomato');
                            $(identifier).closest('tr').fadeOut(800, function(){
                                $(identifier).remove();
                                var sL = rowId;
                                //alert(sL);
                                var table = $('#datatable').DataTable();
                                var rowCount =$('#datatable').dataTable().fnGetData().length;
                                //alert(rowCount);
                                for(var i = parseInt(rowId); i <= rowCount;i++){
                                    // var idString = '#office-row-' + parseInt(i);

                                    table.cell(i,0).data(Number(table.cell(i,0).data())-1);
                                }
                            });
                        },error:function(){

                        }
                    });
                }
            },
            cancel: function () {
                $.alert('Action is canceled');
            }
        }
    });
}