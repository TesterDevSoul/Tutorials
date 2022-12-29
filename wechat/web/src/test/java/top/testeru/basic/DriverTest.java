package top.testeru.basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description drivermanager使用
 * @createTime 2022年10月29日 10:24:00
 */
public class DriverTest {
    static WebDriver webDriver;
    @BeforeAll
    public static void bf(){
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("start-maximized");

//        --enable-automation ⊗options.addArguments("disable-infobars");
//        chromeOptions.addArguments("disable-infobars");
        //禁用自动化提示 默认情况下，ChromeDriver 将 Chrome 配置为允许弹出窗口。如
        // 果您想阻止弹出窗口（即在不受 ChromeDriver 控制时恢复正常的 Chrome 行为），请执行以下操作：
//        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //excludeSwitches排除的开关 阻止  启用浏览器由自动化控制的指示。 ↪
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));



        //判断是否有浏览器

        Optional<Path> browserPath = WebDriverManager.chromedriver().getBrowserPath();
        assumeThat(browserPath).isPresent();

        //使用WebDriverManager创建driver打开浏览器  .cache/selenium
        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        //窗口最大化
        webDriver.manage().window().maximize();
    }

    @Test
    public void test(){
        try {
            sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterAll
    public static void af(){
        webDriver.quit();
    }
}
