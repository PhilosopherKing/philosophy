package com.example.philosophy.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "knowledge_id")
    private List<Enlightenment> comments = new ArrayList<>();

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

    public List<Enlightenment> getComments() { return comments; }

    public void setComments(List<Enlightenment> comments) { this.comments = comments; }

}
