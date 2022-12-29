package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import top.testeru.util.CookieUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Optional;

import static java.lang.Thread.sleep;
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
public class BaseTest {
    public static WebDriver webDriver;
    public static WebDriverWait wait;
    @BeforeAll
    public static void bf(){

        //判断是否有浏览器
        Optional<Path> browserPath = WebDriverManager.chromedriver().getBrowserPath();
        assumeThat(browserPath).isPresent();

        //使用WebDriverManager创建driver打开浏览器  .cache/selenium
        webDriver = WebDriverManager.chromedriver().create();
        //显示等待声明
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30),Duration.ofSeconds(2));

    }
    //截图
    public void screen(String message) {
        //路径1/路径2  路径1/\路径2
        //生成时间戳
        long nowTime = System.currentTimeMillis();
        //进行截图操作
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        //在硬盘中创建一个文件，将截图复制过去
        //当前项目下的jpg文件夹内 时间戳.jpg
        Path jpgPath = Paths.get("jpg", nowTime + ".jpg");
        File file = jpgPath.toFile();
        try {
            //apache common io
            FileUtils.copyFile(screenshot, file);
            //添加到报告中
            Allure.addAttachment(message, "image/jpg", new FileInputStream(jpgPath.toFile()), ".jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //元素高亮  js

    //元素选中
    public WebDriver HighlightElement(WebDriver driver, WebElement element){
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        return driver;
    }
    //去掉选中
    public WebDriver UnhighlightElement(WebDriver driver, WebElement element){
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", element);
        }
        return driver;
    }

//    @AfterAll
//    public static void af(){
//        webDriver.quit();
//    }
}
