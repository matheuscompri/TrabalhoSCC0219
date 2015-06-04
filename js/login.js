window.onload = function () {

    $("#loginForm").submit(function (event) {

        var valid = true;
        // Getting the username
        var username = $("#username").val();
        var password = $("#password").val();

        if (!is_email(username)) {

            $("#usernameLabel").html("Username <spam class='labelError'>(email is not valid!)</spam>");
            $("#username").addClass("error");
            valid = false;
        } else {
            $("#usernameLabel").html("Username");
            $("#username").removeClass("error");
        }

        if (password.trim() === '') {
            $("#passwordLabel").html("Password <spam class='labelError'>(password field is empty!)</spam>");
            $("#password").addClass("error");
            valid = false;
        } else {
            $("#passwordLabel").html("Password");
            $("#password").removeClass("error");
        }

        if (!valid) {
            event.preventDefault();
        }
    });
};