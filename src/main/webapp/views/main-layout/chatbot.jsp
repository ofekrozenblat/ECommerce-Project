<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<button id="chatbot-show-button" class="btn btn-custom" type="button" onclick="showChatBot()">
	<span class="material-icons-outlined">contact_support</span>
</button>



<div id="chatbot" class="card">
  <div class="card-header">
    ChatBot
  </div>
  <div id="chatbot-body" class="card-body overflow-auto">
  	<div id="chatbot-box" class="d-flex flex-column">
  	
	  	<div class="chat-message float-start bg-danger mb-3 text-wrap px-3 py-2">
			<span class="text-light"> Hi! I'm Chatty FAQ bot. </span> 
		</div>
		
		<div class="chat-message float-start bg-danger mb-3 text-wrap px-3 py-2 d-flex flex-column">
			<span class="text-light"> To better assist you, please choose from one of the following options: </span> 
			
						
				<div class="chat-message cursor-pointer float-start bg-light mb-3 text-wrap px-3 py-2 mt-2 mb-1"
					onclick="addChatBotServerMessage('You can view your order history under your account page')">
					<span class="text-danger" >Order History</span> 
				</div>
				
				<div class="chat-message cursor-pointer float-start bg-light mb-3 text-wrap px-3 py-2 mb-1">
					<span class="text-danger"> Return Policy</span> 
				</div>
		</div>
	</div>
  </div>
  <div class="card-footer">
	<button id="chatbot-close-button" class="btn btn-custom float-end rounded-circle" type="button" onclick="closeChatBot()">
		<span class="material-icons-outlined fs-6">close</span>
	</button>
  </div>
</div>