package top.testeru.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.WebDriverWait;
import top.testeru.BaseTest;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description 获取cookie信息
 * @createTime 2022年10月29日 11:35:00
 */
public class GetCookieTest extends BaseTest {

    /**
     * 1、Chrome浏览器判断 WebDriverManager getBrowserPath
     * 2、打开Chrome浏览器 WebDriverManager.chromedriver().create()
     * 3、访问企业微信登录页面  get(URL)
     * 4、扫码过程
     * 方式一：强制等待扫码   sleep(10000)
     * 方式二：显示等待扫码，时间更加灵活 显示等待10s，但是不一定是10s，可能在10s内结束  等待结束条件
     * 5、扫码后cookie保存到本地yaml文件  fasterjson writeValue(new File(文件路径)，文件内容)
     */
    @Test
    public void saveCookie(){
        //访问企业微信登录页面  get(URL)
        //https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        //扫码过程  方式一：强制等待扫码   sleep(10000)
//        try {
//            sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        //方式二：显示等待扫码，时间更加灵活 显示等待30s，但是不一定是30s，可能在30s内结束  等待结束条件  每隔2s判断一下结束条件
        //获取扫码前的URL
        String preURL = webDriver.getCurrentUrl();

        //显示等待的条件就是前后扫码的URL改变
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30),Duration.ofSeconds(2));
        //显示等待的条件判断
        //https://work.weixin.qq.com/wework_admin/frame
//        wait.until(webDriver1 -> !webDriver1.getCurrentUrl().equals(preURL));
        wait.until(webDriver1 -> !StringUtils.equals(webDriver1.getCurrentUrl(), preURL));

        //5、扫码后cookie保存到本地yaml文件
        //获取cookie
        Set<Cookie> cookies = webDriver.manage().getCookies();

        //yaml保存
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            //Paths.get("cookies.yaml").toFile()   <===>   new File("cookies.yaml")
            objectMapper.writeValue(
                    Paths.get("cookies.yaml").toFile(),cookies);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
