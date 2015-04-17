window.onload = function () {
    $("#registerForm").submit(function (event) {
        var valid = true;

        // Getting the name
        var name = $("#name").val();
        var cpf = $("#cpf").val();
        var birth = $("#birth").val();

        if (!is_name(name)) {
            console.log(name + " " + is_name(name));
            valid = false;
        }

        if (!is_cpf(cpf)) {
            console.log(cpf + " " + is_cpf(name));
            valid = false;
        }
        
        if (!is_date(birth)) {
            console.log(birth + " " + is_date(birth));
            valid = false;
        }

        if (!valid) {
            event.preventDefault();
        }
    });
}

function is_cep(cep)
{
    var cep_substring = cep.substr(0,3);
    var state;
    
    var select = $("#state").val();
    
    if((10 <= cep_substring) && (cep_substring <= 199))
    {
        state = "SP";
    }
    else if((200 <= cep_substring) && (cep_substring <= 289))
    {
        state = "RJ";
    }
    else if((290 <= cep_substring) && (cep_substring <= 299))
    {
        state = "ES";
    }
    else if((300 <= cep_substring) && (cep_substring <= 399))
    {
        state = "MG";
    }
    else if((400 <= cep_substring) && (cep_substring <= 489))
    {
        state = "BA";
    }
    else if((490 <= cep_substring) && (cep_substring <= 499))
    {
        state = "SE";
    }
    else if((500 <= cep_substring) && (cep_substring <= 569))
    {
        state = "PE";
    }
    else if((570 <= cep_substring) && (cep_substring <= 579))
    {
        state = "AL";
    }
    else if((580 <= cep_substring) && (cep_substring <= 589))
    {
        state = "PB";
    }
    else if((590 <= cep_substring) && (cep_substring <= 599))
    {
        state = "RN";
    }
    else if((600 <= cep_substring) && (cep_substring <= 639))
    {
        state = "CE";
    }
    else if((640 <= cep_substring) && (cep_substring <= 649))
    {
        state = "PI";
    }
    else if((650 <= cep_substring) && (cep_substring <= 659))
    {
        state = "MA";
    }
    else if((660 <= cep_substring) && (cep_substring <= 688))
    {
        state = "PA";
    }
    else if((690 <= cep_substring) && (cep_substring <= 692))
    {
        state = "AM";
    }
    else if((694 <= cep_substring) && (cep_substring <= 698))
    {
        state = "AM";
    }
    else if(689 === cep_substring)
    {
        state = "AP";
    }
    else if(693 === cep_substring)
    {
        state = "AR";
    }
    else if(699 === cep_substring)
    {
        state = "AC";
    }
    else if((700 <= cep_substring) && (cep_substring <= 736))
    {
        state = "DF";
    }
    else if((728 <= cep_substring) && (cep_substring <= 767))
    {
        state = "GO";
    }
    else if((770 <= cep_substring) && (cep_substring <= 779))
    {
        state = "TO";
    }
    else if((780 <= cep_substring) && (cep_substring <= 788))
    {
        state = "MT";
    }
    else if(789 == cep_substring)
    {
        state = "RO";
    }
    else if((800 <= cep_substring) && (cep_substring <= 879))
    {
        state = "PR";
    }
    else if((880 <= cep_substring) && (cep_substring <= 889))
    {
        state = "SC";
    }
    else if((900 <= cep_substring) && (cep_substring <= 999))
    {
        state = "RS";
    }
    
    if(state === select)
        return true;
    else
        return false;
}

function is_name(name) {
    var nameReg = /^[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*(\s[a-zA-Z][a-zA-Z0-9]{2}[a-zA-Z0-9]*)+$/;
    return nameReg.test(name);
}

function is_date(date) {

    var birthdate;
    var now = new Date();

    var valid = false;

    // Regular expression for date
    var dateReg = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
    
    // Testing if the regex is valid for the string
    if (dateReg.test(date)) {
        valid = true;
        birthdate = new Date(date.substr(6,4), date.substr(3,2) - 1, date.substr(0,2));
    }

    // year after 1900 and before current year
    if (valid && birthdate.getFullYear() >= 1900 && birthdate.getFullYear() <= now.getFullYear()) {
        // if the year is the current one
        if (now.getFullYear() === birthdate.getFullYear()) {
            if (now.getMonth() >= birthdate.getMonth()) {
                if (now.getDay() >= birthdate.getDay()) {
                    return true;
                }
            }
        } else {
            return true;
        }
    }
    return false;
}

function is_cpf(cpf) {
    // Converting to string to simplify access to numbers
    var string = cpf;
    var sum = 0;
    var dig = 0;

    var new_cpf = cpf.substr(0, 9);

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

function is_password(password)
{
    var specialChars = /[\!\@\#\$\%\*?\,\;\.]/;
    var specCharList = [];
    var specCharCount = 0;
    
    var capitalLetters = /[A-Z]/;
    var capitalCount = 0;
    
    var numbers = /[0-9]/;
    var numberCount = 0;
    
    if(password.length < 6 || password.length > 12)
        return ("invalid");
    if(password.length === 6)
        return ("weak");
        
    for(var i = 0; i <= password.length; i++)
    {
        if(specialChars.test(password[i]))
        {
            specCharCount++;
            specCharList.push(password[i]);
        }
        else if(capitalLetters.test(password[i]))
        {
            capitalCount++;
        }
        else if(numbers.test(password[i]))
        {
            numberCount++;
        }
    }
    
    // Verifying if the special chars are unique
    for(var i = 0; i < specCharList.length; i++)
    {
        for(var j = i + 1; j < specCharList.length -1; j++)
        {
            if(specCharList[i] === specCharList[j])
            {
                // the special char i is not unique
                specCharCount--;
            }
        }
    }
    
    // Verifying if password is not weak
    if(specCharCount && numberCount && capitalCount)
    {
         if(specCharCount >= 2)
         {
             return ("strong");
         }
        else{
            return ("medium");
        }
    }
    else
    {
        return("weak");
    }
}