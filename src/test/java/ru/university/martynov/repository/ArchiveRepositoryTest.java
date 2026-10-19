package ru.university.martynov.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.university.martynov.model.Archive;

import java.util.HashMap;
import java.util.UUID;

public class ArchiveRepositoryTest {

    @Test
    public void deleteArchiveTest() {
        Archive archive = new Archive("Документы");
        HashMap<UUID, Archive> map = new HashMap<>();
        map.put(archive.getId(), archive);
        ArchiveRepositoryInMemoryImpl repo = new ArchiveRepositoryInMemoryImpl(map);

        boolean isDeleted = repo.delete(archive.getId());

        Assertions.assertTrue(isDeleted);
        Assertions.assertNull(map.get(archive.getId()));
    }

    @Test
    public void getByIdArchiveTest() {
        Archive archive = new Archive("Дмитрий Иваненко молодец");
        HashMap<UUID, Archive> map = new HashMap<>();
        map.put(archive.getId(), archive);
        ArchiveRepositoryInMemoryImpl repo = new ArchiveRepositoryInMemoryImpl(map);

        var result = repo.getById(archive.getId());

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Дмитрий Иваненко молодец", result.get().getTitle());
    }
}