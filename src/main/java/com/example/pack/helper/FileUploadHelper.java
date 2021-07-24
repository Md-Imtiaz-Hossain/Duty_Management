package com.example.pack.helper;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public final String UPLOAD_DIR = new ClassPathResource("/static/image/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {    }

    public boolean uploadFile(MultipartFile multipartFile) throws IOException {
        boolean isLoaded= false;
        if (!multipartFile.isEmpty()) {
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isLoaded = true;
        }
        return isLoaded;
    }
}
