package ru.university.martynov.exception;

import java.util.UUID;

public class ArchiveNotFoundException extends BusinessException{
    public ArchiveNotFoundException(UUID id){
        super("Архив с id = " + id + " не найден");
    }

    public ArchiveNotFoundException(String title){
        super("Архив с наименованием = " + title + " не найден");
    }
}
