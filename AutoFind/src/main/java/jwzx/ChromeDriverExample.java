package jwzx;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 获得的body体为空
 */
public class ChromeDriverExample {
    public static void main(String[] args) {
        // 设置系统属性，指定ChromeDriver的路径
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");

        // 创建 ChromeOptions 实例并配置选项
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--enable-cookies");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.javascript", 1);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 9.0; Pixel 3 XL Build/PQ3A.190705.003) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"); // 设置特定的 User-Agent
        //options.addArguments("user-agent=Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
        // 创建 ChromeDriver 实例时传入选项
        WebDriver driver = new ChromeDriver(options);

        // 将 WebDriver 对象转换为 JavascriptExecutor 对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // 打开目标网页
        driver.get("http://jwzx.cqupt.edu.cn/student/jiaocai.php");
        // 执行 JavaScript 代码并获取返回结果
        String pageSource = (String) jsExecutor.executeScript("return document.documentElement.outerHTML");
        System.out.println("Page Source: " + pageSource);
        //Set<Cookie> cookies = driver.manage().getCookies();
        //
        //// 打印每个 cookie 的名称和值
        //for (Cookie cookie : cookies) {
        //    System.out.println(cookie.getName() + ": " + cookie.getValue());
        //}

        // 关闭浏览器
        driver.quit();
    }
}
