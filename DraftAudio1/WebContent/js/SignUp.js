function User (nickName, email, name, surname, password){
	this.nickName = nickName;
	this.email = email;
	this.name = name;
	this.surname = surname;
	this.password = password;
}



function register(){
	var nickname = $("#registerInputNickname").val();
	var email = $("#registerInputEmail2").val();
	var name = $("#registerInputName").val();
	var surname = $("#registerInputSurname").val();
	var password = $("#resgisterInputPwd2").val();
	var user = new User(nickname, email, name, surname, password);
	var jsonString ={
			nickName : user.nickName,
			email : user.email,
			name : user.name,
			surname : user.surname,
			password : user.password
	};
	
var result = false;	
$.ajax({
	async : false,
	type : "POST",
	url : "SignUp",
	datatype : "json",
	data : {
		addUser : JSON.stringify(jsonString)
	},
	success : function(data){
		
		if(data == 1){
			alert("Credenziali Errate");
			return false
		}
		else
			result = true;
	}
});
return Boolean(result);
}