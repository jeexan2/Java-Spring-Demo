/**
 * Created by User on 16-Oct-18.
 */
var rowCount;
var table = null;
$(document).ready(function () {
    $('#datatable').dataTable();
    table = $('#datatable').dataTable();
    rowCount = $('#datatable').dataTable().fnGetData().length;


});

function formEdit(identifier){
    formRefresh();
    var getId = $(identifier).data('id');
    $.get('/generic/lookup/'+getId,function(responseText){
        $('#name').val(responseText.name);
        $('#id').val(responseText.lookupId);
        $('#shortName').val(responseText.shortName);
        $('#displayName').val(responseText.displayName);
        $('#value').val(responseText.value);
        $('#description').val(responseText.description);
        $('#type').val(responseText.type);
        if(responseText.isActive == true) {
            $('#isActive1').prop("checked",true);
        }
        else $('#isActive2').prop("checked",true);

        $('#add').html('Update');
    });

}
function formDelete(identifier) {
    var getId =  $(identifier).data('id').split(' ')[0];
    var rowId = $(identifier).data('id').split(' ')[1];
    $.confirm({
        title: 'Delete lookup Info?',
        content: 'Are you sure you want to delete the lookup info?',
        // autoClose: 'cancelAction|8000',
        buttons: {
            deleteLookup: {
                btnClass: "btn-danger",
                text: 'Delete Lookup',
                action: function () {
                    $.ajax({
                        type:'POST',
                        url:'/generic/deleteLookup/' + getId,
                        success:function(){
                            formRefresh();
                            $(identifier).closest('tr').css('background','tomato');
                            $(identifier).closest('tr').fadeOut(800, function(){
                                $(identifier).remove();
                                var sL = rowId;
                                var table = $('#datatable').DataTable();
                                rowCount = $('#datatable').dataTable().fnGetData().length;

                                for(var i = parseInt(rowId); i <= rowCount;i++){
                                   // var idString = '#lookup-row-' + parseInt(i);
                                    table.cell(i,0).data(Number(table.cell(i,0).data())-1);
                                }

                            });
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
function formRefresh(){
    $('#name').val('');
    $('#id').val('-1');
    $('#shortName').val('');
    $('#displayName').val('');
    $('#description').val('');
    $('#value').val('');
    $('#type').val('-1');
    $('#isActive1').prop("checked",true);
    $('.error').remove();
    $("#add").html('Add');
}

$('#addLookupForm').on("submit",function (e) {
    e.preventDefault();
    $('.error').remove();
    formValidation();
    var textStr = $('#add').html() == "Add" ? "Add Lookup" : "Update Lookup";
    var check = validateForm();
    if(check){
        $.confirm({
            title: textStr,
            buttons:{
                addLookup: {
                    btnClass: "btn-blue",
                    text:textStr,
                    action: function () {
                        $('#addLookupForm').unbind("submit").submit();
                    }
                },
                cancel : function () {
                    //formRefresh();
                }
            }
        });
    }


});

