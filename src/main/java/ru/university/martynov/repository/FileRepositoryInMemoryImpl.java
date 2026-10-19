package ru.university.martynov.repository;

import ru.university.martynov.model.File;

import java.util.*;

public class FileRepositoryInMemoryImpl implements FileRepository{
    private final Map<UUID, File> data;
    public FileRepositoryInMemoryImpl(){
        this.data = new HashMap<>();
    }
    public FileRepositoryInMemoryImpl(Map<UUID, File> data){
        this.data = data;
    }
    @Override
    public File create(File file){
        data.put(file.getId(), file);
        return file;
    }
    @Override
    public File update(File file){
        data.put(file.getId(), file);
        return file;
    }
    @Override
    public boolean delete(UUID id){
        return data.remove(id) != null;
    }
    @Override
    public Optional<File> getById(UUID id) {
        return Optional.ofNullable(data.get(id));
    }
    @Override
    public List<File> getAll() {
        return new ArrayList<>(data.values());
    }
}
