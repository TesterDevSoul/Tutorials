package top.testeru.pro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import top.testeru.BaseCookieProTest;
import top.testeru.BaseTest;
import top.testeru.util.CookieUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description 根据cookie信息进行登录
 * @createTime 2022年10月29日 15:34:00
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CookieLoginProTest extends BaseCookieProTest {


    @Order(2)
    @Test
    @DisplayName("通讯录点击添加成员")
    public void addMemberWithConcat(){
        //点击通讯录，进入到通讯录页面  id="menu_contacts"
        By menuContacts = By.id("menu_contacts");
        WebElement menu_contacts = webDriver.findElement(menuContacts);//通讯录定位
        menu_contacts.click();
        //进入通讯录页面判断条件：有搜索按钮内默认内容   显示等待，页面有该元素定位
        // 表达式：   //*[@placeholder="搜索成员、部门、标签"]
        By pageContacts = By.xpath("//*[@placeholder=\"搜索成员、部门、标签\"]");
        //visibilityOfAllElementsLocatedBy        元素不仅被显示，而且具有大于 0 的高度和宽度
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageContacts));

        //点击 添加成员按钮
        By addMemberButon = By.linkText("添加成员");
        WebElement addMemberEle = webDriver.findElement(addMemberButon);
        HighlightElement(webDriver, addMemberEle);
        addMemberEle.click();

        //判断对应点击添加成员是否成功
        By username1 = By.name("username");
        wait.until(ExpectedConditions.elementToBeClickable(username1));


        //添加姓名：
        WebElement username = webDriver.findElement(username1);
        username.clear();
        String uname = "时光会";
        username.sendKeys(uname);
        //添加账号：
        WebElement acctid = webDriver.findElement(By.name("acctid"));
        acctid.clear();
        acctid.sendKeys("2022103011095473");
        //添加邮箱：
        WebElement mail = webDriver.findElement(By.xpath("//*[@name=\"biz_mail\"]"));
        mail.clear();
        mail.sendKeys("2022103011095473");

        //添加手机号：
        WebElement memberAdd_phone = webDriver.findElement(By.id("memberAdd_phone"));
        memberAdd_phone.clear();
        String phone = "13649765234";
        memberAdd_phone.sendKeys(phone);

        //点击保存
        webDriver.findElement(By.linkText("保存")).click();
        //<div id="js_tips" class="ww_tip success" style="z-index: 1000; margin-left: -46px; display: none;">保存成功</div>


        // 页面跳转是否成功  跳转成功对应页面没有企业邮箱字段
        wait.until(webDriver1 -> {
            System.out.println(webDriver.findElement(By.id("js_tips")).getText().contains("保存成功"));
            return StringUtils.contains(webDriver1.getPageSource(),"企业邮箱");

        });


        //搜索刚刚添加的用户
//        memberSearchInput
        webDriver.findElement(By.id("memberSearchInput")).sendKeys("霍格");
        //搜索结果显示名称
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ww_searchResult_title_peopleName")));

        //结果断言 用户名 用户手机号
        //member_display_cover_detail_name class
        String name = webDriver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
        //member_display_item member_display_item_Phone
        //member_display_item_right
        String mobile = webDriver.findElement(By.cssSelector(".member_display_item_Phone .member_display_item_right")).getText();




        assertAll(
                () -> assertThat(uname,equalTo(name)),
                () -> assertThat(phone,equalTo(mobile))
        );

    }
}
