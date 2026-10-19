package ru.university.martynov.repository;

import ru.university.martynov.model.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileRepository {
    List<File> getAll();
    File create(File file);
    File update(File file);
    boolean delete(UUID id);
    Optional<File> getById(UUID id);

}
