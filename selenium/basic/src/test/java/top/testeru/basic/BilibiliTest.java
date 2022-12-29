package top.testeru.basic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project selenium
 * @Description grid 打开哔哩哔哩
 * @createTime 2022年12月20日 16:42:38
 */
public class BilibiliTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    public static DesiredCapabilities caps = new DesiredCapabilities();
    public static WebDriver driver;
    @BeforeAll
    public static void bf() throws MalformedURLException {
        String browser = "chrome";
        if("chrome".equals(browser)){

            caps.setPlatform(Platform.ANY);
            caps.setBrowserName("chrome");
            ChromeOptions options = new ChromeOptions();
            options.merge(caps);
        }
        driver = new RemoteWebDriver(new URL("http://10.1.1.178:4444"),caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.bilibili.com/");


    }
    @AfterAll
    public static void af(){
        driver.quit();

    }

    /**
     * 搜索热门第一个
     */
    @Test

    public void search1() {

        WebElement searchEle= driver.findElement(By.className("nav-search-input"));
        searchEle.click();
        //获取热门搜索文本
        WebElement hotTextEle= driver.findElement(By.className("trending-text"));
        String hotText = hotTextEle.getText();
        //搜索框输入文本
        searchEle.clear();
        searchEle.sendKeys(hotText);
        //点击搜索按钮
        WebElement searchClickEle= driver.findElement(By.className("nav-search-btn"));
        searchClickEle.click();
        List<String> keyTexts = new ArrayList<>();
        List<WebElement> keywordEles = driver.findElements(By.className("keyword"));
        keywordEles.forEach(keywordEle -> keyTexts.add(keywordEle.getText()));
        logger.info("关键字列表：{}",keyTexts);
//        assertThat();
    }
    @Test
    public void tag() {
        //动态
        WebElement titleEle= driver.findElement(By.className("icon-title"));
        titleEle.click();

        WebElement tagEle= driver.findElement(By.className("bili-dyn-item__tag"));
        String tagEleText = tagEle.getText();
        logger.info("动态的tag标签：{}",tagEleText);
//        assertThat();
    }
}
