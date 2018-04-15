package com.research.gradesheet;

import com.research.gradesheet.service.FileUploadService;
import com.research.gradesheet.service.SheetEditService;
import com.research.gradesheet.service.SheetViewService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file)throws IOException, InvalidFormatException{

        String viewUrl = "";
        if(file.isEmpty())
            return new ResponseEntity<>("Please select a file!", HttpStatus.OK);

        try {
            new FileUploadService().saveUploadedFile(Arrays.asList(file));
            //Create a view of the spreadsheet currently uploaded
            viewUrl= new SheetViewService().createSheetView();

            //Get the headers from the spreadsheet
            new SheetEditService().getColumnHeaders();
        }
        catch (IOException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(viewUrl, new HttpHeaders(), HttpStatus.OK);

    }

    


}
