package com.research.gradesheet.service;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SheetEditService {

    private Sheet sheet;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SheetEditService(String filePath)throws IOException, InvalidFormatException{
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        sheet = workbook.getSheetAt(0);
    }

    public ArrayList<String> getAllColumnHeaders(){

        ArrayList<String> headers = new ArrayList<>();

        Row row = sheet.getRow(0);
        Iterator cellIterator = row.cellIterator();
        while (cellIterator.hasNext()){
            Cell cell = (Cell) cellIterator.next();
            if((cell.getCellTypeEnum()).equals(CellType.STRING))
                headers.add(cell.getStringCellValue());
        }

        return headers;
    }

    public ArrayList<String> getUsefulColumnHeaders(){

        ArrayList<String> headers = new ArrayList<>();

        ArrayList<String> allHeaders = this.getAllColumnHeaders();
        for(String h:allHeaders){
            String lowerH = h.toLowerCase();
            if(lowerH.startsWith("homework") || lowerH.startsWith("quiz") || lowerH.startsWith("midterm") || lowerH.startsWith("final") || lowerH.contains("project"))
                headers.add(h);
        }
        return headers;
    }

}
