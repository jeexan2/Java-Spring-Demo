var remove_alert = function(){
    $(".flash-alert").delay(5500).slideUp(500, function () {
        $(this).alert('close');
    });
};
var confirmBlueDialog = function (text) {
    $.confirm({
        title: 'Confirm!',
        content: text,
        type: 'blue',
        typeAnimated: true,
        buttons: {
            confirm: {
                btnClass: 'btn-info',
                keys: ['enter'],
                action: function () {
                    $("form").submit();
                }
            },
            cancel: function () {

            }
        }
    });
};

var confirmDialog = function (text) {
    $.confirm({
        title: 'Confirm!',
        content: text,
        buttons: {
            confirm: {
                btnClass: 'btn-info',
                keys: ['enter'],
                action: function () {
                    $("form").submit();
                }
            },
            cancel: function () {

            }
        }
    });
};

// confirmDialog("Sure to proceed?");