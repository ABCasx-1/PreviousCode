package getBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * TODO 直接调用 Imitate 中的方法 body 体为空
 * 从已有的html源码中获取所有图书的ISBN码
 */
public class GetISBN {
    public static String[] getOrigin(String place) {
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> first_column = new ArrayList<>();
        String html = "here is the code";
        Document doc = Jsoup.parse(html);

        Elements bookInfos = doc.select("table.pTable tr:gt(0)"); // 获取每一行书籍信息，跳过表头

        for (Element bookInfo : bookInfos) {
            Elements tds = bookInfo.select("td");
            if (tds.get(2).text().equals("")) {
                continue;
            }
            String bookName = tds.get(2).text().split("【")[1].trim();  // 提取书名
            bookName = bookName.split("：")[1].trim();
            first_column.add(bookName);
            String isbn = tds.get(2).text().split("【")[4].trim();  // 提取ISBN
            isbn = isbn.split("：")[1].trim();
            ans.add(isbn);
        }
        WriteToExcel.Writer(first_column.toArray(new String[0]),place);
        return ans.toArray(new String[0]);
    }
}
