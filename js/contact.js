window.onload = function () {

    $("#contactForm").submit(function (event) {

        var valid = true;

        //Getting access to the checkboxes status
        var news = $("#newspapers").is(":checked");
        var recc = $("#reccommendation").is(":checked");
        var ntwk = $("#social_network").is(":checked");
        var goog = $("#google_search").is(":checked");
        var magz = $("#magazines").is(":checked");
        var othr = $("#other").is(":checked");

        var message = $("#leaveMessage").val();
        var name = $("#name").val();
        var email = $("#email").val();
        var phone = $("#phone").val();

        if (!(news || recc || ntwk || goog || magz || othr)) {
            $("#checkboxLabel").html("How did you hear about us? <spam class='labelError'>(at least one should be marked!)</spam>");
            valid = false;
        } else {
            $("#checkboxLabel").html("How did you hear about us?");
        }

        if (message.trim() === '') {
            $("#leaveMessageLabel").html("Leave your message: <spam class='labelError'>(this field cannot be empty!)</spam>");
            valid = false;
        } else {
            $("#leaveMessageLabel").html("Leave your message: ");
        }

        if (!is_name(name)) {
            $("#nameLabel").html("Name: <spam class='labelError'>the name and surname must have more than 3 letters!</spam>");
            $("#name").addClass("error");
            valid = false;
        } else {
            $("#nameLabel").html("Name: ");
            $("#name").removeClass("error");
        }
        
        if (!is_email(email)) {
            $("#emailLabel").html("E-mail: <spam class='labelError'>the E-mail is not valid!</spam>");
            $("#email").addClass("error");
            valid = false;
        } else {
            $("#emailLabel").html("E-mail: ");
            $("#email").removeClass("error");
        }
        
        if (!is_phone(phone)) {
            $("#phoneLabel").html("Mobile phone: <spam class='labelError'>the phone number is not valid!</spam>");
            $("#phone").addClass("error");
            valid = false;
        } else {
            $("#phoneLabel").html("Mobile Phone: ");
            $("#phone").removeClass("error");
        }
        
        if (!valid) {
            event.preventDefault();
        }
    });
}