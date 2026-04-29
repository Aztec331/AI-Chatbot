//dto means data transfer object
//a simple class used to send/recieve data between systems

// ChatRequest.java
// Defines what frontend sends to backend.
package com.aztec.springbootpractice.dto;

import javax.swing.Spring;

public class ChatRequest {

    private String message;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message= message;
    }

    // 🔹 Answer (core idea)

    // 👉 Spring does this automatically:

    // JSON field → object field

    // 🔹 Mapping (IMPORTANT)

    // Postman sends:

    // { "message": "Hello AI" }

    // Spring converts to:

    // request.message = "Hello AI"
    
}


