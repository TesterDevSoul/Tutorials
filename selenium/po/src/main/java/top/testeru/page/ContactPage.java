package top.testeru.page;

import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description 通讯录页面
 * @createTime 2022年11月05日 21:14:00
 */
public class ContactPage {
    WebDriver webDriver;


    public ContactPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    MemberPage toMemberPage(){
        System.out.println("跳转到添加成员界面");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webDriver.get("https://gitee.com/");

        return new MemberPage(webDriver);
    }
}
