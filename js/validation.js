// Function that checks if the email is valid
function is_email(email) {
    //RegExp to validate e-mail
    var mailReg = /^[a-z](([a-z0-9.])*[^.])?@[a-z0-9]+([.][a-z]+)+$/;
    return mailReg.test(email);
}

function is_phone(phone) {
    var phoneReg = /^(|[(][0-9]{2}[)][0-9]{5}[-][0-9]{2}[-][0-9]{2})$/;
    return phoneReg.test(phone);
}

// Function that captalize the first letters of the name
function fixName(name) {
    // Spliting the string using the empty spaces
    var array = name.split(" ");
    // New name
    var newName = "";

    // for each word besides the last one
    for (var i = 0; i < array.length - 1; i++) {
        // Captalizing the first letter and adding a white space in the end
        newName += array[i][0].toUpperCase() + array[i].substr(1, array[i].length - 1) + " ";
    }
    // The last word doesn't have the empty space in the end
    newName += array[array.length - 1][0].toUpperCase() + array[i].substr(1, array[i].length - 1);

    // Returning the new name
    return newName;
}

// Function that checks if a cep matches which the right state
function is_cep(cep, select) {

    var state;

    var cepReg = /^[0-9]{8}$/;
    var stateReg = /^[A-Z]{2}$/;

    // Checking if the state and cep regex are valid for the fields
    if (cepReg.test(cep) && stateReg.test(select)) {

        // The first 3 numbers define for which state the cep is
        var cep_substring = cep.substr(0, 3);

        // Checking for which state is the cep
        if ((10 <= cep_substring) && (cep_substring <= 199)) {
            state = "SP";
        } else if ((200 <= cep_substring) && (cep_substring <= 289)) {
            state = "RJ";
        } else if ((290 <= cep_substring) && (cep_substring <= 299)) {
            state = "ES";
        } else if ((300 <= cep_substring) && (cep_substring <= 399)) {
            state = "MG";
        } else if ((400 <= cep_substring) && (cep_substring <= 489)) {
            state = "BA";
        } else if ((490 <= cep_substring) && (cep_substring <= 499)) {
            state = "SE";
        } else if ((500 <= cep_substring) && (cep_substring <= 569)) {
            state = "PE";
        } else if ((570 <= cep_substring) && (cep_substring <= 579)) {
            state = "AL";
        } else if ((580 <= cep_substring) && (cep_substring <= 589)) {
            state = "PB";
        } else if ((590 <= cep_substring) && (cep_substring <= 599)) {
            state = "RN";
        } else if ((600 <= cep_substring) && (cep_substring <= 639)) {
            state = "CE";
        } else if ((640 <= cep_substring) && (cep_substring <= 649)) {
            state = "PI";
        } else if ((650 <= cep_substring) && (cep_substring <= 659)) {
            state = "MA";
        } else if ((660 <= cep_substring) && (cep_substring <= 688)) {
            state = "PA";
        } else if ((690 <= cep_substring) && (cep_substring <= 692)) {
            state = "AM";
        } else if ((694 <= cep_substring) && (cep_substring <= 698)) {
            state = "AM";
        } else if (689 === cep_substring) {
            state = "AP";
        } else if (693 === cep_substring) {
            state = "AR";
        } else if (699 === cep_substring) {
            state = "AC";
        } else if ((700 <= cep_substring) && (cep_substring <= 736)) {
            state = "DF";
        } else if ((728 <= cep_substring) && (cep_substring <= 767)) {
            state = "GO";
        } else if ((770 <= cep_substring) && (cep_substring <= 779)) {
            state = "TO";
        } else if ((780 <= cep_substring) && (cep_substring <= 788)) {
            state = "MT";
        } else if (789 == cep_substring) {
            state = "RO";
        } else if ((800 <= cep_substring) && (cep_substring <= 879)) {
            state = "PR";
        } else if ((880 <= cep_substring) && (cep_substring <= 889)) {
            state = "SC";
        } else if ((900 <= cep_substring) && (cep_substring <= 999)) {
            state = "RS";
        }

        // checking if the given state matches which the expected one
        if (state === select)
            return true;
        else
            return false;
    } else
    // cep regex or state is not valid
        return false;
}

// Function that checks if a name is valid
function is_name(name) {
    // Regex for names with more than 3 letters and at least 1 surname
    var nameReg = /^[a-zA-Z][a-zA-Z]{2}[a-zA-Z]*(\s[a-zA-Z][a-zA-Z]{2}[a-zA-Z]*)+$/;
    return nameReg.test(name);
}


// Function that checks if a birh date is valid
function is_date(date) {

    var birthdate;
    // Current time
    var now = new Date();

    var valid = false;

    // Regular expression for date
    var dateReg = /^(([0][1-9])|([1-2][0-9])|([3][0-1]))\/(([0][1-9])|([1][0-2]))\/((19|[2-9][0-9])[0-9]{2})$/;

    
    // Testing if the regex is valid for the string
    if (dateReg.test(date)) {
        valid = true;
        birthdate = new Date(date.substr(6, 4), date.substr(3, 2) - 1, date.substr(0, 2));
    }

    // checking if is valid and if year after 1900
    if (valid && birthdate.getFullYear() >= 1900) {
        // Comparing the dates
        return compareDate(birthdate, now);
    }
    return false;
}

