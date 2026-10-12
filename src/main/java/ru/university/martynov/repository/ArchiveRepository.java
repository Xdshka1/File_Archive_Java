package ru.university.martynov.repository;

import ru.university.martynov.model.Archive;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArchiveRepository {

    List<Archive> getAll();

    Archive create(Archive archive);

    Archive update(Archive archive);

    boolean delete(UUID id);

    Optional<Archive> getById(UUID id);
}