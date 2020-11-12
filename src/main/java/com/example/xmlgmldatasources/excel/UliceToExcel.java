package com.example.xmlgmldatasources.excel;


import com.example.xmlgmldatasources.db1.entity.Ulica;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class UliceToExcel extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        //-----------------------------------------
        response.setHeader("Content-Disposition", "attachment;filename=\"listaUlic.xls\"");
        List<Ulica> ulice = (List<Ulica>) model.get("uliceGML");
        Sheet sheet = workbook.createSheet("uliceGML");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("ITERYT");
        header.createCell(2).setCellValue("NAZWA_GLOWNA_CZESC");
        header.createCell(3).setCellValue("PRZEDROSTEK1CZESC");


        int rowNum = 1;
        for(Ulica ulica:ulice){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(ulica.getId());
            row.createCell(1).setCellValue(ulica.getIdTERYT());
            row.createCell(2).setCellValue(ulica.getNazwaGlownaCzesc());
            row.createCell(3).setCellValue(ulica.getPrzedrostek1Czesc());

        }
        //-----------------------------------------
/*
        List<Adres> adresy = (List<Adres>) model.get("adresyGML");
         sheet = workbook.createSheet("adresyGML");
         header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("HREF");
        header.createCell(2).setCellValue("ITERYT");



         rowNum = 1;
        for(Adres adres:adresy){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(adres.getId());
            row.createCell(1).setCellValue(adres.getIdTeryt());
            row.createCell(2).setCellValue(adres.getIdTeryt());


        }        //-----------------------------------------
        List<Punkt> punkts = (List<Punkt>) model.get("punktyGML");
        sheet = workbook.createSheet("punktyGML");
        header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("HREF");
        header.createCell(2).setCellValue("KOD");
        header.createCell(3).setCellValue("NR");



        rowNum = 1;
        for(Punkt punkt:punkts){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(punkt.getId());
            row.createCell(1).setCellValue(punkt.getHref());
            row.createCell(2).setCellValue(punkt.getKod());
            row.createCell(3).setCellValue(punkt.getNr());


        }        //-----------------------------------------
        */

    }
}
