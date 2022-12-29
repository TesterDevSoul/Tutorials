package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import top.testeru.util.CookieUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description drivermanager使用
 * @createTime 2022年10月29日 10:24:00
 */
public class BaseCookieProTest {
    public static WebDriver webDriver;
    public static WebDriverWait wait;
//    @BeforeAll
//    public static void bf(){
    @Test
    public void bf(){
        //判断是否有浏览器
        Optional<Path> browserPath = WebDriverManager.chromedriver().getBrowserPath();
        assumeThat(browserPath).isPresent();

        ChromeOptions chromeOptions = new ChromeOptions();
        //excludeSwitches排除的开关 阻止  启用浏览器由自动化控制的指示。 ↪
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        //使用WebDriverManager创建driver打开浏览器  ~/.cache/selenium
        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        //显示等待声明
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30),Duration.ofSeconds(2));
        //窗口最大化
        webDriver.manage().window().maximize();

        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        //cookie登录
        CookieUtil cookieUtil = new CookieUtil();
        String preURL = webDriver.getCurrentUrl();

        if(!Paths.get("cookies.yaml").toFile().exists()){
            cookieUtil.saomaLogin(webDriver,preURL);
        }else {
            cookieUtil.loginWithCookie(webDriver);
        }
        String afterURL = webDriver.getCurrentUrl();
//        assertThat("登录失败", afterURL, is(not(equalTo(preURL))));


    }

    //元素高亮  js

    //元素选中
    public WebDriver HighlightElement(WebDriver driver, WebElement element){
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        return driver;
    }

//    @AfterAll
//    public static void af(){
//        webDriver.quit();
//    }
}
