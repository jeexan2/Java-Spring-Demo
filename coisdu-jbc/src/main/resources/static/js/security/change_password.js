$(document).ready(function() {
    var validation = function () {
        var isValid = true;
        $(".err_msg").text("");
        $(".chk_pass").each(function(){
            if(this.value.length <= 5) {
                $("#err_" + this.id ).text("Password should be atleast 6 char long");
                isValid = false;
            }
        });
        if($('#new_password').val() != $('#new_password_again').val()){
            $('#err_new_password_again').text("Should be same as the new password");
            isValid = false;
        }
        return isValid;
    };

    $(document).on("keyup", ".chk_pass", function () {
        $(".err_msg").text("");
        if(this.value.length <= 5) {
            $("#err_" + this.id ).text("Password should be atleast 6 char long");
        }
    });

    $(document).on("keyup", "#new_password_again", function () {
        if($('#new_password').val().length == 0){
            $("#err_" + this.id ).text("Please enter a new password first");
            $('#new_password_again').val("");
        }
        else {
            if($('#new_password').val() != $(this).val()){
                $("#err_" + this.id ).text("Should be same as the new password");
            }
        }
    });

    $("#refresh_button").click(function (event) {
        $(".chk_pass").val("");
        $(".err_msg").text("");
    });

    $("#submit_button").click(function (event) {
        event.preventDefault();
        var isVaild = validation();
        if(isVaild) {
            confirmDialog("Are you sure to change your password?");
        }
    });
});

