package TestData;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static final String pathToFileTestData = "C:\\Users\\Tom\\IdeaProjects\\test\\src\\main\\resources\\TestData.xlsx";


    public static void setExcelFile() throws Exception {
        try {
            FileInputStream ExcelFile = new FileInputStream(pathToFileTestData);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet("Лист1");
        } catch (Exception e) {
            throw (e);
        }

    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            return Cell.getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    }

    public static Object[][] getTableArray(int row) throws Exception { {
            String[][] tabArray = null;
            try {
                setExcelFile();
                tabArray = new String[1][1];
                tabArray[0][0] = getCellData(row, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return (tabArray);
        }
    }

    public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            FileOutputStream fileOut = new FileOutputStream(pathToFileTestData);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw (e);
        }

    }
}

