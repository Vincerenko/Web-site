async function check (){
	let response  = await fetch("/api/users")
	let users
	if(response.ok){
		users = await response.json()
	} else{
		console.log(response.status)
	}
}
	
	
	
	
	let password = document.getElementById("psw").value
	let confirmPassword = document.getElementById("confirm-psw").value
	
	if(!password.match("[A-Za-z0-9]{8,}")){
		alert("Password should be 8 characters!")
		return false
	}
	if(password==confirmPassword){
		console.log("Password confirmed!")
		return true
	}
	else{
		alert("Password wrong!")
		return false
	}
	
	
	function submitForm(){
		let form = document.getElementById("form1")
		
	}
	
	
	
	

