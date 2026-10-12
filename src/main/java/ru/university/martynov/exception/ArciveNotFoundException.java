package ru.university.martynov.exception;

import java.util.UUID;

public class ArciveNotFoundException extends BusinessException{
    public ArciveNotFoundException(UUID id){
        super("Архив с id = " + id + " не найден");
    }

    public ArciveNotFoundException(String title){
        super("Архив с наименованием = " + title + " не найден");
    }
}
