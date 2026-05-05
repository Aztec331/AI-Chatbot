//MessageRepository 
package com.aztec.springbootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aztec.springbootpractice.entity.Message;
import com.aztec.springbootpractice.entity.Chat;

import java.util.List;

//JpaRepository provides basic CRUD operations for Message entity (Message table).
public interface MessageRepository extends JpaRepository<Message, Long> {

    //using JpaRepository we can only find Message by id (primary key) or find all messages.
    //But we also want to find messages by chat (foreign key).
    //thats why we need to add this custom query method.

    // Custom query method to find messages by chat.
    List<Message> findByChat(Chat chat);

}