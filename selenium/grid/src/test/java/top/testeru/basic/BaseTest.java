package top.testeru.basic;

import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project grid
 * @Description
 * @createTime 2022年12月20日 18:57:05
 */
public class BaseTest {
    private WebDriver webDriver;
    public static final Logger logger = getLogger(lookup().lookupClass());
    public static DesiredCapabilities caps = new DesiredCapabilities();

    public WebDriver initDriver(TestInfo testInfo, Platform platform) throws MalformedURLException {
        String browser = testInfo.getTestMethod().map(Method::getName).get();

//        caps.setPlatform(platform);
        caps.setCapability(CapabilityType.PLATFORM_NAME,platform);

        if("chrome".equals(browser)){

            caps.setBrowserName("chrome");
//            caps.setCapability(CapabilityType.BROWSER_NAME,"chrome");

            ChromeOptions options = new ChromeOptions();
            options.merge(caps);
        }else if ("firefox".equals(browser)){
            caps.setBrowserName("firefox");

            FirefoxOptions options = new FirefoxOptions();
            options.merge(caps);
        }else if ("edge".equals(browser)){
            caps.setBrowserName("MicrosoftEdge");
            EdgeOptions options = new EdgeOptions();
            options.merge(caps);
        }else if ("safari".equals(browser)){
            caps.setBrowserName("safari");
            SafariOptions options = new SafariOptions();
            options.merge(caps);
        }
        webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444"),caps);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return webDriver;
    }
}
