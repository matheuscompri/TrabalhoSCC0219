window.onload = function () {

    // Calculating the password strength
    $("#password").keyup(function () {
        var password = $("#password").val();
        if (is_password(password) === 'invalid') {
            $("#passwordLabel").html("Password: <spam class='labelError'>the password is invalid!</spam>");
            $("#password").removeClass();
            $("#password").addClass("error");
        } else if (is_password(password) === 'weak') {
            $("#passwordLabel").html("Password: <spam class='labelWeak'>the password is weak!</spam>");
            $("#password").removeClass();
        } else if (is_password(password) === 'medium') {
            $("#passwordLabel").html("Password: <spam class='labelMedium'>the password is medium!</spam>");
            $("#password").removeClass();
        } else if (is_password(password) === 'strong') {
            $("#passwordLabel").html("Password: <spam class='labelStrong'>the password is strong!</spam>");
            $("#password").removeClass();
        }
    });

    // Checking if the form is valid when the user clicks on submit
    $("#registerForm").submit(function (event) {
        var valid = true;

        // Getting the fields
        var name = $("#name").val();
        var cpf = $("#cpf").val();
        var birth = $("#birth").val();
        var password = $("#password").val();
        var password_confirmation = $("#password_confirmation").val();
        var cep = $("#cep").val();
        var email = $("#email").val();
        var select = $("#state").val();
        var city = $("#city").val();
        var gender = $("#gender").val();
        var mstatus = $("#mstatus").val();

        // Verifying if the fields are valid
        if (!is_name(name)) {
            $("#nameLabel").html("Name: <spam class='labelError'>the name and surname must have more than 3 letters!</spam>");
            $("#name").addClass("error");
            valid = false;
        } else {
            $("#nameLabel").html("Name: ");
            $("#name").removeClass("error");
            saveData("nome", name);
        }

        if (city.trim() === '') {
            $("#cityLabel").html("City: <spam class='labelError'>the city is required!</spam>");
            $("#city").addClass("error");
            valid = false;
        } else {
            $("#cityLabel").html("City: ");
            $("#city").removeClass("error");
        }

        if (!is_cpf(cpf)) {
            $("#cpfLabel").html("CPF: <spam class='labelError'>the CPF is not valid!</spam>");
            $("#cpf").addClass("error");
            valid = false;
        } else {
            $("#cpfLabel").html("CPF: ");
            $("#cpf").removeClass("error");
        }

        if (!is_date(birth)) {
            $("#birthLabel").html("Date of Birth: <spam class='labelError'>the date is not valid!</spam>");
            $("#birth").addClass("error");
            valid = false;
        } else {
            $("#birthLabel").html("Date of Birth: ");
            $("#birth").removeClass("error");
        }

        if (!is_cep(cep, select)) {
            $("#cepLabel").html("Postal Code: <spam class='labelError'>the Postal Code is not valid!</spam>");
            $("#cep").addClass("error");
            valid = false;
        } else {
            $("#cepLabel").html("Postal Code: ");
            $("#cep").removeClass("error");
        }

        if (is_password(password) === 'invalid') {
            $("#passwordLabel").html("Password: <spam class='labelError'>the password is invalid!</spam>");
            $("#password").addClass("error");
            valid = false;
        } else {
            $("#passwordLabel").html("Password: ");
            $("#password").removeClass("error");
        }

        if (password !== password_confirmation || password_confirmation.trim() === '') {
            $("#password_confirmationLabel").html("Confirm Password: <spam class='labelError'>the passwords does not match!</spam>");
            $("#password_confirmation").addClass("error");
            valid = false;
        } else {
            $("#password_confirmationLabel").html("Confirm password: ");
            $("#password_confirmation").removeClass("error");
        }

        if (!is_email(email)) {
            $("#emailLabel").html("E-mail: <spam class='labelError'>the E-mail is not valid!</spam>");
            $("#email").addClass("error");
            valid = false;
        } else {
            $("#emailLabel").html("E-mail: ");
            $("#email").removeClass("error");
        }

        // if they are not valid, prevent the form submission
        if (!valid) {
            event.preventDefault();
        } else {
            var register = {
                "name": fixName(name),
                "cpf": cpf,
                "birhdate": birth,
                "gender": gender,
                "mstatus": mstatus,
                "city": city,
                "state": select,
                "cep": cep,
                "email": email,
                "password": password
            };
            saveData("register", JSON.stringify(register));
        }
    });
}