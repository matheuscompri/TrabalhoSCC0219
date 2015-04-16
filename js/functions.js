$(document).ready( function(){
    var user = $("#user").val;
	var nome = $("#nome").val;
	var nascimento = $("#nascimento").val;
	var email = $("#email").val;
	var aceitar = $("#aceitar").val;
    
    var regexMail = /^(([a-z]|[0-9])+@([a-z]|[0-9])+.com)$/;
    
    if(!regexMail.test($("#user").val())){
			console.log("Campo email valido");
			$("#user").css("background-image", "url(../images/ic_check_un.png)");
            $("#user").css("background-position", "right top");
            $("#user").css("background-repeat", "no-repeat");
            $("#user").css("background-size", "contain");
    }
    else{
        console.log("Campo email invalido");
			$("#user").css("border", "2px solid #FF9B77");
    }

	/**$("#button").click(function(){
		
		if($("#nome").val() == ""){
			console.log("Campo nome invalido");
			$("#nome").css("border", "2px solid #BC243C" );
		}else{
			$("#nome").css("border", "2px solid #009B77" );
		}

		if($("#nascimento").val() == ""){
			console.log("Campo nascimento invalido");
			$("#nascimento").css("border", "2px solid #BC243C" );
		}

		var regexDate = /^([0][1-9])|([1-2][0-9])|([3][0-1])\/(([0][1-9])|([1][0-2]))\/((19|20)[0-9]{2})$/;
		var regexMail = /^(([a-z]|[0-9])+@([a-z]|[0-9])+.com)$/;
		if(regexDate.test($("#nascimento").val())){
			var d = new Date();
			var month = parseInt(d.getMonth()+1);
			var day = parseInt(d.getDate());
			var year = parseInt(d.getFullYear());

			var values = $("#nascimento").val().split("/");	
			var dayInput = parseInt(values[0]);
			var monthInput = parseInt(values[1]);
			var yearInput = parseInt(values[2]);
			
			//validating date Input
			if(yearInput <= year-18 && yearInput >= year-120 ){
				console.log("Valid Year "+ yearInput);
				if(yearInput==year-18 || yearInput==year-120){
					console.log("Same Year "+ yearInput);
					if(yearInput==year-18 && monthInput <= month){
						console.log("Valid Month "+ monthInput);
						if(monthInput==month){
							console.log("Same Month "+ monthInput);
							if(dayInput<=day){
								console.log("Valid Day "+ dayInput);
								$("#nascimento").css("border", "2px solid #009B77");
							}
							else{
								console.log("Invalid Day "+ dayInput);
								$("#nascimento").css("border", "2px solid #BC243C");	
							} 
						}
					}
					else{
							console.log("Invalid Month "+ monthInput);
							$("#nascimento").css("border", "2px solid #BC243C");	
					}
					//older than 120 years old
					if(yearInput==year-120 && monthInput >= month){
						console.log("Valid Month "+ monthInput);
						if(monthInput==month){
							console.log("Same Month "+ monthInput);
							if(dayInput>=day){
								console.log("Valid Day "+ dayInput);
								$("#nascimento").css("border", "2px solid #009B77");
							}
							else{
								console.log("Invalid Day "+ dayInput);
								$("#nascimento").css("border", "2px solid #BC243C");	
							} 
						}
					}
					else if(yearInput==year-120){
							console.log("Invalid Month "+ monthInput);
							$("#nascimento").css("border", "2px solid #BC243C");	
					} 
				}
				else{
					console.log("Valid Year "+ yearInput);
					$("#nascimento").css("border", "2px solid #009B77");
				}
			}
			else{
				console.log("Invalid Year "+ yearInput);
				$("#nascimento").css("border", "2px solid #BC243C");	
			} 
			//console.log(dayInput + "/"+monthInput+"/"+yearInput);
		}

		if($("#email").val() == ""){
			console.log("Campo email invalido");
			$("#email").css("border", "2px solid #BC243C" );
		}
		if(regexMail.test($("#email").val())){
			console.log("Campo email valido");
			$("#email").css("border", "2px solid #009B77");
		}
		else{
			console.log("invalid email "+$("#email").val());
			$("#email").css("border", "2px solid #BC243C");

		}

		if($("#aceitar").is(":checked") == false){
			if($(".error").length ==0){
				$("#aceitar").after("<p class='error'>Os Termos de Uso devem ser aceitos</p>");
				$(".error").css("background-color","#C99BB4");	
			}
			
		}
		else{
			$(".error").remove();
		}

	});
*/
});   
