function check (){
	let password = document.getElementById("psw").value
	let confirmPassword = document.getElementById("confirm-psw").value
	let n = document.getElementById("name").value
	let e1 = document.getElementById("e").value
	if(!password.match("[A-Za-z0-9]{8,}")){
		alert("Password should be 8 characters!")
		return false
	}
	if(password==confirmPassword){
		console.log("Password comfirned!")
		return true
	}
	else{
		alert("Password wrong!")
		return false
	}
	
	if(!n.match("[A-Za-z]")){
		console.log("name is wrong!")
		return false
	}
	
	
}