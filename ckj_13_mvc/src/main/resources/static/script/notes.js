async function note2() {
	let url = "http://localhost:8090/api/notes"
	let note = notes
	let noteJSON = JSON.stringify(note)
	let reponse = await fetch(url, {
		method: "POST",
		headers: headers,
		body: noteJSON
	}
	)
	
}
note2()
async function fillNotes() {
	let url = "/api/notes"
	let response = await fetch(url)
	let notes = await response.json()
	let root = document.getElementById("notes")
	notes.forEach(n => {
		let elem = document.createElement("div")
		let title = document.createElement("div")
		title.innerText = n.title
		let message = document.createElement("div")
		message.innerText = n.message
		let infoRef = document.createElement("a")
		infoRef.href  = n.links[0].href
		infoRef.innerText = "Info"
		elem.appendChild(title)
		elem.appendChild(message)
		elem.appendChild(infoRef)
		elem.classList.add("item")
		root.appendChild(elem)
	})
}

fillNotes()


