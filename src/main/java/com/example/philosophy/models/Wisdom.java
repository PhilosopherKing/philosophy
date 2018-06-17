package com.example.philosophy.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Wisdom {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private byte[] file;

    public Wisdom() { }

    public int getId() { return id; }

    public Wisdom(byte[] file){ this.file = file; }

    public byte[] getFile() { return file; }

    public void setFile (byte[] file) { this.file = file; }

}
