package ru.university.martynov.service;

import ru.university.martynov.exception.FileNotFoundException;
import ru.university.martynov.model.File;
import ru.university.martynov.repository.FileRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File create(File newFile) {
        return fileRepository.create(newFile);
    }

    public File update(File updatedFile) {
        Optional<File> optionalFile = fileRepository.getById(updatedFile.getId());
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException();
        }

        return fileRepository.update(updatedFile);
    }

    public void deleteById(UUID id) {
        Optional<File> optionalFile = fileRepository.getById(id);
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException(id);
        }

        fileRepository.delete(id);
    }

    public Optional<File> getById(UUID id) {
        return fileRepository.getById(id);
    }

    public List<File> getAll() {
        return fileRepository.getAll();
    }
}