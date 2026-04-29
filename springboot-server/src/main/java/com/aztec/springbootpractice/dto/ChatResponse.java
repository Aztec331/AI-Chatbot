//dto means data transfer object
//a simple class used to send/recieve data between systems

// ChatResponse.java
// Defines what backend sends back to frontend.
package com.aztec.springbootpractice.dto;

public class ChatResponse {

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}