package com.research.gradesheet.service;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SheetEditService {

    private final String filePath = "D:/CSE 487/gradesheet/uploadedFiles/grade.xlsx";
    private Sheet sheet;

    public SheetEditService()throws IOException, InvalidFormatException{
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        sheet = workbook.getSheetAt(0);
    }

    public ArrayList<String> getColumnHeaders(){

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

}
