//Chat Entity or Chat table
package com.aztec.springbootpractice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // No-argument constructor required by JPA/Hibernate.
    // It creates an empty object first.
    // Then JPA fills the fields later while reading/saving data.
    // We usually don't call this manually.
    public Chat() {}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }


}
