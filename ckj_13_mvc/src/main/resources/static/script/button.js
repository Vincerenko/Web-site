function btnOnlick (event){
		let el = event.target
		el.classList.toggle("active")
		el.classList.toggle("diss")
if (el.classList.contains("active")) {
		el.innerText = "Active"
	} else {
		el.innerText = "Disabled"
	}		
	}

function drawButton(){
	let button = document.createElement("div")
	button.classList.add("active")
	button.innerText="Click on me"
	button.addEventListener("click", btnOnlick)
	document.querySelector("body").appendChild(button)
	
}
drawButton()