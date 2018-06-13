package com.example.philosophy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Knowledge {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=100)
    private String name;

    @NotNull
    @Size(min=1, message = "Must not be empty!")
    private String body;

    public Knowledge(String name, String body){
        this.name = name;
        this.body = body;
    }

    public Knowledge(){ }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

}
