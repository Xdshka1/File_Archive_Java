package ru.university.martynov.exception;

public class FileNotFoundException extends BusinessException{
    public FileNotFoundException(String fileName){
        super("Файл " + fileName + " не найден в архиве");
    }
}