// Compare date
function compareDate(date, now) {
    var valid = false;

    // Convert date to a number 
    var dateComp = date.getMonth().toString() + date.getDate().toString();
    var nowComp = now.getMonth().toString() + now.getDate().toString();

    // Checking if the year is greater of equal than current one
    if (date.getFullYear() >= now.getFullYear()) {
        // If the year is the same but the day and month are smaller than the current one
        if (date.getFullYear() === now.getFullYear() && parseInt(dateComp) <= parseInt(nowComp)) {
            valid = true;
        }
    }
    // Else the year is smaller than the current one
    else {
        valid = true;
    }

    return valid;
}

// This function checks if a reservation date is valid
function arrivalDate(date) {

    var arrival, now = new Date();

    var valid = false;

    // Regular expression for date
    var dateReg = /^(([0][1-9])|([1-2][0-9])|([3][0-1]))\/(([0][1-9])|([1][0-2]))\/((19|[2-9][0-9])[0-9]{2})$/;

    // Testing if the regex is valid for the string
    if (dateReg.test(date)) {
        valid = true;
        arrival = new Date(date.substr(6, 4), date.substr(3, 2) - 1, date.substr(0, 2));
    }
    if (valid) {
        now.setDate(now.getDate() + 2);
        if (now <= arrival) {
            return true;
        } else {
            return false;
        }
    }
}

// This function checks if a reservation date is valid
function departureDate(date, date2) {

    var arrival, departure;

    var valid = false;

    // Regular expression for date
     var dateReg = /^(([0][1-9])|([1-2][0-9])|([3][0-1]))\/(([0][1-9])|([1][0-2]))\/((19|[2-9][0-9])[0-9]{2})$/;

    // Testing if the regex is valid for the string
    if (dateReg.test(date) && dateReg.test(date2)) {
        valid = true;
        arrival = new Date(date.substr(6, 4), date.substr(3, 2) - 1, date.substr(0, 2));
        departure = new Date(date2.substr(6, 4), date2.substr(3, 2) - 1, date2.substr(0, 2));
    }

    if (valid) {
        arrival.setDate(arrival.getDate() + 2);
        if (arrival <= departure) {
            return true;
        } else {
            return false;
        }
    }
}



// Function to check if a cpf is valid
// This function recalculates the last 2 digits of the cpf
// If they match which the given ones, then the cpf is valid
function is_cpf(cpf) {
    var string = cpf;
    var sum = 0;
    var dig = 0;

    // Getting the first 9 numbers
    var new_cpf = cpf.substr(0, 9);

    // Checking if the size is valid
    if (cpf.length === 11) {
        for (var i = 0; i < 9; i++) {
            sum += cpf[i] * (10 - i);
        }
        dig = sum % 11;
        if (dig < 2) {
            dig = 0;
        } else {
            dig = 11 - dig;
        }
        new_cpf += dig;

        sum = 0;
        for (var i = 0; i < 10; i++) {
            sum += cpf[i] * (11 - i);
        }
        dig = sum % 11;
        if (dig < 2) {
            dig = 0;
        } else {
            dig = 11 - dig;
        }
        new_cpf = new_cpf + dig;

        console.log(cpf + " " + new_cpf);
        if (cpf === new_cpf) {
            return true;
        }
    }
}

// Function to verify the strengh of the password
function is_password(password) {
    var specialChars = /[\!\@\#\$\%\*?\,\;\.]/;
    var specCharList = [];
    var specCharCount = 0;

    var capitalLetters = /[A-Z]/;
    var capitalCount = 0;

    var numbers = /[0-9]/;
    var numberCount = 0;

    // Checking if the size is valid
    if (password.length < 6 || password.length > 12)
        return ("invalid");
    // Weak passwords have only 6 chars
    if (password.length === 6)
        return ("weak");

    // Checking all the chars of the password
    for (var i = 0; i <= password.length; i++) {
        if (specialChars.test(password[i])) {
            // The password has a special character
            specCharCount++;
            specCharList.push(password[i]);
        } else if (capitalLetters.test(password[i])) {
            // The password has a capital letter
            capitalCount++;
        } else if (numbers.test(password[i])) {
            // The password has a number
            numberCount++;
        }
    }

    // Verifying if the special chars are unique
    for (var i = 0; i < specCharList.length; i++) {
        for (var j = i + 1; j < specCharList.length - 1; j++) {
            if (specCharList[i] === specCharList[j]) {
                // the special char i is not unique
                specCharCount--;
            }
        }
    }

    // Verifying if password is not weak
    if (specCharCount && numberCount && capitalCount) {
        // More than 2 special chars results in a strong password
        if (specCharCount >= 2) {
            return ("strong");
        } else {
            return ("medium");
        }
    } else {
        return ("weak");
    }
}

function saveData(nome, dado) {
    localStorage.setItem(nome, dado);
}