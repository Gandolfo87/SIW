function logIn(){
	var email = $("#exampleInputEmail2").val();
	var password = $("#exampleInputPwdl2").val();
	var jsonString ={
			email : email,
			password : password
	};
	
var result = false;	
$.ajax({
	async : false,
	type : "POST",
	url : "login",
	datatype : "json",
	data : {
		logUser : JSON.stringify(jsonString)
	},
	success : function(data){
		
		if(data == 1){
			alert("Utente non registrato");
			result = false;
		}
		else{
			result = true;
		}
	}
});
return Boolean(result);
}