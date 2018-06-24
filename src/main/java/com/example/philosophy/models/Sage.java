package com.example.philosophy.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sage {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=200, message = "Must not be empty!")
    private String name;

    @Size(max=200, message = "Must be no longer than 200 characters!")
    private String bio;

    @OneToMany
    @JoinColumn(name = "sage_id")
    private List<Wisdom> philosophies = new ArrayList<>();

    public Sage() { }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    public List<Wisdom> getPhilosophies() { return philosophies; }

    public void setPhilosophies(List<Wisdom> philosophies) { this.philosophies = philosophies; }

}
