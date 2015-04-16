window.onload = function () {

    $("#registerForm").submit(function (event) {

        var valid = true;
        
        // Getting the name
        var name = $("#name").val();

        if (!is_name(name)) {
            console.log(name + " " + is_name(name));
            valid = false;
        }

        if (!valid) {
            event.preventDefault();
        }
    });
};

function is_name(name) {
    var nameReg = /^[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*\s[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*$/;
    return nameReg.test(name);
}

function is_name(name) {
    var nameReg = /^[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*\s[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*$/;
    return nameReg.test(name);
}