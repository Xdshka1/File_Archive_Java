package ru.university.martynov.exception;

public class ArchiveTitleException extends BusinessException{
    public ArchiveTitleException(){
        super("Наименование архива не может быть пустым");
    }
}
