package top.testeru.standalone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project selenium
 * @Description grid 获取title
 * @createTime 2022年12月20日 16:42:38
 */
public class ChromeTest {
    public static final Logger logger = getLogger(lookup().lookupClass());


    @Test
    public void chrome(TestInfo testInfo) throws MalformedURLException {
        String browser = testInfo.getTestMethod().map(Method::getName).get();
        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setPlatform(platform);
        caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
//        caps.setBrowserName("chrome");
        caps.setCapability(CapabilityType.BROWSER_NAME, browser);
        WebDriver webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444"),caps);
        webDriver.get("https://www.testeru.top/");
        String title = webDriver.getTitle();
        logger.info("Chrome浏览器打开 {}",title);
        webDriver.quit();
    }



}
