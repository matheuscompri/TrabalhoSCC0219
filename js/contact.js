window.onload = function () {

    $("#contactForm").focusout(function (event) {

        // Getting the name
        var name = $("#contactName");
        var mail = $("#contactMail");
        var phone = $("#contactPhone");

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

        if (is_phone(phone.val())) {
            console.log("valid");
            console.log(phone.val());
            var phoneReg = /^([(][0-9]{2}[)][0-9]{5}[-][0-9]{2}[-][0-9]{2})$/;
            if (phoneReg.test(phone.val())) {
                valid(phone);
            }
        } else {
            console.log("invalid");
            invalid(phone);
            console.log(phone.val());
        }
    });
    
    $("#contactForm").submit (function (event) {
        //Getting access to the checkboxes status
        var news = $("#newspapers").is(":checked");
        var recc = $("#reccommendation").is(":checked");
        var ntwk = $("#social_network").is(":checked");
        var goog = $("#google_search").is(":checked");
        var magz = $("#magazines").is(":checked");
        var othr = $("#other").is(":checked");
      
        if(!(news||recc||ntwk||goog||magz||othr)){
            if($("#warningCheck").length == 0){
                $("#checkOptions").append("<p id ='warningCheck' class='unchecked'>Please choose one of the options above</p>");
           }
            event.preventDefault();
        }
        else{
            $("#warningCheck").remove();
            alert("OK");
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