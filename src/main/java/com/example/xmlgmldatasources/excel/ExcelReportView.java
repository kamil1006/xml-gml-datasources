package com.example.xmlgmldatasources.excel;


import com.example.xmlgmldatasources.db1.entity.Street;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment;filename=\"listaUlic.xls\"");
        List<Street> ulice = (List<Street>) model.get("ulice");
        Sheet sheet = workbook.createSheet("street Data");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("namePrefix");
        header.createCell(2).setCellValue("namePrefix2");
        header.createCell(3).setCellValue("nameOfficial");
        header.createCell(4).setCellValue("typeSymbol");
        header.createCell(5).setCellValue("typeName");
        header.createCell(6).setCellValue("status");

        int rowNum = 1;
        for(Street ulica:ulice){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(ulica.getId());
            row.createCell(1).setCellValue(ulica.getNamePrefix());
            row.createCell(2).setCellValue(ulica.getNamePrefix2());
            row.createCell(3).setCellValue(ulica.getNameOfficial());
            row.createCell(4).setCellValue(ulica.getTypeSymbol());
            row.createCell(5).setCellValue(ulica.getTypeName());
            row.createCell(6).setCellValue(ulica.getStatus());
        }
    }
}
