window.onload = function () {

    var validName = false;
    var validMail = false;
    var validPhone = false;

    $("#contactForm").focusout(function (event) {

        // Getting the name
        var name = $("#contactName");
        var mail = $("#contactMail");
        var phone = $("#contactPhone");

        if (is_name(name.val())) {
            valid(name);
            validName = true;
            
        } else {
            invalid(name);
            validName = false;
        }

        if (is_mail(mail.val())) {
            valid(mail);
            validMail = true;
            
        } else {
            invalid(mail);
            validMail = false;
        }

        if (is_phone(phone.val())) {
            console.log("valid");
            console.log(phone.val());
            var phoneReg = /^([(][0-9]{2}[)][0-9]{5}[-][0-9]{2}[-][0-9]{2})$/;
            validPhone = true;
            if (phoneReg.test(phone.val())) {
                valid(phone);
            }
        } else {
            console.log("invalid");
            invalid(phone);
            validPhone = false;
        }
    });

    $("#contactForm").submit(function (event) {
        //Getting access to the checkboxes status
        var news = $("#newspapers").is(":checked");
        var recc = $("#reccommendation").is(":checked");
        var ntwk = $("#social_network").is(":checked");
        var goog = $("#google_search").is(":checked");
        var magz = $("#magazines").is(":checked");
        var othr = $("#other").is(":checked");

        var msg = $("#leaveMessage");

        if (!(news || recc || ntwk || goog || magz || othr)) {
            if ($("#warningCheck").length == 0) {
                $("#checkOptions").append("<p id ='warningCheck' class='unchecked'>Please choose one of the options above</p>");
                $("#warningCheck").css("color", "yellow");
                $("#warningCheck").css("width", "60%");

            }
            event.preventDefault();
        } else {
            $("#warningCheck").remove();
        }

        if (is_empty(msg.val())) {
            msg.attr("placeholder", "This field cannot be empty");
            msg.css("border", "3px solid yellow");
            event.preventDefault();
        }
        
        if(!(validMail && validName && validPhone)){
            event.preventDefault();
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
    var mailReg = /^[a-z](([a-z0-9.])*[^.])?@[a-z0-9]+([.][a-z]+)+$/;
    return mailReg.test(name);
}

function is_phone(name) {
    var phoneReg = /^(|[(][0-9]{2}[)][0-9]{5}[-][0-9]{2}[-][0-9]{2})$/;
    return phoneReg.test(name);
}

function is_empty(name) {
    if (name.length == 0) {
        return true;
    }
    return false;
}