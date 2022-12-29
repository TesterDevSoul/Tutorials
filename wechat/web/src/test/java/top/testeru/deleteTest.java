package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static java.lang.Thread.sleep;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description
 * @createTime 2022年11月16日 11:41:00
 */
public class deleteTest {
    //删除文章
//    String url = "https://www.testeru.top/admin/index.html#/posts/list?page=0&size=100&statuses=PUBLISHED&statuses=DRAFT&statuses=INTIMATE";



    //删除
    @Test
    public void delete() throws InterruptedException {
        //删除分类
        String url = "https://testeru.top/admin/index.html#/categories";
//        String url = "https://www.testeru.top/admin/index.html#/posts/list?page=0&size=100&statuses=PUBLISHED&statuses=DRAFT&statuses=INTIMATE";
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.get(url);
        sleep(5000);
        webDriver.manage().window().maximize();
        WebElement name = webDriver.findElement(By.xpath("//*[@placeholder=\"用户名/邮箱\"]"));
        name.clear();
        name.sendKeys("18612725214@163.com");
        WebElement pwd = webDriver.findElement(By.xpath("//*[@placeholder=\"密码\"]"));
        pwd.clear();
        pwd.sendKeys("feng!?1104");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/form/div[3]/div/div/span/button")).click();
        sleep(5000);
//        webDriver.get(url);
//        sleep(5000);
        while(webDriver.getPageSource().contains("删除")) {
//            System.out.println(webDriver.getPageSource());
            webDriver.findElements(By.xpath("//*[text()=\"删除\"]/..")).get(0).click();
            sleep(3000);
            webDriver.findElement(
                            By.xpath("//*[text()=\"确 定\"]/.."))
                    .click();
            sleep(3000);
        }

    }

    //删除
    @Test
    public void deleteCategories() throws InterruptedException {
        //删除页面
        String url = "https://www.testeru.top/admin/index.html#/sheets/list?activeKey=custom";
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.get(url);
        sleep(5000);
        webDriver.manage().window().maximize();
        WebElement name = webDriver.findElement(By.xpath("//*[@placeholder=\"用户名/邮箱\"]"));
        name.clear();
        name.sendKeys("18612725214@163.com");
        WebElement pwd = webDriver.findElement(By.xpath("//*[@placeholder=\"密码\"]"));
        pwd.clear();
        pwd.sendKeys("feng!?1104");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/form/div[3]/div/div/span/button")).click();
        sleep(5000);
//        webDriver.get(url);
//        sleep(5000);
        while(webDriver.getPageSource().contains("删除")) {
//            System.out.println(webDriver.getPageSource());
            webDriver.findElements(By.xpath("//*[text()=\"删除\"]/..")).get(0).click();
            sleep(3000);
            webDriver.findElement(
                            By.xpath("//*[text()=\"确 定\"]/.."))
                    .click();

            webDriver.findElement(
                            By.xpath("//*[text()=\"永久删除\"]/.."))
                    .click();
            webDriver.findElement(
                            By.xpath("//*[text()=\"确 定\"]/.."))
                    .click();
            sleep(3000);
        }

    }
}
