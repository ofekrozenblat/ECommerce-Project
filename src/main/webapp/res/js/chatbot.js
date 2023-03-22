const HEIGHT = "400px";
const WIDTH = "350px";

var chatBot = null;
var chatBotShowButton = null;
var chatBox = null;

window.onload = function(){
	chatBot = document.getElementById("chatbot");
	chatBox = document.getElementById("chatbot-box");
	chatBotShowButton = document.getElementById("chatbot-show-button");
}

function showChatBot(){
	chatBotShowButton.style.display = "none"
	chatBot.style.height = HEIGHT;
	chatBot.style.width = WIDTH;
}

function closeChatBot(){
	chatBotShowButton.style.display = "flex"
	chatBot.style.height = "0px";
	chatBot.style.width = "0px";
}

function addChatBotMessage(message){
	let chatBox = document.getElementById("chatbot-box");
	let chatBoxBody = document.getElementById("chatbot-body");
	
	chatBox.innerHTML += `<div class="chat-message float-start bg-danger mb-3 text-wrap px-3 py-2">
		<span class="text-light"> ${message} </span> 
	</div>`;
	
	chatBoxBody.scrollTop = chatBoxBody.scrollHeight;
}


