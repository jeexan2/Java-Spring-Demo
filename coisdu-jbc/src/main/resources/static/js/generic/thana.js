/**
 * Created by User on 04-Nov-18.
 */
var rowCount;
$(document).ready(function() {
    $('#datatable').dataTable();
    rowCount = $('#datatable').dataTable().fnGetData().length;
    //console.log(rowCount);

});
$('#divisionCode').on("change",function () {
    dropDownLoadOfDistrict();
});
function dropDownLoadOfDistrict(id){
    // console.log(id);
    $.getJSON('/generic/getDistrictList',{
            divisionCode: $('#divisionCode').val(),
            ajax: true
        },
        function (data) {
            var html = '';
            var len = data.length;
            for(var i = 0; i < len; i++){
                html += '<option value=' + data[i].districtId + '>'+data[i].districtName+'</option>';

            }
            $('#districtCode').empty();
            $('#districtCode').append(html);
            if(typeof id !== "undefined") {
                $('#districtCode').val(id);
            }
            // $('#districtId').val(Number(data[0].districtId));
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


function formEdit(identifier){
    var getId = $(identifier).data('id');
    $.get('/generic/thana/'+getId,function(responseText){
        $('#thanaName').val(responseText.thanaName);
        $('#id').val(responseText.thanaId);
        $('#shortName').val(responseText.shortName);
        $('#divisionCode').val(responseText.divisionCode);
        //$('#districtCode').val(responseText.districtCode);
        dropDownLoadOfDistrict(responseText.districtCode);
        $('#add').html('Update');
    });
}
$('#addThanaForm').on("submit",function (e) {
    e.preventDefault();
    $('.error').remove();
    formValidation($('#add').html());
    var textStr = $('#add').html() == "Add" ? "Add Thana" : "Update Thana";
    var check = validateForm($('#add').html());
    if(check){
        $.confirm({
            title: textStr,
            buttons:{
                addDistrict: {
                    btnClass: "btn-blue",
                    text:textStr,
                    action: function () {
                        $('#addThanaForm').unbind("submit").submit();
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
        title: 'Delete Thana?',
        content: 'Are you sure you want to delete the Thana?',
        // autoClose: 'cancelAction|8000',
        buttons: {
            deleteThana: {
                btnClass: "btn-danger",
                text: 'Delete Thana',
                action: function () {
                    $.ajax({
                        type:'POST',
                        url:'/generic/deleteThana/' + getId,
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