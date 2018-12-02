/**
 * Created by User on 10-Oct-18.
 */
$(document).ready(function () {
    $("#listTable").dataTable();
    $('#isActiveYes').prop("checked",true);
});

function formRefresh(){
    $('#name').val('');
    $('#designationShortName').val('');
    $('#isActiveYes').prop("checked",true);
    $('.error').remove();
    $("#save").html('Save');
}

function formEdit(identifier){
    var getId = $(identifier).data('id');
    $.get('/designation/getDesignation/'+getId,function(responseText){
        $('#id').val(responseText.designationId);
        $('#name').val(responseText.designationName);
        $('#designationShortName').val(responseText.designationShortName);
        $('#employmentTypeId').val(responseText.employmentTypeId);
        $('#employeeTypeId').val(responseText.employeeTypeId);
        if(responseText.isActive == 1) {
            $('#isActiveYes').prop("checked",true);
        }
        else $('#isActiveNo').prop("checked",true);
        $("#add").html('Update');
    });
}