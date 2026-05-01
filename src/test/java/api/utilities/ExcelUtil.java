package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private Workbook workbook;
    private Sheet sheet;

    // Constructor to load Excel file and sheet
    public ExcelUtil(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get total row count
    public int getRowCount() {
        return sheet.getLastRowNum(); // returns last row index
    }

    // Get total column count (based on first row)
    public int getColumnCount() {
        Row row = sheet.getRow(0);
        return row.getLastCellNum(); // returns number of columns
    }

    // Get cell data (String format)
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";

        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Optional: close workbook
    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getTestData() {

        int rows = getRowCount();     // excludes header row
        int cols = getColumnCount();

        Object[][] data = new Object[rows][cols];

        for (int i = 1; i <= rows; i++) {   // start from 1 (skip header)
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }

    public Object[] getTestData_usernames() {

        int rows = getRowCount();     // excludes header row

        Object[] data = new Object[rows];

        for (int i = 1; i <= rows; i++) {   // start from 1 (skip header)
                data[i - 1]= getCellData(i, 1);
        }
        return data;
    }



}