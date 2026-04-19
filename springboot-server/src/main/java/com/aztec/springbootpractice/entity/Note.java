//Full name of the class is com.aztec.springbootpractice.entity.Note
//Entity based class or models.py
package com.aztec.springbootpractice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity tells Spring this class maps to a database table
// Note class represents a database model
@Entity
public class Note {

    //unique primary key for each entry
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //title for each value
    private String title;

    //content for each value
    private String content;

    //Constructor for Note class
    public Note(){}

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
