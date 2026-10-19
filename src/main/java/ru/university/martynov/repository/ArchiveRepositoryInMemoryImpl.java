package ru.university.martynov.repository;

import ru.university.martynov.model.Archive;

import java.util.*;

public class ArchiveRepositoryInMemoryImpl implements ArchiveRepository {

    private final Map<UUID, Archive> data;

    public ArchiveRepositoryInMemoryImpl() {
        this.data = new HashMap<>();
    }

    public ArchiveRepositoryInMemoryImpl(Map<UUID, Archive> data) {
        this.data = data;
    }

    @Override
    public Archive create(Archive newArchive) {
        data.put(newArchive.getId(), newArchive);
        return newArchive;
    }

    @Override
    public Archive update(Archive updatedArchive) {
        data.put(updatedArchive.getId(), updatedArchive);
        return updatedArchive;
    }

    @Override
    public boolean delete(UUID id) {
        return data.remove(id) != null;
    }

    @Override
    public Optional<Archive> getById(UUID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Archive> getAll() {
        return data.values().stream().toList();
    }
}