function redirect(url){
	window.location.href = url;
}

function handler(request, callback) {
	if ((request.readyState == 4) && (request.status == 200)){
		callback(request.responseText)
	}
}

function ajaxGET(address, data, callback){
	var request = new XMLHttpRequest()
	request.open("GET", (address + "?" + data), true)
	request.onreadystatechange = () => { handler(request, callback) }
	request.send(null)
}