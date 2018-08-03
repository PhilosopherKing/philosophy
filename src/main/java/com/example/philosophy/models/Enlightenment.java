package com.example.philosophy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Enlightenment {

    // Found within the knowledge pages.

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Must not be empty!")
    private String body;

    @ManyToOne
    private Philosopher philosopher;

    @ManyToOne
    private Knowledge knowledge;

    public Enlightenment(){ }

    public int getId() { return id; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public Philosopher getPhilosopher() { return philosopher; }

    public void setPhilosopher(Philosopher philosopher) { this.philosopher = philosopher; }

    public Knowledge getKnowledge() { return knowledge; }

    public void setKnowledge(Knowledge knowledge) { this.knowledge = knowledge; }

}
