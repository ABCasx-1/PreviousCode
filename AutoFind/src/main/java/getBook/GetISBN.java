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
        String html = "<body>\n" +
                "		<div id=\"head\" >\n" +
                "			<div style=\"width: 1000px;margin: 0px auto;\">\n" +
                "				<div style=\"float: left;\">\n" +
                "					<ul><li><a href=\"../index.php\"><img src=\"../images/room.png\" width=\"50px\" alt=\"\"  /></a></li>\n" +
                "\n" +
                "						<li>〉〉2023-2024学年1学期 学生选课 教材查询&gt;&gt;董利伟  </li>\n" +
                "					</ul>\n" +
                "				</div>\n" +
                "				<div style=\"float: right;color: rgb(221, 221, 221);\"> \n" +
                "					今天是第 0 周 星期 3                       \n" +
                "				</div>\n" +
                "			</div>\n" +
                "		</div> \n" +
                "		<div id=\"logo\" style=\"background: #E0E0E0;vertical-align: middle;height: 30px;\">    \n" +
                "		</div>\n" +
                "		<div style=\"width: 1200px;margin: auto;min-height: 500px;padding-top: 10px;\" >\n" +
                "			<table class='pTable' >\n" +
                "				<tr><td>课程号</td><td>课程名称</td><td>教材信息</td></tr><tr ><td>A1040130</td><td>人工智能概论</td><td><br>【书名】：人工智能导论：模型与算法\n" +
                "						<br>【出版社】：高等教育出版社\n" +
                "						<br>【作者】：吴飞\n" +
                "						<br>【ISBN】：9787040534665</td><tr ><td>A1050310</td><td>西班牙语1</td><td><br>【书名】：你好！西班牙语（上册）\n" +
                "						<br>【出版社】：外语教学与研究出版社\n" +
                "						<br>【作者】：（美）安娜·C.哈维斯，（美）拉盖尔·雷布莱多，（美）弗朗西斯科·梅娜-阿伊勇 \n" +
                "						<br>【ISBN】：9787513577564</td><tr ><td>A1090032</td><td>体育（俱乐部）</td><td></td><tr ><td>A1100010</td><td>形势与政策</td><td><br>【书名】：时事报告大学生版\n" +
                "						<br>【出版社】：时事报告杂志社\n" +
                "						<br>【作者】：12\n" +
                "						<br>【ISBN】：1674-6783</td><tr ><td>A1170030</td><td>大学生心理健康教育（2）</td><td><br>【书名】：大学生心理健康辅导\n" +
                "						<br>【出版社】：高等教育出版社\n" +
                "						<br>【作者】：金河岩\n" +
                "						<br>【ISBN】：9787040403251</td><tr ><td>A2040050</td><td>计算机网络B</td><td><br>【书名】：计算机网络（第7版）\n" +
                "						<br>【出版社】：电子工业出版社\n" +
                "						<br>【作者】：谢希仁\n" +
                "						<br>【ISBN】：9787121302954</td><tr ><td>A2040090</td><td>软件工程A</td><td><br>【书名】：软件工程导论（第6版）\n" +
                "						<br>【出版社】：清华大学出版社\n" +
                "						<br>【作者】：张海藩、牟永敏\n" +
                "						<br>【ISBN】：9787302330981</td><tr ><td>A2040291</td><td>计算机图形学B</td><td><br>【书名】：计算机图形学基础教程( 第2 版)\n" +
                "						<br>【出版社】：清华大学出版社\n" +
                "						<br>【作者】：孙家广  胡事民\n" +
                "						<br>【ISBN】：9787302207115</td><tr ><td>A2040300</td><td>课程设计（计算机网络）</td><td></td><tr ><td>A2040650</td><td>云计算与大数据综合实践</td><td></td><tr ><td>A2041040</td><td>深度学习综合实践</td><td></td></tr></table>		</div>\n" +
                "	</body>";

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
