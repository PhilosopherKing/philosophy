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
    private List<Wisdom> uploads = new ArrayList<>();

    public Sage() { }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    public List<Wisdom> getUploads() { return uploads; }

    public void setUploads(List<Wisdom> uploads) { this.uploads = uploads; }

}
