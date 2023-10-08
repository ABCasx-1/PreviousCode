package getBook;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 模拟访问url 获取对应源码
 */
public class Imitate {
    public static String getCode(String url) {
        String username = "null";
        String password = "null";
        // 创建认证凭证提供者
        CredentialsProvider creedsProvider = new BasicCredentialsProvider();
        creedsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        // 创建HttpClient对象并设置认证凭证提供者
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(creedsProvider)
                .build();

        // 创建RequestConfig对象并设置Cookie策略
        RequestConfig defaultConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();

        // 创建HttpGet请求，并设置URL和请求配置
        HttpPost httpGet = new HttpPost(url);
        httpGet.setConfig(defaultConfig);
        String pageSource = null;
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 将响应实体转换为字符串
                pageSource = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageSource;
    }
}
