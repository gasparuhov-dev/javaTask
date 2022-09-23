package com.offerista.task.consumer;

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

    public void log(int primeNumber) {
        Path filePath = Paths.get(logFilePath);
        String toWrite = "" + primeNumber + ", ";
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
