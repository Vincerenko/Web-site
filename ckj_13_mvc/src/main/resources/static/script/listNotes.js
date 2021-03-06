/*async function ListNote() {
	let response = await fetch("/api/notes")
	let notes
	if (response.ok) {
		notes = await response.json()
	} else {
		console.log(response.status)
	}
	let el4 = document.createElement("div")
	let el4 = document.getElementById("el1")
	for (index = 0; index < notes.length; ++index) {
		el4.append("Title : " + notes[index].title + " " + "Message : " + notes[index].message);
	}
}*/
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
		infoRef.href = n.links[0].href
		infoRef.innerText = "Info"
		
		/*let deleteRef=document.createDocument("a")
		deleteRef.href=n.links[0].href
		deleteRef.innerText="Delete"
		deleteRef.onClick=deleteNote*/
		
		elem.appendChild(title)
		elem.appendChild(message)
		elem.appendChild(infoRef)
		//elem.appendChild(deleteRef)
		elem.classList.add("item")
		root.appendChild(elem)
	})
}
fillNotes()

async function deleteNote(event){
	if(confirm("Delete note?")){
	let url = event.target.href
	let response = await fetch(url, {
		method: "DELETE"
		
	})
	}
}

async function saveNote() {
	let title = document.getElementById("title").value
	let message = document.getElementById("message").value
	let note = { title: title, message: message }
	//отправка пост запроса на сохранение этой заметки
	let url = "/api/notes"
	let response = await fetch(url, {
		method: "POST",
		headers: {
			'Content-Type': 'application/json;charset=utf-8'
		},
		body: JSON.stringify(note)
	})
	let result = await response.json()
	return result
}

async function find() {
	let response = await fetch("/api/notes")
	let note
	if (response.ok) {
		note = await response.json()
	} else {
		console.log(response.status)
	}
	let found = document.getElementById("search").value
	let elem = document.createElement("div")
	let root = document.getElementById("notes")
	note.forEach(n => {
		if (n.title == found) {
			let title = document.createElement("div")
			elem.appendChild("title")
		}
		if (n.message == found) {
			let message = document.createElement("div")
			elem.appendChild("message")
		}
		elem.classList.add("item")
		root.appendChild(elem)
	})
}