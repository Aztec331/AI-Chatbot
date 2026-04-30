//dto means data transfer object
//a simple class used to send/recieve data between systems

// ChatRequest.java
// Defines what frontend sends to backend.
package com.aztec.springbootpractice.dto;

import java.util.List;

public class ChatRequest {

    private List<ChatMessage> messages;

    public List<ChatMessage> getMessages(){
        return messages;
    }

    public void setMessages(List<ChatMessage> messages){
        this.messages= messages;
    }

// Answer (core idea)

    // Spring does this automatically:

    // JSON field -> object field

    // Mapping (IMPORTANT)

    // Frontend/Postman sends:

    // {
    //   "messages": [
    //     { "role": "user", "content": "Hello AI" }
    //   ]
    // }

    // Spring converts to:

    // request.messages = List<ChatMessage>
    
}


