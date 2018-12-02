/**
 * Created by User on 10-Oct-18.
 */
$(document).ready(function () {
    $("#listTable").dataTable();
    $('#isActiveYes').prop("checked",true);
});

function formRefresh(){
    $('#name').val('');
    $('#shortName').val('');
    $('#isActiveYes').prop("checked",true);
    $('.error').remove();
    $("#save").html('Save');
}

function formEdit(identifier){
    var getId = $(identifier).data('id');
    $.get('/grade/getGrade/'+getId,function(responseText){
        $('#id').val(responseText.gradeId);
        $('#name').val(responseText.gradeName);
        $('#shortName').val(responseText.shortName);
        if(responseText.isActive == 1) {
            $('#isActiveYes').prop("checked",true);
        }
        else $('#isActiveNo').prop("checked",true);
        $("#add").html('Update');
    });
}