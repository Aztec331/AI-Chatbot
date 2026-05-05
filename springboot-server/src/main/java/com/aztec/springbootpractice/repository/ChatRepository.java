//ChatRepository 
package com.aztec.springbootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aztec.springbootpractice.entity.Chat;

//JpaRepository provides basic CRUD operations for Chat entity (Chat table).
public interface ChatRepository extends JpaRepository<Chat, Long> {
}