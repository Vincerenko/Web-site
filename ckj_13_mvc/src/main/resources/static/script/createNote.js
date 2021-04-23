async function note() {
	let url = "http://localhost:8090/api/notes"
	let headers = { "Content-Type" : "application/json;charset=utf-8" }
	let title = document.getElementById("t1").value
	let message = document.getElementById("m1").value
	let note = {
		title: title,
		message: message
	}
	let noteJSON = JSON.stringify(note)
	let reponse = await fetch(url, {
		method: "POST",
		headers: headers,
		body: noteJSON
	}
	)
	
}