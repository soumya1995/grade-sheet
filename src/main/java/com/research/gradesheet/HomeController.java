package com.research.gradesheet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.research.gradesheet.model.Header;
import com.research.gradesheet.model.Response;
import com.research.gradesheet.service.FileUploadService;
import com.research.gradesheet.service.SheetEditService;
import com.research.gradesheet.service.SheetViewService;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    private final String filePath = "D:/CSE 487/gradesheet/uploadedFiles/grade.xlsx";
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file)throws IOException, InvalidFormatException{

        String viewUrl = "";
        List<String> headers;
        if(file.isEmpty())
            return new ResponseEntity<>("Please select a file!", HttpStatus.OK);

        try {
            new FileUploadService().saveUploadedFile(Arrays.asList(file));
            //Create a view of the spreadsheet currently uploaded
            viewUrl= new SheetViewService().createSheetView();

            //Get the headers from the spreadsheet
            headers = new SheetEditService(filePath).getUsefulColumnHeaders();
        }
        catch (IOException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Convert viewUrl and headers to json
        Map<String,Object> map = new HashedMap<>();

        map.put("url",viewUrl.trim());
        map.put("headers",headers);

        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
        logger.info(json);

        return new ResponseEntity<>(json, new HttpHeaders(), HttpStatus.OK);

    }

    


}
