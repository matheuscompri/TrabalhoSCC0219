window.onload = function () {

    $("#loginForm").focusout(function (event) {

        // Getting the username
        var username = $("#username").val();

        if (is_mail(username)) {

            console.log("Campo email valido");
            //toggle the input class checked
            var us = $("#username").removeClass("unchecked");            
            var us = $("#username").toggleClass("checked");
        }
        else {
            //toggle the input class unchecked
            var us = $("#username").removeClass("checked");                                 var us = $("#username").toggleClass("unchecked");
        }
    });
};

function is_mail(username) {
    //RegExp to validate e-mail
    var mailReg = /^([a-z]([a-z0-9.])+[a-z0-9]@([a-z0-9])+([.][a-z]+)*)$/;
    return mailReg.test(username);
}
