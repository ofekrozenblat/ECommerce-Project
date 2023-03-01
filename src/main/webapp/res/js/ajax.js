
const spinner = `<div id="spinner" class="spinner-grow text-danger my-2" role="status" 
style="width: 3rem; height: 3rem;"> <span class="visually-hidden">Loading...</span> </div>`

function showLoading(div){
	div.innerHTML += spinner
}

function doneLoading(){
	document.getElementById('spinner').remove()
}

function redirect(url){
	window.location.href = url;
}

function handler(request, callback) {
	if ((request.readyState == 4) && (request.status == 200)){
		callback(request)
	}
}

function ajaxGET(address, data, callback){
	var request = new XMLHttpRequest()
	request.open("GET", (address + "?" + data), true)
	request.onreadystatechange = () => { handler(request, callback) }
	request.send(null)
}