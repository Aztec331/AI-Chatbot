//communicates with database, repository class
package com.aztec.springbootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//file imports
import com.aztec.springbootpractice.entity.Note;

//NoteRepository is a child of JpaRepository
//This repository manages Note table with primary key type (id) Long
public interface NoteRepository extends JpaRepository<Note, Long> {
    
}
