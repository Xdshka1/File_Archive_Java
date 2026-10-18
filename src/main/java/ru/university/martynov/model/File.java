package ru.university.martynov.model;

import java.util.UUID;

public class File {

    private UUID id;
    private String name;
    private String path;
    private long sizeBytes;

    public File(UUID id, String name, String path, long sizeBytes) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.sizeBytes = sizeBytes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getPath() {
        return path;
    }


    public long getSizeBytes() {
        return sizeBytes;
    }

}