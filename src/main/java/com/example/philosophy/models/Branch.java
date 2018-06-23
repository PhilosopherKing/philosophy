package com.example.philosophy.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Branch {


    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=100, message = "Must not be empty!")
    private String name;

    @NotNull
    @Size(min=1, message = "Must not be empty!")
    private String description;

    @OneToMany
    @JoinColumn(name = "branch_id")
    private List<Knowledge> philosophies = new ArrayList<>();

    public Branch(){}

    public Branch(String name, String description){
        this.name = name;
        this.description = description;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<Knowledge> getPhilosophies() { return philosophies; }

    public void setPhilosophies(List<Knowledge> philosophies) { this.philosophies = philosophies; }

}
