async function checkForm() {
	let response = await fetch("/api/users")
	let users
	if (response.ok) {
		users = await response.json()
	} else {
		console.log(response.status)
	}


let username = document.getElementById("name").value
let email = document.getElementById("e").value
for (user of users) {
	if (user.username == username) {
		alert("Such username exists!")
		return false
	}
	if (user.email == email) {
		alert("Such email exists!")
		return false
	}
}

let password = document.getElementById("psw").value
let confirmPassword = document.getElementById("confirm-psw").value
if (!password.match("[A-Za-z0-9]{8,}")) {
	alert("Password should contain at least 8 letters or digits!")
	return false
}
if (password != confirmPassword) {
	alert("Password is not confirmed!")
	return false
}
return true
}

async function submitForm() {
	let form = document.getElementById("signup_form")

	if (await checkForm()) {
		form.submit()

	}
}





