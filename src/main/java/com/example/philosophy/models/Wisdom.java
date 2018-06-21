package com.example.philosophy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Wisdom {

    @Id
    @GeneratedValue
    private long id;

    private String fileName;

    private byte[] file;

    @ManyToOne
    private Sage sage;

    public Wisdom() { }

    public long getId() { return id; }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public Wisdom(byte[] file){ this.file = file; }

    public byte[] getFile() { return file; }

    public void setFile (byte[] file) { this.file = file; }

    public Sage getSage() { return sage; }

    public void setSage(Sage sage) { this.sage = sage; }

}
