@PostMapping("/chats/{chatId}/messages")
public Message saveMessage(@PathVariable Long chatId, @RequestBody Message message) {

    //creats a new empty chat object
    Chat chat = new Chat();

    //this sets chatId as 1 for the empty chat object that u created above
    chat.setId(chatId);

    //take the chat object that you created above
    //and put it inside the message object's chat field 
    message.setChat(chat);

    //after the above line message object becomes 
    // message object
    // id = null
    // role = "user"
    // content = "What is Python?"
    // chat = Chat(id=1, title=null)


    return noteService.saveMessage(message);
}
