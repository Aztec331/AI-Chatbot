//Message Entity or Message table
package com.aztec.springbootpractice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private String role;
    
    private String content;


    // No-argument constructor required by JPA/Hibernate.
    // It creates an empty object first.
    // Then JPA fills the fields later while reading/saving data.
    // We usually don't call this manually.
    public Message() {}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Chat getChat(){
        return chat;
    }

    public void setChat(Chat chat){
        this.chat = chat;
    }


    
}
