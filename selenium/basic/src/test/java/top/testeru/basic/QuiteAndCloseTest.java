package top.testeru.basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-tutorial
 * @Description 退出浏览器测试 用例
 * @createTime 2022年11月22日 16:35:00
 */
public class QuiteAndCloseTest {

    @Test
    public void quite() throws InterruptedException {
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.get("https://www.baidu.com/");
        webDriver.findElement(By.linkText("新闻")).click();
        sleep(5000);
        //输出当前的窗口
        Set<String> windowHandles = webDriver.getWindowHandles();
        System.out.println(windowHandles);
        sleep(3000);
        //切换到第一个窗口
        webDriver.switchTo().window(windowHandles.stream().findFirst().get());

        sleep(2000);
        System.out.println(webDriver.getWindowHandle());
        //Quits this driver, closing every associated window.
        webDriver.quit();
        sleep(6000);

    }
    @Test
    public void close() throws InterruptedException {
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().window().maximize();
        sleep(6000);
        webDriver.get("https://www.baidu.com/");
        String mainHandle = webDriver.getWindowHandle();
        webDriver.findElement(By.linkText("新闻")).click();
        sleep(2000);
        //切换到第一个窗口
        webDriver.switchTo().window(mainHandle);
        sleep(2000);
        webDriver.findElement(By.linkText("图片")).click();
        sleep(2000);
        webDriver.close();//关闭第一个窗口
        System.out.println("浏览器关闭第一个窗口");
        sleep(6000);
        //切换到最后一个窗口关闭
        Set<String> handles = webDriver.getWindowHandles();
        ArrayList<String> hanleList = new ArrayList<>(handles);
        String endHandle = hanleList.get(hanleList.size() - 2);
        webDriver.switchTo().window(endHandle);
        sleep(2000);
        webDriver.close();
        System.out.println("浏览器关闭最后一个标签页");
        sleep(6000);
    }
}
