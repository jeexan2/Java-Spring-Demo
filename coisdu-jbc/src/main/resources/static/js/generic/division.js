/**
 * Created by User on 04-Nov-18.
 */
$(document).ready(function () {
    $('#datatable').dataTable();
    table = $('#datatable').dataTable();
    rowCount = $('#datatable').dataTable().fnGetData().length;


});
function formEdit(identifier){
    var getId = $(identifier).data('id');
    $.get('/generic/division/'+getId,function(responseText){
        $('#divisionName').val(responseText.divisionName);
        $('#id').val(responseText.divisionId);
        $('#shortName').val(responseText.shortName);
        $('#add').html('Update');
    });
}

$('#addDivisionForm').on("submit",function (e) {
    e.preventDefault();
    $('.error').remove();
    formValidation($('#add').html());
    var textStr = $('#add').html() == "Add" ? "Add Division" : "Update Division";
    var check = validateForm($('#add').html());
    if(check){
        $.confirm({
            title: textStr,
            buttons:{
                addDivision: {
                    btnClass: "btn-blue",
                    text:textStr,
                    action: function () {
                        $('#addDivisionForm').unbind("submit").submit();
                    }
                },
                cancel : function () {
                    //formRefresh();
                }
            }
        });
    }
});

function formDelete(identifier) {
    var getId =  $(identifier).data('id').split(' ')[0];
    var rowId = $(identifier).data('id').split(' ')[1];
    $.confirm({
        title: 'Delete Division?',
        content: 'Are you sure you want to delete the division?',
        // autoClose: 'cancelAction|8000',
        buttons: {
            deleteDivision: {
                btnClass: "btn-danger",
                text: 'Delete Division',
                action: function () {
                    $.ajax({
                        type:'POST',
                        url:'/generic/deleteDivision/' + getId,
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

function formRefresh() {
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
    $("#add").html('Add');
}