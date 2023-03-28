const HEIGHT = "400px";
const WIDTH = "350px";

function showChatBot(){
	let chatBot = document.getElementById("chatbot");
	let chatBotShowButton = document.getElementById("chatbot-show-button");
	
	chatBotShowButton.style.display = "none"
	chatBot.style.height = HEIGHT;
	chatBot.style.width = WIDTH;
}

function closeChatBot(){
	let chatBot = document.getElementById("chatbot");
	let chatBotShowButton = document.getElementById("chatbot-show-button");
	
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


