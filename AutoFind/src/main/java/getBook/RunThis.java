package getBook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunThis {
    public static void main(String[] args) {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String s = desktopDir.getAbsolutePath();
        String place = s+"\\书的位置.xlsx";//存放在桌面
        search(GetISBN.getOrigin(place), place);
    }

    public static void search(String[] args, String place) {
        ArrayList<String> ans = new ArrayList<>();
        for (String arg : args) {
            String htmlSourceCode = Imitate.getCode("http://172.16.253.133/TSDW/GoToFlash.aspx?szBarCode=" + GetDetail.getFinalCode(arg)); // HTML 源码，替换为实际的 HTML 字符串
            String variableName = "strWZxxxxxx";
            String variableValue = extractVariableValue(htmlSourceCode, variableName);
            if (variableValue != null) {
                String temp = variableValue.substring(variableValue.indexOf("|") + 1);
                ans.add(temp);
            } else ans.add("暂无");
            Collections.reverse(ans);
            try {
                // 打开 Excel 文件
                FileInputStream fileInputStream = new FileInputStream(place);
                Workbook workbook = new XSSFWorkbook(fileInputStream);
                Sheet sheet = workbook.getSheetAt(0);
                sheet.getRow(0).createCell(1).setCellValue("位置");// 假设要操作的是第一个工作表
                int rowIndex = sheet.getLastRowNum();
                for (String str : ans) {
                    Row row = sheet.getRow(rowIndex--);
                    Cell cell = row.createCell(1);
                    cell.setCellValue(str);
                }
                // 保存修改后的 Excel 文件
                FileOutputStream outputStream = new FileOutputStream(place);
                workbook.write(outputStream);

                // 关闭资源
                outputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String extractVariableValue(String htmlSourceCode, String variableName) {
        String regex = "(?s)" + variableName + "\\s*=\\s*\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlSourceCode);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
