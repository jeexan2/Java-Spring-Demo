$(document).ready(function () {
    function readURL(input, target) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $(target).attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#emp_photo_id").change(function () {
        readURL(this, "#emp_photo_prev_id");
        $("#emp_photo_prev_id").show();
    });

    $("#emp_sig_id").change(function () {
        readURL(this, "#emp_sig_prev_id");
        $("#emp_sig_prev_id").show();
    });
    var changeOnBank = function (id) {
        var bankId = $("#emp_bank_name_id").val();
        $.post("/hrm_admin/get_branch_details", {
                bankId: bankId
            },
            function (data, status) {
                var branches = $('#emp_branch_name_id');
                branches.empty();
                $.each(data, function (index, branch) {
                    branches.append($('<option/>', {
                        value: branch.lookupId,
                        text: branch.name
                    }));
                });
                if (data.length != 0) {
                    if (typeof id !== "undefined") {
                        $('#emp_branch_name_id').val(id);
                    }
                }
            });
    }

    $(document).on("change", "#emp_bank_name_id", function () {
        changeOnBank();
    });

    changeOnBank();

    // var setBranchOnEd = function () {
    //     var branchId = $("#edit_branch_id").val();
    //     changeOnBank(branchId);
    // }

});