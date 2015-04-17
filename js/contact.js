window.onload = function () {

    $("#contactForm").focusout(function (event) {

        // Getting the name
        var name = $("#contactName");
        var mail = $("#contactMail");
        console.log(name);
        console.log(mail);
        if (is_name(name.val())) {
            valid(name);
        } else {
            invalid(name);
        }
        
        if (is_mail(mail.val())) {
            valid(mail);
        } else {
            invalid(mail);
        }
    });
};

function valid(name) {
    console.log("Campo " + name.val() + " valido");
    //toggle the input class checked
    name.removeClass("unchecked");
    name.addClass("checked");
}

function invalid(name) {
    //toggle the input class unchecked
    name.removeClass("checked");
    name.addClass("unchecked");
}


function is_name(name) {
    var nameReg = /^[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*\s[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*$/;
    return nameReg.test(name);
}

function is_mail(name) {
    //RegExp to validate e-mail
    //var mailReg = /^(([a-z])*(([a-z0-9.])+)*[a-z0-9]@([a-z0-9])+([.][a-z]+)*)$/;
    var mailReg = /^((([a-z][.a-z0-9]*[^.])|[a-z])@[a-z0-9]+([\.][a-z]+)*)$/;
    return mailReg.test(name);
}