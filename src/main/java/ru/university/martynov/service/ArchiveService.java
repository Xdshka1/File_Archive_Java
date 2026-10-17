package ru.university.martynov.service;

import ru.university.martynov.exception.ArchiveNotFoundException;
import ru.university.martynov.exception.ArchiveTitleException;
import ru.university.martynov.exception.FileNotFoundException;
import ru.university.martynov.model.Archive;
import ru.university.martynov.model.File;
import ru.university.martynov.repository.ArchiveRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ArchiveService {

    private final ArchiveRepository archiveRepository;

    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    public Archive create(Archive newArchive) {
        if (newArchive.getTitle() == null || newArchive.getTitle().isBlank()) {
            throw new ArchiveTitleException();
        }

        return archiveRepository.create(newArchive);
    }

    public Archive update(Archive updatedArchive) {
        if (updatedArchive.getTitle() == null || updatedArchive.getTitle().isBlank()) {
            throw new ArchiveTitleException();
        }

        Optional<Archive> optionalArchive = archiveRepository.getById(updatedArchive.getId());
        if (optionalArchive.isEmpty()) {
            throw new ArchiveNotFoundException(updatedArchive.getId());
        }

        return archiveRepository.update(updatedArchive);
    }

    public void deleteById(UUID id) {
        Optional<Archive> optionalArchive = archiveRepository.getById(id);
        if (optionalArchive.isEmpty()) {
            throw new ArchiveNotFoundException(id);
        }

        archiveRepository.delete(id);
    }

    public Optional<Archive> getById(UUID id) {
        return archiveRepository.getById(id);
    }

    public List<Archive> getAll() {
        return archiveRepository.getAll();
    }

    public void addFileToArchive(UUID archiveId, File file) {
        Optional<Archive> optionalArchive = archiveRepository.getById(archiveId);
        if (optionalArchive.isEmpty()) {
            throw new ArchiveNotFoundException(archiveId);
        }

        Archive archive = optionalArchive.get();
        List<File> files = archive.getFiles();

        for (int i = 0; i < files.size(); i++) {
            File currentFile = files.get(i);
            if (currentFile.getName().equalsIgnoreCase(file.getName())) {
                files.remove(i);
                break;
            }
        }

        files.add(file);
        archiveRepository.update(archive);
    }

    public void removeFileFromArchive(UUID archiveId, String fileName) {
        Optional<Archive> optionalArchive = archiveRepository.getById(archiveId);
        if (optionalArchive.isEmpty()) {
            throw new ArchiveNotFoundException(archiveId);
        }

        Archive archive = optionalArchive.get();
        List<File> files = archive.getFiles();
        boolean removed = false;

        for (int i = 0; i < files.size(); i++) {
            File currentFile = files.get(i);
            if (currentFile.getName().equalsIgnoreCase(fileName)) {
                files.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) {
            throw new FileNotFoundException(fileName);
        }

        archiveRepository.update(archive);
    }

    public List<File> getFilesInArchive(UUID archiveId) {
        Optional<Archive> optionalArchive = archiveRepository.getById(archiveId);
        if (optionalArchive.isEmpty()) {
            throw new ArchiveNotFoundException(archiveId);
        }
        Archive archive = optionalArchive.get();

        return new ArrayList<>(archive.getFiles());
    }
}