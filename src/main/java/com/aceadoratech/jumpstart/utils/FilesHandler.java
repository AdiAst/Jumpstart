package com.aceadoratech.jumpstart.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class FilesHandler {
    public static void saveFile(String dir, String fileName, MultipartFile multipartFile) throws IOException, IOException {
        Path uploadPath = Paths.get(dir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
    }

    public static Resource getFile(String fileName) {
        try {
            Path filePath = Paths.get("/storage/images/").resolve(fileName);
            File file = filePath.toFile();

            if (file.exists()) {
                Resource resource = new UrlResource(file.toURI());
                if (resource.exists()) {
                    return resource;
                } else {
                    throw new FileNotFoundException("File not found: " + fileName);
                }
            } else {
                throw new FileNotFoundException("File not found: " + fileName);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
