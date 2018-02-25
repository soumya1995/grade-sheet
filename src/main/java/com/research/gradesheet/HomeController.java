package com.research.gradesheet;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class HomeController {

    private String uploadedDir = "D:/CSE 487/gradesheet/uploadedFiles/";

    @RequestMapping("/upload")
    public void uploadFile(@RequestParam(name = "file")MultipartFile file)throws IOException{
        saveUploadedFile(file);
    }

    private void saveUploadedFile(MultipartFile file)throws IOException{

        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadedDir+"grade."+ FilenameUtils.getExtension(file.getOriginalFilename()));
        Files.write(path,bytes);
    }

}
