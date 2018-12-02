/**
 * Created by User on 13-Nov-18.
 */

function officeFormValidation() {
    var formLabels = $("input,textarea,select");
    var flag = true;
    $.each(formLabels,function(){
       var type = this.getAttribute('type');
       var id = this.getAttribute('id');
       var name = this.getAttribute('name');
       var val = $('#'+id).val();
      // console.log($('#'+this.getAttribute('id')).val());
       if(name == "name"){
         if(val == '') {
             $('input[name=name]').
                after('<span class=name>The Office Name cannot be empty!</span>');
             $('.name').addClass('error');
             flag = false;
         }
       }
       else if(name == "shortName"){
           if(val == '') {
               $('input[name=shortName]')
                   .after('<span class=shortName>The Office Short Name cannot be empty!</span>');
               $('.shortName').addClass('error');
               flag = false;
           }
       }
       else if(name == "email"){
           if(!validateEmail(val)) {
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
               flag = false;
           }
       }
       else if(name == "contactNumber"){
           if(val == ''){
               $('input[name=contactNumber]')
                   .after('<span class=contactNumber>Contact Number cannot be empty!</span>');
               $('.contactNumber').addClass('error');
               flag = false;

           }
           else if(!validateContactNumber(val)){
               $('input[name=contactNumber]')
                   .after('<span class=contactNumber>Please enter valid Contact Number!</span>');
               $('.contactNumber').addClass('error');
               flag = false;
           }
       }
       else if(name == "category"){
           if(val == -1){
               $('select[name=category]')
                   .after('<span class=category>Please Select the Category</span>');
               $('.category').addClass('error');
               flag = false;
           }
       }
       else if(name == "divisionId") {
           if(val == -1){
               $('select[name=divisionId]')
                   .after('<span class=divisionId>Please Select the Division</span>');
               $('.divisionId').addClass('error');
               flag = false;
           }
       }
       else if(name == "districtId"){
           if(val == -1 || val == null){
               $('select[name=districtId]')
                   .after('<span class=districtId>Please Select the District</span>');
               $('.districtId').addClass('error');
               flag = false;
           }

       }
       else if(name == "thanaId"){
           if(val == -1 || val == null){
               $('select[name=thanaId]')
                   .after('<span class=thanaId>Please Select the Thana</span>');
               $('.thanaId').addClass('error');
               flag = false;
           }
       }
       else if(name == "postCode"){
           if(!validatePostCode(val)){
               var errorString = '';
               if(val.length == 0)
                   errorString = 'Post Code cannot be empty!';
               else errorString = 'Please enter a 4 length number as PostCode';
               $('input[name=postCode]')
                   .after('<span class=postCode>'+errorString+'</span>');
               $('.postCode').addClass('error');
               flag = false;
           }
       }


    });
    return flag;
}


