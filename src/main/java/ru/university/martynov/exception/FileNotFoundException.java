package ru.university.martynov.exception;

import java.util.UUID;

public class FileNotFoundException extends BusinessException {
    public FileNotFoundException() {
        super("Файл не найден");
    }

    public FileNotFoundException(String fileName) {
        super("Файл не найден: " + fileName);
    }

    public FileNotFoundException(UUID id) {
        super("Файл не найден с id = " + id);
    }
}