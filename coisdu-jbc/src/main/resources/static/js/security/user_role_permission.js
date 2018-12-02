/**
 * Created by Geetanjali Oishe on 10/31/2018.
 */
$(document).ready(function () {


    getModuleList(); //get Module list
    getRoleList(); // get Role List

    function getModuleList() {
        $.get('/security/getModuleList2', {
                ajax: true
            },
            function (data) {
                var htmlAdd = '';
                var len = data.length;
                htmlAdd += '<option class="placeholder" selected disabled value="">Select Module</option>';
                for (var i = 0; i < len; i++) {
                    htmlAdd += '<option value=' + data[i].moduleId + '>' + data[i].moduleName + '</option>';
                }
                $('#module-dropdown').empty();
                $('#module-dropdown').append(htmlAdd);

            });
    }

    function getRoleList() {
        $.get('/security/getRoleList', {
                ajax: true
            },
            function (data) {
                var htmlAdd = '';
                var len = data.length;
                htmlAdd += '<option class="placeholder" selected disabled value="">Select Role</option>';
                for (var i = 0; i < len; i++) {
                    htmlAdd += '<option value=' + data[i].roleId + '>' + data[i].roleName + '</option>';
                }
                $('#role-dropdown').empty();
                $('#role-dropdown').append(htmlAdd);

            });
    }

    $("#module-dropdown").change(function () {
        if ($("#role-dropdown :selected").text() != "Select Role")
            moduleWiseTree();
    });

    $("#role-dropdown").change(function () {
        if ($("#module-dropdown :selected").text() != "Select Module")
            moduleWiseTree();
    });

    function moduleWiseTree() {
        var moduleId = $("#module-dropdown").val();
        var roleId = $("#role-dropdown").val();
        $.get('/security/getModuleWiseTree', {
                moduleId: moduleId,
                roleId: roleId,
                ajax: true
            },
            function (data) {
                $("#tree").html(data);
                $("#submitButton").css("visibility", "visible");
                $("#submitButton").show();
                checkBoxFix();
            });
    }

    function checkBoxFix() {
        var checkedOptions = $(document).find("input:checkbox[name='optionStatus']:checked");  //find checked options

        if (checkedOptions.length > 0) {
            $('#moduleStatus').prop('checked', true);  //if any option checked, module is checked

            $(checkedOptions).each(function (index, value) {   // for any option checked, parent submodule checked
                var v = $(value).parents().eq(4).find("input:checkbox[name='subModuleStatus']").first().prop("checked", true);
            });
        }
        else {
            $( ":checkbox" ).prop('checked', false);
        }
    };

    $(document).on("change","#moduleStatus",function() {
        if (this.checked) {
            $( ":checkbox" ).prop('checked', true);  //check all Submodule and option
        }
        else {
            $( ":checkbox" ).prop('checked', false); //uncheck all Submodule and option
        }
    });

    $(document).on("change", "input[name='subModuleStatus']", function() {
        var subm = $(this).parentsUntil('li[data-type="subModule"]').parent();
        if (this.checked) {
            $(subm).children().find("input[type='checkbox']").prop("checked", true);   // check all options under this submodule
            $('#moduleStatus').prop('checked', true);                              // check module
        }
        else {
            $(subm).children().find("input[type='checkbox']").prop("checked", false); // uncheck all options under this submodule
            checkBoxFix();
        }
    });

    $(document).on("change", "input[name='optionStatus']", function () {
        if (this.checked) {
            optionCheck(this);
        }
        else {
            optionUncheck(this);
        }
    });

    $(document).on("change", "input[name='addPermission']", function (){
        if (this.checked)
            checkByPermission(this);
        else {
            var editPermission = $(this).siblings("input:checkbox[name='editPermission']:checked").length;
            var deletePermission = $(this).siblings("input:checkbox[name='deletePermission']:checked").length;
            if ((editPermission==0) && (deletePermission==0)){
                $(this).siblings("input:checkbox[name='optionStatus']").prop("checked", false);
                optionUncheck(this);
            }
        }
    });

    $(document).on("change", "input[name='editPermission']", function (){
        if (this.checked)
            checkByPermission(this);
        else {
            var addPermission = $(this).siblings("input:checkbox[name='addPermission']:checked").length;
            var deletePermission = $(this).siblings("input:checkbox[name='deletePermission']:checked").length;
            if ((addPermission==0) && (deletePermission==0)){
                $(this).siblings("input:checkbox[name='optionStatus']").prop("checked", false);
                optionUncheck(this);
            }
        }
    });

    $(document).on("change", "input[name='deletePermission']", function (){
        if (this.checked)
            checkByPermission(this);
        else {
            var addPermission = $(this).siblings("input:checkbox[name='addPermission']:checked").length;
            var editPermission = $(this).siblings("input:checkbox[name='editPermission']:checked").length;
            if ((addPermission==0) && (editPermission==0)){
                $(this).siblings("input:checkbox[name='optionStatus']").prop("checked", false);
                optionUncheck(this);
            }
        }
    });

    function checkByPermission (data) {
        $(data).siblings("input:checkbox[name='optionStatus']").prop("checked", true);
        $(data).parents().eq(4).find("input:checkbox[name='subModuleStatus']").first().prop("checked", true);
        $('#moduleStatus').prop('checked', true);
    }

    function optionUncheck(data) {
        $(data).parentsUntil('li[data-type="option"]').parent().children().find("input[type='checkbox']").prop("checked", false);

        var subMod = $(data).parentsUntil('li[data-type="subModule"]').parent().first();
        var checkedOptions = $(subMod).find("input:checkbox[name='optionStatus']:checked");

        if (checkedOptions.length == 0) {
            $(subMod).find("input:checkbox[name='subModuleStatus']:checked").prop('checked', false);
            checkBoxFix();
        }
    }

    function optionCheck(data) {
        $(data).parentsUntil('li[data-type="option"]').parent().children().find("input[type='checkbox']").prop("checked", true);
        $(data).parents().eq(4).find("input:checkbox[name='subModuleStatus']").first().prop("checked", true);
        $('#moduleStatus').prop('checked', true);
    }


    $('#submitButton').click(function (e) {
        // var options = $('#tree').find('li[data-type="option"]');
        // $('#checkboxId').is(':checked')
        // var opt = $(options).
        var options = $(document).find("input[name='optionStatus']");
        var add = $(document).find("input[name='addPermission']");
        var edit = $(document).find("input[name='editPermission']");
        var del = $(document).find("input[name='deletePermission']");

        $(options).each(function (index, value) {
            if (value.checked)
                $(value).prev().prop( "disabled", true );  //disable hidden value
        });

        $(add).each(function (index, value) {
            if (value.checked)
                $(value).prev().prop( "disabled", true );  //disable hidden value
        });

        $(edit).each(function (index, value) {
            if (value.checked)
                $(value).prev().prop( "disabled", true );  //disable hidden value
        });

        $(del).each(function (index, value) {
            if (value.checked)
                $(value).prev().prop( "disabled", true );  //disable hidden value

        });
    });

    $('#refreshButton').click(function() {
        // $("#role-dropdown").empty();
        // $("#module-dropdown").empty();
        getModuleList();
        getRoleList();
        $("#submitButton").hide();
        $("#tree").empty();
    });



});