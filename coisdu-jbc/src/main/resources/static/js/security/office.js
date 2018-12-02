/**
 * Created by User on 04-Oct-18.
 */
var rowCount;
var request;
rowCount = $('#datatable').dataTable().fnGetData().length;
$(document).ready(function() {


    $('#datatable').dataTable();
    rowCount = $('#datatable').dataTable().fnGetData().length;
    //console.log(rowCount);
    $('#isActive1').prop("checked",true);
   // formRefresh();
   // dropDownLoad(dropDownLoadOfDistrict());
});
//officeFormValidation();
//On change DropDown
$('#divisionId').on("change",function(){
    //dropDownLoad(dropDownLoadOfDistrict());
    $('.divisionId').remove();
    var val = $('#divisionId').val();
    if(val == -1){
        $('select[name=divisionId]')
            .after('<span class=divisionId>Please Select the Division</span>');
        $('.divisionId').addClass('error');
    }

    dropDownLoadOfDistrict();
});
$('#districtId').on("change",function(){
    var val = $('#districtId').val();
    $('.districtId').remove();
    if(val == -1 || val == null){
        $('select[name=districtId]')
            .after('<span class=districtId>Please Select the District</span>');
        $('.districtId').addClass('error');
    }

    dropDownLoadOfThana();
});
$('#thanaId').on("change",function () {
   var val = $('#thanaId').val();
   $('.thanaId').remove();
   if(val == -1 || val == null){
       $('select[name=thanaId]')
           .after('<span class=thanaId>Please Select the Thana</span>');
       $('.thanaId').addClass('error');
   }
});

$('#email').on("keyup",function () {
    var val = $('#email').val();
   if(validateEmail(val) == true){
       $('.email').remove();
   }
   else {
       $('.email').remove();
       var at = val.includes('@');
       var dot= val.includes('.');
       // console.log(atMis+' '+dotMis);
       var errorString = '';

       if(at== false && dot == false) errorString += '@ and . Missing in your entered email';
       else if(at == false && dot == true) errorString += '@ Missing in your email';
       else if(at== true && dot == false) errorString += '. Missing in your email';
       else errorString += 'Invalid format.Please check the entered email';
       // console.log(at + ' '+dot);
       $('input[name=email]')
           .after('<span class=email>'+errorString+'</span>');
       $('.email').addClass('error');
   }
});
$('#contactNumber').on("keyup",function () {
    var val = $('#contactNumber').val();
    $('.contactNumber').remove();
    if(val == ''){
        $('input[name=contactNumber]')
            .after('<span class=contactNumber>Contact Number cannot be empty!</span>');
        $('.contactNumber').addClass('error');

    }
    else if(!validateContactNumber(val)){
        $('input[name=contactNumber]')
            .after('<span class=contactNumber>Please enter valid Contact Number!</span>');
        $('.contactNumber').addClass('error');
    }

});
$('#category').on("change",function () {
   var val = $('#category').val();
   $('.category').remove();
   if(val == -1 ){
       $('select[name=category]')
           .after('<span class=category>Please Select the Category</span>');
       $('.category').addClass('error');
   }

});
$('#postCode').on("keyup",function () {
    var val = $('#postCode').val();
    if(validatePostCode(val) == true){
        $('.postCode').remove();
    }
    else {
        $('.postCode').remove();
        var errorString = '';
        if(val.length == 0)
            errorString = 'Post Code cannot be empty!';
        else errorString = 'Please enter a 4 length number as PostCode';
        $('input[name=postCode]')
            .after('<span class=postCode>'+errorString+'</span>');
        $('.postCode').addClass('error');
    }
});

$('#name').on("keyup",function(){
    var val = $('#name').val();
    if(val.length > 0){
        $('.name').remove();
    }
    else {
        $('.name').remove();
        $('input[name=name]').
        after('<span class=name>The Office Name cannot be empty!</span>');
        $('.name').addClass('error');
    }
});

$('#shortName').on("keyup",function () {
   var val = $('#shortName').val();
   if(val.length > 0){
       $('.shortName').remove();
   }
   else {
       $('.shortName').remove();
       $('input[name=shortName]')
           .after('<span class=shortName>The Office Short Name cannot be empty!</span>');
       $('.shortName').addClass('error');
   }
});
//On change DropDown

//Callback DropDown
function dropDownLoad(callback){
        setTimeout(function () {
            dropDownLoadOfThana();
           if(typeof callback != "undefined") callback();
        }, 500);
}
//Callback DropDown
//DropDownLoad
function dropDownLoadUpdate(callback,id){
    setTimeout(function () {
        dropDownLoadOfThana(id);
        if(typeof callback != "undefined") callback();
    }, 500);
}

