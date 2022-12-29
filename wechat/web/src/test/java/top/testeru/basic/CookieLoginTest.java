package top.testeru.basic;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import top.testeru.BaseTest;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.apache.commons.lang3.StringUtils;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description 根据cookie信息进行登录
 * @createTime 2022年10月29日 15:34:00
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CookieLoginTest extends BaseTest {

    //cookie读取判断时间
    @Test
    public void cookieTime() throws IOException {
        /**
         * 1、读取cookie的yaml文件
         * 2、从cookie里面获取非空的时间戳列表，如果是null则不需要关注
         * 3、cookie使用规则
         * （当前时间戳 - cookie获取的时间戳） < 7200s ，可直接使用cookie文件
         * 注意⚠️：
         * 第一个时间戳为cookie获取的一年以后的时间戳，需要进行计算转换。
         */
        //1、读取cookie的yaml文件
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String, Object>> cookies = objectMapper.readValue(Paths.get("cookies.yaml").toFile(), typeReference);
        //从cookie里面获取非空的时间戳列表，如果是null则不需要关注
        List<Long> expiryList = new ArrayList<>();
        cookies.forEach(
                cookie -> {
                    if(null != cookie.get("expiry")){
                        String expiryStr = cookie.get("expiry").toString();
                        Long expireL = Long.valueOf(expiryStr);
                        expiryList.add(expireL);
                    }

                }
        );
        //获取到了所有的截止时间戳
        System.out.println(expiryList);
        //第一个截止时间戳是cookie文件的365天以后
        //cookie 文件的获取时间的时间戳
        long nowCookieTime = expiryList.get(0) - 31536000000L;//毫秒级别

        //（当前时间戳 - cookie获取的时间戳） < 7200s ，可直接使用cookie文件
        long nowTime = System.currentTimeMillis();//毫秒级别
        //(nowTime - nowCookieTime ) < 7200s   使用cookie文件
        if((nowTime - nowCookieTime)/1000 < 7200){
            System.out.println("使用cookie文件");
        }else {
            System.out.println("扫码登录，cookie失效");
        }

    }

    @Test
    @Order(1)
    @DisplayName("企业微信-登录")
    public void login() throws IOException, InterruptedException {
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        //获取当前浏览器的URL
        String preURL = webDriver.getCurrentUrl();

        //加载cookies.yaml文件
        //声明要加载的是yaml文件
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        //声明文件要加载的类型
        TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        //读取yaml文件返回结果 cookie
        List<HashMap<String, Object>> cookies = objectMapper.readValue(Paths.get("cookies.yaml").toFile(), typeReference);
        //遍历过滤结果内容
        cookies.stream()
                .filter(
                        //只需要企业微信相关的cookie
                        // 特点：domain的value为.weixin.qq.com
                        cookie -> cookie.get("domain").toString().contains(".weixin.qq.com")
                )
                //开始遍历
                .forEach(cookie -> {
                    //cookie 放入浏览器
                    Cookie setCookie = new Cookie(cookie.get("name").toString(), cookie.get("value").toString());

                    webDriver.manage().addCookie(setCookie);
                });
        //刷新浏览器，刷新的时候会把新的cookie放入，服务器才会返回登录后的页面
        webDriver.navigate().refresh();
        String afterURL = webDriver.getCurrentUrl();
        sleep(5000);
        assertThat("登录失败", afterURL, is(not(equalTo(preURL))));
    }
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


        //添加姓名：霍格
        WebElement username = webDriver.findElement(username1);
        username.clear();
        String uname = "霍格1";
        username.sendKeys(uname);
        //添加账号：20220703171223
        WebElement acctid = webDriver.findElement(By.name("acctid"));
        acctid.clear();
        acctid.sendKeys("2022071014498798");
        //添加邮箱：20220703171223
        WebElement mail = webDriver.findElement(By.xpath("//*[@name=\"biz_mail\"]"));
        mail.clear();
        mail.sendKeys("2022071014498798");

        //添加手机号：
        WebElement memberAdd_phone = webDriver.findElement(By.id("memberAdd_phone"));
        memberAdd_phone.clear();
        String phone = "13299045901";
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


        try {
            sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertAll(
                () -> assertThat(uname,equalTo(name)),
                () -> assertThat(phone,equalTo(mobile))
        );

    }
}
