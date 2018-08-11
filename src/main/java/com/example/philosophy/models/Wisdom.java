package com.example.philosophy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Wisdom {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    private String source;

    @ManyToOne
    private Sage sage;

    @ManyToOne
    private Branch branch;

    public Wisdom() { }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getSource() { return source; }

    public void setSource(String source) { this.source = source; }

    public Sage getSage() { return sage; }

    public void setSage(Sage sage) { this.sage = sage; }

    public Branch getBranch() { return branch; }

    public void setBranch(Branch branch) { this.branch = branch; }

}
