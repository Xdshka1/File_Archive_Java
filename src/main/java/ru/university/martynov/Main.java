package ru.university.martynov;

import ru.university.martynov.controller.CmdController;
import ru.university.martynov.repository.FileRepositoryInMemoryImpl;
import ru.university.martynov.repository.ArchiveRepository;
import ru.university.martynov.repository.ArchiveRepositoryInMemoryImpl;
import ru.university.martynov.repository.FileRepository;
import ru.university.martynov.service.ArchiveService;
import ru.university.martynov.service.FileService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArchiveRepository archiveRepository = new ArchiveRepositoryInMemoryImpl();
        FileRepository fileRepository = new FileRepositoryInMemoryImpl();

        ArchiveService archiveService = new ArchiveService(archiveRepository);
        FileService fileService = new FileService(fileRepository);

        Scanner scanner = new Scanner(System.in);
        CmdController controller = new CmdController(archiveService, fileService, scanner);

        controller.run();
    }
}