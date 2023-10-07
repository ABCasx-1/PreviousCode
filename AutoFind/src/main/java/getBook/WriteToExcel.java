package getBook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteToExcel {
    public static void Writer(String[]strings,String place) {
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            Row headerRow = sheet.createRow(0);
            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("书名");
            int rowIndex = 1;
            for (String str : strings) {
                Row row = sheet.createRow(rowIndex++);
                Cell cell = row.createCell(0);
                cell.setCellValue(str);
            }

            FileOutputStream outputStream = new FileOutputStream(place);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
