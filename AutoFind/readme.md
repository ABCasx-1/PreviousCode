## 自动爬取图书资源

> 通过利用教务在线展示的教材信息，一键前往校图书馆官网查询对应图书存放位置，方便直接借阅教材课本。

* 利用java的POI和JSoup库，以及ChromeDriver。伪造请求头，模拟人为访问网站，通过解析网页源码提取相关信息。

`TODO:`

* 登陆jwzx有安全认证，导致爬取的网页源码为空结构。在控制台的NetWork区也没有发现相关痕迹。尝试延迟等待、更换请求头、设置Cookie策略、提供认证凭证均无果 **(src\main\java\getBook)**。
