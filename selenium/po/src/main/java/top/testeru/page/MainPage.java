package top.testeru.page;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description 首页
 * @createTime 2022年11月05日 21:14:00
 */
public class MainPage {
    WebDriver webDriver;


    ContactPage toContactPage(){
        System.out.println("首页跳转到通讯录页面");
        webDriver = WebDriverManager.chromedriver().create();
        return new ContactPage(webDriver);
    }
}
