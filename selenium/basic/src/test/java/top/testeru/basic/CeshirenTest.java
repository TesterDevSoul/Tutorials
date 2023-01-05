package top.testeru.basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project selenium
 * @Description 测试人论坛
 * @createTime 2023年01月04日 17:06:17
 */
public class CeshirenTest {
    @Test
    public void test(){
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.get("https://ceshiren.com/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //定位搜索框
        webDriver.findElement(By.id("search-button"));
        webDriver.findElement(By.cssSelector("#search-button"));
        //定位logo标志
        webDriver.findElement(By.className("logo-big"));
        webDriver.findElement(By.cssSelector(".logo-big"));

    }

}
