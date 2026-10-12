package ru.university.martynov.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Archive {

    private UUID id;
    private String title;
    private LocalDateTime createdAt;
    private List<File> files;

    public Archive(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.files = new ArrayList<>();
    }

    public Archive(UUID id, String title, LocalDateTime createdAt, List<File> files) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.files = (files != null) ? files : new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}