package com.example.philosophy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Philosopher {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=4, max=70)
    private String username;

    private String email;

    @NotNull
    @Size(min=4, message = "Must not be empty!")
    private String password;

    public Philosopher() {

    }

    public int getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

}
