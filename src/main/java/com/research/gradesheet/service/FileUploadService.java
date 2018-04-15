package com.research.gradesheet.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUploadService {

    private String uploadedDir = "D:/CSE 487/gradesheet/uploadedFiles/";

    public void saveUploadedFile(List<MultipartFile> file)throws IOException {

        for(MultipartFile f: file) {
            if (f.isEmpty())
                continue;

            byte[] bytes = f.getBytes();
            Path path = Paths.get(uploadedDir + "grade." + FilenameUtils.getExtension(f.getOriginalFilename()));
            Files.write(path, bytes);
        }
    }
}
