/**
 * Created by Geetanjali
 * Oishe on 10/4/2018.
 */
$(document).ready(function () {

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


    $( "#option" ).change(function() {
        // $("#access_id").attr("display", "contents");
        $("#access_id").show();
    });

    $("#view-list").empty();

    getOptionSetupTree();

    function getOptionSetupTree() {
        $.get('/security/getOptionSetupTree', {
                ajax: true
            },
            function (data) {
                $("#tree-menu").html(data);
                $('.demo').ntm();
            });
    };


    // turn off when optionsetup tree called
    // $('.demo').ntm();

    $(".radio-button").change(function () {
        $(".error").remove();

        if (this.id == 'option') {
            // $("#access-id").css("display", "contents");
            // $("#module-select-id").css("display", "contents");
            // $("#subModule-select-id").css("display", "contents");
            $("#access-id").show();
            $("#module-select-id").show();
            $("#subModule-select-id").show();
        }

        else if (this.id == 'subModule') {
            // $("#module-select-id").css("display", "contents");
            // $("#subModule-select-id").css("display", "none");
            // $("#access-id").css("display", "none");
            $("#module-select-id").show();
            $("#subModule-select-id").hide();
            $("#access-id").hide();
        }
        else {

            $("#access-id").hide();
            $("#module-select-id").hide();
            $("#subModule-select-id").hide();
        }
    });

    $("#subModule").click(function () {
        // $('#submodule-dropdown').empty();
        dropDownOfModule();
    });

    $("#option").click(function () {
        dropDownOfModule();
    });

    $("#module-dropdown").change(function () {
        dropDownOfSubModule();
    });

    function dropDownOfModule(moduleID, subModuleID) {
        $.getJSON('/security/getModuleList', {
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

                if (typeof moduleID !== "undefined") {
                    $('#module-dropdown').val(moduleID);

                    if (typeof subModuleID !== "undefined") {
                        dropDownOfSubModule(subModuleID);
                        // $('#subModule-dropdown').val(subModuleID);
                    }
                }
            });
    };

    function dropDownOfSubModule(subModuleID) {
        console.log($('#module-dropdown').val())
        $.getJSON('/security/getSubModuleList', {
                moduleId: Number($('#module-dropdown').val()),
                ajax: true
            },
            function (data) {
                var htmlAdd = '';
                var len = data.length;
                htmlAdd += '<option class="placeholder" selected disabled value="">Select Sub-module</option>';
                for (var i = 0; i < len; i++) {
                    htmlAdd += '<option value=' + data[i].subModuleId + '>' + data[i].subModuleName + '</option>';
                }
                $('#subModule-dropdown').empty();
                $('#subModule-dropdown').append(htmlAdd);

                if (typeof subModuleID !== "undefined") {
                    $('#subModule-dropdown').val(subModuleID);
                }
            });
    };

    // Mouse location //works
    $(".tree-menu").mousemove(function (event) {
        $(".btn-group").hide();
        var el = document.elementFromPoint(event.pageX - window.pageXOffset, event.pageY - window.pageYOffset);
        $(el).children(".btn-group").show();
    });

    // $(".tree-menu").hover(function (data) {
    //     console.log("Tree menu hover");
    // })

    // $(document).on("mouseover", "li", function (e) {
    //     // $(".pull-right").hide();
    //     // $(this).children(".pull-right").show();
    //     $(".btn-group").hide();
    //     var b = $(this).children(":first").find(".btn-group").css("display", "block");
    // });

    $(document).on("click", "button", function (event) {

        if (this.value == "Edit") {
            $("#submitButton").html('Update');
            formEdit(this);
        }
        if (this.value == "Delete") {
            var cancelText;
            var confirmDelete = confirm("Confirm Delete?");
            if (confirmDelete == true) {
                formDelete(this);
            } else {
                cancelText = "You pressed Cancel!";
            }

        }

    });


    function formEdit(editData) {
        console.log("Reached Form Edit");
        var elID = $(editData).data('id');
        var elType = $(editData).data('type');

        $.getJSON('/security/getEditData', {
                elementId: elID,
                elementType: elType,
                ajax: true
            },
            function (data) {
                if (elType == "option"  ) {
                    $("#name").val(data.optionName);
                    $("#display-name").val(data.optionDispName);
                    $("#hiddedId").val(data.optionId);
                    // $("#access-id").css("display", "contents");
                    $("#access-id").show();
                    $("#access-url").val(data.accessUrl);
                    $("#option").prop("checked", true);
                    // $("#module-select-id").css("display", "contents");
                    // $("#subModule-select-id").css("display", "contents");
                    $("#module-select-id").show();
                    $("#subModule-select-id").show();
                    var v =  $(editData).parents().filter('[data-type="module"]').get(0);
                    // module ID to get
                    dropDownOfModule(data.moduleId, data.subModuleId);
                    // dropDownOfSubModule(data.subModuleId);

                    if (data.optionActiveSts == 1) {
                        $("#activeStatus").prop('checked', true);
                    }
                    else {
                        $("#activeStatus").prop('checked', false);
                    }
                }

                else if (elType == "subModule" ) {
                    $("#name").val(data.subModuleName);
                    $("#display-name").val(data.subModuleDispName);
                    $("#hiddedId").val(data.subModuleId);
                    // $("#access-id").css("display", "none");
                    $("#access-id").hide();
                    $("#subModule").prop("checked", true);
                    // $("#module-select-id").css("display", "contents");
                    // $("#subModule-select-id").css("display", "none");
                    $("#module-select-id").show();
                    $("#subModule-select-id").hide();
                    dropDownOfModule(data.moduleId);

                    if (data.subModuleActiveSts == 1) {
                        $("#activeStatus").prop('checked', true);
                    }
                    else {
                        $("#activeStatus").prop('checked', false);
                    }
                }

                else if (elType == "module" ) {
                    $("#name").val(data.moduleName);
                    $("#display-name").val(data.moduleDispName);
                    $("#hiddedId").val(data.moduleId);
                    // $("#access-id").css("display", "none");
                    $("#access-id").hide();
                    $("#module").prop("checked", true);
                    // $("#module-select-id").css("display", "none");
                    // $("#subModule-select-id").css("display", "none");
                    $("#module-select-id").hide();
                    $("#subModule-select-id").hide();

                    if (data.moduleActiveSts == 1) {
                        $("#activeStatus").prop('checked', true);
                    }
                    else {
                        $("#activeStatus").prop('checked', false);
                    }
                }
                getOptionSetupTree();
            });
    }

    function formDelete(deleteData) {
        console.log("Reached Form Delete");
        var elID = $(deleteData).data('id');
        var elType = $(deleteData).data('type');
        $.post('/security/getDeleteData', {
                elementId: elID,
                elementType: elType,
                ajax: true
            },
            function (data) {
                alert(data);
                getOptionSetupTree();
            });
    }

    $('#option-setup').on("submit",function (e) {
        e.preventDefault();
        $(".error").remove();
        var hasError = false;

        var name = $('#name').val();
        var displayName = $('#display-name').val();

        if (name.length < 1) {
            $('#name').after('<span class="error">This field is required</span>');
            hasError = true;
        }
        if (displayName.length < 1) {
            $('#display-name').after('<span class="error">This field is required</span>');
            hasError = true;
        }

        if ($('#subModule').is(':checked')) {
            var selectedModule = $("#module-dropdown :selected").text();
            if (selectedModule == "Select Module") {
                $('#module-dropdown').after('<span class="error">Select a Module from List</span>');
                hasError = true;
            }
        }

        else if ($('#option').is(':checked')) {
            var accessUrl = $('#access-url').val();
            var selectedModule = $("#module-dropdown :selected").text();
            var selectedSubModule = $("#subModule-dropdown :selected").text();
            if (accessUrl.length < 1) {
                $('#access-url').after('<span class="error">This field is required</span>');
                hasError = true;
            }
            if (selectedModule == "Select Module") {
                $('#module-dropdown').after('<span class="error">Select a Module from List</span>');
                hasError = true;
            }
            else if (selectedModule != "Select Module" && selectedSubModule == "Select Sub-module") {
                $("#module-dropdown").remove(".error");
                $('#subModule-dropdown').after('<span class="error">Select a Sub-Module from List</span>');
                hasError = true;
            }
        }

        // if(! $(this).hasClass("error"))
        // $(this).unbind('submit').submit();
        if (!hasError) {
            $(this).unbind('submit').submit();
        }
    });

    $('#refreshButton').click(function() {
        $(".error").remove();
        $("#submitButton").html('Add');
        $("#module").prop("checked", true);
        $("#name").val("");
        $("#display-name").val("");
        $("#access-url").val("");
        $('#module-dropdown').empty();
        $('#subModule-dropdown').empty();
        $("#access-id").hide();
        $("#module-select-id").hide();
        $("#subModule-select-id").hide();
        $("#activeStatus").prop('checked', false);
        $("#hiddedId").val("-1");
        // location.reload();
    });
});