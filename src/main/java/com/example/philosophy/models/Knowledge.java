package com.example.philosophy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Knowledge {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=100, message = "Must not be empty!")
    private String name;

    @NotNull
    @Size(min=1, message = "Must not be empty!")
    private String body;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Philosopher philosopher;

    public Knowledge(){ }

    public Knowledge(String name, String body){
        this.name = name;
        this.body = body;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public Branch getBranch() { return branch; }

    public void setBranch(Branch branch) { this.branch = branch; }

    public Philosopher getPhilosopher() { return philosopher; }

    public void setPhilosopher(Philosopher philosopher) { this.philosopher = philosopher; }

}
