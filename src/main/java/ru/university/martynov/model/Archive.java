package ru.university.martynov.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Archive {

    private UUID id;
    private String title;
    private LocalDateTime createdAt;
    private List<String> files;

    public Archive(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.files = new ArrayList<>();
    }

    public Archive(UUID id, String title, LocalDateTime createdAt, List<String> files) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.files = (files != null) ? files : new ArrayList<>();
    }

    public void addFile(String fileName) {
        if (!files.contains(fileName)) {
            files.add(fileName);
        }
    }

    public boolean removeFile(String fileName) {
        return files.remove(fileName);
    }

    public List<String> getFiles() {
        return Collections.unmodifiableList(files);
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
}