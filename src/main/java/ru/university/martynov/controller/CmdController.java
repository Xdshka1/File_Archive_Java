package ru.university.martynov.controller;

import ru.university.martynov.model.Archive;
import ru.university.martynov.model.File;
import ru.university.martynov.service.ArchiveService;
import ru.university.martynov.service.FileService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class CmdController {

    private final ArchiveService archiveService;
    private final FileService fileService;
    private final Scanner scanner;

    public CmdController(ArchiveService archiveService, FileService fileService, Scanner scanner) {
        this.archiveService = archiveService;
        this.fileService = fileService;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("\n=== УПРАВЛЕНИЕ АРХИВАМИ ===");
            System.out.println("1. Показать все архивы");
            System.out.println("2. Найти архив по ID");
            System.out.println("3. Создать архив");
            System.out.println("4. Обновить название архива");
            System.out.println("5. Удалить архив");
            System.out.println("6. Добавить файл в архив");
            System.out.println("7. Удалить файл из архива");
            System.out.println("8. Показать файлы в архиве");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        getAllArchives();
                        break;

                    case "2":
                        getArchiveById();
                        break;

                    case "3":
                        createArchive();
                        break;

                    case "4":
                        updateArchive();
                        break;

                    case "5":
                        deleteArchive();
                        break;

                    case "6":
                        addFileToArchive();
                        break;

                    case "7":
                        removeFileFromArchive();
                        break;

                    case "8":
                        getFilesInArchive();
                        break;

                    case "0":
                        System.out.println("Выход из программы. Пока!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                }
            } catch (Exception e) {
                System.out.println("Возникла ошибка: " + e.getMessage());
            }
        }
    }

    private void getAllArchives() {
        List<Archive> archives = archiveService.getAll();
        if (archives.isEmpty()) {
            System.out.println("Нет созданных архивов");
            return;
        }

        for (Archive archive : archives) {
            System.out.println(formatArchive(archive));
        }
    }

    private void getArchiveById() {
        UUID id = readUUID("Введите id архива: ");
        if (id == null) {
            return;
        }

        Optional<Archive> optionalArchive = archiveService.getById(id);
        if (optionalArchive.isPresent()) {
            System.out.println(formatArchive(optionalArchive.get()));
        } else {
            System.out.println("Архив с таким идентификатором не найден");
        }
    }

    private void createArchive() {
        System.out.print("Введите название архива: ");
        String title = scanner.nextLine();
        Archive archive = new Archive(title);
        Archive created = archiveService.create(archive);
        System.out.println("Создан архив: " + formatArchive(created));
    }

    private void updateArchive() {
        UUID id = readUUID("Введите идентификатор архива: ");
        if (id == null) {
            return;
        }

        Optional<Archive> optionalArchive = archiveService.getById(id);


        if (optionalArchive.isPresent()) {
            Archive archive = optionalArchive.get();
            System.out.print("Введите новое название архива: ");
            String newTitle = scanner.nextLine();
            archive.setTitle(newTitle);
            archiveService.update(archive);
            System.out.println("Архив успешно обновлен");
        } else {
            System.out.println("Нет архива с таким идентификатором");
        }
    }

    private void deleteArchive() {
        UUID id = readUUID("Введите идентификатор архива: ");
        if (id == null) {
            return;
        }

        archiveService.deleteById(id);
        System.out.println("Архив удален");
    }

    private void addFileToArchive() {
        UUID archiveId = readUUID("Введите идентификатор архива: ");
        if (archiveId == null) {
            return;
        }

        System.out.print("Введите имя файла: ");
        String name = scanner.nextLine();

        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();

        long size = readLong("Введите размер файла в байтах: ");

        File file = new File(UUID.randomUUID(), name, path, size);
        fileService.create(file);
        archiveService.addFileToArchive(archiveId, file);

        System.out.println("Файл успешно добавлен в архив");
    }

    private void removeFileFromArchive() {
        UUID archiveId = readUUID("Введите идентификатор архива: ");
        if (archiveId == null) {
            return;
        }

        System.out.print("Введите имя файла для удаления: ");
        String fileName = scanner.nextLine();

        archiveService.removeFileFromArchive(archiveId, fileName);
        System.out.println("Файл успешно удален из архива");
    }

    private void getFilesInArchive() {
        UUID archiveId = readUUID("Введите идентификатор архива: ");
        if (archiveId == null) {
            return;
        }

        List<File> files = archiveService.getFilesInArchive(archiveId);
        if (files.isEmpty()) {
            System.out.println("В архиве нет файлов");
            return;
        }

        System.out.println("Файлы в архиве:");
        for (File file : files) {
            System.out.println(formatFile(file));
        }
    }

    private UUID readUUID(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return UUID.fromString(input);
            } catch (Exception e) {
                System.out.println("Неверно задан идентификатор. Введите заново");
            }
        }
    }

    private long readLong(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Long.parseLong(input);
            } catch (Exception e) {
                System.out.println("Неверно задан размер. Введите целое число");
            }
        }
    }

    private String formatArchive(Archive archive) {
        java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return String.format("ID: %s | Название: %s | Дата создания: %s | Файлов: %d",
                archive.getId(),
                archive.getTitle(),
                archive.getCreatedAt().format(formatter),
                archive.getFiles().size());
    }

    private String formatFile(File file) {
        return String.format("id = %s, name = %s, path = %s, size = %d байт",
                file.getId(), file.getName(), file.getPath(), file.getSizeBytes());
    }
}