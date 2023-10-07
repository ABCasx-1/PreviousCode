package getBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 最后一步
 */
public class GetDetail {
    public static String getFinalCode(String isbn) {
        String FinalUrl = "http://172.16.253.123/opac/" + GetList.getIt(isbn);
        String pageSource = Imitate.getCode(FinalUrl);
        Document doc = Jsoup.parse(pageSource);
        String content=null;
        // 找到第一个 <td width="15%"> 元素
        Element targetElement = doc.select("td[width=\"15%\"]").first();
        if (targetElement != null) {
             content = targetElement.text();
        }
        return content;
    }
}