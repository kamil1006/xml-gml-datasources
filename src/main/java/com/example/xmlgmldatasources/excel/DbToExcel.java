package com.example.xmlgmldatasources.excel;

import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

@Setter
public class DbToExcel {
/*
https://www.codejava.net/coding/java-code-example-to-export-data-from-database-to-excel-file
https://www.codejava.net/coding/how-to-write-excel-files-in-java-using-apache-poi

 */


//-----------------------------------------------------------------------

    private void writeHeaderLine(ResultSet result, XSSFSheet sheet) throws SQLException {
        // write header line containing column names
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

        // exclude the first column which is the ID field
        for (int i = 2; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i - 2);
            headerCell.setCellValue(columnName);
        }
    }
//-----------------------------------------------------------------------
private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet)
        throws SQLException {
    ResultSetMetaData metaData = result.getMetaData();
    int columnCount = metaData.getColumnCount();

    int rowCount = 1;

    while (result.next()) {
        Row row = sheet.createRow(rowCount++);
        System.out.println(rowCount);
        for (int i = 2; i <= columnCount; i++) {
            Object valueObject = result.getObject(i);

            Cell cell = row.createCell(i - 2);
            cell.setCellValue((String) valueObject);

        }

    }
}

//-----------------------------------------------------------------------
private String getFileName(String baseName) {

    return baseName.concat(String.format("_%s.xlsx", "tekst"));
}

//-----------------------------------------------------------------------
public void export(String table) {
    String jdbcURL = "jdbc:h2:mem:testdb";
    String username = "sa";
    String password = "";

    String excelFilePath = getFileName(table.concat("_Export"));

    try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
        String sql = "SELECT * FROM ".concat(table);

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(table);

        writeHeaderLine(result, sheet);

        writeDataLines(result, workbook, sheet);

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        workbook.close();

        statement.close();

    } catch (SQLException e) {
        System.out.println("Datababse error:");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("File IO error:");
        e.printStackTrace();
    }
}
//-----------------------------------------------------------------------
public void exportTablefromQuery(String query) {
    String jdbcURL = "jdbc:h2:mem:testdb";
    String username = "sa";
    String password = "";

    String excelFilePath = "_Export";

    try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
        String sql = query;

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(query);

        writeHeaderLine(result, sheet);

        writeDataLines(result, workbook, sheet);

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        workbook.close();

        statement.close();

    } catch (SQLException e) {
        System.out.println("Datababse error:");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("File IO error:");
        e.printStackTrace();
    }
}
//-----------------------------------------------------------------------


}
