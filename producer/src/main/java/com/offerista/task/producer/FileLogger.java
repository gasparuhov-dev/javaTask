package com.offerista.task.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
@Component
public class FileLogger {
    @Value("${log.file.path}")
    private String logFilePath;

    public void log(String primeNumbers) {
        Path filePath = Paths.get(logFilePath);
        String toWrite = primeNumbers + "\n";
        try {
            if(Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
            Files.write(filePath, toWrite.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