function dropDownLoadOfDistrict(id){
   // console.log(id);
   $.getJSON('/security/getDistrictList',{
       divisionId: $('#divisionId').val(),
       ajax: true
       },
    function (data) {
        var html = '';
        var len = data.length;
        for(var i = 0; i < len; i++){
            html += '<option value=' + data[i].districtId + '>'+data[i].districtName+'</option>';

        }
        $('#districtId').empty();
        $('#districtId').append(html);
        if(typeof id !== "undefined") {
            $('#districtId').val(id);
        }
      // $('#districtId').val(Number(data[0].districtId));
    });
}


function dropDownLoadOfThana(id){
    //alert($('#districtId').val());
  //  alert(id);
  //  console.log(id);
    $.getJSON('/security/getThanaList',{
        districtId: Number($('#districtId').val()),
        ajax: true
        },
        function (data) {
            var htmlAdd = '';
            var len = data.length;
            for(var i = 0; i < len; i++){
                htmlAdd += '<option value=' + data[i].thanaId + '>'+data[i].thanaName+'</option>';

            }
            $('#thanaId').empty();
            $('#thanaId').append(htmlAdd);
            if(typeof id !== "undefined") {
                $('#thanaId').val(id);
               // console.log(data);
            }
    });
}
// Regex Check Part
//Email
function validateEmail(email){
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}
//Contact Number
function validateContactNumber(number){
    var regex = /^(?:\+?88)?01[1,5,6,7,8,9]{1}[0-9]{8}$/;
    return regex.test(number);
}

function validatePostCode(postCode){
    var regex = /^[0-9]{4}$/;
    return regex.test(postCode);
}
//Regex Check Part End


function formRefresh(){
   /* $('#name').val('');
    $('#id').val('-1');
    $('#shortName').val('');
    $('#type').val('');
    $('#category').val('-1');
    $('#description').val('');
    $('#address').val('');
    $('#email').val('');
    $('#contactNumber').val('');
    $('#districtId').val('-1');
    $('#divisionId').val('-1');
    $('#thanaId').val('-1');
    $("#municipalityId").val('-1');
    $("#postCode").val('');
    $('#isActive1').prop("checked",true);*/
   var inputElements = $("input");
    var selectElements = $("select");
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
        else if(type == "radio" && id.includes("1")){
            //$("input:radio[name="+name+"]:not(:disabled):first")
            $('#'+id).prop("checked",true);
        }
    }
    var textAreas = $("textarea");
    for(var i = 0; i < textAreas.length;i++){
        var id = textAreas[i].getAttribute('id');
        $('#'+id).val('');
    }
    for(var i = 0; i < selectElements.length;i++){
        var id = selectElements[i].getAttribute("id");
        var onLoad = $('#'+id).data('id');
       // alert(onLoad);
        if(onLoad == "onLoad") {
            //console.log(id)
            $('#'+id).empty();
        }
        else $('#'+id).val('-1');
    }
    $('.error').remove();
    $("#add").html('Save');
   //$('#addForm')[0].reset();

}
function formEdit(identifier,rowId){
    formRefresh();
    var getId = $(identifier).data('id').split(' ')[0];
    var rowId = $(identifier).data('id').split(' ')[1];
    console.log("id "+getId);
    $.get('/security/getOffice/'+getId,function(responseText){
        console.log(responseText);
        $('#name').val(responseText.name);
        $('#id').val(responseText.secOfficeId);
        $('#shortName').val(responseText.shortName);
        $('#type').val(responseText.type);
        $('#category').val(responseText.category);
        $('#description').val(responseText.description);
        $('#address').val(responseText.address);
        $('#email').val(responseText.email);
        $('#contactNumber').val(responseText.contactNumber);
       // console.log(responseText.divisionId);
        if(responseText.divisionId != null)
           $('#divisionId').val(responseText.divisionId);
        //Callback Again
         dropDownLoadUpdate(dropDownLoadOfDistrict(responseText.districtId),responseText.thanaId);
        $('#municipalityId').val(responseText.municipalityId);
        $('#postCode').val(responseText.postCode);
        if(responseText.isActive == 1) {
            $('#isActive1').prop("checked",true);
        }
        else $('#isActive2').prop("checked",true);
        $("#add").html('Save');
    });
    $('#rowId').val(rowId);
}

