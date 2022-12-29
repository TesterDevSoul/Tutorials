package top.testeru.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description
 * 1、cookie.yaml文件的读取
 * 2、扫码登录的方法封装
 * @createTime 2022年10月29日 19:25:00
 */

public class CookieUtil {
    //1、yaml文件读的封装
    public List<HashMap<String, Object>> getCookieYaml() {
        //1、读取cookie的yaml文件
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String, Object>> cookies = null;
        try {
            cookies = objectMapper.readValue(Paths.get("cookies.yaml").toFile(), typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cookies;
    }


    //2、扫码登录的方法封装
    public void saomaLogin(WebDriver webDriver, String preURL){
        //显示等待
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30),Duration.ofSeconds(2));
        //显示等待的条件判断
        wait.until(webDriver1 -> webDriver1.getCurrentUrl().contains("wework_admin/frame"));

        //5、扫码后cookie保存到本地yaml文件
        Set<Cookie> cookies11 = webDriver.manage().getCookies();
        ObjectMapper objectMapper11 = new ObjectMapper(new YAMLFactory());

        //Paths.get("cookies.yaml").toFile()   <===>   new File("cookies.yaml")
        try {
            objectMapper11.writeValue(
                    Paths.get("cookies.yaml").toFile(),cookies11);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //3、cookie登录封装
    public void loginWithCookie(WebDriver webDriver){
        //获取当前浏览器的URL
        String preURL = webDriver.getCurrentUrl();
        List<HashMap<String, Object>> cookies = getCookieYaml();
        //从cookie里面获取非空的时间戳列表，如果是null则不需要关注
        List<Long> expiryList = new ArrayList<>();
        cookies.forEach(
                cookie -> {
                    if(cookie.get("expiry") != null){
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
        System.out.println("nowCookieTime"+nowCookieTime);
        //（当前时间戳 - cookie获取的时间戳） < 7200000s ，可直接使用cookie文件
        long nowTime = System.currentTimeMillis();//毫秒级别
        System.out.println("nowTime"+nowTime);

        //(nowTime - nowCookieTime ) < 7200s   使用cookie文件
        System.out.println(nowTime - nowCookieTime);
        if((nowTime - nowCookieTime)/1000 < 7200) {
            System.out.println("使用cookie文件");
            //4、加载cookies.yaml文件
            List<HashMap<String, Object>> cookies1 = getCookieYaml();
            cookies1.stream()
                    .filter(
                            cookie -> cookie.get("domain").toString().contains(".weixin.qq.com")
                    )
                    .forEach(cookie -> {
                        //cookie 放入浏览器
                        webDriver.manage().addCookie(
                                new Cookie(cookie.get("name").toString(), cookie.get("value").toString())
                        );
                    });
            //刷新浏览器，刷新的时候会把新的cookie放入，服务器才会返回登录后的页面
            webDriver.navigate().refresh();
        }else {
            saomaLogin(webDriver,preURL);
        }

    }
}
