package top.testeru.page;

import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description 成员页面
 * @createTime 2022年11月05日 21:15:00
 */
public class MemberPage {

    WebDriver webdriver;

    public MemberPage(WebDriver webDriver) {
        this.webdriver = webDriver;
    }

    ContactPage addMember(){
        System.out.println("添加成员");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webdriver.get("https://www.baidu.com");
        return new ContactPage(webdriver);
    }
}