function formDelete(identifier){

    var getId =  $(identifier).data('id').split(' ')[0];
    var rowId = $(identifier).data('id').split(' ')[1];

    $.confirm({
        title: 'Delete Office?',
        content: 'Are you sure you want to delete the office?',
       // autoClose: 'cancelAction|8000',
        buttons: {
            deleteOffice: {
                btnClass: "btn-danger",
                text: 'Delete Office',
                action: function () {
                    $.ajax({
                        type:'POST',
                        url:'/security/deleteOffice/' + getId,
                        success:function(){

                            formRefresh();
                            var siblings = $(identifier).closest('tr');
                            $(identifier).closest('tr').css('background','tomato');
                            $(identifier).closest('tr').fadeOut(800, function(){
                                $(identifier).remove();
                              var sL = rowId;
                              rowCount = $('#datatable').dataTable().fnGetData().length;
                              var table = $('#datatable').DataTable();
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




$('#addForm').on("submit",function (e) {
   e.preventDefault();
   $(".error").remove();
  // formValidation();
   var check = officeFormValidation();
    var textStr = $('#add').html() == "Save" ? "Add Office" : "Update Office";
    var action = $('#add').html();
    //var check = validateForm();

   /** if(check){
        $.confirm({
            title: textStr,
            buttons:{
                addUser: {
                    btnClass: "btn-blue",
                    text:textStr,
                    action: function () {
                        $('#addForm').unbind("submit").submit();
                    }
                },
                cancel : function () {
                    //formRefresh();
                }
            }
        });
    }
    **/
    if(check) {

        if (request) {
            request.abort();
        }
        var inputs = $(this).find("input, select,textarea");

        var serializedData = inputs.serialize();
        inputs.prop("disabled",true);
      //  console.log(serializedData);

        request = $.ajax({
            url:"/security/office",
            //contentType: "application/json",//; charset=utf-8",
            type: "POST",
            data: serializedData
        });

        // Callback handler that will be called on success
        request.done(function (response, textStatus, jqXHR){
           // $.alert("Office has been added Successfully!");


            formRefresh();

            var getUserPermission = function () {
                var permission = {};
                $.getJSON('/security/getUserPermission', {
                        ajax: true, async: false
                    },
                    function (data) {
                        permission.addPermit = data.addPermit;
                        permission.editPermit = data.editPermit;
                        permission.deletePermit = data.deletePermit;
                    });
                return permission;
            }();

            if(action === "Update"){
                   var tableRow = request.responseJSON;
                   console.log(tableRow);
                   var rowId = $('#rowId').val();
                   console.log(rowId);
                  // console.log(tableRow);
                   //var row = $("tr").find("[data-id="+tableRow.secOfficeId+"]");
                    var table = $('#datatable').DataTable();
                    console.log(table.cell(Number(rowId-1),0).data());
                    table.cell(Number(rowId -1),0).data(rowId);
                    table.cell(Number(rowId-1),1).data(tableRow.officeName);
                    table.cell(Number(rowId-1),2).data(tableRow.shortName);
                    table.cell(Number(rowId-1),3).data(tableRow.divisionName);
                    table.cell(Number(rowId-1),4).data(tableRow.districtName);
                    table.cell(Number(rowId-1),5).data(tableRow.thanaName);
                    table.cell(Number(rowId-1),6).data(tableRow.address);

            }
            else  {
                var table = $('#datatable').DataTable();
                //console.log("Table cell:"+ table.cell(rowCount-1,0).data());
                var tableRow = request.responseJSON;
                tableRow.rowId = Number(table.cell(Number($('#datatable').dataTable().fnGetData().length) - 1, 0).data()) + 1;
                // rowCount = rowCount + 1;
                var len = $('#datatable').dataTable().fnGetData().length;
                console.log(table.cell(len - 1, 0).data());
                console.log("RowId:" + tableRow.rowId);
                console.log("Length:" + $('#datatable').dataTable().fnGetData().length);
                var dataIdInfo = tableRow.secOfficeId+ ' '+ tableRow.rowId;
                var editBtn = "";
                var deleteBtn = "";
                if (getUserPermission.editPermit == 1){
                     editBtn = "<div class='col-xs-6 previous'><a class='btn btn-success' value='Edit' onclick=formEdit(this) data-id=" + dataIdInfo + "><i class='fa fa-pencil'></i></a></div>";
                }
                if (getUserPermission.deletePermit == 1){
                     deleteBtn = "<div class='col-xs-6 next'><a class='btn btn-danger' value='Delete' onclick=formDelete(this) data-id=" + dataIdInfo + "><i class='fa fa-trash'></i></a></div>";
                }

                table.row.add([tableRow.rowId,
                    tableRow.officeName,
                    tableRow.shortName,
                    tableRow.divisionName,
                    tableRow.districtName,
                    tableRow.thanaName,
                    tableRow.address,
                    editBtn + deleteBtn

                ]).draw('false');
            }

            //console.log("Text Status: "+textStatus);
            //console.log("jqXHR: "+jqXHR);
        });

        request.fail(function (jqXHR, textStatus, errorThrown){
            // Log the error to the console
            $.alert("failed");
            console.log(
                "The following error occurred: "+
                textStatus, errorThrown
            );
        });

        request.always(function () {
            // Reenable the inputs
            inputs.prop("disabled", false);
            var successText = "Suceesfully Action Performed!";
            $('#success').addClass('alert alert-success');
            $('#success').html(successText);
            //$('#success').removeClass('alert flash-alert success-alert');
            //$('#success').html('');
        });

   }
});

