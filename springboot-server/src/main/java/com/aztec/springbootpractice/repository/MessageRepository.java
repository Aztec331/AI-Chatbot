//MessageRepository 
package com.aztec.springbootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aztec.springbootpractice.entity.Message;

import java.util.List;

//JpaRepository provides basic CRUD operations for Message entity (Message table).
public interface MessageRepository extends JpaRepository<Message, Long> {

    //using JpaRepository we can only find Message by id (primary key) or find all messages.
    //But we also want to find messages by chat (foreign key).
    //thats why we need to add this custom query method.

    // Custom query method to find messages by chat.
    //chatId is variable name and Long is its 
    
    // Spring reads chatId as chat.id
    // First it looks at the chat field inside Message entity
    // Then it goes into Chat entity and finds its id field
    // So chatId here means: Message.chat.id
    List<Message> findByChatIdOrderByIdAsc(Long chatId);

}