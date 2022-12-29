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

public class CookieProTest extends BaseTest {

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
    public void cookie(){
        CookieUtil cookieUtil = new CookieUtil();
        String preURL = webDriver.getCurrentUrl();

        if(!Paths.get("cookies.yaml").toFile().exists()){
            cookieUtil.saomaLogin(webDriver,preURL);
        }else {
            cookieUtil.loginWithCookie(webDriver);
        }
        String afterURL = webDriver.getCurrentUrl();

        assertThat("登录失败", afterURL, is(not(equalTo(preURL))));

    }

}
