package getBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 从图书馆网站源码页查询满足条件的图书列表，并将第一本图书详情页的链接返回
 */
public class GetList {
    public static String getIt(String isbn) {
        // 指定要获取源代码的网页URL
        String url = "http://172.16.253.123/opac/openlink.php?doctype=ALL&with_ebook=on&displaypg=20&showmode=list&sort=CATA_DATE&orderby=desc&dept=ALL&strSearchType=isbn&match_flag=forward&historyCount=1=&strText=" + isbn;
        String pageSource = Imitate.getCode(url);
        Document doc = Jsoup.parse(pageSource);
        // 找到所有的 <a href> 元素
        Elements links = doc.select("a");
        // 确保查询到对应的图书
        if (links.size() >= 2) {
            // 获取倒数第二个 <a href> 元素
            Element secondLastLink = links.get(links.size() - 3);
            // 获取链接的网址
            return secondLastLink.attr("href");
        } else return null;
    }
}
