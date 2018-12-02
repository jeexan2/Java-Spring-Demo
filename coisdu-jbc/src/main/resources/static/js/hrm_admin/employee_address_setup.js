/**
 * Created by User on 20-Nov-18.
 */
$(document).ready(function () {

    $('#presentAddress').on("keyup",function () {
       var val = $(this).val();
       var id = $(this).attr('id');
       var name = $(this).attr('name');
        var labelName = $('label[for="' + id + '"]').html();

       if(val.trim().length >= 1){
           $('.'+name).remove();
       }
       else {

           $('.'+name).remove();
           $('#'+id).after('<span class='+name+'>'+labelName+' cannot be empty!</span>');
           $('.'+name).addClass("error");
       }
    });
    $('#permanentAddress').on("keyup",function () {
        var regexChar = /^([a-z])|([A-Z])$/;
        var val = $(this).val();
        var name = $(this).attr('name');
        var id = $(this).attr('id');
        var labelName = $('label[for="' + id + '"]').html();
        if(val.trim().length >= 1){
            $('.'+name).remove();
        }
        else {
            $('.'+name).remove();
            $('#'+id).after('<span class='+name+'>'+labelName+' cannot be empty!</span>');
            $('.'+name).addClass("error");
        }
    });
   $('#nationalityId').on("change",function () {
       var val = $(this).val();

      if(val == -1){

      }
      else {
          var name = $(this).attr('name');
          $('.'+name).remove();
      }
   });
   $('#countryId').on("change",function(){
       var val = $(this).val();
       if(val == -1){

       }
       else {
           var name = $(this).attr('name');
           $('.'+name).remove();
       }
    });
   $('#presentThanaId').on("change",function () {
       var val = $(this).val();
       if(val == -1){

       }
       else {
           var name = $(this).attr('name');
           $('.'+name).remove();
       }
   });
   $('#permanentThanaId').on("change",function () {
       var val = $(this).val();
       if(val == -1){

       }
       else {
           var name = $(this).attr('name');
           $('.'+name).remove();
       }
   });
    $('#presentDivisionId').on("change",function () {
        var val = $('#presentDivisionId').val();
        if(val == -1){

        }
        else {
            var name = $(this).attr('name');
            $('.'+name).remove();
            dropDownLoadOfDistrict(val,"present");
        }
    });
    $('#presentDistrictId').on("change",function () {
        var val = $('#presentDistrictId').val();
        if(val == -1){

        }
        else {
            var name = $(this).attr('name');
            $('.'+name).remove();
            dropDownLoadOfThana(val,"present");
        }
    });
    $('#permanentDivisionId').on("change",function () {
        var val = $('#permanentDivisionId').val();
        if(val == -1){

        }
        else {
            var name = $(this).attr('name');
            $('.'+name).remove();
            dropDownLoadOfDistrict(val,"permanent");
        }
    });

    $('#permanentDistrictId').on("change",function () {
        var val = $('#permanentDistrictId').val();
        if(val == -1){

        }
        else {
            var name = $(this).attr('name');
            $('.'+name).remove();
            dropDownLoadOfThana(val,"permanent");
        }
    });

    function dropDownLoadOfThana(id,curState){
       // console.log(curState);
        $.getJSON('/generic/getThanaForDistrict/'+id,{

                ajax: true
            },
            function (data) {
                var htmlAdd = '';
                var len = data.length;
                for(var i = 0; i < len; i++){
                    htmlAdd += '<option value=' + data[i].thanaId + '>'+data[i].thanaName+'</option>';

                }
                $('#'+curState+'ThanaId').empty();
                $('#'+curState+'ThanaId').append(htmlAdd);
                if(typeof id !== "undefined") {
                    $('#'+curState+'ThanaId').val(id);
                }
                //else $('#'+curState+'ThanaId').val(-1);
            });
    }


    function dropDownLoadOfDistrict(id,curState){

        $.getJSON('/generic/getDistrictForDivision/'+id,{
                ajax: true
            },
            function (data) {
           // console.log(data);
                var html = '';
                var len = data.length;
                for(var i = 0; i < len; i++){
                    html += '<option value=' + data[i].districtId + '>'+data[i].districtName+'</option>';

                }
                $('#'+curState+'DistrictId').empty();
                $('#'+curState+'DistrictId').append(html);
                if(typeof id !== "undefined") {
                    $('#'+curState+'DistrictId').val(id);
                }

                // $('#districtId').val(Number(data[0].districtId));
            });
    }

    $('#employeeAddressForm').on("submit",function (e) {
        e.preventDefault();
        $('.error').remove();

        var flag = dropDownValidation();
        flag = textAreaValidation();
     //  if(flag == true) $('#employeeAddressForm').unbind('submit').submit();

    });
    function dropDownValidation(){
        var flag = true;
        var dropdowns = document.
                        forms['employeeAddressForm'].
                        getElementsByTagName('select');
        for(var i = 0; i < dropdowns.length;i++){
           // console.log(dropdowns[i].getAttribute('name'));
            var name = dropdowns[i].getAttribute('name');
            var id = dropdowns[i].getAttribute('id');
            var val = $('#'+id).val();
            if(val == -1 || val == null){
                $('#'+id).after('<span class='+name+'>Please Select the dropdown!</span>');
                $('.'+name).addClass("error");
                flag = false;
            }
        }
        return flag;
    }
    function textAreaValidation(){
        flag = true;
        var textAreas = document.
            forms['employeeAddressForm'].
        getElementsByTagName('textarea');

        for(var i = 0; i < textAreas.length;i++){
            var name = textAreas[i].getAttribute('name');
            var id = textAreas[i].getAttribute('id');
            var val = $('#'+id).val();
            var labelName = $('label[for="' + id + '"]').html();

            if(val.trim().length < 1){
                $('#'+id).after('<span class='+name+'>'+labelName+' cannot be empty!</span>');
                $('.'+name).addClass("error");
                flag = false;
            }
        }
        return flag;
    }

    function contactNumberValidation(){

    }
    function validateEmail(email){
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
//Contact Number
    function validateContactNumber(number){
        var regex = /^(?:\+?88)?01[1,5,6,7,8,9]{1}[0-9]{8}$/;
        return regex.test(number);
    }
});
