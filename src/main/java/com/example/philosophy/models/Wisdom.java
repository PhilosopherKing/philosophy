package com.example.philosophy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wisdom {

    @Id
    @GeneratedValue
    private long id;

    private String fileName;

    private byte[] file;

    public Wisdom() { }

    public long getId() { return id; }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public Wisdom(byte[] file){ this.file = file; }

    public byte[] getFile() { return file; }

    public void setFile (byte[] file) { this.file = file; }

}
